package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_meeting_1.*
import kotlinx.android.synthetic.main.activity_create_meeting_3.*

class CreateMeeting3Activity : AppCompatActivity() { //방을 만들기 위해 필요한 페이지 3

    private lateinit var database: DatabaseReference

    lateinit var code : String
    lateinit var room_name : String
    lateinit var room_topic : String

    fun initialize() {
        database = Firebase.database.reference
        if (intent.hasExtra("room_name")&&intent.hasExtra("room_topic")) {
            room_name = intent.getStringExtra("room_name").toString()
            room_topic = intent.getStringExtra("room_topic").toString()
        }
    }

    val ref = FirebaseDatabase.getInstance().getReference("rooms")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_meeting_3)
        initialize()
        next_button3.setOnClickListener({
            val room = Room(room_name, room_topic)
            makeCode()
            database.child("rooms").child(code).setValue(room)
                .addOnSuccessListener {
                    Toast.makeText(this, "방 생성", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, WaitingRoomActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "방 생성 실패", Toast.LENGTH_SHORT).show()
                }
        })
    }

    fun makeCode(){
        code = (Math.random() * 10000000).toInt().toString()
        ref.child(code).get().addOnSuccessListener {
            if(it.value != null) {
                makeCode()
            }
        }
    }
}