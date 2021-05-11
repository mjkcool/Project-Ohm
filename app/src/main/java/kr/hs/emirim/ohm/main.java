package kr.hs.emirim.ohm;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<ColorSpace.Model> models;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = new ArrayList<>();
        adapter = new Adapter() {
            @Override
            public Object getItem(int position) {
                return null;
            }
        };
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        models.add(new ColorSpace.Model(R.drawable.inmain_people, "목적지로 가는 길", "2021.04.22", "5일전"));
        models.add(new Model(R.drawable.inmain_people, "반민초 vs 민초", "2021.04.26", "하루 전"));
        models.add(new Model(R.drawable.inmain_people, "엄마 vs 아빠", "2021.04.01", "26일전"));

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        viewPager.setClipToPadding(false);
        int dpValue = 16;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin / 2);

        for (int i = 0; i < models.size(); i++) {
            Adapter adapter = new Adapter();
            Bundle bundle = new Bundle();
            bundle.putInt("imgRes", models.get(i));
            adapter.setArguments(bundle);
            fragmentAdapter.addItem(adapter);
        }
        fragmentAdapter.notifyDataSetChanged();
    }
}
class FragmentAdapter extends FragmentPagerAdapter {

    // ViewPager에 들어갈 Fragment들을 담을 리스트
    private ArrayList<Fragment> fragments = new ArrayList<>();

    // 필수 생성자
    FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    // List에 Fragment를 담을 함수
    void addItem(Adapter fragment) {
        fragments.add((Fragment) fragment);
    }
}