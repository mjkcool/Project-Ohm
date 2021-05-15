package kr.hs.emirim.ohm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
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
                    toBackBtn.visibility = View.INVISIBLE
                }
                1 ->{
                    toBackBtn.visibility = View.VISIBLE
                    setFrag(--curFagNum)
                }
                2 ->{
                    toBackBtn.visibility = View.VISIBLE
                    setFrag(--curFagNum)
                }
            }
        }

        toNextBtn.setOnClickListener{
            when(curFagNum){
                0 ->{
                    val nickname = findViewById<EditText>(R.id.input_nickname_set)
                    if (nickname.length() in 2..10){ //입력 필수 확인
                        
                        // ***파이어베이스에 닉네임 데이터 등록(필수)
                        
                        setFrag(++curFagNum)
                    }else{
                        Toast.makeText(this, "2자 이상 10자 이하로 입력해주세요", Toast.LENGTH_SHORT)
                    }
                }
                1 ->{
                    // 파이어베이스에 프로필사진 데이터 등록(선택)

                    setFrag(++curFagNum)
                }
                2 ->{
                    // 파이어베이스에 한줄소개 데이터 등록(선택)

                    //HomeActivity로 이동
                    var intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK //실행 액티비티 외 모두 제거
                    startActivity(intent)
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