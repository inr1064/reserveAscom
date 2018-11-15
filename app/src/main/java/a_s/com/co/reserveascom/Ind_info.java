package a_s.com.co.reserveascom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Ind_info extends AppCompatActivity {

    int to_year;
    int to_month;
    int to_day;
    int playTime;
    int startTime;
    int adult;
    int child;

    TextView resDateTV,playTimeTV,numberTV;
    EditText resName,phone,mail;
    Button ind_infoBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ind_info);

        Intent intent = getIntent();
        to_year = intent.getIntExtra("year",0);
        to_month = intent.getIntExtra("month",0);
        to_day = intent.getIntExtra("day",0);
        playTime = intent.getIntExtra("playTime",0);
        startTime = intent.getIntExtra("startTime",0);
        adult = intent.getIntExtra("adult",0);
        child = intent.getIntExtra("child",0);

        final Intent intent2 = new Intent(this,Ind_info_confirm.class);
        intent2.putExtra("year",to_year);
        intent2.putExtra("month",to_month);
        intent2.putExtra("today",to_day);
        intent2.putExtra("playTime",playTime);
        intent2.putExtra("startTime",startTime);
        intent2.putExtra("adult",adult);
        intent2.putExtra("child",child);


        resDateTV = (TextView)findViewById(R.id.resDateTV);
        playTimeTV = (TextView)findViewById(R.id.playTimeTV);
        numberTV = (TextView)findViewById(R.id.numberTV);

        resName = (EditText)findViewById(R.id.resName);
        phone = (EditText)findViewById(R.id.phone);
        mail = (EditText)findViewById(R.id.mail);

        ind_infoBtn = (Button)findViewById(R.id.ind_infoBtn);

        resDateTV.setText(to_year+"-"+to_month+"-"+to_day+" "+"start in :"+startTime+"o`clock");
        playTimeTV.setText(playTime+"hour(s)");
        numberTV.setText(adult+"");

        ind_infoBtn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                                String resN = resName.getText().toString();
                                                String ph = phone.getText().toString();
                                                String ma = mail.getText().toString();

                                                intent2.putExtra("resName",resN);
                                                intent2.putExtra("phone",ph);
                                                intent2.putExtra("email",ma);
                                                startActivity(intent2);

                                           }
                                       }

        );

    }
}
