package kr.hs.emirim.ohm;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class out_room_modal extends AppCompatActivity {

//    private Button end_button;
//    private Button cancel_button;
//
//    Dialog dilaog02; //다이얼로그1
//    Dialog dilaog03; //다이얼로그2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_room_modal);

//        end_button = (Button) findViewById(R.id.end_button);
//        cancel_button = (Button) findViewById(R.id.cancel_button);
//
//        dilaog02 = new Dialog(out_room_modal.this); //다이얼로그 초기화
//        dilaog02.requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀 제거
//        dilaog02.setContentView(R.layout.activity_end_room_dialog); //레이아웃 연결
//
//        dilaog03 = new Dialog(out_room_modal.this); //다이얼로그 초기화
//        dilaog03.requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀 제거
//        dilaog03.setContentView(R.layout.activity_goout_dialog); //레이아웃 연결
//
//        end_button.setOnClickListener(new View.OnClickListener() { // 나가기 창
//            @Override
//            public void onClick(View v) {
//                showDialog02();
//            }

//            public void showDialog02() {
//                dilaog02.show();
//
//                Button endBtn = dilaog02.findViewById(R.id.end_button);
//                endBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(out_room_modal.this, HomeActivity.class);
//                        startActivity(intent);
//                        dilaog02.dismiss(); // 다이얼로그 닫기
//                    }
//                });
//            }
//        });
//
//        cancel_button.setOnClickListener(new View.OnClickListener() { // 나가기 창
//            @Override
//            public void onClick(View v) {
//                showDialog03();
//            }
//
//            public void showDialog03() {
//                dilaog03.show();
//
//                Button CancelBtn = dilaog03.findViewById(R.id.cancel_button);
//                CancelBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dilaog03.dismiss(); // 다이얼로그 닫기
//                    }
//                });
//            }
//        });


    }
}
