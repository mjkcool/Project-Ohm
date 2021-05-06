package kr.hs.emirim.ohm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MyProfile_Activity : AppCompatActivity(){ //내 프로필, 환경에 들어가는 페이지
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //레이아웃 구현을 하지 못 하여 임시로 메인레이아웃을 둠
    }
}