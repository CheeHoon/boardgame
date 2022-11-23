package com.sch.boardgamepro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class Selectchar extends AppCompatActivity {
    ImageView c00, c01,c02,c03,charselect,gamestar;

    String charNum = "";
    private String gameTurn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_c);

        c00 = (ImageView) findViewById(R.id.char0);
        c01 = (ImageView) findViewById(R.id.char1);
        c02 = (ImageView) findViewById(R.id.char2);
        c03 = (ImageView) findViewById(R.id.char3);
        charselect= (ImageView) findViewById(R.id.charselect);
        gamestar = (ImageView) findViewById(R.id.gameStar);
        findViewById(R.id.char0).setOnClickListener(mClickListener);
        findViewById(R.id.char1).setOnClickListener(mClickListener);
        findViewById(R.id.char2).setOnClickListener(mClickListener);
        findViewById(R.id.char3).setOnClickListener(mClickListener);
        findViewById(R.id.gameStar).setOnClickListener(mClickListener);

        Intent receive_intent = getIntent();
        gameTurn = receive_intent.getStringExtra("gameTurn");
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.char0:
                    charselect.setImageResource(R.drawable.ch0);
                    charNum = "0";

                    break;
                case R.id.char1:
                    charselect.setImageResource(R.drawable.ch1);
                    charNum = "1";

                    break;
                case R.id.char2:
                    charselect.setImageResource(R.drawable.ch2);
                    charNum = "2";

                    break;
                case R.id.char3:
                    charselect.setImageResource(R.drawable.ch3);
                    charNum = "3";

                    break;
                case R.id.gameStar:

                    Intent intent = new Intent(getApplication(), MainActivity3.class);
                    intent.putExtra("charSet", charNum);
                    intent.putExtra("gameTurn", gameTurn);
                    startActivity(intent);
                    finish();
                    break;

            }
        }

    };
    }