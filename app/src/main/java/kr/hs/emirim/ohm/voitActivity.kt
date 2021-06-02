package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class voitActivity : AppCompatActivity() {
    private lateinit var back : ImageView//다시 채팅창으로 넘어가는 이미지 버튼역할
    
    private lateinit var voit_main1 : ImageView //제목들을 감싸는 이미지들 => 클릭시 값이 넘겨지게?
    private lateinit var voit_main2 : ImageView
    private lateinit var voit_main3 : ImageView
    private lateinit var voit_main4 : ImageView //항목을 추가할라면 넘어갈 수 있는 이미지 버튼 역할
    
    private lateinit var voit_titile_main1 : TextView //투표를 할 수 있는 후보들
    private lateinit var voit_titile_main2 : TextView
    private lateinit var voit_titile_main3 : TextView
    private lateinit var voit_titile_main4 : TextView // 항목을 추가할 수 있는 걸 알려주는 텍스트


    private lateinit var goto_save : Button //이 정보값을 저장해주는 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voit_main)
        back = findViewById<ImageView>(R.id.quit_btn)  //취소하고 채팅방으로 넘어가는 버튼

        voit_main1 = findViewById<ImageView>(R.id.voit_main1)  //제목을 감싸는 이미지 버튼 역할
        voit_main2 = findViewById<ImageView>(R.id.voit_main2)
        voit_main3 = findViewById<ImageView>(R.id.voit_main3)
        voit_main4 = findViewById<ImageView>(R.id.voit_main4)

        voit_titile_main1 = findViewById<TextView>(R.id.voit_title_1)  //후보들을 상세하게 알 수 있음
        voit_titile_main2 = findViewById<TextView>(R.id.voit_title_2)
        voit_titile_main3 = findViewById<TextView>(R.id.voit_title_3)
        voit_titile_main4 = findViewById<TextView>(R.id.voit_title_4)

        goto_save = findViewById<Button>(R.id.goto_save) //저장하고 넘어가기

        back!!.setOnClickListener {
            val intent = Intent(applicationContext, ChatingActivity::class.java)
            startActivity(intent) //액티비티 이동
        }

        voit_main4!!.setOnClickListener {
            val intent = Intent(applicationContext, ChatingActivity::class.java)
            startActivity(intent) //액티비티 이동
        }


        goto_save!!.setOnClickListener {
            //데이터 저장하는 것들 적기
            val intent = Intent(applicationContext, ChatingActivity::class.java)
            startActivity(intent) //액티비티 이동
        }
    }
}