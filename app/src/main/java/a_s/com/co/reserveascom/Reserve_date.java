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

    int adult;
    int child;
    int playTime;

    ArrayList<ReserveSpaceVO> list = new ArrayList<>();

    Button reserveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_date);

        reserveBtn = (Button) findViewById(R.id.reserveBtn);

        Intent intent = getIntent();
        adult = intent.getIntExtra("adult",0);
        child = intent.getIntExtra("child", 0);
        playTime = intent.getIntExtra("playTime", 0);

        list = (ArrayList<ReserveSpaceVO>) intent.getSerializableExtra("ReserveSpaceList");

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Reserve_date.this, Week_calendar.class);
                intent.putExtra("adult",adult);
                intent.putExtra("child",child);
                intent.putExtra("playTime",playTime);
                intent.putExtra("ReserveSpaceList",list);
                startActivity(intent);
            }
        });

    }
}
