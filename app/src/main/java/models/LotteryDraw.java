package models;

/**
 * Created by Paul on 21/02/2016.
 */

public class LotteryDraw extends LotteryBase {
    private long _id;
    private String Ball6;
    private String BonusBall;
    private long NumberOfWinningRows;
    private String DrawDate;
    private String CHANGED_ymdhms;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getBall6() {
        return Ball6;
    }

    public void setBall6(String Ball6) {
        this.Ball6 = Ball6;
    }

    public String getBonusBall() {
        return BonusBall;
    }

    public void setBonusBall(String BonusBall) {
        this.BonusBall = BonusBall;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }

    public long getNumberOfWinningRows() {
        return NumberOfWinningRows;
    }

    public void setNumberOfWinningRows(long numberOfWinningRows) {
        NumberOfWinningRows = numberOfWinningRows;
    }

    public String getDrawDate() {
        return DrawDate;
    }

    public void setDrawDate(String DrawDate) {
        this.DrawDate = DrawDate;
    }


}

