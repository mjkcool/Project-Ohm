package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_meeting_1.*

class CreateMeeting2Activity : AppCompatActivity() { //방을 만들기 위해 필요한 페이지 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meeting_2)

        next_button.setOnClickListener({
            val intent = Intent(this, CreateMeeting2Activity::class.java)
            startActivity(intent)
        })
    }
}