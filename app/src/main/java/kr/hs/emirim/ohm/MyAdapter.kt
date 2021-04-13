package kr.hs.emirim.ohm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MyAdapter     // Provide a suitable constructor (depends on the kind of dataset)
    (private val mDataset: ArrayList<MyData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // each data item is just a string in this case
        var  mTextView: TextView
        var mImageView: ImageView

        init {
            mImageView = view.findViewById<View>(R.id.image) as ImageView
            mTextView = view.findViewById<View>(R.id.chat_title) as TextView
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.text = mDataset[position].text
        holder.mImageView.setImageResource(mDataset[position].img)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset.size
    }
}
class MyData(var text: String, var img: Int)