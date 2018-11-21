package a_s.com.co.reserveascom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Week_calendar extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    int adult;
    int child;
    int playTime;
    int total;

    final static int RESERVELIMIT = 9;
    final static int BUSSINESSHOUR = 10;

    Calendar cal = Calendar.getInstance();

    int to_year = cal.get(cal.YEAR);
    int to_month = cal.get(cal.MONTH) + 1;
    int to_day = cal.get(cal.DATE);

    Button days[] = new Button[RESERVELIMIT];

    TableRow clocks[] = new TableRow[BUSSINESSHOUR];

    ArrayList<ReserveSpaceVO> list = new ArrayList<>();

    ArrayList<String> week_days = new ArrayList<>();

    AscomScheduler as;

    int[][] reserveCal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_calendar);

        backPressCloseHandler = new BackPressCloseHandler(this);

       // requestWindowFeature(Window.FEATURE_NO_TITLE); //Title Bar 削除

        fourYear();

        Intent intent = getIntent();
        adult = intent.getIntExtra("adult", 0);
        child = intent.getIntExtra("child", 0);
        playTime = intent.getIntExtra("playTime", 0);
        total = intent.getIntExtra("total",0);

        Log.e("receivedData",playTime+"");

        list = (ArrayList<ReserveSpaceVO>) intent.getSerializableExtra("ReserveSpaceList");

        Log.e("weekDays", week_days.toString() + "");

        final Intent intent2 = new Intent(this, Ind_info.class);

        intent2.putExtra("adult",adult);
        intent2.putExtra("child",child);
        intent2.putExtra("total",total);
        as = new AscomScheduler(list);

        reserveCal = as.subtractDate();

        int btid;

        for (int i = 1; i <= days.length; i++) {
            btid = getResources().getIdentifier(
                    "day" + i, "id", "a_s.com.co.reserveascom");
            days[i - 1] = (Button) findViewById(btid);

            days[i - 1].setText(week_days.get(i - 1));
            days[i - 1].setBackgroundColor(Color.WHITE);

        }

        int rowid;

        for (int i = 1; i <= BUSSINESSHOUR; i++) {
            rowid = getResources().getIdentifier(
                    "hour" + i, "id", "a_s.com.co.reserveascom");
            clocks[i - 1] = (TableRow) findViewById(rowid);
        }

        int newBtn;

        TableRow.LayoutParams pm = new TableRow.LayoutParams();

        pm.width = 50;
        pm.height = ViewGroup.LayoutParams.MATCH_PARENT;

        BackToPresent();

        for (int i = 0; i < BUSSINESSHOUR; i++) {
            for (int j = 0; j < RESERVELIMIT; j++) {
                newBtn = View.generateViewId();
                Button btn = new Button(this);
                btn.setId(newBtn);
                btn.setLayoutParams(pm);
                btn.setText(reserveCal[i][j] + " ");
              

                btn.setBackgroundColor(Color.WHITE);
                btn.setOnClickListener(new KnowIndexOnClickListener(i, j,playTime) {
                    Calendar cal = Calendar.getInstance();
                    protected int to_year = cal.get(cal.YEAR);
                    protected int to_month = cal.get(cal.MONTH) + 1;
                    protected int to_day = cal.get(cal.DATE);


                    @Override
                    public void onClick(View view) {

                        switch (j) {
                            case 0:
                                break;
                            default:
                                to_day = to_day+j;
                                fourYear();
                                break;
                        }

                        intent2.putExtra("playTime",playTime);
                        intent2.putExtra("startTime",i+10);
                        intent2.putExtra("year",to_year);
                        intent2.putExtra("month",to_month);
                        intent2.putExtra("day",to_day);
                        startActivity(intent2);

                    }
                });
                clocks[i].addView(btn);

            }
        }


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }


    private void fourYear() {
        if ((to_year % 4 == 0 && to_year % 100 != 0) || to_year % 400 == 0) {
            for (int i = 0; i < RESERVELIMIT; i++) {
                int temp_month = to_month;
                int temp_day = to_day;
                String temp_date = to_month + "/" + to_day;

                week_days.add(temp_date);

                ++to_day;

                switch (to_month) {
                    case 12:
                        if (to_day == 32) {
                            ++to_year;
                            to_month = 1;
                            to_day = 1;
                        }
                        break;
                    case 11:
                    case 9:
                    case 6:
                    case 4:

                        if (to_day == 31) {
                            to_month = 1;
                            to_day = 1;
                        }

                        break;
                    case 10:
                    case 8:
                    case 7:
                    case 5:
                    case 3:
                    case 1:

                        if (to_day == 32) {
                            to_month = 1;
                            to_day = 1;
                        }

                        break;
                    case 2:
                        if (to_day == 30) {

                            to_month = 1;
                            to_day = 1;
                        }
                        break;
                    default:
                        break;
                }
            }
        } else {
            for (int i = 0; i < RESERVELIMIT; i++) {
                int temp_month = to_month;
                int temp_day = to_day;
                String temp_date = to_month + "/" + to_day;

                week_days.add(temp_date);

                ++to_day;

                switch (to_month) {
                    case 12:
                        if (to_day == 32) {
                            ++to_year;
                            to_month = 1;
                            to_day = 1;
                        }
                        break;
                    case 11:
                    case 9:
                    case 6:
                    case 4:

                        if (to_day == 31) {
                            to_month = 1;
                            to_day = 1;
                        }

                        break;
                    case 10:
                    case 8:
                    case 7:
                    case 5:
                    case 3:
                    case 1:

                        if (to_day == 32) {
                            to_month = 1;
                            to_day = 1;
                        }

                        break;
                    case 2:
                        if (to_day == 29) {

                            to_month = 1;
                            to_day = 1;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public abstract class KnowIndexOnClickListener implements View.OnClickListener {

        protected int i;
        protected int j;
        protected int playTime;

        public KnowIndexOnClickListener(int i, int j, int playTime) {
            this.i = i;
            this.j = j;
            this.playTime = playTime;
        }

        public void fourYear() {
            if ((to_year % 4 == 0 && to_year % 100 != 0) || to_year % 400 == 0) {

                    switch (to_month) {
                        case 12:
                            if (to_day == 32) {
                                ++to_year;
                                to_month = 1;
                                to_day = 1;
                            }
                            break;
                        case 11:
                        case 9:
                        case 6:
                        case 4:

                            if (to_day == 31) {
                                to_month = 1;
                                to_day = 1;
                            }

                            break;
                        case 10:
                        case 8:
                        case 7:
                        case 5:
                        case 3:
                        case 1:

                            if (to_day == 32) {
                                to_month = 1;
                                to_day = 1;
                            }

                            break;
                        case 2:
                            if (to_day == 30) {

                                to_month = 1;
                                to_day = 1;
                            }
                            break;
                        default:
                            break;
                    }

            } else {


                    switch (to_month) {
                        case 12:
                            if (to_day == 32) {
                                ++to_year;
                                to_month = 1;
                                to_day = 1;
                            }
                            break;
                        case 11:
                        case 9:
                        case 6:
                        case 4:

                            if (to_day == 31) {
                                to_month = 1;
                                to_day = 1;
                            }

                            break;
                        case 10:
                        case 8:
                        case 7:
                        case 5:
                        case 3:
                        case 1:

                            if (to_day == 32) {
                                to_month = 1;
                                to_day = 1;
                            }

                            break;
                        case 2:
                            if (to_day == 29) {

                                to_month = 1;
                                to_day = 1;
                            }
                            break;
                        default:
                            break;
                    }

            }


        }
    }

        private void BackToPresent() {
            to_year = cal.get(cal.YEAR);
            to_month = cal.get(cal.MONTH) + 1;
            to_day = cal.get(cal.DATE);
        }

    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

}
