package kr.hs.emirim.ohm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.inmain_people, "목적지로 가는 길", "2021.04.22", "5일전"));
        models.add(new Model(R.drawable.inmain_people, "반민초 vs 민초", "2021.04.26", "하루 전"));
        models.add(new Model(R.drawable.inmain_people, "엄마 vs 아빠", "2021.04.01", "26일전"));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        viewPager.setClipToPadding(false);
        viewPager.setPadding(0, 0, 100, 0);
        viewPager.setPageMargin(30);

    }
}