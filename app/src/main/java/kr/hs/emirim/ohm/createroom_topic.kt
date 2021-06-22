package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_createmeeting2.*

class createroom_topic : AppCompatActivity() {

        lateinit var meeting_topic: EditText
        var text_number2: TextView? = null
//        lateinit var code : String
        lateinit var room_name : String
//        val ref = FirebaseDatabase.getInstance().getReference("rooms")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_createmeeting2)

            meeting_topic = findViewById<EditText>(R.id.meeting_topic)
            text_number2 = findViewById<TextView>(R.id.text_number2)

            if(intent.hasExtra("rname")) {
                room_name = intent.getStringExtra("rname").toString()
            }

            next_button2.setOnClickListener {
                val intent = Intent(this, createroom_time::class.java)
                val create = arrayOf(room_name, meeting_topic.text.toString())
                intent.putExtra("create", create);
                startActivity(intent);
                finish()
            }

            back_button2.setOnClickListener {
                val intent = Intent(this, createroom_name::class.java)
                startActivity(intent)
                finish()
            }

            meeting_topic.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    text_number2?.setText(s.length.toString() + "/ 30")
                }
            })
        }

//    fun makeCode(){
//        code = (Math.random() * 10000000).toInt().toString()
//        ref.child(code).get().addOnSuccessListener {
//            if(it.value != null) {
//                makeCode()
//            }
//        }
//    }
//
//    fun createroom(){
//        val user = Firebase.auth.currentUser
//        val room = Room(room_name, meeting_topic.text.toString(), user?.uid)
//        makeCode()
//        ref.child(code).setValue(room)
//            .addOnSuccessListener {
//                Toast.makeText(this, "방 생성", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, ChatingActivity::class.java)
//                intent.putExtra("code", code)
//                startActivity(intent)
//                finish()
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "방 생성 실패", Toast.LENGTH_SHORT).show()
//            }
//    }

}
