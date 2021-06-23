package kr.hs.emirim.ohm

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text
import java.util.ArrayList

class ParticipantAdapter(val context: Context, private val PariVo: ArrayList<ParticipantVo>): RecyclerView.Adapter<ParticipantAdapter.ViewHolder>() {
    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!){
        private val nickname = view?.findViewById<TextView>(R.id.UserName)
        private val profile_img = view?.findViewById<CircleImageView>(R.id.ProfileImg)

        fun bind(user: ParticipantVo, context: Context){
            nickname?.text = user.nickname
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.paticipant_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //바인딩
        holder.bind(PariVo[position], context)
    }

    override fun getItemCount() = PariVo.size
}