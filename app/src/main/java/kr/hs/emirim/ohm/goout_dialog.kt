package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class goout_dialog : AppCompatActivity() {

    var dilaog03 //다이얼로그
    private lateinit var ok_button : Button //확인버튼
    private lateinit var cancel_button : Button //취소버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goout_dialog)

        ok_button = findViewById(R.id.ok_button)
        cancel_button = findViewById(R.id.cancel_button)

        dilaog03 = android.app.Dialog(this@goout_dialog) //다이얼로그 초기화
        dilaog03.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE) //타이틀 제거
        dilaog03.setContentView(R.layout.activity_goout_dialog) //레이아웃 연결

        // 나가기 창
        open fun onClick(v: android.view.View?) {
            showDialog03()
        }

        open fun showDialog03() {
            dilaog03.show()
            val endBtn: android.widget.Button? =
                dilaog03.findViewById<android.widget.Button?>(R.id.ok_button)
            okBtn.setOnClickListener(object : android.view.View.OnClickListener {
                open fun onClick(view: android.view.View?) {
                    val intent: Intent? = Intent(this@goout_dialog, HomeActivity::class.java)
                    startActivity(intent)
                    dilaog03.dismiss() // 다이얼로그 닫기
                }
            })
            dilaog03.findViewById<android.view.View?>(R.id.cancel_button)
                .setOnClickListener(object : android.view.View.OnClickListener {
                    open fun onClick(view: android.view.View?) {
                        val intent: Intent? = Intent(this@goout_dialog, ChatingActivity::class.java)
                        startActivity(intent)
                        dilaog03.dismiss() // 다이얼로그 닫기
                    }
                })
        }
    }
}