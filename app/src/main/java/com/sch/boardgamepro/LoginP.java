package com.sch.boardgamepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LoginP extends AppCompatActivity {
    ImageView ivM1;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_p);
        ivM1 = (ImageView) findViewById(R.id.imV1);
        Glide.with(LoginP.this).load(R.drawable.bac).into(ivM1);
        findViewById(R.id.btnLogin).setOnClickListener(mClickListener);
        findViewById(R.id.btnJoin).setOnClickListener(mClickListener);
        mediaPlayer = MediaPlayer.create(this, R.raw.startbackmusic);
        //mediaPlayer.setLooping(true); //무한재생
        mediaPlayer.start();
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.btnLogin:
                    Intent ex1 = new Intent(LoginP.this, SelectGame.class);
                    startActivity(ex1);
                    mediaPlayer.stop();
                    break;
                case R.id.btnJoin:
                    Intent ex2 = new Intent(LoginP.this, Viewpager2.class);
                    startActivity(ex2);
                    mediaPlayer.stop();
                    break;
            }
        }
    };
}