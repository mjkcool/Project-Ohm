package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import java.util.*

class MainPage_Activity : AppCompatActivity() {
    var viewPager : ViewPager? = null //카드메뉴 보이는 페이저
    var adapter : CardView_Adapter? = null//정보값을 넘겨주는 어뎁터
    var models: ArrayList<CardView_Data> = ArrayList<CardView_Data>() //기본 정보값
    var i = true //코드값으로 구분 => 다음 페이지로 넘어갈 때

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goto_btn = findViewById<ImageView>(R.id.goto_btn) //코드를 입력시 들어갈 수 있는 이미지버튼
        val myprofile_btn = findViewById<ImageView>(R.id.my_page) //내 프로필로 넘어갈 수 있는 이미지버튼
        val make_btn = findViewById<ImageView>(R.id.make_background) //회의생성으로 넘어갈 수 있는 이미지버튼
        val reservation_btn = findViewById<ImageView>(R.id.reservation_background) //회의예약으로 넘어갈 수 있는 이미지버튼

        //goto_btn.visibility = View.INVISIBLE//View.GONE//View.VISIBLE

        adapter = CardView_Adapter(models, this) //어뎁터를 선언 => 여기에 기본값을 알 수 있도록 선언
        viewPager = findViewById(R.id.viewPager) //페이저를 선언

        models.add(CardView_Data(R.drawable.inmain_people, "목적지로 가는 길", "2021.04.22", "5일전")) //카드메뉴 정보 값
        models.add(CardView_Data(R.drawable.inmain_people, "반민초 vs 민초", "2021.04.26", "하루 전"))
        models.add(CardView_Data(R.drawable.inmain_people, "엄마 vs 아빠", "2021.04.01", "26일전"))

        viewPager!!.setAdapter(adapter) //페이저 안에 어뎁터를 선언
        //viewPager!!.setClipToPadding(false)
        //viewPager!!.setPadding(0, 0, 100, 0)
        // viewPager!!.setPageMargin(30)

        goto_btn.setOnClickListener(View.OnClickListener {
            if (i == true) {
                goto_btn.setImageResource(R.drawable.button1_1)
                i = false
                val intent = Intent(applicationContext, Create_meeting_1_Activity::class.java)
                startActivity(intent) //액티비티 띄우기
            } else {
                goto_btn.setImageResource(R.drawable.button_1)
                i = true
            }
        });

        myprofile_btn.setOnClickListener {
            val intent = Intent(application, MyProfile_Activity::class.java)
            startActivity(intent)
        }

        make_btn.setOnClickListener {
            val intent = Intent(application, Make_conference_Activity::class.java)
            startActivity(intent)
        }

        reservation_btn.setOnClickListener {
            val intent = Intent(application, Reservation_conference_Activity::class.java)
            startActivity(intent)
        }

    }
}