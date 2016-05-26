package models;

/**
 * Created by Paul on 21/02/2016.
 */

public class Lottery{

    private long _id;
    private int LOTTERY_DAY;
    private String LOTTERY_NAME;
    private String CHANGED_ymdhms;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getLOTTERY_DAY() {
        return LOTTERY_DAY;
    }

    public void setLOTTERY_DAY(int LOTTERY_DAY) {
        this.LOTTERY_DAY = LOTTERY_DAY;
    }

    public String getLOTTERY_NAME() {
        return LOTTERY_NAME;
    }

    public void setLOTTERY_NAME(String LOTTERY_NAME) {
        this.LOTTERY_NAME = LOTTERY_NAME;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }
}
