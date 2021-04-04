package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

//Google Login result
private val RC_SIGN_IN =9001

//firebase 사용자...? Auth
private var mAuth: FirebaseAuth? = null


class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance();

        login_button.setOnClickListener {
            if (email.text.toString().length == 0) {
                Toast.makeText(this, "이메일을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else if (password.text.toString().length == 0) {
                Toast.makeText(this, "비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show()
            }else{
                mAuth!!.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                        .addOnCompleteListener(this){
                            if(it.isSuccessful){
                                //로그인 성공
                                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                                val user = mAuth?.currentUser
                            }else{
                                //로그인 실패
                                Toast.makeText(this, "로그인 실패 ㅜㅜ ", Toast.LENGTH_SHORT).show()
                            }
                        }
            }
        }

    }

}