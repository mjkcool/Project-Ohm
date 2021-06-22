package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("start", "oncreate")
        val ref = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()
        Log.d("start", "get auth")
        val user = auth.currentUser
        Log.d("start", "get user")
        if(user != null){
            ref.child(auth.currentUser!!.uid).get().addOnSuccessListener {
                if(it.value != null) {
                    Log.d("start", "there is a user")
                    startActivity(Intent(this, HomeActivity::class.java))
                    Log.d("start", "move home")
                    finish()
                }else{
                    Log.d("start", "no user")
                    startActivity(Intent(this, ProfileInitActivity::class.java))
                    Log.d("start", "move profile init")
                    finish()
                }
            }
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}