package kr.hs.emirim.ohm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class HomeActivity : AppCompatActivity() {
    companion object{
        lateinit var context: Context
    }
    lateinit var roomsRecyclerView: RecyclerView
    var roomData = ArrayList<RoomVo>()
    lateinit var roomAdapter: RoomViewAdapter

    private lateinit var admissionBtn: Button //회의 입장 버튼
    lateinit var userProfileBtn: CircleImageView //상단 우측 유저 프로필 버튼
    private lateinit var createMeetingBtn: CardView //회의 생성 카드 버튼
    private lateinit var bookMeetingBtn: CardView //회의 예약 카드 버튼
    private lateinit var code: EditText
    private var database: DatabaseReference = Firebase.database.reference.child("rooms")
    private var user: FirebaseUser = Firebase.auth.currentUser!!
    lateinit var ownerId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        context = this

        admissionBtn = findViewById(R.id.admission_btn)
        userProfileBtn = findViewById(R.id.user_profile_btn)
        createMeetingBtn = findViewById(R.id.home_create_btn_layout)
        bookMeetingBtn = findViewById(R.id.home_book_btn_layout)

        code = findViewById(R.id.input_invitation_code)

        //admissionBtn.visibility = View.INVISIBLE//View.GONE//View.VISIBLE

        roomsRecyclerView = findViewById(R.id.rooms_recycleView_home)

        userProfileBtn.setImageURI(user?.photoUrl)

        //회의 목록 테스트 값
        roomData.add(RoomVo("IT쇼 주제 선정", "2021.04.06", "이틀 후", "#F9D5D3"))
        roomData.add(RoomVo("반민초 vs 민초", "2021.04.01", "3일 전", "#CFE1CD"))
        roomData.add(RoomVo("사진관 결정", "2021.03.20", "2주 전", "#EEDFFF"))

        roomAdapter = RoomViewAdapter(this, roomData)
        roomsRecyclerView.adapter = roomAdapter
        code.setImeOptions(EditorInfo.IME_ACTION_DONE)

        createMeetingBtn.setOnClickListener {
            val intent = Intent(this, createroom_name::class.java)
            startActivity(intent)
            finish()
        }

        userProfileBtn.setOnClickListener {
            val intent = Intent(this, ProfilePageActivity::class.java)
            startActivity(intent)
            finish()
        }

        //회의 일정
        bookMeetingBtn.setOnClickListener{
            val intent = Intent(this, Calendar::class.java)
            startActivity(intent)
        }

        admissionBtn.setOnClickListener {
            checkCode(code.text.toString())
        }

        code.setOnEditorActionListener{ textView, action, event ->
            var handled = false
            if (action == EditorInfo.IME_ACTION_DONE) {
                admissionBtn.performClick()
                handled = true
            }
            handled
        }

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
    fun checkCode(code:String){
        database.child(code).child("open").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            if(it.value != null){
                if(it.value != "0"){
                    val intent = Intent(this, ChatingActivity::class.java)
                    intent.putExtra("code", code)

                    database.child(code).child("ownerID").get()
                        .addOnSuccessListener {
                            ownerId = it.value.toString()
                            if(ownerId == user.uid){
                                startActivity(intent)
                                finish()
                            }else {
                                database.child(code).child("member").child("member uid").setValue(user.uid)
                                    .addOnSuccessListener {
                                        database.child(code).child("member").child("Headcount").get()
                                            .addOnSuccessListener {
                                                Log.i("firebase", "Got value ${it.value}")
                                                val headcount = it.value.toString().toInt() + 1
                                                database.child(code).child("member").child("Headcount")
                                                    .setValue(headcount)
                                                    .addOnSuccessListener {
                                                        database.child(code).child("member").child("userlist").child("user2")
                                                            .setValue(user.displayName)
                                                            .addOnSuccessListener {
                                                                Toast.makeText(this, "방 입장", Toast.LENGTH_SHORT).show()
                                                                startActivity(intent)
                                                                finish()
                                                            }
                                                    }

                                            }.addOnFailureListener {
                                                Log.e("firebase", "Error getting data", it)
                                            }
                                    }
                            }
                        }
                        }

            }else {
                Toast.makeText(this, "해당 코드를 가진 방이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
            Toast.makeText(this, "해당 코드를 가진 방이 없습니다.", Toast.LENGTH_SHORT).show()
        }

    }

}