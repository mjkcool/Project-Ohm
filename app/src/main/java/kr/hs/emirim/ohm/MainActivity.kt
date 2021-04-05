package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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