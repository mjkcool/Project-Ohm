package kr.hs.emirim.ohm

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_showtoolbar.*

class toolbar : Activity() {
    val supportActionBar = null
    val main_toolbar = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showtoolbar)

        main_navigationView.setNavigationItemSelectedListener(this) //navigation 리스너

        setSupportActionBar(main_toolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24) // 홈버튼 이미지 변경

        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                main_drawer_layout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { //볼 수 있는 앱
        when (item.itemId) {
            R.id.account -> Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show()
            R.id.item2 -> Toast.makeText(this, "item2 clicked", Toast.LENGTH_SHORT).show()
            R.id.item3 -> Toast.makeText(this, "item3 clicked", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    override fun onBackPressed() { //뒤로가기 처리
        if(main_drawer_layout.isDrawerOpen(GravityCompat.START)){
            main_drawer_layout.closeDrawers()
            // 테스트를 위해 뒤로가기 버튼시 Toast 메시지
            Toast.makeText(this,"back btn clicked",Toast.LENGTH_SHORT).show()
        } else{
            super.onBackPressed()
        }
    }
}
