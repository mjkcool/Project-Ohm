package kr.hs.emirim.ohm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mypage.view.*
import java.util.*

 class Main : Activity() {

          private var isOpen = false //애니메이션 기본값!=
     
          private lateinit var profile_btn : ImageButton //프로필 버튼
          private lateinit var profile : ImageView //프로필 사진

          private lateinit var fab_main : FloatingActionButton; //플로팅 버튼
          private lateinit var fab_sub1 : FloatingActionButton; //캘린더로 넘어가는 버튼
          private lateinit var fab_sub2 : FloatingActionButton; //새회의로 넘어가는 버튼
          private lateinit var fab_sub3 : FloatingActionButton; //코드창으로 넘어가는 버튼

           private var mRecyclerView: RecyclerView? = null //recyclerView 호출
           private var mAdapter: RecyclerView.Adapter<*>? = null //Adapter 호출
           private var mLayoutManager: RecyclerView.LayoutManager? = null //Layout 호출
           private var myDataset: ArrayList<MyData>? = null //Dataset 호출

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         profile_btn = findViewById<ImageButton>(R.id.profile_btn) //마이페이지로 넘어가는 버튼

         profile = findViewById<ImageView>(R.id.profile_btn); //프로필 사진 띄우기
         profile.setImageResource(R.drawable.profile);


         fab_main = findViewById(R.id.fab_main) //플로팅 버튼이 뜨는 것
         fab_sub1 = findViewById(R.id.fab_sub1)
         fab_sub2 = findViewById(R.id.fab_sub2)
         fab_sub3 = findViewById(R.id.fab_sub3)

         val fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open)  //하위버튼이 보이거나 안 보이는 것
         val fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close)
         
         val fabRClockwise = AnimationUtils.loadAnimation(this,R.anim.rotote_anticlockwise)//+가 모양인 버튼이 열면 x로 변하는 것
         val fabRAntiClockwise = AnimationUtils.loadAnimation(this,R.anim.rotate_clockwise)
         
         
         //마이페이지로 넘어가는 버튼
         profile_btn.setOnClickListener {
             val intent = Intent(this, MyPage::class.java)
             startActivity(intent)
         }
         

        //카드메뉴를 보여주는 코드
        mRecyclerView = findViewById<View>(R.id.my_recycler_view) as RecyclerView

        mRecyclerView!!.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)


        // Adapter 호출문
        myDataset = ArrayList()
        mAdapter = MyAdapter(myDataset!!)
        mRecyclerView!!.adapter = mAdapter


        myDataset!!.add(MyData("#InsideOut", R.drawable.people)) //임시 데이터 , 가로로 넘길 시 밑에 있는 토이스토리만 넘겨짐
        myDataset!!.add(MyData("#Mini", R.drawable.people))
        myDataset!!.add(MyData("#ToyStroy", R.drawable.people))



        //리스트뷰를 보여주는 코드
        val arraylist = ArrayList<String>()

        arraylist.add("가") //임시 데이터
        arraylist.add("나")
        arraylist.add("다")
        arraylist.add("라")

        val Adapter: ArrayAdapter<String>
        Adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arraylist) //simple_list_item => 안드로이드가 제공하는 레이아웃 종류의 한 가지

        val list = findViewById<View>(R.id.my_list_view) as ListView
        list.adapter = Adapter



        //플로팅 버튼을 보여주는 코드
         fab_main.setOnClickListener {

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
             //캘린더로 넘어가는 플로팅 버튼
             fab_sub1.setOnClickListener {
                 Toast.makeText(this,"넘어갑니다.", Toast.LENGTH_SHORT)
                 val intent = Intent(this, newAct::class.java)
                 startActivity(intent)
             }
         }
    }
}