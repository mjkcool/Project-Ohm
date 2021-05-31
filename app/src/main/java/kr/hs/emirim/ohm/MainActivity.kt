package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ref = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if(user != null){
            ref.child(auth.currentUser!!.uid).get().addOnSuccessListener {
                if(it.value != null) {
                    Toast.makeText(this, "최초 로그인이 아닌 로그인", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, "최초 로그인", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ProfileInitActivity::class.java))
                    finish()
                }
            }
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}