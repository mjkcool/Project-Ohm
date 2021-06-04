package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_calendar.*
import javax.security.auth.Subject

data class cal (var title: String? = null, val subject: String? = null, var time: String? = null, var memo: String? = null){

}

class calendar : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var storage: FirebaseStorage
    private lateinit var database: DatabaseReference

    private lateinit var create_intent: ConstraintLayout
    private lateinit var create_window: CardView
    private lateinit var calendarView: CalendarView
    private lateinit var date: TextView

    private lateinit var cancel_btn: Button
    private lateinit var add_btn: Button

    private lateinit var title: EditText
    private lateinit var subject: EditText
    private lateinit var time: EditText
    private lateinit var memo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        auth = FirebaseAuth.getInstance()
        storage = Firebase.storage
        database = Firebase.database.reference

        date = findViewById(R.id.date)  //날짜
        calendarView = findViewById(R.id.calendarView)  //캘린더
        create_intent = findViewById(R.id.create_intent)    //일정 생성
        create_window = findViewById(R.id.create_window)    //일정 생성 창

        cancel_btn = findViewById(R.id.cancel_btn)  //취소 버튼
        add_btn = findViewById(R.id.add_btn)

        title = findViewById(R.id.conference_name)
        subject = findViewById(R.id.conference_subject)
        time = findViewById(R.id.conference_time)
        memo = findViewById(R.id.conference_memo)

        create_intent.setOnClickListener (View.OnClickListener {
            create_window.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate_bottom)
            create_window.startAnimation(animation)
        })

        cancel_btn.setOnClickListener(View.OnClickListener {
            create_window.visibility = View.INVISIBLE
        })

        //캘린더 클릭 시 날짜 확인
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth -> date
             date.text = String.format("%d월 %d일", month + 1, dayOfMonth)
        }

        fun createCal(title: String, subject: String, time: String, memo: String){
            val cal = cal(title, subject, time, memo)

            database.child("calendar").setValue(cal)

        }

    }
}