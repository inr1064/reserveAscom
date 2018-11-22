package a_s.com.co.reserveascom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by jhs on 2018-11-20.
 */

public class Reserve_date extends AppCompatActivity{

    private BackPressCloseHandler backPressCloseHandler;

    int adult;
    int child;
    int playTime;
    int total;

    ArrayList<ReserveSpaceVO> list = new ArrayList<>();

    Button reserveBtn,reserve_back_Btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_date);

        backPressCloseHandler = new BackPressCloseHandler(this);

        reserveBtn = (Button) findViewById(R.id.reserveBtn);
        reserve_back_Btn = (Button) findViewById(R.id.reserve_back_Btn);

        final Intent intent = getIntent();
        adult = intent.getIntExtra("adult",0);
        child = intent.getIntExtra("child", 0);
        playTime = intent.getIntExtra("playTime", 0);
        total = intent.getIntExtra("total",0);

        list = (ArrayList<ReserveSpaceVO>) intent.getSerializableExtra("ReserveSpaceList");

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Reserve_date.this, Week_calendar.class);
                intent.putExtra("adult",adult);
                intent.putExtra("child",child);
                intent.putExtra("playTime",playTime);
                intent.putExtra("ReserveSpaceList",list);
                intent.putExtra("total",total);
                startActivity(intent);
            }
        });

        reserve_back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                setResult(2,intent); //intent의 코드 넘버 요청
                finish(); //intent를 닫는다.

            }
        });

    }
    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }


}
