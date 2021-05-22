package kr.hs.emirim.ohm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class createmeeting_2 extends AppCompatActivity { //최종 회의생성화면 2

    private EditText meeting_topic;
    private TextView text_number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmeeting2);

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
}