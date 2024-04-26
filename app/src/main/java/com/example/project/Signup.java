package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

public class Signup extends AppCompatActivity {
    public Button signup;

    VideoView videoView;
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



        getSupportActionBar().hide();
        videoView=findViewById(R.id.videoview);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid);


        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setLooping(true);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDb = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(MyDbHelper.COLUMN_NAME, ""+Username.getText().toString());
                values.put(MyDbHelper.COLUMN_PASSWORD, ""+Password.getText().toString());
                long newRowId = mDb.insert(MyDbHelper.TABLE_NAME, null, values);
                Intent back = new Intent(Signup.this, MainActivity.class);
                startActivity(back);
                finish();

            }
        });

    }

    //add background video
    @Override
    protected void onResume() {
        videoView.resume();
        super.onResume();
    }
    //add background video
    @Override
    protected void onRestart() {
        videoView.start();
        super.onRestart();
    }
    //add background video
    @Override
    protected void onPause() {
        videoView.suspend();
        super.onPause();
    }
    //add background video

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }
}