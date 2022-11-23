package com.sch.boardgamepro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Mapselect extends AppCompatActivity {
    ImageView ivMaps,arleft,arright;
    EditText turnT;
    String num ="";
    private String gameTurn = "";
    int turn=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapselect1);
        findViewById(R.id.arleft).setOnClickListener(OnClickListener);
        findViewById(R.id.arright).setOnClickListener(OnClickListener);
        findViewById(R.id.ivdmap).setOnClickListener(OnClickListener);
        findViewById(R.id.ivnext).setOnClickListener(OnClickListener);
        turnT=(EditText) findViewById(R.id.turnT);
        ivMaps=(ImageView) findViewById(R.id.ivMaps);
        arleft=(ImageView) findViewById(R.id.arleft);
        arright=(ImageView) findViewById(R.id.arright);
    }
    Button.OnClickListener OnClickListener= new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.arleft:
                    turnT.setText( String.valueOf((Integer.parseInt(turnT.getText().toString()) -1)));
                    turn=Integer.parseInt(turnT.getText().toString());
                    if(turn==1){
                        arleft.setVisibility(View.INVISIBLE);
                        arright.setVisibility(View.VISIBLE);
                        return;

                    }
                    if(turn==2){
                        arleft.setVisibility(View.VISIBLE);
                        arright.setVisibility(View.VISIBLE);
                        return;
                    }
                    if(turn==3){
                        arleft.setVisibility(View.VISIBLE);
                        arright.setVisibility(View.VISIBLE);
                        return;
                    }
                    if(turn==4){
                        arleft.setVisibility(View.VISIBLE);
                        arright.setVisibility(View.INVISIBLE);
                        return;
                    }
                    break;
                case R.id.arright:
                    turnT.setText( String.valueOf((Integer.parseInt(turnT.getText().toString()) +1)));
                    turn=Integer.parseInt(turnT.getText().toString());
                    if(turn==4){
                        arright.setVisibility(View.INVISIBLE);
                        arleft.setVisibility(View.VISIBLE);
                        return;
                    }
                    if(turn==1){
                        arright.setVisibility(View.VISIBLE);
                        arleft.setVisibility(View.INVISIBLE);
                        return;

                    }
                    if(turn==2){
                        arright.setVisibility(View.VISIBLE);
                        arleft.setVisibility(View.VISIBLE);
                        return;
                    }
                    if(turn==3){
                        arright.setVisibility(View.VISIBLE);
                        arleft.setVisibility(View.VISIBLE);
                        return;
                    }
                    break;
                case R.id.ivdmap:
                    ivMaps.setImageResource(R.drawable.gameboard);
                    break;
                case R.id.ivnext:
                    gameTurn = turnT.getText().toString();
                    Intent intentmap = new Intent(getApplication(),Selectchar.class);
                    intentmap.putExtra("gameTurn",gameTurn);
                    startActivity(intentmap);
                    finish();
                    break;
            }

        }
    };
}