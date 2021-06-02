package kr.hs.emirim.ohm

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView

class ProfilePageActivity : AppCompatActivity() {
    private lateinit var logoutBtn : View
    private lateinit var quitBtn: ImageButton
    private lateinit var nicknameView: TextView
    lateinit var introduceView: TextView
    lateinit var profileImgView: CircleImageView
    lateinit var toModifyPageBtn: Button
    lateinit var deleteuser : View
    val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        logoutBtn = findViewById(R.id.btn_logout)
        quitBtn = findViewById(R.id.quit_btn)
        nicknameView = findViewById(R.id.profile_nickname)
        introduceView = findViewById(R.id.profile_introduce)
        profileImgView = findViewById(R.id.User_profile)
        toModifyPageBtn = findViewById(R.id.to_modify_btn)
        deleteuser = findViewById(R.id.btn_userout)

        //**파이어베이스-최수림 :: 현재 로그인 된 Auth에서 정보 불러오기 & TextView 지정 바랍니다.
        
        nicknameView.text = user!!.displayName
        introduceView.text = "[한줄소개 from Firebase]"
        profileImgView.setImageURI(user.photoUrl)


        toModifyPageBtn.setOnClickListener {
            startActivity(Intent(this, ModifyProfileActivity::class.java))
        }

        logoutBtn.setOnClickListener {
            signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        quitBtn.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        deleteuser.setOnClickListener {
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("ProfilePage", "User account deleted.")
                    }
                }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()
        // [END auth_sign_out]
    }
}