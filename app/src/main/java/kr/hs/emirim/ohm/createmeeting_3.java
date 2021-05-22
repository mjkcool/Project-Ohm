package kr.hs.emirim.ohm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.EditText;

public class createmeeting_3 extends AppCompatActivity { //최종 회의생성화면 3

    private EditText hourText;
    private EditText minText;
    private EditText secondText;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmeeting3);

        hourText = findViewById(R.id.hour);
        minText = findViewById(R.id.min);
        secondText = findViewById(R.id.second);
    }
}