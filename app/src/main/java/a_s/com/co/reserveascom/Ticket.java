package a_s.com.co.reserveascom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Ticket extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    TextView resNumTV;



    int resNum;

    String resName;
    String phone;


    URL url;
    HttpURLConnection con;

    Button backToIntro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket);



        backPressCloseHandler = new BackPressCloseHandler(this);

        Intent intent = getIntent();

        resNum = intent.getIntExtra("resNum",0);

        resNumTV = (TextView)findViewById(R.id.resNumTV);
        resNumTV.setText(Integer.toString(resNum));
        backToIntro = (Button)findViewById(R.id.backToIntro);
        backToIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   Intent intentHome = new Intent(getApplicationContext(),MainActivity.class );
                   intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   intentHome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                   startActivity(intentHome);
                }

        }) ;

    }

    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

}
