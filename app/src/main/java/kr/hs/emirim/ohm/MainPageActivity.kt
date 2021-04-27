package kr.hs.emirim.ohm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog;
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainPageActivity : AppCompatActivity() {
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


            myDataset!!.add(MyData("#InsideOut", R.drawable.inmain_people)) //임시 데이터 , 가로로 넘길 시 밑에 있는 토이스토리만 넘겨짐
            myDataset!!.add(MyData("#Mini", R.drawable.inmain_people))
            myDataset!!.add(MyData("#ToyStroy", R.drawable.inmain_people))

    }
}