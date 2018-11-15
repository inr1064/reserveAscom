package a_s.com.co.reserveascom;

import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AscomScheduler {

    private static int col = 9;
    private static int row = 10;

    private int reserveYear;
    private int reserveMonth;
    private int reserveDay;
    private int reserveHour;
    private int[][] reserveCal;



    Calendar cal = Calendar.getInstance();

    int to_year = cal.get(cal.YEAR);
    int to_month = cal.get(cal.MONTH);
    int to_day = cal.get(cal.DATE);

    ArrayList<ReserveSpaceVO> list;

    public AscomScheduler(ArrayList<ReserveSpaceVO> list) {

        this.list = list;

        reserveCal = new int[row][col];

        ArrayList<ReserveDayHourNum> rdhn = new ArrayList<>();


    }

    public int[][] subtractDate(){

        for (int j = 0; j < col ; j++)
        {
            for (int i = 0 ; i < list.size(); i++)
            {
                reserveYear = Integer.parseInt(list.get(i).getresDate().substring(0,4));
                reserveMonth = Integer.parseInt(list.get(i).getresDate().substring(4,6));
                reserveDay = Integer.parseInt(list.get(i).getresDate().substring(6,8));
                reserveHour = list.get(i).getresTime();



                if (reserveDay == to_day)
                {
                    ++reserveCal[reserveHour-row][j];
                }

            }
            fourYear();
        }
        return reserveCal;
    }


    private void fourYear()
    {
        if ((to_year % 4 == 0 && to_year % 100 != 0 ) || to_year % 400 == 0)
        {
                ++to_day;

                switch (to_month)
                {
                    case 12: if (to_day == 32)
                    {
                        ++to_year;
                        to_month = 1;
                        to_day = 1;
                    }
                        break;
                    case 11: case 9: case 6: case 4:

                    if (to_day == 31){
                        to_month = 1;
                        to_day = 1;
                    }

                    break;
                    case 10: case 8: case 7: case 5: case 3: case 1:

                    if (to_day == 32){
                        to_month = 1;
                        to_day = 1;
                    }

                    break;
                    case 2:
                        if(to_day == 30){

                            to_month = 1;
                            to_day = 1;
                        }
                        break;
                    default:
                        break;
                }

        }
        else
        {
                ++to_day;

                switch (to_month)
                {
                    case 12: if (to_day == 32)
                    {
                        ++to_year;
                        to_month = 1;
                        to_day = 1;
                    }
                        break;
                    case 11: case 9: case 6: case 4:

                    if (to_day == 31){
                        to_month = 1;
                        to_day = 1;
                    }

                    break;
                    case 10: case 8: case 7: case 5: case 3: case 1:

                    if (to_day == 32){
                        to_month = 1;
                        to_day = 1;
                    }

                    break;
                    case 2:
                        if(to_day == 29){

                            to_month = 1;
                            to_day = 1;
                        }
                        break;
                    default:
                        break;
                }

        }
    }


    public static int getCol() {
        return col;
    }

    public static void setCol(int col) {
        AscomScheduler.col = col;
    }

    public static int getRow() {
        return row;
    }

    public static void setRow(int row) {
        AscomScheduler.row = row;
    }

    public int[][] getReserveCal() {
        return reserveCal;
    }

    private void setReserveCal(int[][] reserveCal) {
        this.reserveCal = reserveCal;
    }

    @Override
    public String toString() {
        return "AscomScheduler{" +
                "reserveYear=" + reserveYear +
                ", reserveMonth=" + reserveMonth +
                ", reserveDay=" + reserveDay +
                ", reserveHour=" + reserveHour +
                ", reserveCal=" + Arrays.toString(reserveCal) +
                ", cal=" + cal +
                ", to_year=" + to_year +
                ", to_month=" + to_month +
                ", to_day=" + to_day +
                ", list=" + list +
                '}';
    }
}
