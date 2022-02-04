package com.thejunklab.buckets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BUCKET_DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String BUCKET_TABLE = "BUCKET_TABLE";
    private static final String EVENT_TABLE = "EVENT_TABLE";

    public DatabaseManager (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String createBucket = "create table " + BUCKET_TABLE + "(" +
                "ID text, " +
                "NAME text, " +
                "BALANCE real, " +
                "IS_CONSTANT integer, " +
                "INCOME_SHARE real, " +
                "DATE_CREATED text, " +
                "DATE_MODIFIED text)";

        String createEvent = "create table " + EVENT_TABLE + "(" +
                "ID text, " +
                "AMOUNT real, " +
                "MEMO text, " +
                "BUCKET_ID text, " +
                "BUCKET_BALANCE real, " +
                "DATE_CREATED text, " +
                "DATE_MODIFIED text)";

        db.execSQL(createBucket);
        db.execSQL(createEvent);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insertBucket(Bucket bucket) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("ID", bucket.getId());
        row.put("NAME", bucket.getName());
        row.put("BALANCE", bucket.getBalance());
        row.put("IS_CONSTANT", bucket.isConstant);
        row.put("INCOME_SHARE", bucket.getIncomeShare());
        row.put("DATE_CREATED", bucket.getDateCreated());
        row.put("DATE_MODIFIED", bucket.getDateModified());

        db.insert(BUCKET_TABLE, null, row);
        db.close();
    }

    public void deleteBucket(String name) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(BUCKET_TABLE, "NAME = ?", new String[]{name});
        db.close();
    }

    public void update(Bucket bucket) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("NAME", bucket.getName());
        row.put("BALANCE", bucket.getBalance());
        row.put("IS_CONSTANT", bucket.isConstant);
        row.put("INCOME_SHARE", bucket.getIncomeShare());
        row.put("DATE_MODIFIED", bucket.getDateModified());

        db.update(BUCKET_TABLE, row, "NAME =?", new String[]{bucket.getName()});
        db.close();
    }

    public LinkedList<Bucket> selectBucket(String name) {
        SQLiteDatabase db = getWritableDatabase();
        LinkedList<Bucket> bucketLinkedList = new LinkedList<>();

        Cursor cursor = db.query(BUCKET_TABLE, new String[]{"ID", "NAME", "BALANCE", "IS_CONSTANT",
                                "INCOME_SHARE", "DATE_CREATED", "DATE_MODIFIED"},
                                "NAME = ?", new String[]{name},
                                null, null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String bucketName = cursor.getString(1);
            float balance = cursor.getFloat(2);
            int intConstant = cursor.getInt(3);
            float incomeShare = cursor.getFloat(4);
            String dateCreated = cursor.getString(5);
            String dateModified = cursor.getString(6);

            boolean isConstant = intConstant != 0;
            Bucket bucket = new Bucket(id, bucketName, balance, isConstant, incomeShare, dateCreated,
                    dateModified);

            bucketLinkedList.addLast(bucket);
        }

        cursor.close();
        db.close();

        return bucketLinkedList;
    }

    public LinkedList<Bucket> allBuckets() {
        SQLiteDatabase db = getWritableDatabase();
        LinkedList<Bucket> bucketLinkedList = new LinkedList<>();

        Cursor cursor = db.query(BUCKET_TABLE, new String[]{"ID", "NAME", "BALANCE", "IS_CONSTANT",
                        "INCOME_SHARE", "DATE_CREATED", "DATE_MODIFIED"},
                        null, null, null, null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String bucketName = cursor.getString(1);
            float balance = cursor.getFloat(2);
            int intConstant = cursor.getInt(3);
            float incomeShare = cursor.getFloat(4);
            String dateCreated = cursor.getString(5);
            String dateModified = cursor.getString(6);

            boolean isConstant = intConstant != 0;
            Bucket bucket = new Bucket(id, bucketName, balance, isConstant, incomeShare, dateCreated,
                    dateModified);

            bucketLinkedList.addLast(bucket);
        }

        cursor.close();
        db.close();

        return bucketLinkedList;
    }

    public void insertEvent(Event event) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("ID", event.getId());
        row.put("AMOUNT", event.getAmount());
        row.put("MEMO", event.getMemo());
        row.put("BUCKET_ID", event.getBucketId());
        row.put("BUCKET_BALANCE", event.getBucketBalance());
        row.put("DATE_CREATED", event.getDateCreated());
        row.put("DATE_MODIFIED", event.getDateModified());

        db.insert(EVENT_TABLE, null, row);
        db.close();
    }

    public void deleteEvent(String id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(EVENT_TABLE, "ID = ?", new String[]{id});
        db.close();
    }

    public void updateEvent(Event event) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("MEMO", event.getMemo());
        row.put("AMOUNT", event.getAmount());
        row.put("DATE_MODIFIED", event.dateModified);

        db.update(EVENT_TABLE, row, "ID =?", new String[]{event.getId()});
        db.close();
    }

    public LinkedList<Event> selectEvent(String id) {
        SQLiteDatabase db = getWritableDatabase();
        LinkedList<Event> eventLinkedList = new LinkedList<>();

        Cursor cursor = db.query(EVENT_TABLE, new String[]{"ID", "AMOUNT", "MEMO", "BUCKET_ID",
                        "BUCKET_BALANCE", "DATE_CREATED", "DATE_MODIFIED"},
                "ID = ?", new String[]{id},
                null, null, null);

        while (cursor.moveToNext()) {
            String eventId = cursor.getString(0);
            float amount = cursor.getFloat(1);
            String memo = cursor.getString(2);
            String bucketId = cursor.getString(3);
            float bucketBalance = cursor.getFloat(4);
            String dateCreated = cursor.getString(5);
            String dateModified = cursor.getString(6);

            Event event = new Event(eventId, amount, memo, bucketId, bucketBalance, dateCreated,
                    dateModified);

            eventLinkedList.addLast(event);
        }

        cursor.close();
        db.close();

        return eventLinkedList;
    }

    public LinkedList<Event> allEvents() {
        SQLiteDatabase db = getWritableDatabase();
        LinkedList<Event> eventLinkedList = new LinkedList<>();

        Cursor cursor = db.query(EVENT_TABLE, new String[]{"ID", "AMOUNT", "MEMO", "BUCKET_ID",
                        "BUCKET_BALANCE", "DATE_CREATED", "DATE_MODIFIED"},
                        null, null, null, null, null);

        while (cursor.moveToNext()) {
            String eventId = cursor.getString(0);
            float amount = cursor.getFloat(1);
            String memo = cursor.getString(2);
            String bucketId = cursor.getString(3);
            float bucketBalance = cursor.getFloat(4);
            String dateCreated = cursor.getString(5);
            String dateModified = cursor.getString(6);

            Event event = new Event(eventId, amount, memo, bucketId, bucketBalance, dateCreated,
                    dateModified);

            eventLinkedList.addLast(event);
        }

        cursor.close();
        db.close();

        return eventLinkedList;
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(BUCKET_TABLE, null, null);
        db.delete(EVENT_TABLE, null, null);
        db.close();
    }
}
