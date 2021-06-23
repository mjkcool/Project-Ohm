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
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.ArrayList

class Calendar : AppCompatActivity() {

    lateinit var code : String

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var calendar: String

    private lateinit var create_intent: ConstraintLayout
    private lateinit var create_window: CardView
    private lateinit var calendarView: CalendarView
    private lateinit var date: TextView

    var CalData = ArrayList<Calendars>()
    lateinit var RecyclerView_Cal: RecyclerView
    lateinit var CalendarAdapter: CalendarAdapter

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
        create_intent = findViewById(R.id.create_intent)    //일정 생성
        create_window = findViewById(R.id.create_window)    //일정 생성 창

        back_btn = findViewById(R.id.back_button)
        cancel_btn = findViewById(R.id.cancel_btn)  //취소 버튼
        add_btn = findViewById(R.id.add_btn)

//        conference_title = findViewById(R.id.tv_title)
//        conference_time = findViewById(R.id.tv_time)
//        theme_color = findViewById(R.id.theme_color)

        RecyclerView_Cal = findViewById(R.id.recycleView_cal)

        CalendarAdapter = CalendarAdapter(this, CalData)
        RecyclerView_Cal.adapter = CalendarAdapter


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
            calendar = String.format("%d-%d-%d", year, month+1, dayOfMonth)
        }

//        CalData.add(Calendars("목적지로 가는 길", "2021.04.22", "이틀전", "메모"))
//        CalData.add(Calendars("반민초 vs 민초", "2021.04.26", "하루전", "메모"))
//        CalData.add(Calendars("엄마 vs 아빠", "2021.04.01", "26일전", "메모"))


//        database.child(user!!.uid).child("calendars").child(user!!.uid).child("title").get().addOnSuccessListener {
//            Log.i("firebase", "Got value ${it.value}")
//            conference_title.text = it.value.toString()
//            window_calendar.visibility = View.VISIBLE
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//        }
//
//        database.child("calendars").child(user!!.uid).child("time").get().addOnSuccessListener {
//            Log.i("firebase", "Got value ${it.value}")
//            conference_time.text = it.value.toString()
//            view_calendar.visibility = View.VISIBLE
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//        }
    }

    fun createCal(title: String, subject: String, time: String, memo: String) {
        val cal = Calendars(title, subject, time, memo)
        val ref = FirebaseDatabase.getInstance().getReference("users").child(user?.uid!!).child("calendar").child(calendar)

        makeCode()
        ref.child(code).setValue(cal)
            .addOnSuccessListener {
                Toast.makeText(this, "일정이 저장되었습니다.", Toast.LENGTH_SHORT).show()
                create_window.visibility = View.INVISIBLE
            }
            .addOnFailureListener {
                Toast.makeText(this, "일정 저장이 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun makeCode(){
        code = (Math.random() * 10000000).toInt().toString()
        var ref = FirebaseDatabase.getInstance().getReference("users").child(user?.uid!!).child("calendar").child(calendar)

        ref.child(code).get().addOnSuccessListener {
            if(it.value != null) {
                makeCode()
            }
        }
    }

}