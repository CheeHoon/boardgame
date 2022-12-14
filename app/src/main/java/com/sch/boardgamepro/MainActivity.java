package com.sch.boardgamepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;

import com.bumptech.glide.Glide;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String charSetInfo="";
    String charSetInfo1="";
    int charimage=0;

    private String gameTurn = "";

    public static TextView textView;
    public static ImageView img1, img2,meeple1,meeple2,meeple3,meeple4,meeple5;
    int[] ivnum = new int[41];
    int playerescape =2;
    int play2escape =2;
    int num1,num2;
    int numPlayer =0;
    int numPlayer2 =0;
    int playertotalrotate =0;
    int comtotalrotate =0;
    int[] charictor =new int[4];
    int playchar = 0;
    int play2char = 0;
    ImageView[] playermove = new ImageView[ivnum.length];
    ImageView[] commove = new ImageView[ivnum.length];
    ImageView button,playNum,win,charSlt,ivMent,ivfin,maindice,subdice;
    Animation ani1,ani2;
    TextView tvroundt,roundc;

    private SoundPool soundPool;
    int soundPlay0,soundPlay1,soundPlay2,soundPlay3,soundPlay4,soundPlay5;
    boolean isMyturn=false;
    Handler handler = new Handler();
    final int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    final int dice2[] = {R.drawable.di1, R.drawable.di2, R.drawable.di3,
            R.drawable.di4, R.drawable.di5, R.drawable.di6};
    final int player[] = {R.drawable.ch0, R.drawable.ch1, R.drawable.ch2,R.drawable.ch3};
    final int com[] = {R.drawable.ch0, R.drawable.ch1, R.drawable.ch2,R.drawable.ch3};
    final int cardEvent[] = {R.drawable.card1, R.drawable.card2, R.drawable.card3,R.drawable.card4};
    final int comcardEvent[] = {R.drawable.card1, R.drawable.card2, R.drawable.card3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        charSlt=(ImageView)findViewById(R.id.charSlt);
        tvroundt=(TextView) findViewById(R.id.tvroundt);
        roundc=(TextView) findViewById(R.id.roundc);
        ivMent=(ImageView) findViewById(R.id.ivMent);
        ivfin=(ImageView) findViewById(R.id.ivfin);
        Intent receive_intent = getIntent();
        charSetInfo = receive_intent.getStringExtra("charSet");
        charSetInfo1 = receive_intent.getStringExtra("charSet1");
        gameTurn=receive_intent.getStringExtra("gameTurn");

        tvroundt.setText(gameTurn);
        playertotalrotate=Integer.parseInt(gameTurn)-1;
        comtotalrotate=Integer.parseInt(gameTurn)-1;
        if(charSetInfo.equals("0")){
            playchar=0;
            charSlt.setImageResource(player[0]);
        }
        if(charSetInfo.equals("1")){
            playchar=1;
            charSlt.setImageResource(player[1]);
        }
        if(charSetInfo.equals("2")){
            playchar=2;
            charSlt.setImageResource(player[2]);
        }
        if(charSetInfo.equals("3")){
            playchar=3;
            charSlt.setImageResource(player[3]);
        }
        if(charSetInfo1.equals("0")){
            play2char=0;

        }
        if(charSetInfo1.equals("1")){
            play2char=1;

        }
        if(charSetInfo1.equals("2")){
            play2char=2;

        }
        if(charSetInfo1.equals("3")){
            play2char=3;

        }


        soundPool = new SoundPool(6,AudioManager.STREAM_MUSIC, 0);
        soundPlay0 = soundPool.load(this, R.raw.shake_dice, 1);
        soundPlay1 = soundPool.load(this, R.raw.doub, 1);
        soundPlay2 = soundPool.load(this, R.raw.myturn, 1);
        soundPlay3 = soundPool.load(this, R.raw.winsound, 1);
        soundPlay4 = soundPool.load(this, R.raw.fire, 1);
        soundPlay5 = soundPool.load(this, R.raw.lostsound, 1);
        soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
        soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
        soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
        Handler mHandler = new Handler();
        final int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
        final int dice2[] = {R.drawable.di1, R.drawable.di2, R.drawable.di3,
                R.drawable.di4, R.drawable.di5, R.drawable.di6};

        button = findViewById(R.id.btndice);
        ani1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
        ani2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
        img1 = findViewById(R.id.die1);
        img2 = findViewById(R.id.die2);
        playNum = findViewById(R.id.playNum);
        maindice = findViewById(R.id.ivMainDice);
        subdice = findViewById(R.id.ivSubDice);
        /** ????????????1 ?????? ID*/
        playermove[0] = findViewById(R.id.playerImv0);
        playermove[1] = findViewById(R.id.playerImv1);
        playermove[2] = findViewById(R.id.playerImv2);
        playermove[3] = findViewById(R.id.playerImv3);
        playermove[4] = findViewById(R.id.playerImv4);
        playermove[5] = findViewById(R.id.playerImv5);
        playermove[6] = findViewById(R.id.playerImv6);
        playermove[7] = findViewById(R.id.playerImv7);
        playermove[8] = findViewById(R.id.playerImv8);
        playermove[9] = findViewById(R.id.playerImv9);
        playermove[10] = findViewById(R.id.playerImv10);
        playermove[11] = findViewById(R.id.playerImv11);
        playermove[12] = findViewById(R.id.playerImv12);
        playermove[13] = findViewById(R.id.playerImv13);
        playermove[14] = findViewById(R.id.playerImv14);
        playermove[15] = findViewById(R.id.playerImv15);
        playermove[16] = findViewById(R.id.playerImv16);
        playermove[17] = findViewById(R.id.playerImv17);
        playermove[18] = findViewById(R.id.playerImv18);
        playermove[19] = findViewById(R.id.playerImv19);
        playermove[20] = findViewById(R.id.playerImv20);
        playermove[21] = findViewById(R.id.playerImv21);
        playermove[22] = findViewById(R.id.playerImv22);
        playermove[23] = findViewById(R.id.playerImv23);
        playermove[24] = findViewById(R.id.playerImv24);
        playermove[25] = findViewById(R.id.playerImv25);
        playermove[26] = findViewById(R.id.playerImv26);
        playermove[27] = findViewById(R.id.playerImv27);
        playermove[28] = findViewById(R.id.playerImv28);
        playermove[29] = findViewById(R.id.playerImv29);
        playermove[30] = findViewById(R.id.playerImv30);
        playermove[31] = findViewById(R.id.playerImv31);
        playermove[32] = findViewById(R.id.playerImv32);
        playermove[33] = findViewById(R.id.playerImv33);
        playermove[34] = findViewById(R.id.playerImv34);
        playermove[35] = findViewById(R.id.playerImv35);
        playermove[36] = findViewById(R.id.playerImv36);
        playermove[37] = findViewById(R.id.playerImv37);
        playermove[38] = findViewById(R.id.playerImv38);
        playermove[39] = findViewById(R.id.playerImv39);
        playermove[40] = findViewById(R.id.playerImv40);
        /** ????????????2 ?????? ID*/
        commove[0] = findViewById(R.id.comImv0);
        commove[1] = findViewById(R.id.comImv1);
        commove[2] = findViewById(R.id.comImv2);
        commove[3] = findViewById(R.id.comImv3);
        commove[4] = findViewById(R.id.comImv4);
        commove[5] = findViewById(R.id.comImv5);
        commove[6] = findViewById(R.id.comImv6);
        commove[7] = findViewById(R.id.comImv7);
        commove[8] = findViewById(R.id.comImv8);
        commove[9] = findViewById(R.id.comImv9);
        commove[10] = findViewById(R.id.comImv10);
        commove[11] = findViewById(R.id.comImv11);
        commove[12] = findViewById(R.id.comImv12);
        commove[13] = findViewById(R.id.comImv13);
        commove[14] = findViewById(R.id.comImv14);
        commove[15] = findViewById(R.id.comImv15);
        commove[16] = findViewById(R.id.comImv16);
        commove[17] = findViewById(R.id.comImv17);
        commove[18] = findViewById(R.id.comImv18);
        commove[19] = findViewById(R.id.comImv19);
        commove[20] = findViewById(R.id.comImv20);
        commove[21] = findViewById(R.id.comImv21);
        commove[22] = findViewById(R.id.comImv22);
        commove[23] = findViewById(R.id.comImv23);
        commove[24] = findViewById(R.id.comImv24);
        commove[25] = findViewById(R.id.comImv25);
        commove[26] = findViewById(R.id.comImv26);
        commove[27] = findViewById(R.id.comImv27);
        commove[28] = findViewById(R.id.comImv28);
        commove[29] = findViewById(R.id.comImv29);
        commove[30] = findViewById(R.id.comImv30);
        commove[31] = findViewById(R.id.comImv31);
        commove[32] = findViewById(R.id.comImv32);
        commove[33] = findViewById(R.id.comImv33);
        commove[34] = findViewById(R.id.comImv34);
        commove[35] = findViewById(R.id.comImv35);
        commove[36] = findViewById(R.id.comImv36);
        commove[37] = findViewById(R.id.comImv37);
        commove[38] = findViewById(R.id.comImv38);
        commove[39] = findViewById(R.id.comImv39);
        commove[40] = findViewById(R.id.playerImv40);
        win=  findViewById(R.id.wining);
        ani1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.set);
        ani2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale2);
        roundc.setText( ""+(playertotalrotate+1));

        playermove[0].setImageResource(player[playchar]);
        commove[0].setImageResource(com[play2char]);
        maindice.setImageResource(R.drawable.maindice);
        subdice.setImageResource(R.drawable.subdice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.btndice:
                        roundc.setText( ""+(playertotalrotate+1));
                        ivMent.setImageResource(R.drawable.imgnono);
                        if (!isMyturn) {
                            CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    if (isMyturn) {
                                        playNum.setImageResource(R.drawable.player2);
                                        img1.setImageResource(dice2[5]);
                                        img2.setImageResource(dice2[5]);
                                        charSlt.setImageResource(com[play2char]);
                                    }
                                    if (!isMyturn) {
                                        playNum.setImageResource(R.drawable.player1);
                                        img1.setImageResource(dice[5]);
                                        img2.setImageResource(dice[5]);
                                        charSlt.setImageResource(player[playchar]);
                                    }
                                }
                            }.start();
                            soundPool.play(soundPlay0, 1f, 1f, 0, 0, 1f);
                            Random random = new Random();
                            num1 = random.nextInt(6);
                            Random random1 = new Random();
                            num2 = random1.nextInt(6);
                            img1.setImageResource(dice[num1]);
                            img1.startAnimation(ani1);
                            img2.setImageResource(dice[num2]);
                            img2.startAnimation(ani1);
                            playermove[40].setImageResource(R.drawable.imgnono);
                            CountDownTimer countDownTimer1 = new CountDownTimer(2000, 500) {
                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {


                                    if (num1 == 0&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 38) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 1;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer]);
                                            isMyturn = true;
                                            if (numPlayer == 7 || numPlayer == 13 || numPlayer == 17 || numPlayer == 23 || numPlayer == 31) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                player1Event();
                                            }
                                            if (numPlayer == 10) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                playerescapeevent();

                                            }
                                            return;
                                        }
                                        if (numPlayer >= 39) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 1;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Winer.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            return;
                                        }
                                    }
                                    if (num1 == 1&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 37) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 2;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer]);
                                            isMyturn = true;
                                            if (numPlayer == 7 || numPlayer == 13 || numPlayer == 17 || numPlayer == 23 || numPlayer == 31) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                player1Event();
                                            }
                                            if (numPlayer == 10) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                playerescapeevent();

                                            }
                                            return;
                                        }
                                        if (numPlayer >= 38) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 2;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Winer.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            return;
                                        }
                                    }
                                    if (num1 == 2&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 36) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 3;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer]);
                                            isMyturn = true;
                                            if (numPlayer == 7 || numPlayer == 13 || numPlayer == 17 || numPlayer == 23 || numPlayer == 31) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                player1Event();
                                            }
                                            if (numPlayer == 10) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                playerescapeevent();

                                            }
                                            return;
                                        }
                                        if (numPlayer >= 37) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 3;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Winer.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            return;
                                        }
                                    }
                                    if (num1 == 3&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 35) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 4;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer]);
                                            isMyturn = true;
                                            if (numPlayer == 7 || numPlayer == 13 || numPlayer == 17 || numPlayer == 23 || numPlayer == 31) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                player1Event();
                                            }
                                            if (numPlayer == 10) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                playerescapeevent();

                                            }
                                            return;
                                        }
                                        if (numPlayer >= 36) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 4;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Winer.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            return;
                                        }
                                    }
                                    if (num1 == 4&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 34) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 5;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer]);
                                            isMyturn = true;

                                            if (numPlayer == 7 || numPlayer == 13 || numPlayer == 17 || numPlayer == 23 || numPlayer == 31) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                player1Event();
                                            }
                                            if (numPlayer == 10) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                playerescapeevent();

                                            }
                                            return;
                                        }
                                        if (numPlayer >= 35) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 5;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Winer.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            return;
                                        }
                                    }
                                    if (num1 == 5&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 33) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 6;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer]);
                                            isMyturn = true;
                                            if (numPlayer == 7 || numPlayer == 13 || numPlayer == 17 || numPlayer == 23 || numPlayer == 31) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                player1Event();
                                            }
                                            if (numPlayer == 10) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                playerescapeevent();

                                            }
                                            return;
                                        }
                                        if (numPlayer >= 34) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 6;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Winer.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            return;

                                        }
                                    }
                                    if(num1==num2){
                                        soundPool.play(soundPlay1, 1f, 1f, 0, 0, 1f);
                                        ivMent.setImageResource(R.drawable.ment1);
                                        if (numPlayer >= 0 && numPlayer <= (38-num1)) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + (num1+1);
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer]);
                                            isMyturn = false;
                                            if (numPlayer == 7 || numPlayer == 13 || numPlayer == 17 || numPlayer == 23 || numPlayer == 31) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                player1Event();
                                                isMyturn=false;

                                            }
                                            if (numPlayer == 10) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                playerescapeevent();

                                            }
                                            return;
                                        }
                                        if (numPlayer >= (39-num1)) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + (num1+1);
                                            if(numPlayer>39 && playertotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Winer.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = false;
                                            return;

                                        }
                                    }
                                }
                            }.start();

                        }
                        /**????????????2 */
                        if (isMyturn) {
                            CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    if(isMyturn) {
                                        playNum.setImageResource(R.drawable.player2);
                                        img1.setImageResource(dice2[5]);
                                        img2.setImageResource(dice2[5]);
                                        charSlt.setImageResource(com[play2char]);
                                    }
                                    if(!isMyturn) {
                                        playNum.setImageResource(R.drawable.player1);
                                        img1.setImageResource(dice[5]);
                                        img2.setImageResource(dice[5]);
                                        charSlt.setImageResource(player[playchar]);
                                    }
                                }
                            }.start();
                            soundPool.play(soundPlay0, 1f, 1f, 0, 0, 1f);
                            Random random = new Random();
                            num1 = random.nextInt(6);
                            Random random1 = new Random();
                            num2 = random1.nextInt(6);
                            img1.startAnimation(ani1);
                            img2.startAnimation(ani1);
                            img1.setImageResource(dice2[num1]);
                            img2.setImageResource(dice2[num2]);


                            playermove[40].setImageResource(R.drawable.imgnono);
                            CountDownTimer countDownTimer1 = new CountDownTimer(2000, 500) {
                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {

                                    if (num1 == 0&&num1!=num2) {
                                        if (numPlayer2 >= 0 && numPlayer2 <= 38) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 1;
                                            commove[numPlayer2].setImageResource(com[play2char]);
                                            isMyturn = false;
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2]);
                                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                player2Event();

                                            }
                                            if (numPlayer2 == 10) {
                                                commove[numPlayer2].startAnimation(ani2);
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                comescapeevent();

                                            }
                                            commove[numPlayer2].startAnimation(ani2);
                                            return;
                                        }

                                        if (numPlayer2 >= 39) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 1;
                                            if(numPlayer2>39 && comtotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Lostgame.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            comtotalrotate--;
                                            commove[numPlayer2 - 40].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2 - 40]);
                                            numPlayer2 = numPlayer2 - 40;
                                            isMyturn = false;
                                            return;
                                        }
                                    }
                                    if (num1 == 1&&num1!=num2) {
                                        if (numPlayer2 >= 0 && numPlayer2 <= 37) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 2;
                                            commove[numPlayer2].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2]);
                                            isMyturn = false;
                                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                player2Event();

                                            }
                                            commove[numPlayer2].startAnimation(ani2);
                                            if (numPlayer2 == 10) {
                                                commove[numPlayer2].startAnimation(ani2);
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                comescapeevent();

                                            }
                                            return;
                                        }

                                        if (numPlayer2 >= 38) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 2;
                                            if(numPlayer2>39 && comtotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Lostgame.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            comtotalrotate--;
                                            commove[numPlayer2 - 40].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2 - 40]);
                                            numPlayer2 = numPlayer2 - 40;
                                            isMyturn = false;
                                            return;
                                        }
                                    }
                                    if (num1 == 2&&num1!=num2) {

                                        if (numPlayer2 >= 0 && numPlayer2 <= 36) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 3;
                                            commove[numPlayer2].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2]);
                                            isMyturn = false;
                                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                player2Event();

                                            }
                                            if (numPlayer2 == 10) {
                                                commove[numPlayer2].startAnimation(ani2);
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                comescapeevent();

                                            }
                                            commove[numPlayer2].startAnimation(ani2);
                                            return;
                                        }

                                        if (numPlayer2 >= 37) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 3;
                                            if(numPlayer2>39 && comtotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Lostgame.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            comtotalrotate--;
                                            commove[numPlayer2 - 40].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2 - 40]);
                                            numPlayer2 = numPlayer2 - 40;
                                            isMyturn = false;
                                            return;
                                        }
                                    }
                                    if (num1 == 3&&num1!=num2) {

                                        if (numPlayer2 >= 0 && numPlayer2 <= 35) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 4;
                                            commove[numPlayer2].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2]);
                                            isMyturn = false;
                                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                player2Event();

                                            }
                                            if (numPlayer2 == 10) {
                                                commove[numPlayer2].startAnimation(ani2);
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                comescapeevent();

                                            }
                                            commove[numPlayer2].startAnimation(ani2);
                                            return;
                                        }

                                        if (numPlayer2 >= 36) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 4;
                                            if(numPlayer2>39 && comtotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Lostgame.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            comtotalrotate--;
                                            commove[numPlayer2 - 40].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2 - 40]);
                                            numPlayer2 = numPlayer2 - 40;
                                            isMyturn = false;
                                            return;
                                        }
                                    }
                                    if (num1 == 4&&num1!=num2) {

                                        if (numPlayer2 >= 0 && numPlayer2 <= 34) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 5;
                                            commove[numPlayer2].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2]);
                                            isMyturn = false;
                                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                player2Event();

                                            }
                                            if (numPlayer2 == 10) {
                                                commove[numPlayer2].startAnimation(ani2);
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                comescapeevent();

                                            }
                                            commove[numPlayer2].startAnimation(ani2);
                                            button.setClickable(false);
                                            return;
                                        }

                                        if (numPlayer2 >= 35) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 5;
                                            if(numPlayer2>39 && comtotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Lostgame.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            comtotalrotate--;
                                            commove[numPlayer2 - 40].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2 - 40]);
                                            numPlayer2 = numPlayer2 - 40;
                                            isMyturn = false;
                                            return;
                                        }
                                    }
                                    if (num1 == 5&&num1!=num2) {

                                        if (numPlayer2 >= 0 && numPlayer2 <= 33) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 6;
                                            commove[numPlayer2].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2]);
                                            isMyturn = false;
                                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                player2Event();

                                            }
                                            if (numPlayer2 == 10) {
                                                commove[numPlayer2].startAnimation(ani2);
                                                commove[numPlayer2].setImageResource(com[play2char]);
                                                comescapeevent();

                                            }
                                            commove[numPlayer2].startAnimation(ani2);
                                            return;
                                        }
                                        if (numPlayer2 >= 34) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + 6;
                                            if(numPlayer2>39 && comtotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Lostgame.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            comtotalrotate--;
                                            commove[numPlayer2 - 40].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2 - 40]);
                                            numPlayer2 = numPlayer2 - 40;
                                            isMyturn = false;
                                            return;
                                        }
                                    }
                                    if(num1==num2){
                                        soundPool.play(soundPlay1, 1f, 1f, 0, 0, 1f);
                                    }
                                    if (numPlayer2 == 10) {
                                        commove[numPlayer2].startAnimation(ani2);
                                        commove[numPlayer2].setImageResource(com[play2char]);
                                        comescapeevent();
                                        return;
                                    }
                                    if (numPlayer2 >= 0 && numPlayer2 <= (38-num1)) {
                                        commove[numPlayer2].startAnimation(ani2);
                                        commove[numPlayer2].setImageResource(R.drawable.noiv);
                                        numPlayer2 = numPlayer2 + (num1+1);
                                        commove[numPlayer2].setImageResource(com[play2char]);
                                        Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2]);
                                        isMyturn = true;
                                        if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                            commove[numPlayer2].startAnimation(ani2);
                                            commove[numPlayer2].setImageResource(com[play2char]);
                                            player2Event();
                                            isMyturn=true;
                                            return;
                                        }
                                        if (numPlayer2 >= (39-num1)) {
                                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                                            numPlayer2 = numPlayer2 + (num1+1);
                                            if(numPlayer2>39 && comtotalrotate==0){
                                                Intent act1 = new Intent(MainActivity.this, Lostgame.class);
                                                startActivity(act1);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                                return;
                                            }
                                            comtotalrotate--;
                                            commove[numPlayer2 - 40].setImageResource(com[play2char]);
                                            Glide.with(MainActivity.this).load(com[play2char]).into(commove[numPlayer2 - 40]);
                                            numPlayer2 = numPlayer2 - 40;
                                            isMyturn = true;
                                            return;

                                        }
                                    }
                                }
                            }.start();
                        }

                }
            }
        });
    }
    void player1Event(){
        Random cardevent = new Random();
        int event = cardevent.nextInt(4);
        if (numPlayer == 7) {
            if (event == 0) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 1) {
                playermove[40].setImageResource(cardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer +2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 2) {
                playermove[40].setImageResource(cardEvent[2]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 3) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -3;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
        }
        if (numPlayer == 13) {
            if (event == 0) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 1) {
                playermove[40].setImageResource(cardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer +2;
                        playermove[numPlayer].setImageResource(player[playchar]);

                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 2) {
                playermove[40].setImageResource(cardEvent[2]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 3) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -3;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
        }
        if (numPlayer == 17) {
            if (event == 0) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 1) {
                playermove[40].setImageResource(cardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer +2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 2) {
                playermove[40].setImageResource(cardEvent[2]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 3) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -3;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
        }
        if (numPlayer == 23) {
            if (event == 0) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 1) {
                playermove[40].setImageResource(cardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer +2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 2) {
                playermove[40].setImageResource(cardEvent[2]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 3) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -3;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
        }
        if (numPlayer == 31) {
            if (event == 0) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 1) {
                playermove[40].setImageResource(cardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer +2;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
            if (event == 2) {
                playermove[40].setImageResource(cardEvent[2]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 3) {
                ivMent.setImageResource(R.drawable.ment3);
                playermove[40].setImageResource(cardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        playermove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playermove[numPlayer].setImageResource(R.drawable.imgnono);
                        numPlayer = numPlayer -3;
                        playermove[numPlayer].setImageResource(player[playchar]);
                    }
                }, 2000);
                isMyturn = true;
                return;
            }
        }
    }
    void player2Event(){
        Random cardevent = new Random();
        int event = cardevent.nextInt(1);
        if (numPlayer2 == 7) {
            if (event == 0) {
                commove[40].setImageResource(comcardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 1) {
                commove[40].setImageResource(comcardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2+2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }

            if (event == 2) {
                commove[40].setImageResource(comcardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-3;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
        }
        if (numPlayer2 == 13) {
            if (event == 0) {
                commove[40].setImageResource(comcardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 1) {
                commove[40].setImageResource(comcardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2+2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }

            if (event == 2) {
                commove[40].setImageResource(comcardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-3;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
        }
        if (numPlayer2 == 17) {
            if (event == 0) {
                commove[40].setImageResource(comcardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 1) {
                commove[40].setImageResource(comcardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2+2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }

            if (event == 2) {
                commove[40].setImageResource(comcardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-3;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
        }
        if (numPlayer2 == 23) {
            if (event == 0) {
                commove[40].setImageResource(comcardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 1) {
                commove[40].setImageResource(comcardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2+2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }

            if (event == 2) {
                commove[40].setImageResource(comcardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-3;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
        }
        if (numPlayer2 == 31) {
            if (event == 0) {
                commove[40].setImageResource(comcardEvent[0]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
            if (event == 1) {
                commove[40].setImageResource(comcardEvent[1]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2+2;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }

            if (event == 2) {
                commove[40].setImageResource(comcardEvent[3]);
                CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        commove[40].setImageResource(R.drawable.imgnono);
                    }
                }.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commove[numPlayer2].setImageResource(R.drawable.imgnono);
                        numPlayer2 = numPlayer2-3;
                        commove[numPlayer2].setImageResource(com[play2char]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
        }
    }
    void complay(){

    }
    void playerescapeevent(){
        if(num1==num2){
            playermove[10].setImageResource(R.drawable.imgnono);
            playermove[10+(num1+1)].setImageResource(player[playchar]);
            isMyturn=true;
            playerescape=2;
        }
        if(num1!=num2&&playerescape!=0){
            playermove[10].setImageResource(player[playchar]);
            numPlayer=10;
            playerescape--;
            isMyturn=true;
        }
        if(playerescape==0){
            playermove[10].setImageResource(R.drawable.imgnono);
            playermove[10+(num1+1)].setImageResource(player[playchar]);
            isMyturn=true;
            playerescape=2;
        }

    }
    void comescapeevent(){
        if(num1==num2){
            commove[10].setImageResource(R.drawable.imgnono);
            commove[10+(num1+1)].setImageResource(com[play2char]);
            isMyturn=false;
            play2escape =2;
        }
        if(num1!=num2&& play2escape !=0){
            commove[10].setImageResource(com[play2char]);
            numPlayer2=10;
            play2escape--;
            isMyturn=false;

        }
        if(play2escape ==0){
            commove[10].setImageResource(R.drawable.imgnono);
            commove[10+(num1+1)].setImageResource(com[play2char]);
            isMyturn=false;
            play2escape =2;
        }

    }
    void complay1(){


        Random random = new Random();
        int num1 = random.nextInt(6);
        Random random1 = new Random();
        int num2 = random1.nextInt(6);
        img1.startAnimation(ani1);
        img2.startAnimation(ani1);
        img1.setImageResource(dice2[num1]);
        img2.setImageResource(dice2[num2]);
        playermove[40].setImageResource(R.drawable.imgnono);

        if (num1 == 0) {
            if (numPlayer2 >= 0 && numPlayer2 <= 38) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 1;
                commove[numPlayer2].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2]);
                if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                    commove[numPlayer2].setImageResource(com[3]);
                    player2Event();
                }
                commove[numPlayer2].startAnimation(ani2);
                isMyturn = false;
                button.setClickable(false);
                return;
            }

            if (numPlayer2 >= 39) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 1;
                commove[numPlayer2 - 40].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2 - 40]);
                numPlayer2 = numPlayer2 - 40;
                isMyturn = false;
                button.setClickable(false);
                return;
            }
        }
        if (num1 == 1) {
            if (numPlayer2 >= 0 && numPlayer2 <= 37) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 2;
                commove[numPlayer2].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2]);
                isMyturn = false;
                if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                    commove[numPlayer2].setImageResource(com[3]);
                    player2Event();
                }
                commove[numPlayer2].startAnimation(ani2);
                button.setClickable(false);
                return;
            }

            if (numPlayer2 >= 38) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 2;
                commove[numPlayer2 - 40].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2 - 40]);
                numPlayer2 = numPlayer2 - 40;
                isMyturn = false;
                button.setClickable(false);
                return;
            }
        }
        if (num1 == 2) {

            if (numPlayer2 >= 0 && numPlayer2 <= 36) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 3;
                commove[numPlayer2].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2]);
                isMyturn = false;
                if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                    commove[numPlayer2].setImageResource(com[3]);
                    player2Event();
                }
                commove[numPlayer2].startAnimation(ani2);
                button.setClickable(false);
                return;
            }

            if (numPlayer2 >= 37) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 3;
                commove[numPlayer2 - 40].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2 - 40]);
                numPlayer2 = numPlayer2 - 40;
                isMyturn = false;
                button.setClickable(false);
                return;
            }
        }
        if (num1 == 3) {

            if (numPlayer2 >= 0 && numPlayer2 <= 35) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 4;
                commove[numPlayer2].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2]);
                isMyturn = false;
                if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                    commove[numPlayer2].setImageResource(com[3]);
                    player2Event();
                }
                commove[numPlayer2].startAnimation(ani2);
                button.setClickable(false);
                return;
            }

            if (numPlayer2 >= 36) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 4;
                commove[numPlayer2 - 40].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2 - 40]);
                numPlayer2 = numPlayer2 - 40;
                isMyturn = false;
                button.setClickable(false);
                return;
            }
        }
        if (num1 == 4) {

            if (numPlayer2 >= 0 && numPlayer2 <= 34) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 5;
                commove[numPlayer2].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2]);
                isMyturn = false;
                if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                    commove[numPlayer2].setImageResource(com[3]);
                    player2Event();
                }
                commove[numPlayer2].startAnimation(ani2);
                button.setClickable(false);
                return;
            }

            if (numPlayer2 >= 35) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 5;
                commove[numPlayer2 - 40].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2 - 40]);
                numPlayer2 = numPlayer2 - 40;

                isMyturn = false;
                button.setClickable(false);
                return;
            }
        }
        if (num1 == 5) {

            if (numPlayer2 >= 0 && numPlayer2 <= 33) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 6;
                commove[numPlayer2].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2]);
                isMyturn = false;
                if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                    commove[numPlayer2].setImageResource(com[3]);
                    player2Event();
                }
                commove[numPlayer2].startAnimation(ani2);
                button.setClickable(false);
                return;
            }
            if (numPlayer2 >= 34) {
                commove[numPlayer2].setImageResource(R.drawable.noiv);
                numPlayer2 = numPlayer2 + 6;
                commove[numPlayer2 - 40].setImageResource(com[3]);
                Glide.with(MainActivity.this).load(com[3]).into(commove[numPlayer2 - 40]);
                numPlayer2 = numPlayer2 - 40;
                isMyturn = false;
                button.setClickable(false);
                return;
            }
        }
    }


}



