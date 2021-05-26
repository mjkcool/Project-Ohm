package kr.hs.emirim.ohm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_createmeeting3.*

class createroom_time : AppCompatActivity() {
    private lateinit var database: DatabaseReference

        lateinit var code : String
        lateinit var room_name : String
        lateinit var room_topic : String
        lateinit var room_time : String

        val ref = FirebaseDatabase.getInstance().getReference("rooms")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_createmeeting3)


            init()

            next_button3.setOnClickListener {
                createroom()
            }
        }

        fun init(){
            room_name = findViewById<EditText>(R.id.room_name).text.toString()
            room_topic = findViewById<EditText>(R.id.meeting_topic).text.toString()
            room_time = findViewById<EditText>(R.id.hour).text.toString()+findViewById<EditText>(R.id.min).text.toString()+findViewById<EditText>(R.id.min).text.toString()+findViewById<EditText>(R.id.second).text.toString()
        }

        fun createroom(){
            val room = Room(room_name, room_topic, room_time)
            makeCode()
//            database.child("rooms").child(code).setValue(room)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "방 생성", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, WaitingRoomActivity::class.java)
//                    startActivity(intent)
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this, "방 생성 실패", Toast.LENGTH_SHORT).show()
//                }
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