package android.ua.testapp.dbmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.ua.testapp.dbmanager.models.Record;

import java.util.LinkedList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "records.db";
    private static final String TABLE_RECORDS = "records";
    private static final String KEY_ID = "id";
    private static final String KEY_MESSAGE = "message";
    private static final String[] COLUMNS = {KEY_ID, KEY_MESSAGE};

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_RECORDS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_MESSAGE + " TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS + ";");
        this.onCreate(sqLiteDatabase);
    }

    public void addRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE, record.getMessage());
        db.insert(TABLE_RECORDS, null, values);
        db.close();
    }

    public Record getRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECORDS, COLUMNS, KEY_ID + " = ?",
		        new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        Record record = new Record();
        record.setId(Integer.parseInt(cursor.getString(0)));
        record.setMessage(cursor.getString(1));

        return record;
    }

    public List<Record> getRecords() {

        List<Record> records = new LinkedList<Record>();

        String sql = "SELECT * FROM " + TABLE_RECORDS + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        Record record = null;
        if(cursor.moveToFirst()) {
            do{
                record = new Record();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setMessage(cursor.getString(1));

                records.add(record);
            } while(cursor.moveToNext());
        }

        return records;
    }

    public int updateRecord(Record record) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE, record.getMessage());

        int i = db.update(TABLE_RECORDS, values, KEY_ID + " = ?",
                new String[]{ String.valueOf(record.getId()) });

        db.close();

        return i;
    }

    public void deleteRecord(Record record) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RECORDS, KEY_ID + " = ?",
                new String[]{ String.valueOf(record.getId()) });

        db.close();
    }

    public void deleteRecord(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RECORDS, KEY_ID + " = ?",
                new String[]{ String.valueOf(id) });

        db.close();
    }

    public void truncateDatabase() {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.execSQL("DELETE FROM " + TABLE_RECORDS);
	    db.execSQL("VACUUM");
	    db.close();
    }
}
