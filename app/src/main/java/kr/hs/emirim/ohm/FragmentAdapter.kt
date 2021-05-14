package kr.hs.emirim.ohm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class FragmentAdapter (fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    // ViewPager에 들어갈 Fragment들을 담을 리스트
    private val fragments = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    // List에 Fragment를 담을 함수
    fun addItem(fragment: Fragment) {
        fragments.add(fragment)
    }
}