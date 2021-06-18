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
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }else{
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