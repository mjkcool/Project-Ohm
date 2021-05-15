package kr.hs.emirim.ohm

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RoomViewAdapter(val context: Context, private val RoomData: ArrayList<RoomVo>): RecyclerView.Adapter<RoomViewAdapter.ViewHolder>(){

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!){
        private val title = view?.findViewById<TextView>(R.id.title_meeting_item)
        private val desc = view?.findViewById<TextView>(R.id.desc_meeting_item)
        private val day = view?.findViewById<TextView>(R.id.date_meeting_item)

        fun bind(room: RoomVo, context: Context){
            title?.text = room.title
            desc?.text = room.desc
            day?.text = room.day
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //바인딩
        holder.bind(RoomData[position], context)
    }

    override fun getItemCount() = RoomData.size

}