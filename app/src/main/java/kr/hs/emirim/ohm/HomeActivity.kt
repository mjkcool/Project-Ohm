package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {
    lateinit var roomsRecyclerView: RecyclerView //회의 목록 뷰페이저
    var roomData = ArrayList<RoomVo>() //기본 정보값
    // var i = true //코드값으로 구분 => 다음 페이지로 넘어갈 때 **사용되지 않는 변수

    lateinit var roomAdapter: RoomViewAdapter

    private lateinit var admissionBtn: Button //회의 입장 버튼
    private lateinit var userProfileBtn: CircleImageView //상단 우측 유저 프로필 버튼
    private lateinit var createMeetingBtn: CardView //회의 생성 카드 버튼
    private lateinit var bookMeetingBtn: CardView //회의 예약 카드 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        admissionBtn = findViewById(R.id.admission_btn)
        userProfileBtn = findViewById(R.id.User_profile_btn)
        createMeetingBtn = findViewById(R.id.home_create_btn_layout)
        bookMeetingBtn = findViewById(R.id.home_book_btn_layout)

        //admissionBtn.visibility = View.INVISIBLE//View.GONE//View.VISIBLE

        roomsRecyclerView = findViewById(R.id.rooms_recycleView_home)

        //회의 목록 테스트 값
        roomData.add(RoomVo("목적지로 가는 길", "2021.04.22", "5일전"))
        roomData.add(RoomVo("반민초 vs 민초", "2021.04.26", "하루전"))
        roomData.add(RoomVo("엄마 vs 아빠", "2021.04.01", "26일전"))


        roomAdapter = RoomViewAdapter(this, roomData)
        roomsRecyclerView.adapter = roomAdapter


//        val dpValue = 10
//        val d = resources.displayMetrics.density //카드메뉴 값을 지정해주는 것
//        val margin = (dpValue * d).toInt()
//        roomsViewPager.setPadding(margin, 0, margin, 0) //viewpager의 안에 있는 패딩값을 조절
//        roomsViewPager.pageMargin = margin / 1 //viewpager의 마진을 조절


        // FragmentAdapter에 Fragment 추가
//        for (i in roomData.indices) {
//            var cardViewFragment = CardViewFragment()
//            val bundle = Bundle()
//            bundle.putString("titleRes", roomData[i].title)
//            bundle.putString("descRes", roomData[i].desc)
//            bundle.putString("dayRes", roomData[i].day)
//            cardViewFragment.arguments = bundle
//            roomViewAdapter.addItem(cardViewFragment)
//        }
//
//        roomViewAdapter.notifyDataSetChanged()






        /*
        goto_btn.setOnClickListener(View.OnClickListener {

            if (i == true) {
                goto_btn.setBackgroundColor(Color.parseColor("#1E349D"))
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
        */
    }
}