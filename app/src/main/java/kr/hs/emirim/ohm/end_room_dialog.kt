package kr.hs.emirim.ohm

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle

class end_room_dialog(context:Context): Dialog(context) {
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_room_dialog)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}