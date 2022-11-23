package com.sch.boardgamepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Selectchar1 extends AppCompatActivity {
    ImageView c00, c01,c02,c03,c04,c05,c06,c07,charselect,charselect1,gamestar;

    String charNum = "";
    String charNum1 = "";
    private String gameTurn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_c1);

        c00 = (ImageView) findViewById(R.id.char0);
        c01 = (ImageView) findViewById(R.id.char1);
        c02 = (ImageView) findViewById(R.id.char2);
        c03 = (ImageView) findViewById(R.id.char3);
        c04 = (ImageView) findViewById(R.id.char4);
        c05 = (ImageView) findViewById(R.id.char5);
        c06 = (ImageView) findViewById(R.id.char6);
        c07 = (ImageView) findViewById(R.id.char7);
        charselect= (ImageView) findViewById(R.id.charselect);
        charselect1= (ImageView) findViewById(R.id.charselect1);
        gamestar = (ImageView) findViewById(R.id.gameStar);
        findViewById(R.id.char0).setOnClickListener(mClickListener);
        findViewById(R.id.char1).setOnClickListener(mClickListener);
        findViewById(R.id.char2).setOnClickListener(mClickListener);
        findViewById(R.id.char3).setOnClickListener(mClickListener);
        findViewById(R.id.char4).setOnClickListener(mClickListener);
        findViewById(R.id.char5).setOnClickListener(mClickListener);
        findViewById(R.id.char6).setOnClickListener(mClickListener);
        findViewById(R.id.char7).setOnClickListener(mClickListener);
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

                case R.id.char4:
                    charselect1.setImageResource(R.drawable.ch0);
                    charNum1 = "0";
                    break;

                case R.id.char5:
                    charselect1.setImageResource(R.drawable.ch1);
                    charNum1 = "1";
                    break;

                case R.id.char6:
                    charselect1.setImageResource(R.drawable.ch2);
                    charNum1 = "2";
                    break;

                case R.id.char7:
                    charselect1.setImageResource(R.drawable.ch3);
                    charNum1 = "3";
                    break;

                case R.id.gameStar:

                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    intent.putExtra("charSet", charNum);
                    intent.putExtra("charSet1", charNum1);
                    intent.putExtra("gameTurn", gameTurn);
                    startActivity(intent);
                    finish();
                    break;

            }
        }

    };
    }