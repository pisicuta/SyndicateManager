package models;

/**
 * Created by Paul on 21/02/2016.
 */
public class EuroDraw extends LotteryBase {

    private long _id;
    private String DrawDate;
    private long NumberOfWinningRows;
    private String luckyStar1;
    private String luckyStar2;
    private String CHANGED_ymdhms;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDrawDate() {
        return DrawDate;
    }

    public void setDrawDate(String DrawDate) {
        DrawDate = DrawDate;
    }

    public long getNumberOfWinningRows() {
        return NumberOfWinningRows;
    }

    public void setNumberOfWinningRows(long edNumberOfWinningRows) {
        NumberOfWinningRows = edNumberOfWinningRows;
    }

    public String getLuckyStar1() {
        return luckyStar1;
    }

    public void setLuckyStar1(String luckyStar1) {
        this.luckyStar1 = luckyStar1;
    }

    public String getLuckyStar2() {
        return luckyStar2;
    }

    public void setLuckyStar2(String luckyStar2) {
        this.luckyStar2 = luckyStar2;
    }


    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        CHANGED_ymdhms = CHANGED_ymdhms;
    }
}
