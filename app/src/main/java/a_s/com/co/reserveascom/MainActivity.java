package a_s.com.co.reserveascom;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    Button mainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        mainBtn = (Button) findViewById(R.id.mainBtn);

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,People_time.class);

                startActivity(intent);

            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


    }

    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

}


