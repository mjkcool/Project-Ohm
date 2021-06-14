package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class end_room_dialog : AppCompatActivity() {

    var dilaog02 //다이얼로그
    private lateinit var end_button : Button //종료버튼
    private lateinit var cancel_button : Button //취소버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_room_dialog)

        end_button = findViewById(R.id.end_button)
        cancel_button = findViewById(R.id.cancel_button)

        dilaog02 = android.app.Dialog(this@end_room_dialog) //다이얼로그 초기화
        dilaog02.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE) //타이틀 제거
        dilaog02.setContentView(R.layout.activity_out_room_modal) //레이아웃 연결

        // 나가기 창
        open fun onClick(v: android.view.View?) {
            showDialog02()
        }

        open fun showDialog02() {
            dilaog02.show()
            val endBtn: android.widget.Button? =
                dilaog02.findViewById<android.widget.Button?>(R.id.end_button)
            endBtn.setOnClickListener(object : android.view.View.OnClickListener {
                open fun onClick(view: android.view.View?) {
                    val intent: Intent? = Intent(this@end_room_dialog, HomeActivity::class.java)
                    startActivity(intent)
                    dilaog02.dismiss() // 다이얼로그 닫기
                }
            })
            dilaog02.findViewById<android.view.View?>(R.id.cancel_button)
                .setOnClickListener(object : android.view.View.OnClickListener {
                    open fun onClick(view: android.view.View?) {
                        val intent: Intent? = Intent(this@end_room_dialog, ChatingActivity::class.java)
                        startActivity(intent)
                        dilaog02.dismiss() // 다이얼로그 닫기
                    }
                })
        }

    }
}