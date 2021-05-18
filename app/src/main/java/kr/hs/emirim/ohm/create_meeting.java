package kr.hs.emirim.ohm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_meeting extends AppCompatActivity {

    private String roomcode;

    private EditText room_name; //CM_1
    private TextView text_number; //CM_1

    private EditText meeting_topic; //CM_2
    private TextView text_number2; //CM_2

    private EditText hourText; //CM_3
    private EditText minText; //CM_3
    private EditText secondText;//CM_3
    private CountDownTimer countDownTimer;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("rooms");

    private Button nextbutton3;

    private long time = 0;
    private long tempTime = 0;

    FrameLayout CM_1;
    FrameLayout CM_2;
    FrameLayout CM_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        room_name=findViewById(R.id.room_name);
        text_number=findViewById(R.id.text_number);

        nextbutton3 = (Button) findViewById(R.id.next_button3);

        hourText = findViewById(R.id.hour);//CM_3
        minText = findViewById(R.id.min); //CM_3
        secondText = findViewById(R.id.second);//CM_3

        CM_1 = findViewById(R.id.CM_1);
        CM_2 = findViewById(R.id.CM_2);
        CM_3 = findViewById(R.id.CM_3);

        String roomtime = hourText.toString()+":"+minText.toString()+":"+secondText.toString();

        nextbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCode();
                makeRoom(room_name.toString(), meeting_topic.toString(), roomtime, roomcode);
                Intent intent = new Intent(getApplicationContext(), WaitingRoomActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //EditText 리스너 (입력시 반응)
        room_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                text_number.setText(s.length()+"30");   //글자수 TextView에 보여주기.
            }
        });

        meeting_topic=findViewById(R.id.meeting_topic);
        text_number2=findViewById(R.id.text_number2);

        //EditText 리스너 (입력시 반응)
        meeting_topic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                text_number2.setText(s.length()+"30");   //글자수 TextView에 보여주기.
            }
        });


    }

    public void makeRoom(String roomname, String roomtopic, String roomtime, String code) {
        Room room = new Room(roomname, roomtopic, roomtime);
        ref.child(code).setValue(room);
    }

    public void makeCode(){
        roomcode = String.valueOf((int) (Math.random() * 10000000));
        ref.child(roomcode).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if(String.valueOf(task.getResult().getValue())!=null){
                        makeCode();
                    }
                }
            }
        });
    }
}