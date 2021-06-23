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

class CalendarAdapter(val context: Context, private val CalData: ArrayList<Calendars>): RecyclerView.Adapter<CalendarAdapter.ViewHolder>(){

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!){
        private val title = view?.findViewById<TextView>(R.id.tv_title)
        private val time = view?.findViewById<TextView>(R.id.tv_time)

        fun bind(cal: Calendars, context: Context){
            title?.text = cal.title
            time?.text = cal.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.create_calendar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //바인딩
        holder.bind(CalData[position], context)
    }

    override fun getItemCount() = CalData.size

}