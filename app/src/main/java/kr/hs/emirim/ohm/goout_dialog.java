package kr.hs.emirim.ohm;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class goout_dialog extends AppCompatActivity {

    private Button OK_button;
    private Button cancel_button;

    Dialog dilaog03; //다이얼로그2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goout_dialog);

        OK_button = (Button) findViewById(R.id.end_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);

        dilaog03 = new Dialog(goout_dialog.this); //다이얼로그 초기화
        dilaog03.requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀 제거
        dilaog03.setContentView(R.layout.activity_goout_dialog); //레이아웃 연결

        OK_button.setOnClickListener(new View.OnClickListener() { // 나가기 창
            @Override
            public void onClick(View v) {
                showDialog03();
            }

            public void showDialog03() {
                dilaog03.show();

                Button okBtn = dilaog03.findViewById(R.id.ok_button);
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(goout_dialog.this, HomeActivity.class);
                        startActivity(intent);
                        dilaog03.dismiss(); // 다이얼로그 닫기
                    }
                });
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() { // 나가기 창
            @Override
            public void onClick(View v) {
                showDialog03();
            }

            public void showDialog03() {
                dilaog03.show();

                Button CancelBtn = dilaog03.findViewById(R.id.cancel_button);
                CancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dilaog03.dismiss(); // 다이얼로그 닫기
                    }
                });
            }
        });


    }
}
