package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import kr.hs.emirim.ohm.fragments.FragInitImage
import kr.hs.emirim.ohm.fragments.FragInitIntroduce
import kr.hs.emirim.ohm.fragments.FragInitNickname

class ProfileInitActivity : AppCompatActivity() {
    lateinit var toBackBtn: ImageButton
    lateinit var toNextBtn: Button
    private var curFagNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_init_activity)
        
        toBackBtn = findViewById(R.id.back_btn_nickname_set)
        toNextBtn = findViewById(R.id.next_btn_nickname_set)

        setFrag(curFagNum) //첫 프래그먼트

        toBackBtn.setOnClickListener{
            when(curFagNum){
                0 ->{

                }
                1 ->{
                    setFrag(--curFagNum)
                }
                2 ->{
                    setFrag(--curFagNum)
                }
            }
        }

        toNextBtn.setOnClickListener{
            when(curFagNum){
                0 ->{
                    val nickname = findViewById<EditText>(R.id.input_nickname_set)
                    if (nickname.length() in 2..10){ //입력 필수 확인
                        setFrag(++curFagNum)
                    }
                    else{
                        Toast.makeText(this, "2자 이상 10자 이하로 입력해주세요", Toast.LENGTH_SHORT)
                    }

                }
                1 ->{
                    setFrag(++curFagNum)
                }
                2 ->{

                }
            }

        }
    }

    private fun setFrag(num: Int){
        val ft = supportFragmentManager.beginTransaction()
        when(num){
            0 -> {
                ft.replace(R.id.profileFragmentContainer, FragInitNickname()).commit()
                toNextBtn.text = "다음"
            }
            1 -> {
                ft.replace(R.id.profileFragmentContainer, FragInitImage()).commit()
                toNextBtn.text = "건너뛰기"
            }
            2 -> {
                ft.replace(R.id.profileFragmentContainer, FragInitIntroduce()).commit()
                toNextBtn.text = "건너뛰기"
            }
        }
    }
}