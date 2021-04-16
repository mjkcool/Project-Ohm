package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main2.*

class User_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = Firebase.auth.currentUser
        if (user != null) {
            startActivity(Intent(this, Main::class.java))
            finish()
        } else {
            setContentView(R.layout.activity_main2)
        }

        login_button.setOnClickListener {
            startActivity(Intent(this, login::class.java))
            finish()
        }

        join_button.setOnClickListener {
            startActivity(Intent(this, join::class.java))
            finish()
        }
    }
}