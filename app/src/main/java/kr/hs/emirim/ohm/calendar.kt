package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class calendar : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var create_intent: ConstraintLayout
    private lateinit var create_window: CardView
    private lateinit var calendarView: CalendarView
    private lateinit var date: TextView

    private lateinit var cancel_btn: Button
    private lateinit var add_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        date = findViewById(R.id.date)  //날짜
        calendarView = findViewById(R.id.calendarView)  //캘린더
        create_intent = findViewById(R.id.create_intent)    //일정 생성
        create_window = findViewById(R.id.create_window)    //일정 생성 창

        cancel_btn = findViewById(R.id.cancel_btn)  //취소 버튼
        add_btn = findViewById(R.id.add_btn)

        create_intent.setOnClickListener (View.OnClickListener {
            create_window.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate_bottom)
            create_window.startAnimation(animation)
        })

        cancel_btn.setOnClickListener(View.OnClickListener {
            create_window.visibility = View.INVISIBLE
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