package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_createmeeting2.*

class createroom_topic : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_createmeeting2)

            next_button2.setOnClickListener({
                val intent = Intent(this, createroom_time::class.java)
                startActivity(intent)
            })
        }
}