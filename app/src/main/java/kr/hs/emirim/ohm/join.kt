package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_join.*

//파이어베이스 회원가입 및 로그인
private var mAuth: FirebaseAuth = Firebase.auth

class join : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        mAuth =  Firebase.auth

        // 현재 로그인 상태인지 파악
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            reload();
        }

        join_button.setOnClickListener {
            if(show_email.text.toString().length == 0){
                Toast.makeText(this, "email을 입력해주세요.",Toast.LENGTH_SHORT).show()
            }else if(password.text.toString().length == 0){
                Toast.makeText(this, "비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show()
            }else if(password.text.toString() != password_check.text.toString()){
                Toast.makeText(this, "비밀번호 확인이 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
            }else if(password_check.text.toString().length == 0){
                Toast.makeText(this, "비밀번호 확인을 입력해주세요.",Toast.LENGTH_SHORT).show()
            }else{
                mAuth!!.createUserWithEmailAndPassword(show_email.text.toString(), password.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                val user = mAuth!!.currentUser
                                updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }
            }

        }

    }


    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this, login::class.java))
        finish()
    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}

//이메일  회원가입