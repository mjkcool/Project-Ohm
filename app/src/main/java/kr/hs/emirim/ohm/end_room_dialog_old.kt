//package kr.hs.emirim.ohm
//
//import android.widget.Button
//
//class end_room_dialog(context : Context) {
//    private val dialog2 = Dialog(context) //부모 액티비티의 context 가 들어감
//    private lateinit var btnEnd : Button
//    private lateinit var btnCancel : Button
//
//    fun start(content : String) {
//        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE) //타이틀바 제거
//        dialog2.setContentView(R.layout.activity_end_room_modal) //다이얼로그에 사용할 xml 파일을 불러옴
//        dialog2.setCancelable(true) //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히도록 함
//
//        btnEnd = dialog2.findViewById(R.id.end_button)
//        btnEnd.setOnClickListener {
////            val intent: Intent? = Intent(this@end_room_dialog, HomeActivity::class.java)
////            startActivity(intent)
//            dialog2.dismiss()
//        }
//
//        btnCancel = dialog2.findViewById(R.id.cancel_button)
//        btnCancel.setOnClickListener {
////            val intent: Intent? = Intent(this@end_room_dialog, ChatingActivity::class.java)
////            startActivity(intent)
//            dialog2.dismiss()
//        }
//    }
//}

//import android.content.Context
//import android.os.Bundle
//
//class CustomDialog(context: Context) {
//    private val dialog = Dialog(context)
//
//    fun myDig() {
//        dialog.show()
//
//    }
//
//    fun myDig() {
//        dialog.seContentView(R.layout.activity_end_room_modal)
//        dialog.setCanceledOnTouchOutside(true)
//        dialog.setCalcelable(true)
//
//        val btnend = dialog.findViewById<button>(R.id.end_button)
//        val btncancel = dialog.findViewById<button>(R.id.cancel_button)
//
//        btnend.setOnClickListener {
//            val intent: Intent? = Intent(this@end_room_dialog, HomeActivity::class.java)
//                startActivity(intent)
//                dilaog02.dismiss() // 다이얼로그 닫기
//        }
//
//        btncancel.setOnClickListener {
//            val intent: Intent? = Intent(this@end_room_dialog, ChatingActivity::class.java)
//                startActivity(intent)
//                dilaog02.dismiss() // 다이얼로그 닫기
//        }
//    }
//
//    class end_room_dialog : AppCompatActivity() {
//        override fun onCreate(savedlnstanceState: Bundle?)
//
//
//        val dialog = endroomDialog(this)
//        dialog.dialog2()
//    }
//}

//    var dilaog02 //다이얼로그
//    private lateinit var endbutton //종료버튼
//    private lateinit var cancelbutton //취소버튼
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_end_room_dialog)
//
//        endbutton = findViewById(R.id.end_button)
//        cancelbutton = findViewById(R.id.cancel_button)
//
//        dilaog02 = android.app.Dialog(this@end_room_dialog) //다이얼로그 초기화
//        dilaog02.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE) //타이틀 제거
//        dilaog02.setContentView(R.layout.activity_out_room_modal) //레이아웃 연결
//
//        // 나가기 창
//        open fun onClick(v: android.view.View?) {
//            showDialog02()
//        }
//
//        open fun showDialog02() {
//            dilaog02.show()
//            val endBtn: android.widget.Button? =
//                dilaog02.findViewById<android.widget.Button?>(R.id.end_button)
//            endBtn.setOnClickListener(object : android.view.View.OnClickListener {
//                open fun onClick(view: android.view.View?) {
//                    val intent: Intent? = Intent(this@end_room_dialog, HomeActivity::class.java)
//                    startActivity(intent)
//                    dilaog02.dismiss() // 다이얼로그 닫기
//                }
//            })
//            dilaog02.findViewById<android.view.View?>(R.id.cancel_button)
//                .setOnClickListener(object : android.view.View.OnClickListener {
//                    open fun onClick(view: android.view.View?) {
//                        val intent: Intent? = Intent(this@end_room_dialog, ChatingActivity::class.java)
//                        startActivity(intent)
//                        dilaog02.dismiss() // 다이얼로그 닫기
//                    }
//                })
//        }
//
//    }
//}