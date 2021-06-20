package kr.hs.emirim.ohm

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_calendar.*

class Calendar : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var conference_title: TextView
    private lateinit var conference_time: TextView
    private lateinit var theme_color: ImageView

    private lateinit var create_intent: ConstraintLayout
    private lateinit var view_calendar: ConstraintLayout
    private lateinit var create_window: CardView
    private lateinit var calendarView: CalendarView
    private lateinit var date: TextView

    private lateinit var back_btn: Button
    private lateinit var cancel_btn: Button
    private lateinit var add_btn: Button
    private lateinit var cal: DatabaseReference

    val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        cal = Firebase.database.getReference("calendars")

        date = findViewById(R.id.date)  //날짜
        calendarView = findViewById(R.id.calendarView)  //캘린더
        view_calendar = findViewById(R.id.view_calendar)
        create_intent = findViewById(R.id.create_intent)    //일정 생성
        create_window = findViewById(R.id.create_window)    //일정 생성 창

        back_btn = findViewById(R.id.back_button)
        cancel_btn = findViewById(R.id.cancel_btn)  //취소 버튼
        add_btn = findViewById(R.id.add_btn)

        conference_title = findViewById(R.id.tv_title)
        conference_time = findViewById(R.id.tv_time)
        theme_color = findViewById(R.id.theme_color)

        create_intent.setOnClickListener (View.OnClickListener {
            create_window.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate_bottom)
            create_window.startAnimation(animation)
        })

        cancel_btn.setOnClickListener(View.OnClickListener {
            create_window.visibility = View.INVISIBLE
        })

        back_btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        })

        add_btn.setOnClickListener(View.OnClickListener {
            val title = findViewById<EditText>(R.id.conference_name).text
            val subject = findViewById<EditText>(R.id.conference_subject).text
            val time = findViewById<EditText>(R.id.conference_time).text
            val memo = findViewById<EditText>(R.id.conference_memo).text

            createCal(title.toString(), subject.toString(), time.toString(), memo.toString())
        })

        //캘린더 클릭 시 날짜 확인
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth -> date
             date.text = String.format("%d월 %d일", month + 1, dayOfMonth)
        }

//        cal.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val newTitle = snapshot.child("title").toString()
//                val newTime = snapshot.child("time").toString()
//
//                conference_title.setText(newTitle)
//                conference_time.setText(newTime)
//                view_calendar.visibility = View.INVISIBLE
//                Toast.makeText(applicationContext, "일정 보여주기", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(TAG, "에러 발생")
//                Toast.makeText(applicationContext, "일정 안보여주기", Toast.LENGTH_SHORT).show()
//            }
//        })

        database.child("calendars").child(user!!.uid).child("title").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            conference_title.text = it.value.toString()
            view_calendar.visibility = View.VISIBLE
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        database.child("calendars").child(user!!.uid).child("time").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            conference_time.text = it.value.toString()
            view_calendar.visibility = View.VISIBLE
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

    fun createCal(title: String, subject: String, time: String, memo: String) {
        val cal = Calendars(title, subject, time, memo)
        database.child("calendars").child(auth.currentUser!!.uid).setValue(cal)
            .addOnSuccessListener {
                Toast.makeText(this, "일정이 저장되었습니다.", Toast.LENGTH_SHORT).show()
                create_window.visibility = View.INVISIBLE
            }
            .addOnFailureListener {
                Toast.makeText(this, "일정 저장이 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
    }

}