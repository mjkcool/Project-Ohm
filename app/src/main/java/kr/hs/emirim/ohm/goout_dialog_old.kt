//package kr.hs.emirim.ohm
//
//import android.widget.Button
//
//class end_room_dialog(context : Context) {
//    private val dialog2 = Dialog(context) //부모 액티비티의 context 가 들어감
//    private lateinit var btnOK : Button
//    private lateinit var btnCancel : Button
//
//    fun start(content : String) {
//        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE) //타이틀바 제거
//        dialog2.setContentView(kr.hs.emirim.ohm.R.layout.activity_goout_dialog) //다이얼로그에 사용할 xml 파일을 불러옴
//        dialog2.setCancelable(true) //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히도록 함
//
//        btnOK = dialog2.findViewById(kr.hs.emirim.ohm.R.id.ok_button)
//        btnOK.setOnClickListener {
//            val intent: Intent? = Intent(this@goout_dialog, HomeActivity::class.java)
//            startActivity(intent)
//            dialog2.dismiss()
//        }
//
//        btnCancel = dialog2.findViewById(kr.hs.emirim.ohm.R.id.cancel_button)
//        btnCancel.setOnClickListener {
//            val intent: Intent? = Intent(this@goout_dialog, kr.hs.emirim.ohm.ChatingActivity::class.java)
//            startActivity(intent)
//            dialog2.dismiss()
//        }
//    }
//}