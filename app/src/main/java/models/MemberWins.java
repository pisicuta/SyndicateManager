package models;

/**
 * Created by Paul on 21/02/2016.
 */

public class MemberWins {
    private int _id;
    private int MEMBER_ID;
    private String DRAW_DATE;
    private int NUMBER_BALLS_CORRECT;
    private String CHANGED_hmdyms;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getMEMBER_ID() {
        return MEMBER_ID;
    }

    public void setMEMBER_ID(int MEMBER_ID) {
        this.MEMBER_ID = MEMBER_ID;
    }

    public String getDRAW_DATE() {
        return DRAW_DATE;
    }

    public void setDRAW_DATE(String DRAW_DATE) {
        this.DRAW_DATE = DRAW_DATE;
    }

    public int getNUMBER_BALLS_CORRECT() {
        return NUMBER_BALLS_CORRECT;
    }

    public void setNUMBER_BALLS_CORRECT(int NUMBER_BALLS_CORRECT) {
        this.NUMBER_BALLS_CORRECT = NUMBER_BALLS_CORRECT;
    }

    public String getCHANGED_hmdyms() {
        return CHANGED_hmdyms;
    }

    public void setCHANGED_hmdyms(String CHANGED_hmdyms) {
        this.CHANGED_hmdyms = CHANGED_hmdyms;
    }
}

