package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val TAG : String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login_button = findViewById<Button>(R.id.login_button)
        val googlelogin_button = findViewById<Button>(R.id.googlelogin_button)
        val join_button = findViewById<Button>(R.id.join_button)

        login_button.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        join_button.setOnClickListener {
            val intent = Intent(this, join::class.java)
            startActivity(intent)
        }

        googlelogin_button.setOnClickListener{
            //이건 나중에 구현할게용...
        }

    }
}