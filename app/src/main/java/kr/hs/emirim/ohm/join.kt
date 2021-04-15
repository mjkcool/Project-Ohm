package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_join.*

//파이어베이스 회원가입 및 로그인


class join : AppCompatActivity() {

    private lateinit var mAuth:FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

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

                }
            }
        }

    }
    //참고할 영상: https://www.youtube.com/watch?v=qq-JO0GrwZE

}






//mAuth!!.createUserWithEmailAndPassword(show_email.text.toString(), password.text.toString())
//.addOnCompleteListener(this){
//    if(it.isSuccessful){
//        var user:FirebaseUser = mAuth!!.currentUser
//        var userId = user!!.uid
//        databaseReference = FirebaseDatabase.getInstance()
//            .getReference("Users").child(userId)
//        var hashMap:HashMap<String, String> = HashMap()
//        hashMap.put("userId", userId)
//        hashMap.put("userPassword", password.text.toString())
//
//        databaseReference.setValue(hashMap).addOnCompleteListener(this){
//            if(it.isSuccessful){
//                //open home activity
//                //var intent = Intent(this@LoginActivity)
//                Toast.makeText(this, "회원가입 성공",Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        finish()
//    }else{
//        Toast.makeText(this, "회원가입에 실패했습니다.",Toast.LENGTH_SHORT).show()
//    }
//}
