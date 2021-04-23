package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_join.*

//파이어베이스 회원가입 및 로그인
private lateinit var mAuth: FirebaseAuth

public var TERMS_AGREE_1 = 0;
public var TERMS_AGREE_2 = 0;
public var TERMS_AGREE_3 = 0;

//AppCompatCheckBox check1;
//AppCompatCheckBox check2;
//AppCompatCheckBox check3;

class join : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        nickname.setHorizontallyScrolling(true);
        show_email.setHorizontallyScrolling(true);
        password.setHorizontallyScrolling(true);
        password_check.setHorizontallyScrolling(true);

//        event_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if (isChecked) {
//
//                    TERMS_AGREE_1 = 1;
//                } else {
//
//                    TERMS_AGREE_1 = 0;
//                }
//            }
//        });
//
//        // 2항동의
//        push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    TERMS_AGREE_2 = 1;
//                } else {
//                    TERMS_AGREE_2 = 0;
//                }
//            }
//        }
//        );
//
//        // 전체동의
//        consent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    check1.setChecked(true);
//                    check2.setChecked(true);
//                    TERMS_AGREE_3 = 1;
//                } else {
//                    check1.setChecked(false);
//                    check2.setChecked(false);
//                    TERMS_AGREE_3 = 0;
//                }
//            }
//        }
//        );

        mAuth =  FirebaseAuth.getInstance()

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
                join(show_email.text.toString(), password.text.toString())
            }

        }

    }

    private fun join(email: String, password: String){
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //회원가입 성공
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = mAuth!!.currentUser
                    updateUI(user)
                } else {
                    //회원가입 실패
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "회원가입 실패",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    //화면 전환
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