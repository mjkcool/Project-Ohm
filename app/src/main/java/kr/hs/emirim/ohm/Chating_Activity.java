package kr.hs.emirim.ohm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class Chating_Activity extends AppCompatActivity {
    private RecyclerView recyclerView; //리사이클뷰
    public RecyclerView.Adapter chatAapter; //리사이클뷰에 들어갈 채팅 어챕터
    private RecyclerView.LayoutManager layoutManager; //리사이클뷰에 들어갈 레이아웃
    private List<Chating_Data> chatlist; //채팅 데이터 리스트

    private String nick = "nick1"; //닉네임 임시설정

    private EditText chatting_say; //채팅 칠 내용
    private Button chatting_send; // 채팅 보내는 버튼

    private DatabaseReference myRef; //파이어베이스 값을 불러오는 것

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatting_send =  findViewById(R.id.chatting_send);
        chatting_say = findViewById(R.id.chatting_say);

        chatting_send.setOnClickListener(new View.OnClickListener() { //버튼을 누를 시
            @Override
            public void onClick(View v) {
                String msg = chatting_say.getText().toString(); //채팅 할 때 사용하는 메세지

                if(msg != null) { //칠 내용이 null값이 아닌경우
                    Chating_Data chat = new Chating_Data(); //주의상황 : 파이어베이스에 다른 클래스의데이터베이스를 넣을거면 전에 있던 값을 삭제
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

        chatlist = new ArrayList<>();
        chatAapter = new Chating_Adapter(chatlist, Chating_Activity.this, nick);
        recyclerView.setAdapter(chatAapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance(); //파이어베이스 값의 읽어 오는 것
        myRef = database.getReference();
        
        
        myRef.addChildEventListener(new ChildEventListener() { //파이어베이스에 있는 것들이 실행할 내용
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("CHATCHAT", snapshot.getValue().toString()); //오류가 나서 값이 제대로 실행 되는지 보기 위한 코드
                Chating_Data chat = snapshot.getValue(Chating_Data.class); //데이터값에 데이터 클래스를 넣어주는 것
                ((Chating_Adapter) chatAapter).addChat(chat); //리사이클뷰 어뎁터에 데이터를 넣어 알려주는 것
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
}