package kr.hs.emirim.ohm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LoginActivity: AppCompatActivity() {

    lateinit var googleLoginBtn: FloatingActionButton
    lateinit var facebookLoginBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        googleLoginBtn.setOnClickListener() {

        }

        facebookLoginBtn.setOnClickListener() {

        }

    }
}