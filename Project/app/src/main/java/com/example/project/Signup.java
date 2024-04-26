package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {
    public Button signup;
    public EditText Username;
    public  EditText Password;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=findViewById(R.id.Signup);
        Username=findViewById(R.id.Username);
        Password=findViewById(R.id.Password);
        MyDbHelper dbHelper = new MyDbHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDb = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(MyDbHelper.COLUMN_NAME, ""+Username.getText().toString());
                values.put(MyDbHelper.COLUMN_PASSWORD, ""+Password.getText().toString());
                long newRowId = mDb.insert(MyDbHelper.TABLE_NAME, null, values);

            }
        });

    }
}