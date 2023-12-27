package com.example.prerape_session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.provider.MediaStore;
public class HomePage extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor cursor;
    TextView txtName;
    DBHelper sqlHelper;
    String username;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        txtName = findViewById(R.id.txtName);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username= extras.getString("name");
        }

        txtName.setText(username);


    }
    public void service(View view) {
        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); КАМЕРА
        //Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS); ТАЙМЕР
        /*Uri webpage = Uri.parse("https://developer.android.com/guide/components/intents-common");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage); СТРАНИЦА */
        /*Uri geoLocation = Uri.parse("geo:47.6,-122.3?z=11");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);  КАРТЫ */
        /*Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE); КОНТАКТЫ*/
        //startActivity(intent);
    }


}