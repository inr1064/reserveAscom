package a_s.com.co.reserveascom;

import android.app.Activity;
import android.widget.Toast;

public class BackPressCloseHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;
    public BackPressCloseHandler(Activity context){
        this.activity = context;
    }

    public void onBackPressed(){
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            activity.finishAffinity();
            toast.cancel();
        }
    }

    public void showGuide(){
        toast = Toast.makeText(activity, "Press Back Again to Exit", Toast.LENGTH_SHORT);
        toast.show();
    }
}
