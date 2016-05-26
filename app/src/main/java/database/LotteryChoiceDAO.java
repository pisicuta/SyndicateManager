package database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;
import java.util.List;

import models.LotteryChoice;
import models.SyndicateMember;

/**
 * Created by Paul on 23/02/2016.
 */
public class LotteryChoiceDAO {

    public static final String TAG = "LotteryChoiceDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DbHelper.KEY_ID,
                                    DbHelper.LOTTERYKEY,
                                    DbHelper.SYNDICATENAME,
                                    DbHelper.CHANGED_ymdhms};


    public LotteryChoiceDAO(Context context) {
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

    public LotteryChoice createLotteryChoice(int lotteryKey, String syndicateName, String dateTime) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.LOTTERYKEY, lotteryKey);
        values.put(DbHelper.SYNDICATENAME, syndicateName);
        values.put(DbHelper.CHANGED_ymdhms, dateTime);

        long insertId = mDatabase
                .insert(DbHelper.LOTTERYCHOICE_TABLE, null, values);

        Cursor cursor = mDatabase.query(DbHelper.LOTTERYCHOICE_TABLE, mAllColumns,
                DbHelper.KEY_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        LotteryChoice newLotteryChoice = cursorToLotteryChoice(cursor);
        cursor.close();
        return newLotteryChoice;
    }

    public void deleteCompany(LotteryChoice lc) {
        long lotteryChoiceKey = lc.getLOTTERY_KEY();
        long id = lc.get_id();
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
            mDatabase.delete(DbHelper.LOTTERYCHOICE_TABLE, DbHelper.KEY_ID + " = " + id, null);
        }

    }



    protected LotteryChoice cursorToLotteryChoice(Cursor cursor) {
        LotteryChoice lc = new LotteryChoice(cursor.getLong(0),cursor.getLong(1),cursor.getString(2),cursor.getString(3));

//        lc.set_id(cursor.getLong(0));
//        lc.setLOTTERY_KEY(cursor.getLong(1));
//        lc.setSYNDICATE_NAME(cursor.getString(2));
//        lc.setCHANGED_ymdhms(cursor.getString(3));

        return lc;
    }
}

