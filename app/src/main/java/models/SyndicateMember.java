package models;

/**
 * Created by Paul on 21/02/2016.
 */

public class SyndicateMember extends LotteryBase {
    private long _id;
    private long LOTTERY_CHOICE_KEY;
    private String MEMBER_NAME;
    private String MEMBER_CONTACT_INFO;
    private String LuckyStar1;
    private String LuckyStar2;
    private String EndDate;
    private String CHANGED_ymdhms;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getLOTTERY_CHOICE_KEY() {
        return LOTTERY_CHOICE_KEY;
    }

    public void setLOTTERY_CHOICE_KEY(long LOTTERY_CHOICE_KEY) {
        this.LOTTERY_CHOICE_KEY = LOTTERY_CHOICE_KEY;
    }

    public String getMEMBER_NAME() {
        return MEMBER_NAME;
    }

    public void setMEMBER_NAME(String MEMBER_NAME) {
        this.MEMBER_NAME = MEMBER_NAME;
    }

    public String getMEMBER_CONTACT_INFO() {
        return MEMBER_CONTACT_INFO;
    }

    public void setMEMBER_CONTACT_INFO(String MEMBER_CONTACT_INFO) {
        this.MEMBER_CONTACT_INFO = MEMBER_CONTACT_INFO;
    }

    public String getLuckyStar1() {
        return LuckyStar1;
    }

    public void setLuckyStar1(String LuckyStar1) {
        this.LuckyStar1 = LuckyStar1;
    }

    public String getLuckyStar2() {
        return LuckyStar2;
    }

    public void setLuckyStar2(String LuckyStar2) {
        this.LuckyStar2 = LuckyStar2;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }

    public SyndicateMember(int _id,int MEMBER_ID, int LOTTERY_CHOICE_KEY, String MEMBER_NAME, String MEMBER_CONTACT_INFO, String ball1, String ball2, String ball3, String ball4, String ball5, String LuckyStar1, String LuckyStar2, String CHANGED_ymdhms) {
        this.set_id(_id);
        this.setLOTTERY_CHOICE_KEY(LOTTERY_CHOICE_KEY);
        this.setMEMBER_NAME(MEMBER_NAME);
        this.setMEMBER_CONTACT_INFO(MEMBER_CONTACT_INFO);
        this.setBall1(ball1);
        this.setBall2(ball2);
        this.setBall3(ball3);
        this.setBall4(ball4);
        this.setBall5(ball5);
        this.setLuckyStar1(LuckyStar1);
        this.setLuckyStar2(LuckyStar2);
        this.setCHANGED_ymdhms(CHANGED_ymdhms);
    }

    public SyndicateMember() {

    }
}
