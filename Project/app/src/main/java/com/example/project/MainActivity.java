package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD = 1;
    private static final int REQUEST_CODE_EDIT = 2;

    private SQLiteDatabase mDb;
    public Button login;
    public Button Signup;
    public EditText Username;
    public EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDbHelper dbHelper = new MyDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        login=findViewById(R.id.login);
        Username= findViewById(R.id.Username);
        Password=findViewById(R.id.Password);
        Signup=findViewById(R.id.Signup);

        Intent intent = new Intent(this, Signup.class);
        Intent next=new Intent(this,MainMenu.class);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.check_credentials(mDb,Username.getText().toString(),Password.getText().toString())){
                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    startActivity(next);
                }
                else{
                    Toast.makeText(MainActivity.this, "You have entered wrong credentials if you don't have an account signup please", Toast.LENGTH_SHORT).show();
                };
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(intent);

            }
        });
    }

}