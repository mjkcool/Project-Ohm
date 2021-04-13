package kr.hs.emirim.ohm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_setting.*

class MyPage : Activity() {

    private lateinit var back_btn : ImageButton //뒤로 돌아갈 수 있는 버튼
    private lateinit var setting_btn: ImageButton //settings 페이지로 넘어갈 수 있는 버튼
    private lateinit var my_profile : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        back_btn = findViewById<ImageButton>(R.id.back_btn) //메인으로 넘어가는 버튼
        setting_btn = findViewById<ImageButton>(R.id.settings) //settings 페이지로 넘어갈 수 있는 버튼

        //메인으로 넘어갈 수 있는 버튼
        back_btn.setOnClickListener {
            val intent = Intent(this, MyPage::class.java)
            startActivity(intent)
        }

        //settings으로 넘어갈 수 있는 버튼
        setting_btn.setOnClickListener {
            val intent = Intent(this, settings::class.java)
            startActivity(intent)
        }

    }
}