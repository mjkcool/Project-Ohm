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
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
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

        RecyclerView_Cal = findViewById(R.id.recycleView_cal)
        CalendarAdapter = CalendarAdapter(this, CalData)

        //생성
        create_intent.setOnClickListener (View.OnClickListener {
            create_window.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate_bottom)
            create_window.startAnimation(animation)
        })

        //취소
        cancel_btn.setOnClickListener(View.OnClickListener {
            create_window.visibility = View.INVISIBLE
        })

        //뒤로가기
        back_btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        })

        //추가
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


//        database.child("users").child(user?.uid!!).child("calendar").child(calendar)
//        Log.i("firebase", "Got value ${it.value}")
////            conference_title.text = it.value.toString()
////            window_calendar.visibility = View.VISIBLE
//            CalData.add(it.value.toString())
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//        }

//        val DB = FirebaseDatabase.getInstance().getReference("users")
//
//        if (user != null) {
//        }
//        DB.child(user.uid).child("calendar").child(calendar).child(code).addValueEventListener( object :
//            ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                Log.w(TAG, "loadPost:onCancelled")
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot!!.exists()) {
//                    CalData.clear()
//                    RecyclerView_Cal.setAdapter(CalendarAdapter)
//                }
//            }
//        })

//        myRef.addChildEventListener(new ChildEventListener() { //파이어베이스에 있는 것들이 실행할 내용
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Log.d("CHATCHAT", snapshot.getValue().toString()); //오류가 나서 값이 제대로 실행 되는지 보기 위한 코드
//                ChatingData chat = snapshot.getValue(ChatingData.class); //데이터값에 데이터 클래스를 넣어주는 것
//                chatlist.add(chat);
//                recyclerView.setAdapter(chatAapter);
//                //recyclerView.invalidate();
//                //((Chating_Adapter) chatAapter).addChat(chat); //리사이클뷰 어뎁터에 데이터를 넣어 알려주는 것
//            }


        //읽어오기 -- 에러
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child("calendar") != null) {
//                    var post: ArrayList<Calendars>
//                    Log.d("캘린더", dataSnapshot.getValue().toString())
                    for(dataSnapshot1 in dataSnapshot.child("calendar").children){
                        Log.d("캘린더", dataSnapshot1.getValue().toString())
                        for(dataSnapShot2 in dataSnapshot1 .children){
                            Log.d("캘린더 안", dataSnapShot2.getValue().toString())
                            val post = dataSnapShot2.getValue<Calendars>()!!
                            CalData.add(post)
                        }
                    }
                    Log.d("캘린더", "현재 cal 데이터 개수는 :" + CalData.size)
                    RecyclerView_Cal.adapter = CalendarAdapter
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        FirebaseDatabase.getInstance().getReference("users").child(user?.uid!!).addValueEventListener(postListener)

    }

    //데이터베이스 쓰기
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

    //날짜 다음 데이터베이스 추가
    private fun makeCode(){
        code = (Math.random() * 10000000).toInt().toString()
        val ref = FirebaseDatabase.getInstance().getReference("users").child(user?.uid!!).child("calendar").child(calendar)


        ref.child(code).get().addOnSuccessListener {
            if(it.value != null) {
                makeCode()
            }
        }
    }

}