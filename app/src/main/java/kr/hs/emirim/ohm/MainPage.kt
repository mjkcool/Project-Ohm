package kr.hs.emirim.ohm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import java.util.*

class MainPage : AppCompatActivity() {
    var viewPager: ViewPager? = null
//    var adapter: Adapter? = null
    var models: MutableList<Model>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_room)
//        adapter = Adapter(models!!, this)
//        viewPager = findViewById(R.id.viewPager)
//        viewPager.setAdapter(adapter)
//        viewPager.setClipToPadding(false)
//        viewPager.setPadding(0, 0, 50, 0)
//        viewPager.setPageMargin(30)
//
//        models = ArrayList()
//        (models as ArrayList<Model>).add(Model(R.drawable.inmain_people, "목적지로 가는 길", "2021.04.22", "5일전"))
//        (models as ArrayList<Model>).add(Model(R.drawable.inmain_people, "반민초 vs 민초", "2021.04.26", "하루 전"))
//        (models as ArrayList<Model>).add(Model(R.drawable.inmain_people, "엄마 vs 아빠", "2021.04.01", "26일전"))


    }
}