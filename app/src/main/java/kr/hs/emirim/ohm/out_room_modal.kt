package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class out_room_modal : AppCompatActivity() {

    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_room_modal)
        Log.d(TAG, "out_room_modal - onCreate() called")
    }

    fun onDialogBtnClicked(view: View){
        Log.d(TAG, "out_room_modal - onCreate() called")

        val end_room_dialog = end_room_dialog(this)

        end_room_dialog.show()
    }
}