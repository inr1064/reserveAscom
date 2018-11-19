package a_s.com.co.reserveascom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Ticket extends AppCompatActivity {

    TextView resNumTV;
    int resNum;

    Button backToIntro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket);

        Intent intent = getIntent();

        resNum = intent.getIntExtra("resNum",0);

        resNumTV = (TextView)findViewById(R.id.resNumTV);
        backToIntro = (Button)findViewById(R.id.backToIntro);

        resNumTV.setText(Integer.toString(resNum));

        backToIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
