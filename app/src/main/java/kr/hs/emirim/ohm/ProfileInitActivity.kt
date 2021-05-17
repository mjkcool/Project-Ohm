package kr.hs.emirim.ohm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView
import kr.hs.emirim.ohm.fragments.FragInitImage
import kr.hs.emirim.ohm.fragments.FragInitImage.Companion.profileImageView
import kr.hs.emirim.ohm.fragments.FragInitIntroduce
import kr.hs.emirim.ohm.fragments.FragInitNickname
import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ProfileInitActivity : AppCompatActivity() {
    companion object{
        lateinit var ProfileInitContext: Context
    }

    lateinit var toBackBtn: ImageButton
    lateinit var toNextBtn: Button
    private var curFagNum = 0

    private lateinit var auth: FirebaseAuth
    private lateinit var imageUri: Uri
    private lateinit var myUri: String
    lateinit var nickname : String

    private lateinit var database: DatabaseReference

    fun initializeDbRef() {
        database = Firebase.database.reference
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.profile_init_activity)
        ProfileInitContext = this
        toBackBtn = findViewById(R.id.back_btn_nickname_set)
        toNextBtn = findViewById(R.id.next_btn_nickname_set)

        setFrag(curFagNum) //첫 프래그먼트

        initializeDbRef()

        toBackBtn.setOnClickListener{
            when(curFagNum){
                0 ->{

                }
                1 ->{
                    setFrag(--curFagNum)
                }
                2 ->{
                    setFrag(--curFagNum)
                }
            }
        }

        toNextBtn.setOnClickListener{
            when(curFagNum){
                0 ->{
                    nickname = findViewById<EditText>(R.id.input_nickname_set).text.toString()
                    if (nickname.length in 2..10){ //입력 필수 확인

                        // ***파이어베이스에 닉네임 데이터 등록(필수)
                        setFrag(++curFagNum)
                    }
                    else{
                        Toast.makeText(this, "2자 이상 10자 이하로 입력해주세요", Toast.LENGTH_SHORT).show()
                    }

                }
                1 ->{
                    // 파이어베이스에 프로필사진 데이터 등록(선택)
                    val profileImg = findViewById<CircleImageView>(R.id.init_profileimg_view) //등록할 이미지 데이터를 가진 객체(데이터 아님, 데이터 직접 추출해주길..감사^^)
                    setFrag(++curFagNum)
                }
                2 ->{
                    // 파이어베이스에 한줄소개 데이터 등록(선택)
                    val introduceTxt = findViewById<EditText>(R.id.input_introduce_set).text //등록할 텍스트 데이터
                    writeNewUser(nickname, introduceTxt.toString())

                }
            }

        }


    } //end of onCreate

    private fun setFrag(num: Int){
        val ft = supportFragmentManager.beginTransaction()
        when(num){
            0 -> {
                ft.replace(R.id.profileFragmentContainer, FragInitNickname()).commit()
                toBackBtn.visibility = View.INVISIBLE
                toNextBtn.text = "다음"
            }
            1 -> {
                ft.replace(R.id.profileFragmentContainer, FragInitImage()).commit()
                toBackBtn.visibility = View.VISIBLE
                toNextBtn.text = "건너뛰기"
            }
            2 -> {
                ft.replace(R.id.profileFragmentContainer, FragInitIntroduce()).commit()
                toBackBtn.visibility = View.VISIBLE
                toNextBtn.text = "건너뛰기"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null){

            var result = CropImage.getActivityResult(data) //result.uri
            profileImageView.setImageURI(result.uri)
        }
    }


    fun writeNewUser(nickname: String, introduceTxt: String) {
        val user = User(nickname, introduceTxt)
        database.child("users").child(auth.currentUser.uid.toString()).setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this, "사용자 디비 적재 성공", Toast.LENGTH_SHORT).show()
                updateUI(auth.currentUser)
            }
            .addOnFailureListener {
                Toast.makeText(this, "사용자 디비 적재 실패", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(user: FirebaseUser) {
        var intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK //실행 액티비티 외 모두 제거
        startActivity(intent)
    }


}