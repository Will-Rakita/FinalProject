package com.example.androidlabs;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class updateDatabase extends SQLiteOpenHelper {
    protected final static String DATABASE_NAME = "myListDB";
    protected final static int VERSION_NUM = 1;

    public final static String TABLE_NAME = "PAGES";
    public final static String COL_DATE = "DATE";
    public final static String COL_HDURL = "HD_URL";
    public final static String COL_EXPLANATION = "PAGE_EXPLANATION";
    public final static String COL_ID = "_id";


    public updateDatabase(Context ctx){super(ctx, DATABASE_NAME, null, VERSION_NUM);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_DATE + " text, "+ COL_EXPLANATION + " text, " + COL_HDURL + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
