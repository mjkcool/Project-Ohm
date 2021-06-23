package kr.hs.emirim.ohm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class ChatingActivity extends AppCompatActivity {

    private TextView tv_hour, tv_minute, tv_second, tv_end;
    int hour, minute, second;

    private RecyclerView recyclerView; //리사이클뷰
    public  RecyclerView.Adapter chatAapter; //리사이클뷰에 들어갈 채팅 어챕터
    private RecyclerView.LayoutManager layoutManager; //리사이클뷰에 들어갈 레이아웃
    private List<ChatingData> chatlist; //채팅 데이터 리스트

    private String nick = "nick";//닉네임 임시설정
    private String mynick; //본인 이름
    private String shownick2; //상대 이름
    private String code;

    private TextView text_title;
    private TextView text_code;

    private EditText chatting_say; //채팅 칠 내용
    private FloatingActionButton chatting_send; // 채팅 보내는 버튼
    private TextView header_main_title1;

    private TextView title_bar;

    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("rooms"); //파이어베이스 값을 불러오는 것
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private ImageButton exit; //나가기 버튼
    private ImageView search; //검색하는 버튼
    private ImageView drawer; //창을 열고 닫을 수 있는 버튼
    private Button goto_voit; //투표하기 창으로 갈 수 있는 버튼

    private SeekBar seekBar1, seekBar2, seekBar3; //투표할 수 있는 전체적 투표바
    private TextView poll_result1, poll_result2, poll_result3; // 투표의 전체 값
    private TextView poll_index1, poll_index2 ,poll_index3; //투표를 하는 후보들
    double count1=1, count2=1, count3=1; //값
    boolean flag1=true, flag2=true, flag3=true; //투표하는 거 클릭시 값 들어가는 것

    private DatabaseReference mDatabase;

    Dialog dilaog01; //다이얼로그

    private ParticipantAdapter participantAdapter;
    private ArrayList<ParticipantVo> participantData;
    private RecyclerView participantView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        nick = user.getDisplayName(); //사용자 닉네임으로 바꿈
        mynick = user.getDisplayName(); // 본인 닉네임 이 변수 지우고 위에 있는 거 써도 됨.

        chatting_send = (FloatingActionButton) findViewById(R.id.send); //메세지 보내는 거 id 선언
        chatting_say = (EditText) findViewById(R.id.editTextTextMultiLine2); //메세지 받는 거 id 선언

        exit = (ImageButton) findViewById(R.id.exit); //채팅방 나가는 것
        search = (ImageView) findViewById(R.id.search_bar); //채팅을 하다가 모르는 거 검색
        drawer = (ImageView) findViewById(R.id.hamberger_bar); //채팅에서 필요한 정보를 보여줄 수 있는 것
        goto_voit = (Button) findViewById(R.id.goto_voit); //투표하기 상세정보 볼 수 있는 창

        seekBar1 = findViewById(R.id.seek_id1); //투표 결과를 알려주는 그래프
        seekBar2 = findViewById(R.id.seek_id2);
        seekBar3 = findViewById(R.id.seek_id3);

        poll_result1 = findViewById(R.id.poll_result1); //투표를 하는 종목
        poll_result2 = findViewById(R.id.poll_result2);
        poll_result3 = findViewById(R.id.poll_result3);

        poll_index1 = findViewById(R.id.poll_index1); //투표를 몇 % 정도 했는지 알려주는 것
        poll_index2 = findViewById(R.id.poll_index2);
        poll_index3 = findViewById(R.id.poll_index3);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_View); //어느정도의 정보만 보일 수 있는 창
        drawerLayout.closeDrawer(Gravity.RIGHT); //오른쪽으로 지정해 오른쪽으로 열고 닫는 것

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_View);
        recyclerView.setHasFixedSize(true); //리사이클뷰의 크기와 넓이를 그대로 지정해주는 것

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager); //리사이클뷰의 레이아웃을 정의

        chatlist = new ArrayList<>(); //전에 있는 채팅을 읽어오는것
        chatAapter = new ChatingAdapter(chatlist, ChatingActivity.this, nick);
        recyclerView.setAdapter(chatAapter);

        dilaog01 = new Dialog(ChatingActivity.this); //다이얼로그 초기화
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀 제거
        dilaog01.setContentView(R.layout.activity_goout_dialog); //레이아웃 연결

        title_bar = findViewById(R.id.title_bar);
        text_title = findViewById(R.id.text_title);
        text_code = findViewById(R.id.text_big_title);
        header_main_title1 = findViewById(R.id.header_main_title1);

        Intent intent = getIntent();
        code = intent.getExtras().getString("code");
        myRef = myRef.child(code).child("chat");
        mDatabase = FirebaseDatabase.getInstance().getReference();


        participantData = new ArrayList<ParticipantVo>();
        participantData.add(new ParticipantVo(mynick));
        participantData.add(new ParticipantVo(shownick2));

        participantAdapter = new ParticipantAdapter(this, participantData);
        participantView.setAdapter(participantAdapter);



        ValueEventListener hourListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hour = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        mDatabase.child("rooms").child(code).child("time").child("hour").addValueEventListener(hourListener);

        
        //참가자 목록 세팅

        
        
        

//        mDatabase.child("rooms").child(code).child("time").child("hour").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//
//                }
//                else {
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                    hour = Integer.parseInt(String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });

        ValueEventListener minListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                minute = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        mDatabase.child("rooms").child(code).child("time").child("min").addValueEventListener(minListener);

//        mDatabase.child("rooms").child(code).child("time").child("min").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//
//                }
//                else {
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                    minute = Integer.parseInt(String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });

        ValueEventListener secListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                second = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        mDatabase.child("rooms").child(code).child("time").child("sec").addValueEventListener(secListener);

//        mDatabase.child("rooms").child(code).child("time").child("sec").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//
//                }
//                else {
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                    second = Integer.parseInt(String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });

        //타이머 관련
        tv_hour = (TextView)findViewById(R.id.hour);
        tv_minute = (TextView)findViewById(R.id.minute);
        tv_second = (TextView)findViewById(R.id.second);
        tv_end = (TextView)findViewById(R.id.end);

        text_code.setText(code);
        checkPeople();
        checkList();
        countDown();

        mDatabase.child("rooms").child(code).child("roomname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());

                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    title_bar.setText(String.valueOf(task.getResult().getValue()));
                    text_title.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });

        drawer.setOnClickListener(new View.OnClickListener() { //drawer창의 이미지을 눌렀을 경우 열리는 코드
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(Gravity.RIGHT)) { //열리는 쪽이 오른쪽 일 경우
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
                if (flag1) {
                    count1++;
                    count2 = 1;
                    count3 = 1;

                    flag1 = false;
                    flag2 = true;
                    flag3 = true;
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
                if (flag2) {
                    count1 = 1;
                    count2++;
                    count3 = 1;

                    flag1 = true;
                    flag2 = false;
                    flag3 = true;
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
                if (flag3) {
                    count1 = 1;
                    count2 = 1;
                    count3++;

                    flag1 = true;
                    flag2 = true;
                    flag3 = false;

                    calculatePercent();
                }

            }
        });

        search.setOnClickListener(new View.OnClickListener() { //검색하기 버튼을 눌렀을경우
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "죄송합니다. 아직 개발하지 못 했습니다. 조금만 기다려주세요 ! ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        goto_voit.setOnClickListener(new View.OnClickListener() { //투표하기 창
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatingActivity.this, voitActivity.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() { // 나가기 창
                                    @Override
                                    public void onClick(View v) {
                                        showDialog01();
                                    }

                                    public void showDialog01() {
                                        dilaog01.show();

                                        Button endBtn = dilaog01.findViewById(R.id.ok_button);
                                        endBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(ChatingActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                                dilaog01.dismiss(); // 다이얼로그 닫기
                                            }
                                        });

                                        dilaog01.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                dilaog01.dismiss();  // 다이얼로그 닫기
                                            }
                                        });
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
                    chatting_say.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"입력 받은 텍스트가 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chatting_say.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                chatting_send.performClick();
                return true;
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
    } //end of onCreate
    
    private void countDown() {
        second = 4;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //카운트 다운
                if(second != 0) {
                    second--;
                    mDatabase.child("rooms").child(code).child("time").child("sec").setValue(second);
                } else if(minute != 0) {
                    second = 60;
                    second--;
                    minute--;
                    mDatabase.child("rooms").child(code).child("time").child("sec").setValue(second);
                    mDatabase.child("rooms").child(code).child("time").child("min").setValue(minute);
                } else if(hour != 0) {
                    second = 60;
                    minute = 60;
                    second--;
                    minute--;
                    hour--;
                    mDatabase.child("rooms").child(code).child("time").child("sec").setValue(second);
                    mDatabase.child("rooms").child(code).child("time").child("min").setValue(minute);
                    mDatabase.child("rooms").child(code).child("time").child("hour").setValue(hour);
                }
                //시간이 한자리수면 앞에 0 추가
                if(second <= 9){
                    tv_second.setText("0" + second);
                } else {
                    tv_second.setText(Integer.toString(second));
                }

                if(minute <= 9){
                    tv_minute.setText("0" + minute);
                } else {
                    tv_minute.setText(Integer.toString(minute));
                }

                if(hour <= 9){
                    tv_hour.setText("0" + hour);
                } else {
                    tv_hour.setText(Integer.toString(hour));
                }

                // 시분초가 모두 0이 될 때 때 회의 종료
                if(hour == 0 && minute == 0 && second == 0) {
                    timer.cancel();//타이머 종료
                    tv_end.setText("회의가 종료되었습니다.");
                    chatting_send.setEnabled(false);
                    mDatabase.child("rooms").child(code).child("open").setValue(0);
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }


        private void calculatePercent () {
            double total = count1 + count2 + count3;
            double percent1 = (count1 / total) * 100;
            double percent2 = (count2 / total) * 100;
            double percent3 = (count3 / total) * 100;

            poll_result1.setText(String.format("%.0f%%", percent1));

            seekBar1.setProgress((int) percent1);
            poll_result2.setText((String.format("%.0f%%", percent2)));

            seekBar2.setProgress((int) percent2);
            poll_result3.setText((String.format("%.0f%%", percent3)));

            seekBar3.setProgress((int) percent3);
        }

        private void checkList () {
            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != user.getDisplayName()) {
                        shownick2 = String.valueOf(dataSnapshot.getValue());
                        Log.d("chat", shownick2);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                }
            };

            mDatabase.child("rooms").child(code).child("member").child("userlist").child("user1").addValueEventListener(postListener);
            mDatabase.child("rooms").child(code).child("member").child("userlist").child("user2").addValueEventListener(postListener);
        }

        private void checkPeople() {
            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    header_main_title1.setText("참여자(" + dataSnapshot.getValue() + ")");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                }
            };

            mDatabase.child("rooms").child(code).child("member").child("Headcount").addValueEventListener(postListener);

        }
    }