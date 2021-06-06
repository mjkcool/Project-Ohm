package kr.hs.emirim.ohm


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView


class ModifyProfileActivity : AppCompatActivity() {
    private lateinit var saveBtn: Button
    private lateinit var cancelBtn: Button
    private lateinit var nicknameInput: EditText
    private lateinit var introduceInput: EditText
    private lateinit var database: DatabaseReference
    var user = Firebase.auth.currentUser
    var imageUri = user!!.photoUrl

    lateinit var profileImageView: CircleImageView
    lateinit var resetImageButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_profile)

        saveBtn = findViewById(R.id.modify_profile_save_btn)
        cancelBtn = findViewById(R.id.modify_profile_cancel_btn)
        nicknameInput = findViewById(R.id.modify_profile_nickname_input)
        introduceInput = findViewById(R.id.modify_profile_introduce_input)
        
        profileImageView = findViewById(R.id.init_profileimg_view) //프로필 이미지 뷰
        resetImageButton = findViewById(R.id.init_profileimg_reset_btn) //프로필 이미지 재설정 뷰


        //**파이어베이스-최수림 :: 현재 로그인 된 Auth에서 정보 불러오기 & TextView 지정 바랍니다.
        lateinit var introduce : String
        nicknameInput.setText(user?.displayName)
        database = Firebase.database.reference
        profileImageView.setImageURI(user!!.photoUrl)

        database.child("users").child(user!!.uid).child("introduce").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            introduce = it.value.toString()
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        introduceInput.setText(" ")

        resetImageButton.setOnClickListener{
            CropImage.activity().setAspectRatio(1, 1).start(this)
        }

        saveBtn.setOnClickListener{
            val usertxt = User(introduceInput.text.toString())  //introduce
            val auth = Firebase.auth
            //**파이어베이스에 아래 정보로 기본 데이터 덮어쓰기 하기(저장) //nickname
            database.child("users").child(auth.currentUser!!.uid).setValue(usertxt)
                .addOnSuccessListener {
                    Log.d("modify user profile", "introducetxt updated")
                }
                .addOnFailureListener {

                }

            profileImageView //이 객체에서 Uri나 이미지 소스 추출 필요

            val profileUpdates = userProfileChangeRequest {
                displayName = nicknameInput.text.toString() //nickname
                photoUri = Uri.parse(imageUri.toString()) //추출해서 프로필 사진 담기.

            }

            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("modify user profile", "User profile updated.")
                    }
                }
            finish() //현재 액티비티 종료
        }
        cancelBtn.setOnClickListener{
            finish() //현재 액티비티 종료
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            var result = CropImage.getActivityResult(data) //result.uri
            profileImageView.setImageURI(result.uri)
            imageUri = result.uri
        }
    }
}