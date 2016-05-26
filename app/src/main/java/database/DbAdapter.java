package database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Paul on 21/02/2016.
 */

public class DbAdapter {


    // If debug is set true then it will show all Logcat message ***/
    public static final boolean DEBUG = true;
    // Logcat tag
    private static final String LOG_TAG = "DbAdapter";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database path
    // Might not need path
    // private static String DB_PATH = "/data/data/com.paulcurle.syndicatemanager/databases/";

    // Database Name
    private static final String DATABASE_NAME = "syndicateManager";

    // Table names
    private static final String EURODRAW_TABLE = "euro_draw";
    private static final String LOTTERY_TABLE = "lottery";
    private static final String LOTTERYCHOICE_TABLE = "lottery_choice";
    private static final String LOTTERYDRAW_TABLE ="lottery_draw";
    private static final String MEMBERPAID_TABLE = "member_paid";
    private static final String MEMBERWINS_TABLE = "member_wins";
    private static final String SYNDICATEMEMBER_TABLE = "syndicate_member";
    private static final String THUNDERBALLDRAW_TABLE ="lottery_draw";

    //Column Names
    //Columns common to more than one table

    private static final String KEY_ID = "_id";
    private static final String BALL1 = "ball1";
    private static final String BALL2 = "ball2";
    private static final String BALL3 = "ball3";
    private static final String BALL4 = "ball4";
    private static final String BALL5 = "ball5";
    private static final String LUCKYSTAR1 = "luckystar1";
    private static final String LUCKYSTAR2 = "luckystar2";
    private static final String CHANGED_ymdhms = "changed_ymdhms";
    private static final String DRAWDATE = "DrawDate";
    private static final String WINNINGROWS = "NumberOfWinningRows";
    private static final String MEMBERID = "memberId"; //_id from syndicate_member

    /******************* EURO_DRAW columns *******************/
    //private static final String KEY_ID = "_id";
    //private static final String DRAWDATE = "DrawDate";
    //private static final String WINNINGROWS = "NumberOfWinningRows";
    //private static final String BALL1 = "ball1";
    //private static final String BALL2 = "ball2";
    //private static final String BALL3 = "ball3";
    //private static final String BALL4 = "ball4";
    //private static final String BALL5 = "ball5";
    //private static final String LUCKYSTAR1 = "luckystar1";
    //private static final String LUCKYSTAR2 = "luckystar2";
    //private static final String CHANGED_ymdhms;

    /******************* LOTTERY columns *******************/
    //private static final String KEY_ID = "_id";
    private static final String LOTTERYDAY = "lottery_day";
    private static final String LOTTERYNAME = "lottery_name";
    //private static final String CHANGED_ymdhms;

    /******************* LOTTERYCHOICE columns *******************/
    //private static final String KEY_ID = "_id";
    private static final String LOTTERYKEY = "lottery_key"; // _id from lottery table
    private static final String SYNDICATENAME = "syndicate_name";
    //private static final String CHANGED_ymdhms;

    /******************* LOTTERYDRAW columns *******************/
    //private static final String KEY_ID = "_id";
    //private static final String DRAWDATE = "DrawDate";
    //private static final String WINNINGROWS = "NumberOfWinningRows";
    //private static final String BALL1 = "ball1";
    //private static final String BALL2 = "ball2";
    //private static final String BALL3 = "ball3";
    //private static final String BALL4 = "ball4";
    //private static final String BALL5 = "ball5";
    private static final String BALL6 = "ball6";
    private static final String BONUSBALL = "bonusball";
    //private static final String CHANGED_ymdhms;

    /******************* MEMBERPAID columns *******************/
    //private static final String KEY_ID = "_id";
    //private static final String MEMBERID = "memberId"; //_id from syndicate_member
    //The following will integers used to store 0 (false) and 1 (true) as there is no boolean datatype in sqlite
    private static final String JANUARY = "Jan";
    private static final String FEBRUARY = "Feb";
    private static final String MARCH = "Mar";
    private static final String APRIL = "Apr";
    private static final String MAY = "May";
    private static final String JUNE = "Jun";
    private static final String JULY = "Jul";
    private static final String AUGUST = "Aug";
    private static final String SEPTEMBER = "Sep";
    private static final String OCTOBER = "Oct";
    private static final String NOVEMBER = "Nov";
    private static final String DECEMBER = "Dec";
    //private static final String CHANGED_ymdhms;

    /******************* MEMBERWINS columns *******************/
    //private static final String KEY_ID = "_id";
    //private static final String MEMBERID = "memberId"; //_id from syndicate_member
//    private static final String DRAWDATE = "draw_date";
    private static final String NUMBERBALLSCORRECT = "numberofballscorrect";
    //private static final String CHANGED_hmdyms;

    /******************* SYNDICATEMEMBER columns *******************/
    //private static final String KEY_ID = "_id";
    //private static final String MEMBERID = "memberId"; //_id from syndicate_member
    private static final String LOTTERYCHOICEKEY = "lottery_choice_key";
    private static final String MEMBERNAME = "member_name";
    private static final String MEMBERCONTACTINFO = "member_contact_info";
//    private static final String BALL1 = "ball1";
//    private static final String BALL2 = "ball2";
//    private static final String BALL3 = "ball3";
//    private static final String BALL4 = "ball4";
//    private static final String BALL5 = "ball5";
//    private static final String LUCKYSTAR1 = "LuckyStar1";
//    private static final String LUCKYSTAR2 = "LuckyStar2";
    private static final String ENDDATE = "end_date";
//    private static final String CHANGED_ymdhms;

    /******************* THUNDERBALLDRAW *******************/
    //private static final String KEY_ID = "_id";
    //private static final String DRAWDATE = "DrawDate";
    //private static final String NUMBEROFWINNINGROWS = "NumberOfWinningRows";
    //private static final String BALL1 = "ball1";
    //private static final String BALL2 = "ball2";
    //private static final String BALL3 = "ball3";
    //private static final String BALL4 = "ball4";
    //private static final String BALL5 = "ball5";
    private static final String THUNDERBALL = "thunderball";
    //private static final String CHANGED_ymdhms;

    /******************* END OF TABLES *******************/

    /**** Set string array of all tables with comma seperated like USER_TABLE,ABC_TABLE ******/
    private static final String[ ] ALL_TABLES = { EURODRAW_TABLE, LOTTERY_TABLE, LOTTERYCHOICE_TABLE, LOTTERYDRAW_TABLE, MEMBERPAID_TABLE, MEMBERWINS_TABLE, SYNDICATEMEMBER_TABLE, THUNDERBALLDRAW_TABLE };

    /** Create tables */
    // Table Create Statements

    private static final String CREATE_TABLE_EURODRAW_TABLE = "CREATE TABLE " +
            EURODRAW_TABLE + " (" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            DRAWDATE + " TEXT NOT NULL, " +
            WINNINGROWS + " INTEGER NOT NULL, " +
            BALL1 + " TEXT NOT NULL, " +
            BALL2 + " TEXT NOT NULL, " +
            BALL3 + " TEXT NOT NULL, " +
            BALL4 + " TEXT NOT NULL, " +
            BALL5 + " TEXT NOT NULL, " +
            LUCKYSTAR1 + " TEXT NOT NULL, " +
            LUCKYSTAR2 + " TEXT NOT NULL, " +
            CHANGED_ymdhms + " DATETIME" + ");";

    private static final String CREATE_TABLE_LOTTERY_TABLE = "CREATE TABLE " +
            LOTTERY_TABLE + " (" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            LOTTERYDAY + " TEXT NOT NULL, " +
            LOTTERYNAME + " TEXT NOT NULL, " +
            CHANGED_ymdhms + " DATETIME" + ");";

    private static final String CREATE_TABLE_LOTTERYCHOICE_TABLE = "CREATE TABLE " +
            LOTTERYCHOICE_TABLE + " (" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            LOTTERYKEY + " INTEGER NOT NULL, " +
            SYNDICATENAME + " TEXT NOT NULL, " +
            CHANGED_ymdhms + " DATETIME" + ");";

    private static final String CREATE_TABLE_LOTTERYDRAW_TABLE = "CREATE TABLE " +
            LOTTERYDRAW_TABLE + "(" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            DRAWDATE + " TEXT NOT NULL, " +
            WINNINGROWS + " INTEGER NOT NULL, " +
            BALL1 + " TEXT NOT NULL, " +
            BALL2 + " TEXT NOT NULL, " +
            BALL3 + " TEXT NOT NULL, " +
            BALL4 + " TEXT NOT NULL, " +
            BALL5 + " TEXT NOT NULL, " +
            BALL6 + " TEXT NOT NULL, " +
            BONUSBALL + " TEXT NOT NULL, " +
            CHANGED_ymdhms + " DATETIME" + ");";

    private static final String CREATE_TABLE_MEMBERPAID_TABLE = "CREATE TABLE " +
            MEMBERPAID_TABLE + "(" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            MEMBERID + " INTEGER NOT NULL, " +
            JANUARY + " INTEGER NOT NULL, " +
            FEBRUARY + " INTEGER NOT NULL, " +
            MARCH + " INTEGER NOT NULL, " +
            APRIL + " INTEGER NOT NULL, " +
            MAY + " INTEGER NOT NULL, " +
            JUNE + " INTEGER NOT NULL, " +
            JULY + " INTEGER NOT NULL, " +
            AUGUST + " INTEGER NOT NULL, " +
            SEPTEMBER + " INTEGER NOT NULL, " +
            OCTOBER + " INTEGER NOT NULL, " +
            NOVEMBER + " INTEGER NOT NULL, " +
            DECEMBER + " INTEGER NOT NULL, " +
            CHANGED_ymdhms + " DATETIME" + ");";

    private static final String CREATE_TABLE_MEMBERWINS_TABLE = "CREATE TABLE " +
            MEMBERWINS_TABLE + "(" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            MEMBERID + " INTEGER NOT NULL, " +
            DRAWDATE + " TEXT NOT NULL, " +
            NUMBERBALLSCORRECT + " INTEGER NOT NULL, " +
            CHANGED_ymdhms + " DATETIME" + ");";

    private static final String CREATE_TABLE_SYNDICATEMEMBER_TABLE = "CREATE TABLE " +
            SYNDICATEMEMBER_TABLE + "(" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            LOTTERYCHOICEKEY + " INTEGER NOT NULL, " +
            MEMBERNAME + " TEXT NOT NULL, " +
            MEMBERCONTACTINFO + " TEXT NOT NULL, " +
            BALL1 + " TEXT NOT NULL, " +
            BALL2 + " TEXT NOT NULL, " +
            BALL3 + " TEXT NOT NULL, " +
            BALL4 + " TEXT NOT NULL, " +
            BALL5 + " TEXT NOT NULL, " +
            LUCKYSTAR1 + " TEXT NOT NULL, " +
            LUCKYSTAR2 + " TEXT NOT NULL, " +
            ENDDATE + " TEXT NOT NULL, " +
            CHANGED_ymdhms + " DATETIME" + ");";

    private static final String CREATE_TABLE_THUNDERBALLDRAW_TABLE = "CREATE TABLE " +
            THUNDERBALLDRAW_TABLE + "(" + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            DRAWDATE + " TEXT NOT NULL, " +
            WINNINGROWS + " INTEGER NOT NULL, " +
            BALL1 + " TEXT NOT NULL, " +
            BALL2 + " TEXT NOT NULL, " +
            BALL3 + " TEXT NOT NULL, " +
            BALL4 + " TEXT NOT NULL, " +
            BALL5 + " TEXT NOT NULL, " +
            THUNDERBALL + " TEXT NOT NULL, " +
            CHANGED_ymdhms + " DATETIME" + ");";

    /******************** Used to open database in syncronized way ************/
    private static DataBaseHelper DBHelper = null;

    protected DbAdapter() {
    }

    /*********** Initialize database *************/
    public static void init(Context context) {
        if (DBHelper == null) {
            if (DEBUG)
                Log.i("DBAdapter", context.toString());
            DBHelper = new DataBaseHelper(context);
        }
    }

    /********** Main Database creation INNER class ********/
    public static class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if (DEBUG)
                Log.i(LOG_TAG, "new create");
            try {
                db.execSQL(CREATE_TABLE_EURODRAW_TABLE);
                db.execSQL(CREATE_TABLE_LOTTERY_TABLE);
                db.execSQL(CREATE_TABLE_LOTTERYCHOICE_TABLE);
                db.execSQL(CREATE_TABLE_LOTTERYDRAW_TABLE);
                db.execSQL(CREATE_TABLE_MEMBERPAID_TABLE);
                db.execSQL(CREATE_TABLE_MEMBERWINS_TABLE);
                db.execSQL(CREATE_TABLE_SYNDICATEMEMBER_TABLE);
                db.execSQL(CREATE_TABLE_THUNDERBALLDRAW_TABLE);
            } catch (Exception exception) {
                if (DEBUG)
                    Log.i(LOG_TAG, "Exception onCreate() exception");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (DEBUG)
                Log.w(LOG_TAG, "Upgrading database from version" + oldVersion
                        + "to" + newVersion + "...");

            for (String table : ALL_TABLES) {
                db.execSQL("DROP TABLE IF EXISTS " + table);
            }
            onCreate(db);
        }

    } // Inner class closed

    /***** Open database for insert,update,delete in syncronized manner *****/
    public static synchronized SQLiteDatabase open() throws SQLException {
        return DBHelper.getWritableDatabase();
    }
}


