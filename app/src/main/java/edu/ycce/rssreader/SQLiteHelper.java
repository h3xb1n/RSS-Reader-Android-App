package edu.ycce.rssreader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "RSSReader";
    public static final String TABLE_NAME = "SOURCES";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_URL = "URL";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  COLUMN_NAME + " VARCHAR, " + COLUMN_URL +
            " VARCHAR, " + COLUMN_CATEGORY + " VARCHAR);");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'MoneyControl - Latest News'," +
                " 'http://www.moneycontrol.com/rss/latestnews.xml', 'Other')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'MoneyControl - Technology'," +
                        " 'http://www.moneycontrol.com/rss/technology.xml', 'Technology')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'MoneyControl - Business'," +
                " 'http://www.moneycontrol.com/rss/business.xml', 'Business')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'MoneyControl - Entertainment'," +
                " 'http://www.moneycontrol.com/rss/entertainment.xml', 'Entertainment')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'MoneyControl - Sport'," +
                " 'http://www.moneycontrol.com/rss/sports.xml', 'Sport')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'NDTV - Sport'," +
                " 'http://feeds.feedburner.com/ndtvsports-latest', 'Sport')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'NDTV - Business'," +
                " 'http://feeds.feedburner.com/ndtvprofit-latest', 'Business')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'NDTV - Technology'," +
                " 'http://feeds.feedburner.com/gadgets360-latest', 'Technology')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'Lokmat - Technology'," +
                " 'http://lokmat.news18.com/rss/technology-2.xml', 'Technology')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'Lokmat - Sport'," +
                " 'http://lokmat.news18.com/rss/sport.xml', 'Sport')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ( 'FoxNews - Latest News'," +
                " 'http://feeds.foxnews.com/foxnews/latest', 'Other')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertRecord(FeedSourceModel source) {
        database = this.getReadableDatabase();
        database.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_URL + "," + COLUMN_CATEGORY + ") VALUES ('" +
            source.getName() + "','" + source.getUrl() + "','"+ source.getCategory() + "')");
        database.close();
    }

    public ArrayList<FeedSourceModel> getAllRecords(String CATEGORY) {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, COLUMN_CATEGORY + " LIKE ?", new String[] {"%"+CATEGORY+"%"}, null, null, COLUMN_NAME + " ASC");
        ArrayList<FeedSourceModel> sources = new ArrayList<>();
        FeedSourceModel feedSourceModel;
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                feedSourceModel = new FeedSourceModel();
                feedSourceModel.setID(cursor.getString(0));
                feedSourceModel.setName(cursor.getString(1));
                feedSourceModel.setUrl(cursor.getString(2));
                feedSourceModel.setCategory(cursor.getString(3));
                sources.add(feedSourceModel);
            }
        }
        cursor.close();
        database.close();
        return sources;
    }
}
