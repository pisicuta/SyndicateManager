package database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.SyndicateMember;
/**
 * Created by Paul on 23/02/2016.
 */
public class SyndicateMemberDAO {

    //TODO Get rid of MEMBERID column in SyndicateMember table

    public static final String TAG = "SyndicateMemberDAO";

    // Database fields
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private Context mContext;

    private static final String TABLE_NAME = "syndicate_member";
    private String[] mAllColumns = {DbHelper.KEY_ID,
            DbHelper.LOTTERYCHOICEKEY ,
            DbHelper.MEMBERNAME,
            DbHelper.MEMBERCONTACTINFO,
            DbHelper.BALL1,
            DbHelper.BALL2,
            DbHelper.BALL3,
            DbHelper.BALL4,
            DbHelper.BALL5,
            DbHelper.LUCKYSTAR1,
            DbHelper.LUCKYSTAR2,
            DbHelper.ENDDATE,
            DbHelper.CHANGED_ymdhms};


    public SyndicateMemberDAO(Context context) {
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

    // Adding new syndicate_member
//    public SyndicateMember createSyndicateMember (SyndicateMember _sm) {
     public long createSyndicateMember (SyndicateMember _sm) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.MEMBERNAME, _sm.getMEMBER_NAME());
        values.put(DbHelper.MEMBERCONTACTINFO, _sm.getMEMBER_CONTACT_INFO());
        values.put(DbHelper.LOTTERYCHOICEKEY, _sm.getLOTTERY_CHOICE_KEY());
        values.put(DbHelper.BALL1, _sm.getBall1());
        values.put(DbHelper.BALL2, _sm.getBall2());
        values.put(DbHelper.BALL3, _sm.getBall3());
        values.put(DbHelper.BALL4, _sm.getBall4());
        values.put(DbHelper.BALL5, _sm.getBall5());
        values.put(DbHelper.LUCKYSTAR1, _sm.getLuckyStar1());
        values.put(DbHelper.LUCKYSTAR2, _sm.getLuckyStar2());
         values.put(DbHelper.ENDDATE, _sm.getEndDate());
        values.put(DbHelper.CHANGED_ymdhms, _sm.getCHANGED_ymdhms());

        long insertId = mDatabase
                .insert(DbHelper.SYNDICATEMEMBER_TABLE, null, values);

        if(insertId != -1)
            Toast.makeText(mContext, "New row added, row id: " + insertId, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Something wrong", Toast.LENGTH_SHORT).show();

//        Cursor cursor = mDatabase.query(DbHelper.SYNDICATEMEMBER_TABLE, mAllColumns,
//                DbHelper.KEY_ID + " = " + insertId, null, null,
//                null, null);
//        cursor.moveToFirst();
//        SyndicateMember newSyndicateMember = cursorToSyndicateMember(cursor);
//        cursor.close();
//        return newSyndicateMember;
        return insertId;
    }


    public SyndicateMember createSyndicateMember (int lotteryKey,
                                                  String memberName,
                                                  String memberContactInfo,
                                                  String Ball1,
                                                  String Ball2,
                                                  String Ball3,
                                                  String Ball4,
                                                  String Ball5,
                                                  String LuckyStar1,
                                                  String LuckyStar2,
                                                  String dateTime ) {

        ContentValues values = new ContentValues();
        values.put(DbHelper.MEMBERNAME, memberName);
        values.put(DbHelper.MEMBERCONTACTINFO, memberContactInfo);
        values.put(DbHelper.BALL1, Ball1);
        values.put(DbHelper.BALL1, Ball2);
        values.put(DbHelper.BALL1, Ball3);
        values.put(DbHelper.BALL1, Ball4);
        values.put(DbHelper.BALL1, Ball5);
        values.put(DbHelper.BALL1, LuckyStar1);
        values.put(DbHelper.BALL1, LuckyStar2);
        values.put(DbHelper.CHANGED_ymdhms, dateTime);

        long insertId = mDatabase
                .insert(DbHelper.SYNDICATEMEMBER_TABLE, null, values);

        Cursor cursor = mDatabase.query(DbHelper.SYNDICATEMEMBER_TABLE, mAllColumns,
                DbHelper.KEY_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        SyndicateMember newSyndicateMember = cursorToSyndicateMember(cursor);
        cursor.close();
        return newSyndicateMember;


    }

    public ArrayList<SyndicateMember> getAllSyndicateMembers() {

        ArrayList<SyndicateMember> smList = new ArrayList<SyndicateMember>();

        //Select all query

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    SyndicateMember sm = new SyndicateMember();
                    sm.set_id(Integer.parseInt(cursor.getString(0)));
                    sm.setLOTTERY_CHOICE_KEY(Integer.parseInt(cursor.getString(1)));
                    sm.setMEMBER_NAME(cursor.getString(2));
                    sm.setMEMBER_CONTACT_INFO(cursor.getString(3));
                    sm.setBall1(cursor.getString(4));
                    sm.setBall2(cursor.getString(5));
                    sm.setBall3(cursor.getString(6));
                    sm.setBall4(cursor.getString(7));
                    sm.setBall5(cursor.getString(8));
                    sm.setLuckyStar1(cursor.getString(9));
                    sm.setLuckyStar2(cursor.getString(10));
                    sm.setCHANGED_ymdhms(cursor.getString(11));
                    // Adding contact to list
                    smList.add(sm);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();

        // return contact list
        return smList;

    }

    public void deleteSyndicateMember(SyndicateMember sm) {
        long id = sm.get_id();

        //TODO Make this a log entry
        //System.out.println("The deleted Lottery Choice has the id: " + lotteryChoiceKey);
        mDatabase.delete(DbHelper.SYNDICATEMEMBER_TABLE, DbHelper.KEY_ID + " = " + id, null);

    }

    protected SyndicateMember cursorToSyndicateMember(Cursor cursor) {
        SyndicateMember sm = new SyndicateMember();
//(cursor.getLong(0),cursor.getLong(1),cursor.getString(2),cursor.getString(3));

        sm.set_id(cursor.getLong(0));
        sm.setLOTTERY_CHOICE_KEY(cursor.getLong(1));
        sm.setMEMBER_NAME(cursor.getString(2));
        sm.setMEMBER_CONTACT_INFO(cursor.getString(3));
        sm.setBall1(cursor.getString(4));
        sm.setBall2(cursor.getString(5));
        sm.setBall3(cursor.getString(6));
        sm.setBall4(cursor.getString(7));
        sm.setBall5(cursor.getString(8));
        sm.setLuckyStar1(cursor.getString(9));
        sm.setLuckyStar2(cursor.getString(10));
        sm.setCHANGED_ymdhms(cursor.getString(11));

        return sm;
    }
}
