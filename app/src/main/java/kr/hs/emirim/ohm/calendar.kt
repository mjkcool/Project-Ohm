package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_calendar.*

class calendar : AppCompatActivity() {

    lateinit var calendarView: CalendarView
    lateinit var date: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView = findViewById(R.id.calendarView)

        date = findViewById(R.id.date)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth -> date
            date.visibility = View.VISIBLE

            date.text = String.format("%d년 %d월 %d일", year, month + 1, dayOfMonth)
        }
    }
}