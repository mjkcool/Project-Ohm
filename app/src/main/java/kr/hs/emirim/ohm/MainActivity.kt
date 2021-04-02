package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.login_button) as Button
        }

    fun onClickButton(view : View){
        var intent = Intent(this, login::class.java)
        startActivity(intent)
    }

}