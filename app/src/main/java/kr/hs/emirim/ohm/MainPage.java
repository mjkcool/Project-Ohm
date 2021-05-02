package kr.hs.emirim.ohm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    ViewPager viewPager; //카드메뉴 보이는 페이저
    Adapter adapter; //정보값을 넘겨주는 어뎁터
    List<Model> models; //기본 정보값

    Boolean i = true; //코드값으로 구분

    public static final int sub = 1001;
    
    ImageButton goto_btn;  //대기실로 들어가는 버튼
    ImageButton myprofile_btn; //내 프로필로 들어가는 버튼
    ImageButton make_btn; //회의생성으로 들어가는 버튼
    ImageButton reservation; //회의예약으로 들어가는 버튼
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        goto_btn = findViewById(R.id.goto_btn);
        myprofile_btn = findViewById(R.id.my_page);
        make_btn = findViewById(R.id.make_background);
        reservation = findViewById(R.id.reservation_background);

        models = new ArrayList<>();
        viewPager = findViewById(R.id.viewPager);
        
        models.add(new Model(R.drawable.inmain_people, "목적지로 가는 길", "2021.04.22", "5일전"));
        models.add(new Model(R.drawable.inmain_people, "반민초 vs 민초", "2021.04.26", "하루 전"));
        models.add(new Model(R.drawable.inmain_people, "엄마 vs 아빠", "2021.04.01", "26일전"));

        adapter = new Adapter(models, this);
        
        viewPager.setAdapter(adapter);

        viewPager.setClipToPadding(false);
        viewPager.setPadding(0, 0, 100, 0);
        viewPager.setPageMargin(30);

        goto_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(i == true){
                   goto_btn.setImageResource(R.drawable.button1_1);
                   i = false;
                   Intent intent = new Intent(getApplicationContext(),create_meeting_1.class);
                   startActivityForResult(intent,sub);//액티비티 띄우기
               }else{
                   goto_btn.setImageResource(R.drawable.button_1);
                   i = true;
               }
            }
        });
    }
}