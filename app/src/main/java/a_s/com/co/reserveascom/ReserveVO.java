package a_s.com.co.reserveascom;

//it`s class of reservation infomation
public class ReserveVO {

    private int resNum;
    private String date;
    private int resTime;
    private int useTime;
    private String resName;
    private String mail;
    private String phone;
    private String inTime;
    private int resAdult;
    private int resChild;

    public ReserveVO() {

    }

    public ReserveVO(String date, int resTime, int useTime) {
        super();
        this.date = date;
        this.resTime = resTime;
        this.useTime = useTime;
    }

    public ReserveVO(int useTime, int resAdult, int resChild) {
        super();
        this.useTime = useTime;
        this.resAdult = resAdult;
        this.resChild = resChild;
    }

    public ReserveVO(String date, int resTime, int useTime, String resName, String mail, String phone, String inTime,
                     int resAdult, int resChild) {
        super();
        this.date = date;
        this.resTime = resTime;
        this.useTime = useTime;
        this.resName = resName;
        this.mail = mail;
        this.phone = phone;
        this.inTime = inTime;
        this.resAdult = resAdult;
        this.resChild = resChild;
    }

    public ReserveVO(int resNum, String date, int resTime, int useTime, String resName, String mail, String phone,
                     String inTime, int resAdult, int resChild) {
        super();
        this.resNum = resNum;
        this.date = date;
        this.resTime = resTime;
        this.useTime = useTime;
        this.resName = resName;
        this.mail = mail;
        this.phone = phone;
        this.inTime = inTime;
        this.resAdult = resAdult;
        this.resChild = resChild;
    }

    public int getResNum() {
        return resNum;
    }

    public void setResNum(int resNum) {
        this.resNum = resNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResTime() {
        return resTime;
    }

    public void setResTime(int resTime) {
        this.resTime = resTime;
    }

    public int getUseTime() {
        return useTime;
    }

    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public int getResAdult() {
        return resAdult;
    }

    public void setResAdult(int resAdult) {
        this.resAdult = resAdult;
    }

    public int getResChild() {
        return resChild;
    }

    public void setResChild(int resChild) {
        this.resChild = resChild;
    }

    @Override
    public String toString() {
        return "ReserveVO [resNum=" + resNum + ", date=" + date + ", resTime=" + resTime + ", useTime=" + useTime
                + ", resName=" + resName + ", mail=" + mail + ", phone=" + phone + ", inTime=" + inTime + ", resAdult="
                + resAdult + ", resChild=" + resChild + "]";
    }


}