package database;

/**
 * Created by Paul on 23/02/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TAG = "DbHelper";
    private static DbHelper sInstance;

    // Database Name + version
    private static final String DATABASE_NAME = "syndicateManager.db";
    private static final int DATABASE_VERSION = 2;

    // Table names
    public static final String EURODRAW_TABLE = "euro_draw";
    public static final String LOTTERY_TABLE = "lottery";
    public static final String LOTTERYCHOICE_TABLE = "lottery_choice";
    public static final String LOTTERYDRAW_TABLE ="lottery_draw";
    public static final String MEMBERPAID_TABLE = "member_paid";
    public static final String MEMBERWINS_TABLE = "member_wins";
    public static final String SYNDICATEMEMBER_TABLE = "syndicate_member";
    public static final String THUNDERBALLDRAW_TABLE ="thunderball_draw";

    //Column Names
    //Columns common to more than one table

    public static final String KEY_ID = "_id";
    public static final String BALL1 = "ball1";
    public static final String BALL2 = "ball2";
    public static final String BALL3 = "ball3";
    public static final String BALL4 = "ball4";
    public static final String BALL5 = "ball5";
    public static final String LUCKYSTAR1 = "luckystar1";
    public static final String LUCKYSTAR2 = "luckystar2";
    public static final String CHANGED_ymdhms = "changed_ymdhms";
    public static final String DRAWDATE = "DrawDate";
    public static final String WINNINGROWS = "NumberOfWinningRows";
    public static final String MEMBERID = "memberId"; //_id from syndicate_member

    /******************* EURO_DRAW columns *******************/
    //public static final String KEY_ID = "_id";
    //public static final String DRAWDATE = "DrawDate";
    //public static final String WINNINGROWS = "NumberOfWinningRows";
    //public static final String BALL1 = "ball1";
    //public static final String BALL2 = "ball2";
    //public static final String BALL3 = "ball3";
    //public static final String BALL4 = "ball4";
    //public static final String BALL5 = "ball5";
    //public static final String LUCKYSTAR1 = "luckystar1";
    //public static final String LUCKYSTAR2 = "luckystar2";
    //public static final String CHANGED_ymdhms;

    /******************* LOTTERY columns *******************/
    //public static final String KEY_ID = "_id";
    public static final String LOTTERYDAY = "lottery_day";
    public static final String LOTTERYNAME = "lottery_name";
    //public static final String CHANGED_ymdhms;

    /******************* LOTTERYCHOICE columns *******************/
    //public static final String KEY_ID = "_id";
    public static final String LOTTERYKEY = "lottery_key"; // _id from lottery table
    public static final String SYNDICATENAME = "syndicate_name";
    //public static final String CHANGED_ymdhms;

    /******************* LOTTERYDRAW columns *******************/
    //public static final String KEY_ID = "_id";
    //public static final String DRAWDATE = "DrawDate";
    //public static final String WINNINGROWS = "NumberOfWinningRows";
    //public static final String BALL1 = "ball1";
    //public static final String BALL2 = "ball2";
    //public static final String BALL3 = "ball3";
    //public static final String BALL4 = "ball4";
    //public static final String BALL5 = "ball5";
    public static final String BALL6 = "ball6";
    public static final String BONUSBALL = "bonusball";
    //public static final String CHANGED_ymdhms;

    /******************* MEMBERPAID columns *******************/
    //public static final String KEY_ID = "_id";
    //public static final String MEMBERID = "memberId"; //_id from syndicate_member
    //The following will integers used to store 0 (false) and 1 (true) as there is no boolean datatype in sqlite
    public static final String JANUARY = "Jan";
    public static final String FEBRUARY = "Feb";
    public static final String MARCH = "Mar";
    public static final String APRIL = "Apr";
    public static final String MAY = "May";
    public static final String JUNE = "Jun";
    public static final String JULY = "Jul";
    public static final String AUGUST = "Aug";
    public static final String SEPTEMBER = "Sep";
    public static final String OCTOBER = "Oct";
    public static final String NOVEMBER = "Nov";
    public static final String DECEMBER = "Dec";
    //public static final String CHANGED_ymdhms;

    /******************* MEMBERWINS columns *******************/
    //public static final String KEY_ID = "_id";
    //public static final String MEMBERID = "memberId"; //_id from syndicate_member
//    public static final String DRAWDATE = "draw_date";
    public static final String NUMBERBALLSCORRECT = "numberofballscorrect";
    //public static final String CHANGED_hmdyms;

    /******************* SYNDICATEMEMBER columns *******************/
    //public static final String KEY_ID = "_id";
    //public static final String MEMBERID = "memberId"; //_id from syndicate_member
    public static final String LOTTERYCHOICEKEY = "lottery_choice_key";
    public static final String MEMBERNAME = "member_name";
    public static final String MEMBERCONTACTINFO = "member_contact_info";
//    public static final String BALL1 = "ball1";
//    public static final String BALL2 = "ball2";
//    public static final String BALL3 = "ball3";
//    public static final String BALL4 = "ball4";
//    public static final String BALL5 = "ball5";
//    public static final String LUCKYSTAR1 = "LuckyStar1";
//    public static final String LUCKYSTAR2 = "LuckyStar2";
    public static final String ENDDATE = "end_date";
    //public static final String CHANGED_ymdhms;

    /******************* THUNDERBALLDRAW *******************/
    //public static final String KEY_ID = "_id";
    //public static final String DRAWDATE = "DrawDate";
    //public static final String NUMBEROFWINNINGROWS = "NumberOfWinningRows";
    //public static final String BALL1 = "ball1";
    //public static final String BALL2 = "ball2";
    //public static final String BALL3 = "ball3";
    //public static final String BALL4 = "ball4";
    //public static final String BALL5 = "ball5";
    public static final String THUNDERBALL = "thunderball";
    //public static final String CHANGED_ymdhms;

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

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EURODRAW_TABLE);
        db.execSQL(CREATE_TABLE_LOTTERY_TABLE);
        db.execSQL(CREATE_TABLE_LOTTERYCHOICE_TABLE);
        db.execSQL(CREATE_TABLE_LOTTERYDRAW_TABLE);
        db.execSQL(CREATE_TABLE_MEMBERPAID_TABLE);
        db.execSQL(CREATE_TABLE_MEMBERWINS_TABLE);
        db.execSQL(CREATE_TABLE_SYNDICATEMEMBER_TABLE);
        db.execSQL(CREATE_TABLE_THUNDERBALLDRAW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to "+ newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + EURODRAW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOTTERY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOTTERYCHOICE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOTTERYDRAW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEMBERPAID_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEMBERWINS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SYNDICATEMEMBER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + THUNDERBALLDRAW_TABLE);

        // recreate the tables
        onCreate(db);
    }

    public static synchronized DbHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DbHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DbHelper(Context context, String name, CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
}
