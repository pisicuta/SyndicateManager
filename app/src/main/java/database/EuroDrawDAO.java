package database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.EuroDraw;


/**
 * Created by Paul on 23/02/2016.
 */
public class EuroDrawDAO {

    public static final String TAG = "EuroDrawDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;
    private static final String TABLE_NAME = "euro_draw";

    private String[] mAllColumns = {DbHelper.KEY_ID,
            DbHelper.DRAWDATE,
            DbHelper.WINNINGROWS,
            DbHelper.BALL1,
            DbHelper.BALL2,
            DbHelper.BALL3,
            DbHelper.BALL4,
            DbHelper.BALL5,
            DbHelper.LUCKYSTAR1,
            DbHelper.LUCKYSTAR2,
            DbHelper.CHANGED_ymdhms};


    public EuroDrawDAO(Context context) {
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

    public EuroDraw createEuroDraw(String drawDate,
    int winningRows,
    String ball1,
    String ball2,
    String ball3,
    String ball4,
    String ball5,
    String luckyStar1,
    String luckyStar2,
    String dateTime) {



        ContentValues values = new ContentValues();
        values.put(DbHelper.DRAWDATE, drawDate);
        values.put(DbHelper.WINNINGROWS, winningRows);
        values.put(DbHelper.BALL1, ball1);
        values.put(DbHelper.BALL2, ball2);
        values.put(DbHelper.BALL3, ball3);
        values.put(DbHelper.BALL4, ball4);
        values.put(DbHelper.BALL5, ball5);
        values.put(DbHelper.LUCKYSTAR1, luckyStar1);
        values.put(DbHelper.LUCKYSTAR2, luckyStar2);
        values.put(DbHelper.CHANGED_ymdhms, dateTime);

        long insertId = mDatabase
                .insert(DbHelper.EURODRAW_TABLE, null, values);

        Cursor cursor = mDatabase.query(DbHelper.EURODRAW_TABLE, mAllColumns,
                DbHelper.KEY_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        EuroDraw newEuroDraw = cursorToEuroDraw(cursor);
        cursor.close();
        return newEuroDraw;
    }

    public void deleteEuroDraw(EuroDraw ed) {
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

    public ArrayList<EuroDraw> getAllEuroDraws() {

        ArrayList<EuroDraw> edList = new ArrayList<EuroDraw>();

        //Select all query

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EuroDraw ed = new EuroDraw();
                ed.set_id(Integer.parseInt(cursor.getString(0)));
                ed.setDrawDate(cursor.getString(1));
                ed.setNumberOfWinningRows(Integer.parseInt(cursor.getString(2)));
                ed.setBall1(cursor.getString(3));
                ed.setBall2(cursor.getString(4));
                ed.setBall3(cursor.getString(5));
                ed.setBall4(cursor.getString(6));
                ed.setBall5(cursor.getString(7));
                ed.setLuckyStar1(cursor.getString(8));
                ed.setLuckyStar2(cursor.getString(9));
                ed.setCHANGED_ymdhms(cursor.getString(10));
                // Adding contact to list
                edList.add(ed);
            } while (cursor.moveToNext());
        }

        // return euro draw list
        return edList;

    }

    protected EuroDraw cursorToEuroDraw(Cursor cursor) {
        EuroDraw lc = new EuroDraw();

        lc.set_id(cursor.getLong(0));
        lc.setDrawDate(cursor.getString(1));
        lc.setNumberOfWinningRows(cursor.getLong(2));
        lc.setBall1(cursor.getString(3));
        lc.setBall2(cursor.getString(4));
        lc.setBall3(cursor.getString(5));
        lc.setBall4(cursor.getString(6));
        lc.setBall5(cursor.getString(7));
        lc.setLuckyStar1(cursor.getString(8));
        lc.setLuckyStar2(cursor.getString(9));
        lc.setCHANGED_ymdhms(cursor.getString(10));

        return lc;
    }
}

