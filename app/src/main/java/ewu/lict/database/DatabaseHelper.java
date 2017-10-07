package ewu.lict.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import ewu.lict.model.Student;

/**
 * Created by Faculty on 9/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "lict_android.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableAttributes obj = new TableAttributes();
        String query = obj.tableCreation();

        try {
            db.execSQL(query);
            Log.i("Table Create", "Hoise");
        }catch (SQLException e){
            Log.e("SQL Error", e.toString());
        }
    }

    public boolean insertStudent(Student std){
        boolean flag=false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableAttributes.STUDENT_NAME, std.getUserName());
        values.put(TableAttributes.STUDENT_PASSWORD, std.getPassword());
        values.put(TableAttributes.STUDENT_PHONENO, std.getPhoneNo());
        values.put(TableAttributes.STUDENT_CGPA, std.getCGPA());

        try {
            db.insert(TableAttributes.TABLE_NAME, null, values);
            flag = true;
            Log.i("Insert", "Hoise");
        }catch (SQLException e){
            Log.e("Student Insert Error", e.toString());
        }finally {
            db.close();
        }
        return flag;
    }

    public ArrayList<Student> getAllStudentData(){
        ArrayList<Student> arrayListStd = new ArrayList<Student>();
        String query = "SELECT * FROM "+TableAttributes.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if(cursor.getCount()>0) {
            while (!cursor.isAfterLast()) {
                Student std = new Student();
                std.setId(cursor.getInt(cursor.getColumnIndex(TableAttributes.STUDENT_ID)));
                Log.i("Student_ID", cursor.getInt(cursor.getColumnIndex(TableAttributes.STUDENT_ID))+"");
                std.setUserName(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_NAME)));
                std.setPhoneNo(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_PHONENO)));
                std.setCGPA(cursor.getFloat(cursor.getColumnIndex(TableAttributes.STUDENT_CGPA)));
                arrayListStd.add(std);

                cursor.moveToNext();
            }
        }
        return arrayListStd;
    }

    public int deleteStudent(int student_id) {
        int flag=0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Log.i("StudentID ", student_id+"");
            flag = db.delete(TableAttributes.TABLE_NAME, TableAttributes.STUDENT_ID + "=" + student_id, null);
            Log.i("Delete", "Hoise"+flag);
        }catch (SQLException e){
            Log.e("Delete", e.toString());
        }
        return flag;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
