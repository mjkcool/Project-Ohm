package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_meeting_1.*


class CreateMeeting1Activity : AppCompatActivity() { //방을 만들기 위해 필요한 페이지 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meeting_1)

        next_button.setOnClickListener({
            val intent = Intent(this, CreateMeeting1Activity::class.java)
            startActivity(intent)
            })
        }
    }