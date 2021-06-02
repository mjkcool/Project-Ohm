package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class VoitConfirmActivity : AppCompatActivity() {
    private lateinit var back : ImageView //다시 투표 상세정보로 넘어가는 이미지 버튼역할

    private lateinit var voit_main_title : EditText //투표를 하는 창의 제목 적기

    private lateinit var voit_title_1 : EditText //투표를 해야 하는 후보들 적기
    private lateinit var voit_title_2: EditText

    private lateinit var anonymity_btn : RadioButton //익명투표
    private lateinit var plural_btn : RadioButton //복수투표

    private lateinit var goto_save : Button //이 정보값을 저장해주는 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voit_main)

        back = findViewById<ImageView>(R.id.quit_btn)  //취소하고 채팅방으로 넘어가는 버튼

        voit_main_title = findViewById<EditText>(R.id.voit_main_title) // 투표할 제목 작성

        voit_title_1 = findViewById<EditText>(R.id.voit_confirm1)  //투표할 항목들 작성
        voit_title_2 = findViewById<EditText>(R.id.voit_confirm2)

        goto_save = findViewById<Button>(R.id.goto_save)//저장하고 넘어가기

        back!!.setOnClickListener {
            val intent = Intent(applicationContext, ChatingActivity::class.java)
            startActivity(intent) //액티비티 이동
        }

        goto_save!!.setOnClickListener {
            //데이터 저장하는 것들 적기
            val intent = Intent(applicationContext, voitActivity::class.java)
            startActivity(intent) //액티비티 이동
        }
    }
}