package kr.hs.emirim.ohm

import android.app.Activity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class settings : Activity() {
    private lateinit var settings: ImageButton //수정을 할 수 있는 버튼을 보여줌

    private lateinit var profile : ImageView //이미지를 보여줌
    private lateinit var settings2: ImageView
    private lateinit var setting_1 : ImageView
    private lateinit var setting_2 : ImageView
    private lateinit var setting_3 : ImageView
    private lateinit var setting_4 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        profile = findViewById<ImageView>(R.id.profile1); //내 프로필을 볼 수 있는 것 => 임시설정
        profile.setImageResource(R.drawable.button_1);

        setting_1 = findViewById<ImageView>(R.id.setting_1); //각 문장을 담고 있는 버튼의 edittext 이미지를 보여줌
        setting_1.setImageResource(R.drawable.edittext);

        setting_2 = findViewById<ImageView>(R.id.setting_2);
        setting_2.setImageResource(R.drawable.edittext);

        setting_3 = findViewById<ImageView>(R.id.setting_3);
        setting_3.setImageResource(R.drawable.edittext);

        setting_4 = findViewById<ImageView>(R.id.setting_4);
        setting_4.setImageResource(R.drawable.edittext);

        settings = findViewById<ImageButton>(R.id.settings1) //settings 페이지로 넘어갈 수 있는 버튼
            settings2 = findViewById<ImageView>(R.id.settings1); //세팅으로 넘어가는 이미지 보여줌
            settings2.setImageResource(R.drawable.ohmm_mainpage);



    }
}