package com.example.prerape_session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nameBox;
    EditText passBox;
    Button saveButton;

    DBHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursor;
    long userId=0;
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameBox = findViewById(R.id.editLog);
        passBox = findViewById(R.id.editPass);
        saveButton = findViewById(R.id.btn_Enter);

        sqlHelper = new  DBHelper(this);


    }

    public void save(View view){
        db = sqlHelper.getReadableDatabase();
        cursor = db.rawQuery(String.format("select * from %s where login = '%s' and pass = '%s'",DBHelper.TABLE, nameBox.getText().toString(),passBox.getText().toString()), null);

        if(cursor.moveToFirst()){
            int kk = cursor.getColumnIndex(DBHelper.COLUMN_ID);
            userId = cursor.getInt(kk);
        }
        db.close();

        db = sqlHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( DBHelper.COLUMN_LOG, nameBox.getText().toString());
        cv.put( DBHelper.COLUMN_PASS, passBox.getText().toString());

        if (userId > 0) {
            db.update( DBHelper.TABLE, cv,  DBHelper.COLUMN_ID + "=" + userId, null);
        } else {
            db.insert( DBHelper.TABLE, null, cv);
        }
        db.close();

        db = sqlHelper.getReadableDatabase();
        cursor = db.rawQuery(String.format("select * from %s where login = '%s' and pass = '%s'",DBHelper.TABLE, nameBox.getText().toString(),passBox.getText().toString()), null);

        if(cursor.moveToFirst()){
            int kk = cursor.getColumnIndex(DBHelper.COLUMN_LOG);
            username = cursor.getString(kk);
        }

        goHome();
    }

    private void goHome(){
        db.close();

        Intent intent = new Intent(this, HomePage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("name",username);
        startActivity(intent);
    }
}
