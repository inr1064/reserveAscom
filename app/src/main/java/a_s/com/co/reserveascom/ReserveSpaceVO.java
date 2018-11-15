package a_s.com.co.reserveascom;

import java.io.Serializable;

public class ReserveSpaceVO implements Serializable {

    private String resDate;
    private int resTime;


    public ReserveSpaceVO()  {

    }

    public ReserveSpaceVO(String resDate, int resTime) {
        super();
        this.resDate = resDate;
        this.resTime = resTime;

    }

    public String getresDate() {
        return resDate;
    }

    public void setresDate(String resDate) {
        this.resDate = resDate;
    }

    public int getresTime() {
        return resTime;
    }

    public void setresTime(int resTime) {
        this.resTime = resTime;
    }

    @Override
    public String toString() {
        return "resDate=" + resDate + ", resTime=" + resTime;
    }

}
