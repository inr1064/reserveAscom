package a_s.com.co.reserveascom;

public class ReserveDayHourNum {

    private int to_day;
    private int reserveHour;
    private int peoNum;

    public ReserveDayHourNum() {

    }

    public ReserveDayHourNum(int to_day, int reserveHour, int peoNum) {
        this.to_day = to_day;
        this.reserveHour = reserveHour;
        this.peoNum = peoNum;
    }

    public int getTo_day() {
        return to_day;
    }

    public void setTo_day(int to_day) {
        this.to_day = to_day;
    }

    public int getReserveHour() {
        return reserveHour;
    }

    public void setReserveHour(int reserveHour) {
        this.reserveHour = reserveHour;
    }

    public int getPeoNum() {
        return peoNum;
    }

    public void setPeoNum(int peoNum) {
        this.peoNum = peoNum;
    }

    @Override
    public String toString() {
        return "ReserveDayHourNum{" +
                "to_day=" + to_day +
                ", reserveHour=" + reserveHour +
                ", peoNum=" + peoNum +
                '}';
    }
}
