package kr.hs.emirim.ohm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class create_meeting extends AppCompatActivity {

    private EditText room_name; //CM_1
    private TextView text_number; //CM_1

    private EditText meeting_topic; //CM_2
    private TextView text_number2; //CM_2

    private EditText hourText; //CM_3
    private EditText minText; //CM_3
    private EditText secondText;//CM_3
    private CountDownTimer countDownTimer;

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

        hourText = findViewById(R.id.hour);//CM_3
        minText = findViewById(R.id.min); //CM_3
        secondText = findViewById(R.id.second);//CM_3

        CM_1 = findViewById(R.id.CM_1);
        CM_2 = findViewById(R.id.CM_2);
        CM_3 = findViewById(R.id.CM_3);
    }
}