package kr.hs.emirim.ohm

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_createmeeting1.*


class createroom_name : AppCompatActivity(){

    lateinit var room_name: EditText
    lateinit var text_number: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_createmeeting1)

            room_name = findViewById(R.id.room_name)
            text_number = findViewById(R.id.text_number)

            next_button1.setOnClickListener {
                val intent = Intent(this, createroom_topic::class.java)
                intent.putExtra("rname", room_name.text.toString())
                startActivity(intent)
                next_button1.setEnabled(false);
                finish()
            }

            back_button1.setOnClickListener {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                back_button1.setEnabled(false);
                finish()
            }

            room_name.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //키보드에 완료버튼을 누른 후 수행할 것
                        next_button1.performClick()
                    true
                } else false
            })

            room_name?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    text_number?.setText(s.length.toString() + "/ 30")
                }
            })

        }

}
