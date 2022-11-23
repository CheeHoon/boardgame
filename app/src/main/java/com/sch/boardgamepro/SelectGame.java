package com.sch.boardgamepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectGame extends AppCompatActivity {
    ImageView vsplay, vsai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_p);

        vsplay = (ImageView) findViewById(R.id.vsplayer);
        vsai = (ImageView) findViewById(R.id.vsai);
        findViewById(R.id.vsplayer).setOnClickListener(mClickListener);
        findViewById(R.id.vsai).setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.vsplayer:
                    Intent ex1 = new Intent(SelectGame.this, Mapselect1.class);
                    startActivity(ex1);

                    break;
                case R.id.vsai:
                    Intent ex2 = new Intent(SelectGame.this, Mapselect.class);
                    startActivity(ex2);

                    break;

            }
        }

    };
    }