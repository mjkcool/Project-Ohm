package kr.hs.emirim.ohm

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : Activity() {
    private var mRecyclerView: RecyclerView? = null //recyclerView 호출
    private var mAdapter: RecyclerView.Adapter<*>? = null //Adapter 호출
    private var mLayoutManager: RecyclerView.LayoutManager? = null //Layout 호출
    private var myDataset: ArrayList<MyData>? = null //Dataset 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }
}
