package com.example.project;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydatabase.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Credentials";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }
    public boolean check_credentials(SQLiteDatabase db,String Username,String Password){

        String query = "SELECT * FROM Credentials WHERE Username = ? AND Password = ?";
        String[] selectionArgs = { Username,Password };
        Cursor cursor = db.rawQuery(query, selectionArgs);
        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int passIndex = cursor.getColumnIndex(COLUMN_PASSWORD);
            if (nameIndex == -1 || passIndex == -1) {
                return false;
            }
            String dbName = cursor.getString(nameIndex);
            String dbPass = cursor.getString(passIndex);
            if (dbName.equals(Username) && dbPass.equals( Password)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}
