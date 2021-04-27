//package kr.hs.emirim.ohm
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import java.util.*
//
//class MyAdapter     // Provide a suitable constructor (depends on the kind of dataset)
//    (private val mDataset: ArrayList<MyData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
//    // Provide a reference to the views for each data item
//    // Complex data items may need more than one view per item, and
//    // you provide access to all the views for a data item in a view holder
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        // each data item is just a string in this case
//        var mImageView: ImageView //사람을 나타나는 이미지
//        var mTextView1: TextView // 내가 했던 회의 이름들
//        var mTextView2: TextView // 내가 했었던 회의 날짜
//        var mTextView3: TextView //지금부터 몇분 전이였는지 표시
//
//        init {
//            mImageView = view.findViewById<View>(R.id.image) as ImageView
//            mTextView1 = view.findViewById<View>(R.id.card_title) as TextView
//            mTextView2 = view.findViewById<View>(R.id.card_desc) as TextView
//            mTextView3 = view.findViewById<View>(R.id.card_day) as TextView
//        }
//    }
//
//    // Create new views (invoked by the layout manager)
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        // create a new view
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
//        // set the view's size, margins, paddings and layout parameters
//        return ViewHolder(v)
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.mTextView1.text = mDataset[position].text1
//        holder.mTextView2.text = mDataset[position].text2
//        holder.mTextView3.text = mDataset[position].text3
//        holder.mImageView.setImageResource(mDataset[position].img)
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    override fun getItemCount(): Int {
//        return mDataset.size
//    }
//}
//
//class MyData(var text1: String, var text2: String, var text3: String,  var img: Int)