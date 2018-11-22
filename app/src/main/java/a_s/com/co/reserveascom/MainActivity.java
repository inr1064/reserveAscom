package a_s.com.co.reserveascom;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;


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

    //intent의 코드 넘버 받는 곳
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //People_time 에서 요청한 resultCode의 값을 받는다.
        switch(resultCode) {
            //resultCode가 1일 경우
            case 1:
               break;
        }
    }
}


