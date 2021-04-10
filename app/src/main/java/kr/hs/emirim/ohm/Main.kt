package kr.hs.emirim.ohm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

 class Main : Activity() {
     private var mRecyclerView: RecyclerView? = null //recyclerView 호출
     private var mAdapter: RecyclerView.Adapter<*>? = null //Adapter 호출
     private var mLayoutManager: RecyclerView.LayoutManager? = null //Layout 호출
     private var myDataset: ArrayList<MyData>? = null //Dataset 호출

     private val fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open)
     private val fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close)
     private val fabRClockwise = AnimationUtils.loadAnimation(this,R.anim.rotote_anticlockwise)
     private val fabRAntiClockwise = AnimationUtils.loadAnimation(this,R.anim.rotate_clockwise)

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         val  fab:FloatingActionButton = findViewById(R.id.fab_sub1)
         fab.tooltipText  = "캘린더"

        //카드메뉴를 보여주는 코드
        mRecyclerView = findViewById<View>(R.id.my_recycler_view) as RecyclerView

        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)

        // Adapter 호출문
        myDataset = ArrayList()
        mAdapter = MyAdapter(myDataset!!)
        mRecyclerView!!.adapter = mAdapter


        myDataset!!.add(
            MyData(
                "#InsideOut",
                R.drawable.people
            )
        ) //임시 데이터 , 가로로 넘길 시 밑에 있는 토이스토리만 넘겨짐
        myDataset!!.add(MyData("#Mini", R.drawable.people))
        myDataset!!.add(MyData("#ToyStroy", R.drawable.people))


        //인기 급상승을 알려주는 코드
        val arraylist = ArrayList<String>()

        arraylist.add("가") //임시 데이터
        arraylist.add("나")
        arraylist.add("다")
        arraylist.add("라")

        val Adapter: ArrayAdapter<String>
        Adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arraylist
        ) //simple_list_item => 안드로이드가 제공하는 레이아웃 종류의 한 가지

        val list = findViewById<View>(R.id.my_list_view) as ListView
        list.adapter = Adapter

        //플로팅 뜨는 코드
         fab_main.setOnClickListener {

             var isOpen = false

             if(isOpen){
                 fab_sub1.startAnimation(fabClose)
                 fab_sub2.startAnimation(fabClose)
                 fab_sub3.startAnimation(fabClose)
                 fab_main.startAnimation(fabRClockwise)

                 isOpen = false
             }else{
                 fab_sub1.startAnimation(fabOpen)
                 fab_sub2.startAnimation(fabOpen)
                 fab_sub3.startAnimation(fabOpen)
                 fab_main.startAnimation(fabRAntiClockwise)

                 isOpen = false
             }
             fab_sub1.setOnClickListener {
                 Toast.makeText(this,"넘어갑니다.", Toast.LENGTH_SHORT)
                 val intent = Intent(this, newAct::class.java)
                 startActivity(intent)
             }
         }
    }
}