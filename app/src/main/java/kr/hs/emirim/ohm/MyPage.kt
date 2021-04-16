package kr.hs.emirim.ohm

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_setting.*


class MyPage : Activity() {
    private var mAuth: FirebaseAuth = Firebase.auth

    private lateinit var back_btn : ImageButton //뒤로 돌아갈 수 있는 버튼
    private lateinit var back : ImageView
    
    private lateinit var setting_btn: ImageButton //settings 페이지로 넘어갈 수 있는 버튼
    private lateinit var setting : ImageView
    
    private lateinit var help_btn : Button //도움말을 보여줄 수 있는 버튼
    private lateinit var show_btn : Button //개발자 소개를 보여줄 수 있는 버튼
    private lateinit var logout_btn :Button //로그아웃을 할 수 있는 버튼
    private lateinit var out_btn : Button //아예 나갈 수 있는 버튼
    
    private lateinit var profile : ImageView //이미지를 보여줌
    private lateinit var main : ImageView
    private lateinit var main_1 : ImageView
    private lateinit var main_2 : ImageView
    private lateinit var main_3 : ImageView
    private lateinit var main_4 : ImageView
    private lateinit var main_5 : ImageView
    private lateinit var main_6 : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val user = Firebase.auth.currentUser!!
        mAuth =  Firebase.auth
        
        profile = findViewById<ImageView>(R.id.my_profile); //내 프로필을 볼 수 있는 것 => 임시설정
        profile.setImageResource(R.drawable.button_1);
            main_6 = findViewById<ImageView>(R.id.main_6); //프로필과 한줄소개 변경 edittext
            main_6.setImageResource(R.drawable.edittext);
        
        main_1 = findViewById<ImageView>(R.id.main_1); //각 문장을 담고 있는 버튼의 edittext 이미지를 보여줌
        main_1.setImageResource(R.drawable.edittext); //알람모드

        logout_btn = findViewById(R.id.logout_btn) //로그아웃 버튼
            main_2  = findViewById<ImageView>(R.id.main_2); //로그아웃 edittext
            main_2.setImageResource(R.drawable.edittext);

        main_3  = findViewById<ImageView>(R.id.main_3); //다크모드 edittext
        main_3.setImageResource(R.drawable.edittext);

        show_btn = findViewById(R.id.show_btn); //개발자 설명 버튼
            main_4  = findViewById<ImageView>(R.id.main_4); //개발자 설명 edittext
            main_4.setImageResource(R.drawable.edittext);

        help_btn = findViewById(R.id.help_btn) //도움말 버튼
            main_5  = findViewById<ImageView>(R.id.main_5); //도움말 edittext
            main_5.setImageResource(R.drawable.edittext);
        
        out_btn = findViewById(R.id.peopelout_btn) //회원탈퇴 버튼
            main = findViewById<ImageView>(R.id.main_7); //회원탈퇴 edittext
            main.setImageResource(R.drawable.edittext);

        back_btn = findViewById<ImageButton>(R.id.back_btn) //메인으로 넘어가는 버튼
            back = findViewById<ImageView>(R.id.back_btn); //메인으로 넘어가는 이미지 보여줌
            back.setImageResource(R.drawable.back);
        
        setting_btn = findViewById<ImageButton>(R.id.settings) //settings 페이지로 넘어갈 수 있는 버튼
            setting = findViewById<ImageView>(R.id.settings); //세팅으로 넘어가는 이미지 보여줌
            setting.setImageResource(R.drawable.ohmm_mainpage);

        //메인으로 넘어갈 수 있는 버튼
        back_btn.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }

        //settings으로 넘어갈 수 있는 버튼
        setting_btn.setOnClickListener {
            val intent = Intent(this, settings::class.java)
            startActivity(intent)
        }

        logout_btn.setOnClickListener {
            mAuth.signOut()
            Toast.makeText(this, "로그아웃 되었습니다.", LENGTH_SHORT).show()
            startActivity(Intent(this, User_Login::class.java))
            finish()

        }

        out_btn.setOnClickListener{
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "회원 탈퇴 완료.")
                        Toast.makeText(this, "회원 탈퇴 완료. 이용해주셔서 감사합니다 :)",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, User_Login::class.java))
                        finish()
                    }
                }
        }
    }
}