package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener({
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        })

        join_button.setOnClickListener({
            val intent = Intent(this, join::class.java)
            startActivity(intent)
        })
    }

//    fun onClickButton(view : View){
//        var intent = Intent(this, login::class.java)
//        startActivity(intent)
//    }

}