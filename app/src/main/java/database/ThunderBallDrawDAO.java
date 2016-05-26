package database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;
import java.util.List;

import models.LotteryDraw;
import models.ThunderballDraw;

/**
 * Created by Paul on 25/02/2016.
 */
public class ThunderBallDrawDAO {

    public static final String TAG = "ThunderBallDrawDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DbHelper.KEY_ID,
            DbHelper.DRAWDATE,
            DbHelper.WINNINGROWS,
            DbHelper.BALL1,
            DbHelper.BALL2,
            DbHelper.BALL3,
            DbHelper.BALL4,
            DbHelper.BALL5,
            DbHelper.THUNDERBALL,
            DbHelper.CHANGED_ymdhms};


    public ThunderBallDrawDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DbHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public ThunderballDraw createThunderballDraw(String drawDate,
                                         long winningRows,
                                         String ball1,
                                         String ball2,
                                         String ball3,
                                         String ball4,
                                         String ball5,
                                         String thunderball,
                                         String dateTime) {



        ContentValues values = new ContentValues();
        values.put(DbHelper.DRAWDATE, drawDate);
        values.put(DbHelper.WINNINGROWS, winningRows);
        values.put(DbHelper.BALL1, ball1);
        values.put(DbHelper.BALL2, ball2);
        values.put(DbHelper.BALL3, ball3);
        values.put(DbHelper.BALL4, ball4);
        values.put(DbHelper.BALL5, ball5);
        values.put(DbHelper.THUNDERBALL, thunderball);
        values.put(DbHelper.CHANGED_ymdhms, dateTime);

        long insertId = mDatabase
                .insert(DbHelper.EURODRAW_TABLE, null, values);

        Cursor cursor = mDatabase.query(DbHelper.EURODRAW_TABLE, mAllColumns,
                DbHelper.KEY_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        ThunderballDraw newThunderballDraw = cursorToThunderballDraw(cursor);
        cursor.close();
        return newThunderballDraw;
    }

    public void deleteLotteryDraw(LotteryDraw ed) {
        long id = ed.get_id();
        boolean canDelete = false;
        // Check if any SyndicateMembers have this LotteryChoice
        //TODO Create SyndicateMemberDAO
//        SyndicateMemberDAO smDao = new SyndicateMemberDAO(mContext);
//        List<SyndicateMember> listSyndicateMembers = smDao.getSyndicateMembers(lotteryChoiceKey);
//        if (listSyndicateMembers != null && !listSyndicateMembers.isEmpty()) {
//            //Cannot delete cos it's being used
//            }
//        }

        if (canDelete) {
            //TODO Make this a log entry
            //System.out.println("The deleted Lottery Choice has the id: " + lotteryChoiceKey);
            mDatabase.delete(DbHelper.EURODRAW_TABLE, DbHelper.KEY_ID + " = " + id, null);
        }

    }



    protected ThunderballDraw cursorToThunderballDraw(Cursor cursor) {
        ThunderballDraw tb = new ThunderballDraw();

        tb.set_id(cursor.getLong(0));
        tb.setNumberOfWinningRows(cursor.getLong(1));
        tb.setBall1(cursor.getString(2));
        tb.setBall2(cursor.getString(3));
        tb.setBall3(cursor.getString(4));
        tb.setBall4(cursor.getString(5));
        tb.setBall5(cursor.getString(6));
        tb.setThunderBall(cursor.getString(8));
        tb.setCHANGED_ymdhms(cursor.getString(3));

        return tb;
    }
}