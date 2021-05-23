package kr.hs.emirim.ohm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class createmeeting_1 extends AppCompatActivity { //최종 회의생성화면 1

    private EditText room_name;
    private TextView text_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmeeting1);

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
    }
}