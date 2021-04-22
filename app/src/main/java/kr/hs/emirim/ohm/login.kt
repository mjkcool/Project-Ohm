package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.show_email

//Google Login result
private val RC_SIGN_IN =9001

//firebase 사용자...? Auth
private lateinit var mAuth: FirebaseAuth


class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        show_email.setHorizontallyScrolling(true);
        password.setHorizontallyScrolling(true);

        mAuth =  FirebaseAuth.getInstance()

        find_button.setOnClickListener {
            val intent = Intent(this, password_find::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener {
            if(show_email.text.toString().isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else if (password.text.toString().isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show()
            }else{
                login(show_email.text.toString(), password.text.toString())
            }
        }

    }

    private fun login(email: String, password:String){
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    //로그인 성공
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    val user = mAuth?.currentUser
                    updateUI(user)
                }else{
                    //로그인 실패
                    Toast.makeText(this, "로그인 실패 ㅜㅜ ", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this, Main::class.java))
        finish()
    }

}