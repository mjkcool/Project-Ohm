package kr.hs.emirim.ohm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class CardViewAdapter(private val models: List<CardViewData>, private val context: Context) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return models.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater!!.inflate(R.layout.activity_item, container, false)

        val imageView: ImageView
        val title: TextView
        val desc: TextView
        val day: TextView

        imageView = view.findViewById(R.id.card_image)
        title = view.findViewById(R.id.card_title)
        desc = view.findViewById(R.id.card_desc)
        day = view.findViewById(R.id.card_day)

        imageView.setImageResource(models[position].image)
        title.text = models[position].title
        desc.text = models[position].desc
        day.text = models[position].day

        view.setOnClickListener { //카드메뉴를 누를 시에 이동되는 클래스
            if (position == 0) {
                val intent = Intent(context, ChatingActivity::class.java)
                //intent.putExtra("param", models.get(position).getTitle());
                context.startActivity(intent)
            } else if (position == 1) {
                val intent = Intent(context, ChatingActivity::class.java)
                //intent.putExtra("param", models.get(position).getTitle());
                context.startActivity(intent)
            } else if (position == 2) {
                val intent = Intent(context,  ChatingActivity::class.java)
                //intent.putExtra("param", models.get(position).getTitle());
                context.startActivity(intent)
            } else if (position == 3) {
                val intent = Intent(context,   ChatingActivity::class.java)
                //intent.putExtra("param", models.get(position).getTitle());
                context.startActivity(intent)
            }
        }
        container.addView(view, 0) //카드메뉴 추가
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}