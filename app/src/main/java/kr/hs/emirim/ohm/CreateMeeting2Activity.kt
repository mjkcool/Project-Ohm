//package kr.hs.emirim.ohm
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import kotlinx.android.synthetic.main.activity_create_meeting_1.*
//import kotlinx.android.synthetic.main.activity_create_meeting_2.*
//
//class CreateMeeting2Activity : AppCompatActivity() { //방을 만들기 위해 필요한 페이지 2
//
//    lateinit var room_name : String
//    lateinit var room_topic : String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_create_meeting_2)
//
//        room_topic = findViewById<EditText>(R.id.meeting_topic).text.toString()
//
//        if (intent.hasExtra("room_name")) {
//            room_name = intent.getStringExtra("room_name").toString()
//            Toast.makeText(this, room_name, Toast.LENGTH_SHORT).show()
//        }
//
//        next_button2.setOnClickListener({
//            val intent = Intent(this, CreateMeeting3Activity::class.java)
//            intent.putExtra("room_topic", room_topic)
//            intent.putExtra("room_name", room_name)
//            startActivity(intent)
//        })
//    }
//}