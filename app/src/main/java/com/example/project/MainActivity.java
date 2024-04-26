package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD = 1;
    private static final int REQUEST_CODE_EDIT = 2;


    //add background video
    VideoView videoView;

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

//add background video
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