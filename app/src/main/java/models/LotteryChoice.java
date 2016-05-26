package models;

import java.io.Serializable;
/**
 * Created by Paul on 21/02/2016.
 */

public class LotteryChoice implements Serializable {

    private long _id;
    private long LOTTERY_KEY;
    private String SYNDICATE_NAME;
    private String CHANGED_ymdhms;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getLOTTERY_KEY() {
        return LOTTERY_KEY;
    }

    public void setLOTTERY_KEY(long LOTTERY_KEY) {
        this.LOTTERY_KEY = LOTTERY_KEY;
    }

    public String getSYNDICATE_NAME() {
        return SYNDICATE_NAME;
    }

    public void setSYNDICATE_NAME(String SYNDICATE_NAME) {
        this.SYNDICATE_NAME = SYNDICATE_NAME;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }


    public LotteryChoice(long _id, long LOTTERY_KEY, String SYNDICATE_NAME, String CHANGED_ymdhms) {
        this.set_id(_id);
        this.setLOTTERY_KEY(LOTTERY_KEY);
        this.setSYNDICATE_NAME(SYNDICATE_NAME);
        this.setCHANGED_ymdhms(CHANGED_ymdhms);
    }
}
