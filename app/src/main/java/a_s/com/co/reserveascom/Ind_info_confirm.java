package a_s.com.co.reserveascom;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Ind_info_confirm extends AppCompatActivity {

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

    String resName;
    String phone;
    String email;

    URL url;
    HttpURLConnection con;

    TextView adultTV,childTV,playTimeTV,priceTV,dateTV,resNameTV,phoneTV,emailTV;
    Button backwardBtn,forwardBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ind_info_confirm);
        backPressCloseHandler = new BackPressCloseHandler(this);

        Intent intent = getIntent();
        to_year = intent.getIntExtra("year",0);
        to_month = intent.getIntExtra("month",0);
        to_day = intent.getIntExtra("today",0);
        playTime = intent.getIntExtra("playTime",0);
        startTime = intent.getIntExtra("startTime",0);
        adult = intent.getIntExtra("adult",0);
        child = intent.getIntExtra("child",0);
        total = intent.getIntExtra("total", 0);

        resName = intent.getStringExtra("resName");
        phone = intent.getStringExtra("phone");
        email = intent.getStringExtra("email");

        adultTV = (TextView)findViewById(R.id.adultTV);
        childTV = (TextView)findViewById(R.id.childTV);
        playTimeTV = (TextView)findViewById(R.id.playTimeTV);
        priceTV = (TextView)findViewById(R.id.priceTV);
        dateTV = (TextView)findViewById(R.id.dateTV);
        resNameTV = (TextView)findViewById(R.id.resNameTV);
        phoneTV = (TextView)findViewById(R.id.phoneTV);
        emailTV = (TextView)findViewById(R.id.emailTV);

        backwardBtn = (Button)findViewById(R.id.backwardBtn);
        forwardBtn = (Button) findViewById(R.id.forwardBtn);

        adultTV.setText(Integer.toString(adult)+"人");
        childTV.setText(Integer.toString(child)+"人");
        playTimeTV.setText(Integer.toString(playTime)+"時間");
        dateTV.setText(to_year+"年 "+to_month+"月 "+to_day+"日 "+startTime+"時から");
        resNameTV.setText(resName+"様 ");
        phoneTV.setText(phone+"");
        emailTV.setText(email);
        priceTV.setText(Integer.toString(total));

        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> params = new HashMap<>();
                params.put("resAdult",Integer.toString(adult));
                params.put("resChild",Integer.toString(child));
                params.put("useTime",Integer.toString(playTime));
                params.put("resTime",Integer.toString(startTime));
                params.put("date",to_year+"-"+to_month+"-"+to_day);
                params.put("resName",resName);
                params.put("phone",phone);
                params.put("mail",email);


                String param = makeParams(params);

                try{
                    GMailSender sender = new GMailSender("ascomkikikids","a-s.com.co");//MailAccount,password
                    sender.sendMail("KikiKids予約完了のお知らせ",
                            "この度は「KikKids」をご利用いただきまして誠にありがとうございます。\n" +
                                    "\n"+
                                    "ご予約が完了いたしました。下記予約内容をご確認いただき、そのまま予約日時にご来店ください。\n" +
                                    "※ご入店の際には予約番号が必要になります。メモを取るかこのメールを保存してご来店ください。\n"+
                                    "\n"+
                                    "ご予約内容\n"+
                                    "●予約番号 :"+resNum+"\n"+
                                    "●ご来店日時 :"+startTime+"時"+"\n"+
                                    "●利用時間 :"+playTime+"時間"+"\n"+
                                    "●人数 :"+"大人 "+adult+"人 "+"子供"+child+"人"+"\n"+
                                    "●施設料金 :"+total+"円"+"\n"+
                                    "\n"+
                                    "※本メールは送信専用です。このメッセージに返信頂いても回答いたしかねますのでご了承ください。\n"+
                                    "※本メールにお心あたりがない場合、ご不明な点がある場合は下記電話番号までお問い合わせください。\n"+
                                    "\n"+
                                    "KikiKids\n" +
                                    "電話番号 000-0000-0000"
                            ,
                            "ascomkikikids@gmail.com",
                            "ik872000@gmail.com"
                    );
                } catch (Exception e){
                    Log.e("SendMail",e.getMessage(),e);
                }//MailSender


                try{
                    //サーバーのIPアドレス、ポートの番号、Context root、Request Mapping
                    url = new URL("http://192.168.1.156:8888/kidsCafe/mobile/insertReserve");
                } catch (MalformedURLException e){
                    Toast.makeText(Ind_info_confirm.this,"Wrong URL.", Toast.LENGTH_SHORT).show();
                }

                try{
                    con = (HttpURLConnection) url.openConnection();

                    if(con != null){

                        con.setConnectTimeout(10000);	//limit connection time
                        con.setUseCaches(false);		//able/disable Cache
                        con.setRequestMethod("POST"); // select Method
                        con.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset.
                        con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;charset=UTF-8");

                        OutputStream os = con.getOutputStream();
                        os.write(param.getBytes("UTF-8"));
                        os.flush();
                        os.close();

                        if(con.getResponseCode() == HttpURLConnection.HTTP_OK){

                            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                            String line;
                            String page = "";

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

                            /*Log.e(this.getClass().getName(),"arraylist = "+list+"");*/



                        }

                    }
                }catch (Exception e){
                    Toast.makeText(Ind_info_confirm.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                } finally {
                    if(con != null){
                        con.disconnect();
                    }
                }

                Intent intent2 = new Intent(Ind_info_confirm.this,Ticket.class);
                intent2.putExtra("resNum",resNum);
                startActivity(intent2);


                /*Intent intent2 = new Intent(Ind_info_confirm.this,Ticket.class);

                intent2.putExtra("year",to_year);
                intent2.putExtra("month",to_month);
                intent2.putExtra("today",to_day);
                intent2.putExtra("playTime",playTime);
                intent2.putExtra("startTime",startTime);
                intent2.putExtra("adult",adult);
                intent2.putExtra("child",child);
                intent2.putExtra("resName",resName);
                intent2.putExtra("phone",phone);
                intent2.putExtra("email",email);*/



            }
        });


    }

    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
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
        JSONArray jarray = null;
        JSONObject item = null;
        String tempInt = null;


        try {
            jarray = new JSONArray(page);

            for (int i = 0; i < jarray.length(); i++) {

                item = jarray.getJSONObject(i);

                tempInt = item.getString("resNum");

                resNum = Integer.parseInt(tempInt);

                /*space = new ReserveSpaceVO(item.getString("resDate"),item.getInt("resTime"));*/

                /*list.add(space);*/

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
