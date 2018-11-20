package a_s.com.co.reserveascom;

import android.content.Intent;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class People_time extends AppCompatActivity {

    Button ptBtn;
    int adult = 1;
    int child = 1;
    int playTime = 1;
    MyNumberPicker a ;
    MyNumberPicker c ;
    MyNumberPicker p;
    CheckBox cb;

    ArrayList<ReserveSpaceVO> list = new ArrayList<>();

    URL url;
    HttpURLConnection con;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_time);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ptBtn = (Button) findViewById(R.id.ptBtn);
        a = (MyNumberPicker) findViewById(R.id.adult);
        c = (MyNumberPicker) findViewById(R.id.child);
        p = (MyNumberPicker) findViewById(R.id.playTime);
        cb = (CheckBox)findViewById(R.id.acceptCheck);



        ptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked()==true){

                adult = a.getValue();
                child = c.getValue();
                playTime = p.getValue();

                HashMap<String, String> params = new HashMap<>();
                params.put("adult",adult+"");
                params.put("child",child+"");
                params.put("playTime",playTime+"");

                String param = makeParams(params);

                try{
                    //サーバーのIPアドレス、ポートの番号、Context root、Request Mapping
                    url = new URL("http://192.168.1.156:8888/kidsCafe/mobile/TimeSche");
                } catch (MalformedURLException e){
                    Toast.makeText(People_time.this,"Wrong URL.", Toast.LENGTH_SHORT).show();
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
                        /*os.close();*/

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

                            Log.e(this.getClass().getName(),"arraylist = "+list+"");



                        }

                    }
                }catch (Exception e){
                    Toast.makeText(People_time.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                } finally {
                    if(con != null){
                        con.disconnect();
                    }
                }

                Intent intent = new Intent(People_time.this,Reserve_date.class);
                intent.putExtra("adult",adult);
                intent.putExtra("child",child);
                intent.putExtra("playTime",playTime);
                intent.putExtra("ReserveSpaceList",list);

                startActivity(intent);
                }
                else {
                    Toast.makeText(People_time.this,"ご利用の為に注意事項の同意が必要です。",Toast.LENGTH_LONG).show();
                    cb.requestFocus();
                }

            }

        });
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
        ReserveSpaceVO space = null;


        try {
            jarray = new JSONArray(page);

            for (int i = 0; i < jarray.length(); i++) {

                item = jarray.getJSONObject(i);

                space = new ReserveSpaceVO(item.getString("resDate"),item.getInt("resTime"));

                list.add(space);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


