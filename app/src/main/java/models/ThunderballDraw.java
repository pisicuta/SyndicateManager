package models;

/**
 * Created by Paul on 21/02/2016.
 */
public class ThunderballDraw extends LotteryBase {
    private long _id;
    private String ThunderBall;
    private String DrawDate;
    private long NumberOfWinningRows;
    private String CHANGED_ymdhms;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setNumberOfWinningRows(long numberOfWinningRows) {
        NumberOfWinningRows = numberOfWinningRows;
    }

    public String getThunderBall() {
        return ThunderBall;
    }

    public void setBALL6(String ThunderBall) {
        this.ThunderBall = ThunderBall;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }

    public void setThunderBall(String ThunderBall) {
        this.ThunderBall = ThunderBall;
    }

    public long getNumberOfWinningRows() {
        return NumberOfWinningRows;
    }



    public String getDrawDate() {
        return DrawDate;
    }

    public void setDrawDate(String DrawDate) {
        this.DrawDate = DrawDate;
    }
}
