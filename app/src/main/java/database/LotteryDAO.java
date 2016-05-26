package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import models.Lottery;

/**
 * Created by Paul on 06/03/2016.
 */
public class LotteryDAO {
    /*
        private int _id;
    private int LOTTERY_DAY;
    private String LOTTERY_NAME;
    private String CHANGED_ymdhms;
     */
    public static final String TAG = "LotteryDrawDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DbHelper.KEY_ID,
            DbHelper.LOTTERYDAY,
            DbHelper.LOTTERYNAME,
            DbHelper.CHANGED_ymdhms};

    public LotteryDAO(Context context) {
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

    public Lottery createLottery(String lotteryday,
                                 String lotteryname,
                                 String dateTime) {

        ContentValues values = new ContentValues();
        values.put(DbHelper.LOTTERYDAY,lotteryday);
        values.put(DbHelper.LOTTERYNAME,lotteryname);
        values.put(DbHelper.CHANGED_ymdhms, dateTime);

        long insertId = mDatabase
                .insert(DbHelper.LOTTERY_TABLE, null, values);

        Cursor cursor = mDatabase.query(DbHelper.EURODRAW_TABLE, mAllColumns,
                DbHelper.KEY_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Lottery newLottery = cursorToLottery(cursor);
        cursor.close();
        return newLottery;

    }

    public void deleteLottery(Lottery ed) {
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
            mDatabase.delete(DbHelper.LOTTERY_TABLE, DbHelper.KEY_ID + " = " + id, null);
        }

    }

    protected Lottery cursorToLottery(Cursor cursor) {
        Lottery l = new Lottery();

        l.set_id(cursor.getLong(0));
        l.setLOTTERY_DAY(cursor.getInt(1));
        l.setLOTTERY_NAME(cursor.getString(2));
        l.setCHANGED_ymdhms(cursor.getString(3));

        return l;
    }
}
