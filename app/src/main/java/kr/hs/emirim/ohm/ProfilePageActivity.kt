package kr.hs.emirim.ohm

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfilePageActivity : AppCompatActivity() {
    private lateinit var logoutBtn : View
    private lateinit var quitBtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        logoutBtn = findViewById(R.id.btn_logout)
        quitBtn = findViewById(R.id.quit_btn)

        logoutBtn.setOnClickListener {
            signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        quitBtn.setOnClickListener{

        }

    }
    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()
        // [END auth_sign_out]
    }
}