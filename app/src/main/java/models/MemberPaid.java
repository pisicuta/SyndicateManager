package models;

/**
 * Created by Paul on 21/02/2016.
 */

public class MemberPaid {
    private int _id;
    private int MEMBER_ID;
    //The following integers are used to store 0 (false) and 1 (true) as there is no boolean datatype in sqlite
    private int JANUARY;
    private int FEBRUARY;
    private int MARCH;
    private int APRIL;
    private int MAY;
    private int JUNE;
    private int JULY;
    private int AUGUST;
    private int SEPTEMBER;
    private int OCTOBER;
    private int NOVEMBER;
    private int DECEMBER;
    private String CHANGED_ymdhms;

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

    public int getJANUARY() {
        return JANUARY;
    }

    public void setJANUARY(int JANUARY) {
        this.JANUARY = JANUARY;
    }

    public int getFEBRUARY() {
        return FEBRUARY;
    }

    public void setFEBRUARY(int FEBRUARY) {
        this.FEBRUARY = FEBRUARY;
    }

    public int getMARCH() {
        return MARCH;
    }

    public void setMARCH(int MARCH) {
        this.MARCH = MARCH;
    }

    public int getAPRIL() {
        return APRIL;
    }

    public void setAPRIL(int APRIL) {
        this.APRIL = APRIL;
    }

    public int getMAY() {
        return MAY;
    }

    public void setMAY(int MAY) {
        this.MAY = MAY;
    }

    public int getJUNE() {
        return JUNE;
    }

    public void setJUNE(int JUNE) {
        this.JUNE = JUNE;
    }

    public int getJULY() {
        return JULY;
    }

    public void setJULY(int JULY) {
        this.JULY = JULY;
    }

    public int getAUGUST() {
        return AUGUST;
    }

    public void setAUGUST(int AUGUST) {
        this.AUGUST = AUGUST;
    }

    public int getSEPTEMBER() {
        return SEPTEMBER;
    }

    public void setSEPTEMBER(int SEPTEMBER) {
        this.SEPTEMBER = SEPTEMBER;
    }

    public int getOCTOBER() {
        return OCTOBER;
    }

    public void setOCTOBER(int OCTOBER) {
        this.OCTOBER = OCTOBER;
    }

    public int getNOVEMBER() {
        return NOVEMBER;
    }

    public void setNOVEMBER(int NOVEMBER) {
        this.NOVEMBER = NOVEMBER;
    }

    public int getDECEMBER() {
        return DECEMBER;
    }

    public void setDECEMBER(int DECEMBER) {
        this.DECEMBER = DECEMBER;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }
}

