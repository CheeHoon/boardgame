package com.sch.boardgamepro;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Winer extends AppCompatActivity {
    ImageView ivfire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner);

        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ivfire=(ImageView) findViewById(R.id.ivfire);
        Glide.with(Winer.this).load(R.drawable.firecraf).into(ivfire);
        findViewById(R.id.btnhome).setOnClickListener(mOnClickListener);
    }

        View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnhome:
                        Intent act1 = new Intent(Winer.this, SelectGame.class);
                        startActivity(act1);
                        finish();
                        break;
                }
            }
        };
}