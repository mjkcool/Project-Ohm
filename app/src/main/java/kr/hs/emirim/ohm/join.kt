package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_join.*

//파이어베이스 회원가입 및 로그인
private var mAuth: FirebaseAuth?=null

class join : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        mAuth =  FirebaseAuth.getInstance();

        join_button.setOnClickListener {
            when {
                show_email.text.toString().isEmpty() -> {
                    Toast.makeText(this, "email을 입력해주세요.",Toast.LENGTH_SHORT).show()
                }
                password.text.toString().isEmpty() -> {
                    Toast.makeText(this, "비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show()
                }
                password.text.toString() != password_check.text.toString() -> {
                    Toast.makeText(this, "비밀번호 확인이 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
                }
                password_check.text.toString().isEmpty() -> {
                    Toast.makeText(this, "비밀번호 확인을 입력해주세요.",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    mAuth!!.createUserWithEmailAndPassword(show_email.text.toString(), password.text.toString())
                        .addOnCompleteListener(this){
                            if(it.isSuccessful){
                                val user = mAuth!!.currentUser
                                finish()
                            }else{
                                Toast.makeText(this, "회원가입에 실패했습니다.",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }

        }

        }
    }

    //이메일  회원가입
