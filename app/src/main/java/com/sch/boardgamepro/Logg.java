package com.sch.boardgamepro;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

public class Logg extends AppCompatActivity {
    ImageView ivM1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logg);
        ivM1 = (ImageView) findViewById(R.id.imageView);
        Glide.with(Logg.this).load(R.drawable.log).into(ivM1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Logg.this, LoginP.class);
                startActivity(intent);
                finish();
            }
        },7000);

    }
}