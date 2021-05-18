package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_meeting_1.*

class CreateMeeting1Activity : AppCompatActivity() { //방을 만들기 위해 필요한 페이지 1

    lateinit var room_name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meeting_1)

        room_name = findViewById<EditText>(R.id.room_name).text.toString()

        next_button1.setOnClickListener {
            Toast.makeText(this, room_name, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CreateMeeting2Activity::class.java)
            intent.putExtra("room_name", room_name)
            startActivity(intent)
        }
      }
}