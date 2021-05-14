package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.*

class Mainpage :  AppCompatActivity() {

    var viewPager: ViewPager? = null //카드메뉴 보이는 페이저
    var adapter: CardViewAdapter? = null//정보값을 넘겨주는 어뎁터
    var models: ArrayList<CardViewData> = ArrayList<CardViewData>() //기본 정보값
    var i = true //코드값으로 구분 => 다음 페이지로 넘어갈 때
    val fragmentAdapter = FragmentAdapter(supportFragmentManager) //

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val goto_btn = findViewById<ImageView>(R.id.goto_btn) //코드를 입력시 들어갈 수 있는 이미지버튼
            val myprofile_btn = findViewById<ImageView>(R.id.my_page) //내 프로필로 넘어갈 수 있는 이미지버튼
            val make_btn = findViewById<ImageView>(R.id.make_background) //회의생성으로 넘어갈 수 있는 이미지버튼
            val reservation_btn = findViewById<ImageView>(R.id.reservation_background) //회의예약으로 넘어갈 수 있는 이미지버튼

            //goto_btn.visibility = View.INVISIBLE//View.GONE//View.VISIBLE

            adapter = CardViewAdapter(models, this) //어뎁터를 선언 => 여기에 기본값을 알 수 있도록 선언
            viewPager = findViewById(R.id.viewPager) //페이저를 선언

            models.add(CardViewData("목적지로 가는 길", "2021.04.22", "5일전")) //카드메뉴 정보 값, 위에 올라갈 수록 최신순
            models.add(CardViewData("반민초 vs 민초", "2021.04.26", "하루전"))
            models.add(CardViewData("엄마 vs 아빠", "2021.04.01", "26일전"))
            //models.add(CardViewData("목적지로 가는 길", "2021.04.22", "5일전")) //카드메뉴 정보 값, 위에 올라갈 수록 최신순

            viewPager!!.setAdapter(fragmentAdapter)
            viewPager!!.setClipToPadding(false) //이상한 패딩 값을 넣지 않는 것

            val dpValue = 10
            val d = resources.displayMetrics.density //카드메뉴 값을 지정해주는 것
            val margin = (dpValue * d).toInt()
            viewPager!!.setPadding(margin, 0, margin, 0) //viewpager의 안에 있는 패딩값을 조절
            viewPager!!.setPageMargin(margin / 1) //viewpager의 마진을 조절

            // FragmentAdapter에 Fragment 추가, 카드메뉴 개수만큼 추가
            for (i in models.indices) {
                val cardViewFragment = CardViewFragment() 
                val bundle = Bundle()
                //bundle.putInt("imgRes", models[i].image) //이미지를 불러올 때 사용하는 것
                bundle.putString("titleRes", models[i].title)
                bundle.putString("descRes", models[i].desc)
                bundle.putString("dayRes", models[i].day)
                cardViewFragment.arguments = bundle
                fragmentAdapter.addItem(cardViewFragment)
            }
            fragmentAdapter.notifyDataSetChanged() //


            goto_btn.setOnClickListener(View.OnClickListener {
                if (i == true) {
                    goto_btn.setImageResource(R.drawable.button1_1)
                    i = false
                    val intent = Intent(applicationContext, CreateMeeting1Activity::class.java)
                    startActivity(intent) //액티비티 띄우기

                } else {
                    //customToastView("잘못된 코드를 입력하셨습니다.");
                }
            });

            myprofile_btn.setOnClickListener {
                val intent = Intent(application, MyProfileActivity::class.java)
                startActivity(intent)
            }

            make_btn.setOnClickListener {
                val intent = Intent(application, MakeConferenceActivity::class.java)
                startActivity(intent)
            }

            reservation_btn.setOnClickListener {
                val intent = Intent(application, Reservation_conferenceActivity::class.java)
                startActivity(intent)
            }
        }
}

 class FragmentAdapter (fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    // ViewPager에 들어갈 Fragment들을 담을 리스트
    private val fragments = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    // List에 Fragment를 담을 함수
    fun addItem(fragment: Fragment) {
        fragments.add(fragment)
    }
}