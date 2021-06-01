package kr.hs.emirim.ohm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class ChatingActivity extends AppCompatActivity {

    private RecyclerView recyclerView; //리사이클뷰
    public  RecyclerView.Adapter chatAapter; //리사이클뷰에 들어갈 채팅 어챕터
    private RecyclerView.LayoutManager layoutManager; //리사이클뷰에 들어갈 레이아웃
    private List<ChatingData> chatlist; //채팅 데이터 리스트

    private String nick = "nick2"; //닉네임 임시설정 (애뮬레이터 당 닉네임 바꿔서)

    private EditText chatting_say; //채팅 칠 내용
    private Button chatting_send; // 채팅 보내는 버튼

    private TextView count; //카운트다운

    private DatabaseReference myRef; //파이어베이스 값을 불러오는 것

    private ImageButton exit; //나가기 버튼
    private ImageView search; //검색하는 버튼
    private ImageView drawer; //창을 열고 닫을 수 있는 버튼

    private SeekBar seekBar1, seekBar2, seekBar3; //투표할 수 있는 전체적 투표바
    private TextView poll_result1, poll_result2, poll_result3; // 투표의 전체 값
    private TextView poll_index1, poll_index2 ,poll_index3; //투표를 하는 후보들
    double count1=1, count2=1, count3=1;
    boolean flag1=true, flag2=true, flag3=true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatting_send = (Button) findViewById(R.id.send); //메세지 보내는 거 id 선언
        chatting_say = (EditText) findViewById(R.id.editTextTextMultiLine2); //메세지 받는 거 id 선언

        exit = (ImageButton) findViewById(R.id.exit); //채팅방 나가는 것
        search = (ImageView) findViewById(R.id.search_bar); //채팅을 하다가 모르는 거 검색
        drawer = (ImageView) findViewById(R.id.hamberger_bar); //채팅에서 필요한 정보를 보여줄 수 있는 것

        seekBar1 = findViewById(R.id.seek_id1); //투표 결과를 알려주는 그래프
        seekBar2 = findViewById(R.id.seek_id2);
        seekBar3 = findViewById(R.id.seek_id3);

        poll_result1 = findViewById(R.id.poll_result1); //투표를 하는 종목
        poll_result2 = findViewById(R.id.poll_result2);
        poll_result3 = findViewById(R.id.poll_result3);

        poll_index1 = findViewById(R.id.poll_index1); //투표를 몇 % 정도 했는지 알려주는 것
        poll_index2 = findViewById(R.id.poll_index2);
        poll_index3 = findViewById(R.id.poll_index3);

        count = findViewById(R.id.time_bar); //화면에 보일 시간
        
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_View); //어느정도의 정보만 보일 수 있는 창
        drawerLayout.closeDrawer(Gravity.RIGHT); //오른쪽으로 지정해 오른쪽으로 열고 닫는 것

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_View);
        recyclerView.setHasFixedSize(true); //리사이클뷰의 크기와 넓이를 그대로 지정해주는 것

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager); //리사이클뷰의 레이아웃을 정의

        chatlist = new ArrayList<>(); //전에 있는 채팅을 읽어오는것
        chatAapter = new ChatingAdapter(chatlist, ChatingActivity.this, nick);
        recyclerView.setAdapter(chatAapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance(); //파이어베이스 값의 읽어 오는 것
        myRef = database.getReference();

        drawer.setOnClickListener(new View.OnClickListener() { //drawer창의 이미지을 눌렀을 경우 열리는 코드
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });

        seekBar1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        poll_index1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1){
                    count1++;
                    count2 = 1;
                    count3 = 1;

                    flag1=false;
                    flag2=true;
                    flag3=true;
                    calculatePercent();
                }

            }
        });

        seekBar2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        poll_index2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2){
                    count1 = 1;
                    count2++;
                    count3 = 1;

                    flag1=true;
                    flag2=false;
                    flag3=true;
                    calculatePercent();
                }

            }
        });

        seekBar3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        poll_index3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag3){
                    count1 = 1;
                    count2 = 1;
                    count3++;

                    flag1=true;
                    flag2=true;
                    flag3=false;

                    calculatePercent();
                }

            }
        });

        search.setOnClickListener(new View.OnClickListener() { //검색하기 버튼을 눌렀을경우
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "죄송합니다. 아직 개발하지 못 했습니다. 조금만 기달려주세요 ! ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        exit.setOnClickListener(new View.OnClickListener() { // 나가기 창
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ChatingActivity.this, out_room_modal.class);
                startActivity(intent); //액티비티 이동
            }
        });

        chatting_send.setOnClickListener(new View.OnClickListener() { //채팅을 보내는 버튼을 누를 시
            @Override
            public void onClick(View v) {
                String msg = chatting_say.getText().toString(); //채팅 할 때 사용하는 메세지

                if(msg != null) { //칠 내용이 null값이 아닌경우
                    ChatingData chat = new ChatingData(); //주의상황 : 파이어베이스에 다른 클래스의데이터베이스를 넣을거면 전에 있던 값을 삭제
                    chat.setNickname(nick);
                    chat.setMsg(msg);
                    myRef.push().setValue(chat); //푸쉬를 통해 채팅의 데이터 읽어오기
                }
            }
        });


        myRef.addChildEventListener(new ChildEventListener() { //파이어베이스에 있는 것들이 실행할 내용
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("CHATCHAT", snapshot.getValue().toString()); //오류가 나서 값이 제대로 실행 되는지 보기 위한 코드
                ChatingData chat = snapshot.getValue(ChatingData.class); //데이터값에 데이터 클래스를 넣어주는 것
                chatlist.add(chat);
                recyclerView.setAdapter(chatAapter);
                //recyclerView.invalidate();
                //((Chating_Adapter) chatAapter).addChat(chat); //리사이클뷰 어뎁터에 데이터를 넣어 알려주는 것
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void calculatePercent() {
        double total = count1+count2+count3;
        double percent1 = (count1/total) * 100;
        double percent2 = (count2/total) * 100;
        double percent3 = (count3/total) * 100;

        poll_result1.setText(String.format("%.0f%%", percent1));

        seekBar1.setProgress((int)percent1);
        poll_result2.setText((String.format("%.0f%%",percent2)));

        seekBar2.setProgress((int)percent2);
        poll_result3.setText((String.format("%.0f%%",percent3)));

        seekBar3.setProgress((int)percent3);
    }
}