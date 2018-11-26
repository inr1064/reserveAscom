package a_s.com.co.reserveascom;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Ind_info extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    int to_year;
    int to_month;
    int to_day;
    int playTime;
    int startTime;
    int adult;
    int child;
    int total;
    int resNum;

    TextView resDateTV,playTimeTV,numberTV;
    EditText resName,phone,mail;
    Button ind_infoBtn, ind_back_infoBtn;

    URL url;
    HttpURLConnection con;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ind_info);
        backPressCloseHandler = new BackPressCloseHandler(this);

        Intent intent = getIntent();
        to_year = intent.getIntExtra("year",0);
        to_month = intent.getIntExtra("month",0);
        to_day = intent.getIntExtra("day",0);
        playTime = intent.getIntExtra("playTime",0);
        startTime = intent.getIntExtra("startTime",0);
        adult = intent.getIntExtra("adult",0);
        child = intent.getIntExtra("child",0);
        total = intent.getIntExtra("total",0);

        final Intent intent2 = new Intent(this,Ind_info_confirm.class);
        intent2.putExtra("year",to_year);
        intent2.putExtra("month",to_month);
        intent2.putExtra("today",to_day);
        intent2.putExtra("playTime",playTime);
        intent2.putExtra("startTime",startTime);
        intent2.putExtra("adult",adult);
        intent2.putExtra("child",child);
        intent2.putExtra("total",total);


        resDateTV = (TextView)findViewById(R.id.resDateTV);
        playTimeTV = (TextView)findViewById(R.id.playTimeTV);
        numberTV = (TextView)findViewById(R.id.numberTV);

        resName = (EditText)findViewById(R.id.resName);
        phone = (EditText)findViewById(R.id.phone);
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());//連絡先入力の時に自動で‘－‘入力
        mail = (EditText)findViewById(R.id.mail);

        ind_infoBtn = (Button)findViewById(R.id.ind_infoBtn);
        ind_back_infoBtn = (Button) findViewById(R.id.ind_back_infoBtn);

        resDateTV.setText(to_year+"-"+to_month+"-"+to_day+" "+"start in :"+startTime+"o`clock");
        playTimeTV.setText(playTime+"hour(s)");
        numberTV.setText(adult+"");

        ind_infoBtn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                                if (resName.getText().toString().length() == 0){
                                                    Toast.makeText(Ind_info.this,"お名前を入力してください",Toast.LENGTH_SHORT).show();
                                                    resName.requestFocus();
                                                    return;
                                                }
                                                else if (phone.getText().toString().length() == 0){
                                                   Toast.makeText(Ind_info.this,"携帯電話番号を入力してください",Toast.LENGTH_SHORT).show();
                                                   phone.requestFocus();
                                                   return;
                                                }
                                               else  if (mail.getText().toString().length() == 0){
                                                    Toast.makeText(Ind_info.this,"E-mailを入力してください",Toast.LENGTH_SHORT).show();
                                                    mail.requestFocus();
                                                    return;
                                                }
                                                else if (!Pattern.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$", phone.getText().toString())) {
                                                    Toast.makeText(Ind_info.this,"携帯電話番号形式ではありません",Toast.LENGTH_SHORT).show();
                                                    phone.requestFocus();
                                                    return;
                                                }
                                                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches()) {
                                                   Toast.makeText(Ind_info.this,"E-mail形式ではありません",Toast.LENGTH_SHORT).show();
                                                   mail.requestFocus();
                                                   return;
                                               }


                                               try{
                                                   //サーバーのIPアドレス、ポートの番号、Context root、Request Mapping
                                                   url = new URL("http://192.168.1.156:8888/kidsCafe/mobile/getResNum");
                                               } catch (MalformedURLException e){
                                                   Toast.makeText(Ind_info.this,"Wrong URL.", Toast.LENGTH_SHORT).show();
                                               }

                                               try{
                                                   con = (HttpURLConnection) url.openConnection();

                                                   if(con != null){

                                                       con.setConnectTimeout(10000);	//limit connection time
                                                       con.setUseCaches(false);		//able/disable Cache
                                                       con.setRequestMethod("POST"); // select Method
                                                       con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset.
                                                       con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");



                                                       if(con.getResponseCode() == HttpURLConnection.HTTP_OK){

                                                           BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                                                           String line;
                                                           String page = "";
                                                           System.out.print(page);

                                                           while ((line = reader.readLine()) != null){
                                                               page += line;
                                                           }

                                                           try
                                                           {
                                                               jsonParse(page);

                                                           }
                                                           catch (Exception e)
                                                           {

                                                               Log.e(this.getClass().getName(),"problem = "+e+"");
                                                           }




                                                       }

                                                   }
                                               }catch (Exception e){
                                                   Toast.makeText(Ind_info.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                               } finally {
                                                   if(con != null){
                                                       con.disconnect();
                                                   }
                                               }



                                               String resN = resName.getText().toString();
                                                String ph = phone.getText().toString();
                                                String ma = mail.getText().toString();

                                               intent2.putExtra("resName",resN);
                                               intent2.putExtra("phone",ph);
                                               intent2.putExtra("email",ma);
                                               intent2.putExtra("resNum",resNum);

                                               startActivity(intent2);

                                           }
                                       }

        );

        ind_back_infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("adult",adult);
                intent.putExtra("child",child);
                intent.putExtra("playTime",playTime);
                intent.putExtra("total",total);
                setResult(3,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode){

            case 4:
              adult = data.getIntExtra("adult",0);
              child = data.getIntExtra("child",0);
              total = data.getIntExtra("total",0);
              to_year = data.getIntExtra("year",0);
              to_month = data.getIntExtra("month",0);
              to_day = data.getIntExtra("today",0);
              playTime = data.getIntExtra("playTime",0);
              startTime = data.getIntExtra("startTime",0);
              break;

        }
    }

    //サーバーに送るデータをStringに変換
    public String makeParams(HashMap<String,String> params){
        StringBuffer sbParam = new StringBuffer();
        String key = "";
        String value = "";
        boolean isAnd = false;

        for(Map.Entry<String,String> elem : params.entrySet()){
            key = elem.getKey();
            value = elem.getValue();

            if(isAnd){
                sbParam.append("&");
            }

            sbParam.append(key).append("=").append(value);

            if(!isAnd){
                if(params.size() >= 2){
                    isAnd = true;
                }
            }
        }

        return sbParam.toString();
    }

    public void jsonParse(String page){
        JSONObject item = null;
        String tempInt = null;


        try {
            item = new JSONObject(page);


                tempInt = item.getString("resNum");

                resNum = Integer.parseInt(tempInt);
                //Toast.makeText(Ind_info.this, "" + resNum, Toast.LENGTH_SHORT).show();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
