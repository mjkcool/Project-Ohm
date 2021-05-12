package kr.hs.emirim.ohm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
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

    private DatabaseReference myRef; //파이어베이스 값을 불러오는 것

    private ImageView exit; //나가기 버튼

    private AppBarConfiguration mAppBarConfiguration;
    
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        Toolbar toolbar = findViewById(R.id.toolbar); //toolbar 선언
//        setSupportActionBar(toolbar); //toolbar에 있는 기능들 꺼내기

//        DrawerLayout drawer = findViewById(R.id.drawer_layout); //레이아웃 선언
//        NavigationView navigationView = findViewById(R.id.nav_view); //네이게이션 선언

        chatting_send =  findViewById(R.id.chatting_send); //메세지 보내는 거 id 선언
        chatting_say = findViewById(R.id.chatting_say); //메세지 받는 거 id 선언

        exit = findViewById(R.id.exit); //채팅방 나가는 것

//        NavController navController = Navigation.findNavController(this, R.id.my_recycler_View);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);


        chatting_send.setOnClickListener(new View.OnClickListener() { //버튼을 누를 시
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
        
        recyclerView = findViewById(R.id.my_recycler_View);
        recyclerView.setHasFixedSize(true); //리사이클뷰의 크기와 넓이를 그대로 지정해주는 것

        layoutManager = new LinearLayoutManager(this); 
        recyclerView.setLayoutManager(layoutManager); //리사이클뷰의 레이아웃을 정의

        chatlist = new ArrayList<>(); //전에 있는 채팅을 읽어오는것
        chatAapter = new ChatingAdapter(chatlist, ChatingActivity.this, nick);
        recyclerView.setAdapter(chatAapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance(); //파이어베이스 값의 읽어 오는 것
        myRef = database.getReference();
        
        
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

//    private void setSupportActionBar(Toolbar toolbar) {
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.my_recycler_View);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}