package a_s.com.co.reserveascom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Week_calendar extends AppCompatActivity {

    int adult;
    int child;
    int playTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_calendar);




        Intent intent =getIntent();
        adult = intent.getIntExtra("adult",0);
        child = intent.getIntExtra("child",0);
        playTime = intent.getIntExtra("playTime",0);

        Toast.makeText(Week_calendar.this,adult+" "+child+" "+playTime,Toast.LENGTH_LONG);



    }
}
