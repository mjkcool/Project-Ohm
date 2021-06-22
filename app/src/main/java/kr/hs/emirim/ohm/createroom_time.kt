package kr.hs.emirim.ohm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_createmeeting3.*


class createroom_time : AppCompatActivity() {

        lateinit var code : String
        lateinit var room_name : String
        lateinit var room_topic : String
        lateinit var roomG: Array<String>
        lateinit var room_time : Array<Int>

        lateinit var hour : EditText
        lateinit var min : EditText
        lateinit var sec : EditText

        val ref = FirebaseDatabase.getInstance().getReference("rooms")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_createmeeting3)

            hour = findViewById(R.id.hour)
            min = findViewById(R.id.min)
            sec = findViewById(R.id.second)

            init()


            next_button3.setOnClickListener {
                createroom()
            }
        }

        fun init(){
            if(intent.hasExtra("create")) {
                roomG = intent.getStringArrayExtra("create")!!
            }
            room_name = roomG[0]
            room_topic = roomG[1]
        }

        private fun createroom(){
            val user = Firebase.auth.currentUser

            val room = Room(room_name, room_topic, user?.uid)
            makeCode()
            ref.child(code.toString()).setValue(room)
                .addOnSuccessListener {
                    Toast.makeText(this, "방 생성", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ChatingActivity::class.java)
                    val bundle = Bundle()
                    bundle.putInt("hour", hour.text.toString().toInt());
                    bundle.putInt("min", min.text.toString().toInt());
                    bundle.putInt("sec", sec.text.toString().toInt());
                    bundle.putString("code", code);
                    intent.putExtra("bundle", bundle)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "방 생성 실패", Toast.LENGTH_SHORT).show()
                }
        }

        private fun makeCode(){
            code = (Math.random() * 10000000).toInt().toString()
            ref.child(code).get().addOnSuccessListener {
                if(it.value != null) {
                    makeCode()
                }
            }
        }
}