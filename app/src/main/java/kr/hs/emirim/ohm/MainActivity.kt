package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        join_button.setOnClickListener {
            val intent = Intent(this, join::class.java)
            startActivity(intent)
        }
    }
}