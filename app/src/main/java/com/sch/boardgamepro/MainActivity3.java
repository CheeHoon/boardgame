package com.sch.boardgamepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.view.animation.Animation;

import com.bumptech.glide.Glide;

import java.util.Locale;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    String charSetInfo="";//intant 값
    String maproundt="";
    int charimage=0;
    Chronometer chro;//intant 값
    private String gameTurn = "";
    public static TextView textView;
    public static ImageView img1, img2,meeple1,meeple2,meeple3,meeple4,meeple5;
    int[] ivnum = new int[41];
    int playerescape =2;//플레이어 탈출이벤트 미구현..
    int comescape=2;//컴 탈출이벤트 미구현..
    int num1,num2; //주사위 랜덤값
    int numPlayer =0; //플레이어 이미지뷰 값
    int numPlayer2 =0;//컴 이미지뷰 값
    int playertotalrotate =0;//플레이어 총 바퀴수
    int comtotalrotate =0;// 컴 총 바퀴수
    int[] charictor =new int[4];//XXX
    int playchar = 0;//플레이어 캐릭 선택값
    int comchar = 0;//컴 캐릭 선택값
    Switch switch1;
    ImageView[] playermove = new ImageView[ivnum.length]; //플레이어 이미지뷰 배열
    ImageView[] commove = new ImageView[ivnum.length];//컴 이미지뷰 배열
    ImageView button,playNum,win,charSlt,ivMent,ivfin,maindice,subdice,ivnumgo;// 나머지 이미지뷰
    Animation ani1,ani2;//이미지 이동 애니메이션
    TextView tvroundt,roundc;//바퀴수 표시

    private SoundPool soundPool;//사운드
    int soundPlay0,soundPlay1,soundPlay2,soundPlay3,soundPlay4,soundPlay5;
    MediaPlayer mediaPlayer;
    boolean isMyturn=false;// boolean으로 턴 교대
    Handler handler = new Handler();// 거의 사용안함,,,
    final int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};//주사위 값
    final int dice2[] = {R.drawable.di1, R.drawable.di2, R.drawable.di3,
            R.drawable.di4, R.drawable.di5, R.drawable.di6};//주사위 값
    final int player[] = {R.drawable.ch0, R.drawable.ch1, R.drawable.ch2,R.drawable.ch3};//플레이어 캐릭 선택
    final int com[] = {R.drawable.ch0, R.drawable.ch1, R.drawable.ch2,R.drawable.ch3};//컴 캐릭 선택
    final int cardEvent[] = {R.drawable.card1, R.drawable.card2, R.drawable.card3,R.drawable.card4};//플레이어 카드이벤트
    final int comcardEvent[] = {R.drawable.card1, R.drawable.card2, R.drawable.card3};//컴 카드이벤트
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        ivnumgo = (ImageView)findViewById(R.id.ivnumgo);
        charSlt=(ImageView)findViewById(R.id.charSlt);
        tvroundt=(TextView) findViewById(R.id.tvroundt);
        roundc=(TextView) findViewById(R.id.roundc);
        ivMent=(ImageView) findViewById(R.id.ivMent);
        ivfin=(ImageView) findViewById(R.id.ivfin);
        Intent receive_intent = getIntent();
        charSetInfo = receive_intent.getStringExtra("charSet");//캐릭값
        gameTurn=receive_intent.getStringExtra("gameTurn");//바퀴수값
        tvroundt.setText(gameTurn);
        playertotalrotate=Integer.parseInt(gameTurn)-1;
        comtotalrotate=Integer.parseInt(gameTurn)-1;
        //intent 값을 캐릭선택 값으로
        if(charSetInfo.equals("0")){
            playchar=0;
            charSlt.setImageResource(player[0]);
            Random random = new Random();
            comchar = random.nextInt(4);
            if(playchar==comchar) {
                comchar = random.nextInt(4);
            }
        }
        if(charSetInfo.equals("1")){
            playchar=1;
            charSlt.setImageResource(player[1]);
            Random random = new Random();
            comchar = random.nextInt(4);
            if(playchar==comchar) {
                comchar = random.nextInt(4);
            }
        }
        if(charSetInfo.equals("2")){
            playchar=2;
            charSlt.setImageResource(player[2]);
            Random random = new Random();
            comchar = random.nextInt(4);
            if(playchar==comchar) {
                comchar = random.nextInt(4);
            }
        }
        if(charSetInfo.equals("3")){
            playchar=3;
            charSlt.setImageResource(player[3]);
            Random random = new Random();
            comchar = random.nextInt(4);
            if(playchar==comchar) {
                comchar = random.nextInt(4);
            }
        }


        chro = findViewById(R.id.chro);
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
        ani1 = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.translate);
        ani2 = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.translate);
        img1 = findViewById(R.id.die1);
        img2 = findViewById(R.id.die2);
        playNum = findViewById(R.id.playNum);
        maindice = findViewById(R.id.ivMainDice);
        subdice = findViewById(R.id.ivSubDice);
        /** 플레이어1 필드 ID*/
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
        /** 플레이어2 필드 ID*/
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
        ani1 = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.set);
        ani2 = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.scale2);
        roundc.setText( ""+(playertotalrotate+1));
        mediaPlayer = MediaPlayer.create(this, R.raw.gamebgm);
        mediaPlayer.setLooping(true); //무한재생
        mediaPlayer.start();
        playermove[0].setImageResource(player[playchar]);
        commove[0].setImageResource(com[comchar]);
        maindice.setImageResource(R.drawable.maindice);
        subdice.setImageResource(R.drawable.subdice);
        chro.setBase(SystemClock.elapsedRealtime());
        chro.start();
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
                                        playNum.setImageResource(R.drawable.computername);
                                        img1.setImageResource(dice2[5]);
                                        img2.setImageResource(dice2[5]);
                                        button.setImageResource(R.drawable.imgnono);
                                        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                                            public void onTick(long millisUntilFinished) {
                                            }

                                            public void onFinish() {

                                                complay();
                                                CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                                                    public void onTick(long millisUntilFinished) {
                                                    }

                                                    public void onFinish() {
                                                        if(isMyturn) {
                                                            complay();
                                                            CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                                                                public void onTick(long millisUntilFinished) {
                                                                }

                                                                public void onFinish() {
                                                                    if(isMyturn) {
                                                                        complay();
                                                                    }
                                                                }
                                                            }.start();
                                                        }
                                                    }
                                                }.start();
                                            }
                                        }.start();
                                    }
                                    if (!isMyturn) {
                                        soundPool.play(soundPlay2, 1f, 1f, 0, 0, 1f);
                                        playNum.setImageResource(R.drawable.player1);
                                        img1.setImageResource(dice[5]);
                                        img2.setImageResource(dice[5]);
                                        button.setImageResource(R.drawable.buttonc);
                                        button.setClickable(true);
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
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer]);
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
                                            button.setClickable(false);
                                            return;
                                        }
                                        if (numPlayer >= 39) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 1;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                chro.stop();
                                                mediaPlayer.stop();
                                                playermove[0].setImageResource(player[playchar]);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                Intent act1 = new Intent(MainActivity3.this, Winer.class);
                                                startActivity(act1);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            button.setClickable(false);
                                            return;
                                        }
                                    }
                                    if (num1 == 1&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 37) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 2;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer]);
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
                                            button.setClickable(false);
                                            return;
                                        }
                                        if (numPlayer >= 38) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 2;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                chro.stop();
                                                mediaPlayer.stop();
                                                playermove[0].setImageResource(player[playchar]);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                Intent act1 = new Intent(MainActivity3.this, Winer.class);
                                                startActivity(act1);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            button.setClickable(false);
                                            return;
                                        }
                                    }
                                    if (num1 == 2&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 36) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 3;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer]);
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
                                            button.setClickable(false);
                                            return;
                                        }
                                        if (numPlayer >= 37) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 3;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                chro.stop();
                                                mediaPlayer.stop();
                                                playermove[0].setImageResource(player[playchar]);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                Intent act1 = new Intent(MainActivity3.this, Winer.class);
                                                startActivity(act1);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            button.setClickable(false);
                                            return;
                                        }
                                    }
                                    if (num1 == 3&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 35) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 4;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer]);
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
                                            button.setClickable(false);
                                            return;
                                        }
                                        if (numPlayer >= 36) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 4;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                chro.stop();
                                                mediaPlayer.stop();
                                                playermove[0].setImageResource(player[playchar]);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                Intent act1 = new Intent(MainActivity3.this, Winer.class);
                                                startActivity(act1);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            button.setClickable(false);
                                            return;
                                        }
                                    }
                                    if (num1 == 4&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 34) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 5;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer]);
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
                                            button.setClickable(false);
                                            return;
                                        }
                                        if (numPlayer >= 35) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 5;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                chro.stop();
                                                mediaPlayer.stop();
                                                playermove[0].setImageResource(player[playchar]);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                Intent act1 = new Intent(MainActivity3.this, Winer.class);
                                                startActivity(act1);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            button.setClickable(false);
                                            return;
                                        }
                                    }
                                    if (num1 == 5&&num1!=num2) {
                                        if (numPlayer >= 0 && numPlayer <= 33) {
                                            playermove[numPlayer].startAnimation(ani2);
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 6;
                                            playermove[numPlayer].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer]);
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
                                            button.setClickable(false);
                                            return;
                                        }
                                        if (numPlayer >= 34) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + 6;
                                            if(numPlayer>39 && playertotalrotate==0){
                                                chro.stop();
                                                mediaPlayer.stop();
                                                playermove[0].setImageResource(player[playchar]);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                Intent act1 = new Intent(MainActivity3.this, Winer.class);
                                                startActivity(act1);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = true;
                                            button.setClickable(false);
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
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer]);
                                            isMyturn = false;
                                            if (numPlayer == 7 || numPlayer == 13 || numPlayer == 17 || numPlayer == 23 || numPlayer == 31) {
                                                playermove[numPlayer].startAnimation(ani2);
                                                playermove[numPlayer].setImageResource(player[playchar]);
                                                player1Event();
                                                isMyturn=false;

                                            }
                                            button.setClickable(true);
                                            return;
                                        }
                                        if (numPlayer >= (39-num1)) {
                                            playermove[numPlayer].setImageResource(R.drawable.noiv);
                                            numPlayer = numPlayer + (num1+1);
                                            if(numPlayer>39 && playertotalrotate==0){
                                                chro.stop();
                                                mediaPlayer.stop();
                                                playermove[0].setImageResource(player[playchar]);
                                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                                soundPool.play(soundPlay3, 1f, 1f, 0, 0, 1f);
                                                soundPool.play(soundPlay4, 1f, 1f, 0, 0, 1f);
                                                Intent act1 = new Intent(MainActivity3.this, Winer.class);
                                                startActivity(act1);
                                                return;
                                            }
                                            playertotalrotate--;
                                            if((playertotalrotate+1)==1){
                                                ivMent.setImageResource(R.drawable.ment2);
                                                ivfin.setImageResource(R.drawable.fina);
                                            }
                                            roundc.setText( ""+(playertotalrotate+1));
                                            playermove[numPlayer - 40].setImageResource(player[playchar]);
                                            Glide.with(MainActivity3.this).load(player[playchar]).into(playermove[numPlayer - 40]);
                                            numPlayer = numPlayer - 40;
                                            isMyturn = false;
                                            button.setClickable(true);
                                            return;

                                        }
                                    }
                                }
                            }.start();

                        }
                        /**플레이어2 */
//

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
                ivMent.setImageResource(R.drawable.ment4);
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
                ivMent.setImageResource(R.drawable.ment1);
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
                ivMent.setImageResource(R.drawable.ment4);
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
                ivMent.setImageResource(R.drawable.ment1);
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
                ivMent.setImageResource(R.drawable.ment4);
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
                ivMent.setImageResource(R.drawable.ment1);
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
                ivMent.setImageResource(R.drawable.ment4);
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
                ivMent.setImageResource(R.drawable.ment1);
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
                ivMent.setImageResource(R.drawable.ment4);
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
                ivMent.setImageResource(R.drawable.ment1);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
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
                        commove[numPlayer2].setImageResource(com[comchar]);
                    }
                }, 2000);
                isMyturn = false;
                return;
            }
        }
    }
    void complay(){
        if (isMyturn) {
            CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    if(isMyturn) {
                        playNum.setImageResource(R.drawable.computername);
                        img1.setImageResource(dice2[5]);
                        img2.setImageResource(dice2[5]);
                        button.setImageResource(R.drawable.imgnono);
                    }
                    if(!isMyturn) {
                        soundPool.play(soundPlay2, 1f, 1f, 0, 0, 1f);
                        playNum.setImageResource(R.drawable.player1);
                        img1.setImageResource(dice[5]);
                        img2.setImageResource(dice[5]);
                        button.setImageResource(R.drawable.buttonc);
                        button.setClickable(true);
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
                            commove[numPlayer2].setImageResource(com[comchar]);
                            isMyturn = false;
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2]);
                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                commove[numPlayer2].setImageResource(com[comchar]);
                                player2Event();

                            }
                            commove[numPlayer2].startAnimation(ani2);
                            button.setClickable(false);
                            return;
                        }

                        if (numPlayer2 >= 39) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 1;
                            if(numPlayer2>39 && comtotalrotate==0){
                                chro.stop();
                                mediaPlayer.stop();
                                commove[0].setImageResource(com[comchar]);
                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                Intent act1 = new Intent(MainActivity3.this, Lostgame.class);
                                startActivity(act1);
                                return;
                            }
                            comtotalrotate--;
                            commove[numPlayer2 - 40].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2 - 40]);
                            numPlayer2 = numPlayer2 - 40;
                            isMyturn = false;
                            button.setClickable(false);
                            return;
                        }
                    }
                    if (num1 == 1&&num1!=num2) {
                        if (numPlayer2 >= 0 && numPlayer2 <= 37) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 2;
                            commove[numPlayer2].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2]);
                            isMyturn = false;
                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                commove[numPlayer2].setImageResource(com[comchar]);
                                player2Event();

                            }
                            commove[numPlayer2].startAnimation(ani2);
                            button.setClickable(false);
                            return;
                        }

                        if (numPlayer2 >= 38) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 2;
                            if(numPlayer2>39 && comtotalrotate==0){
                                chro.stop();
                                mediaPlayer.stop();
                                commove[0].setImageResource(com[comchar]);
                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                Intent act1 = new Intent(MainActivity3.this, Lostgame.class);
                                startActivity(act1);
                                return;
                            }
                            comtotalrotate--;
                            commove[numPlayer2 - 40].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2 - 40]);
                            numPlayer2 = numPlayer2 - 40;
                            isMyturn = false;
                            button.setClickable(false);
                            return;
                        }
                    }
                    if (num1 == 2&&num1!=num2) {

                        if (numPlayer2 >= 0 && numPlayer2 <= 36) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 3;
                            commove[numPlayer2].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2]);
                            isMyturn = false;
                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                commove[numPlayer2].setImageResource(com[comchar]);
                                player2Event();

                            }
                            commove[numPlayer2].startAnimation(ani2);
                            button.setClickable(false);
                            return;
                        }

                        if (numPlayer2 >= 37) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 3;
                            if(numPlayer2>39 && comtotalrotate==0){
                                chro.stop();
                                mediaPlayer.stop();
                                commove[0].setImageResource(com[comchar]);
                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                Intent act1 = new Intent(MainActivity3.this, Lostgame.class);
                                startActivity(act1);
                                return;
                            }
                            comtotalrotate--;
                            commove[numPlayer2 - 40].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2 - 40]);
                            numPlayer2 = numPlayer2 - 40;
                            isMyturn = false;
                            button.setClickable(false);
                            return;
                        }
                    }
                    if (num1 == 3&&num1!=num2) {

                        if (numPlayer2 >= 0 && numPlayer2 <= 35) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 4;
                            commove[numPlayer2].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2]);
                            isMyturn = false;
                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                commove[numPlayer2].setImageResource(com[comchar]);
                                player2Event();

                            }
                            commove[numPlayer2].startAnimation(ani2);
                            button.setClickable(false);
                            return;
                        }

                        if (numPlayer2 >= 36) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 4;
                            if(numPlayer2>39 && comtotalrotate==0){
                                chro.stop();
                                mediaPlayer.stop();
                                commove[0].setImageResource(com[comchar]);
                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                Intent act1 = new Intent(MainActivity3.this, Lostgame.class);
                                startActivity(act1);
                                return;
                            }
                            comtotalrotate--;
                            commove[numPlayer2 - 40].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2 - 40]);
                            numPlayer2 = numPlayer2 - 40;
                            isMyturn = false;
                            button.setClickable(false);
                            return;
                        }
                    }
                    if (num1 == 4&&num1!=num2) {

                        if (numPlayer2 >= 0 && numPlayer2 <= 34) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 5;
                            commove[numPlayer2].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2]);
                            isMyturn = false;
                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                commove[numPlayer2].setImageResource(com[comchar]);
                                player2Event();

                            }
                            commove[numPlayer2].startAnimation(ani2);
                            button.setClickable(false);
                            return;
                        }

                        if (numPlayer2 >= 35) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 5;
                            if(numPlayer2>39 && comtotalrotate==0){
                                chro.stop();
                                mediaPlayer.stop();
                                commove[0].setImageResource(com[comchar]);
                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                Intent act1 = new Intent(MainActivity3.this, Lostgame.class);
                                startActivity(act1);
                                return;
                            }
                            comtotalrotate--;
                            commove[numPlayer2 - 40].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2 - 40]);
                            numPlayer2 = numPlayer2 - 40;
                            isMyturn = false;
                            button.setClickable(false);
                            return;
                        }
                    }
                    if (num1 == 5&&num1!=num2) {

                        if (numPlayer2 >= 0 && numPlayer2 <= 33) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 6;
                            commove[numPlayer2].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2]);
                            isMyturn = false;
                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                commove[numPlayer2].setImageResource(com[comchar]);
                                player2Event();

                            }
                            commove[numPlayer2].startAnimation(ani2);
                            button.setClickable(false);
                            return;
                        }
                        if (numPlayer2 >= 34) {
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + 6;
                            if(numPlayer2>39 && comtotalrotate==0){
                                chro.stop();
                                mediaPlayer.stop();
                                commove[0].setImageResource(com[comchar]);
                                soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                Intent act1 = new Intent(MainActivity3.this, Lostgame.class);
                                startActivity(act1);
                                return;
                            }
                            comtotalrotate--;
                            commove[numPlayer2 - 40].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2 - 40]);
                            numPlayer2 = numPlayer2 - 40;
                            isMyturn = false;
                            button.setClickable(false);
                            return;
                        }
                    }
                    if(num1==num2) {
                        soundPool.play(soundPlay1, 1f, 1f, 0, 0, 1f);
                        if (numPlayer2 >= 0 && numPlayer2 <= (38 - num1)) {
                            commove[numPlayer2].startAnimation(ani2);
                            commove[numPlayer2].setImageResource(R.drawable.noiv);
                            numPlayer2 = numPlayer2 + (num1 + 1);
                            commove[numPlayer2].setImageResource(com[comchar]);
                            Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2]);
                            isMyturn = true;
                            if (numPlayer2 == 7 || numPlayer2 == 13 || numPlayer2 == 17 || numPlayer2 == 23 || numPlayer2 == 31) {
                                commove[numPlayer2].startAnimation(ani2);
                                commove[numPlayer2].setImageResource(com[comchar]);
                                player2Event();
                                isMyturn = true;
                            }
                                button.setClickable(false);
                                return;
                            }
                            if (numPlayer2 >= (39 - num1)) {
                                commove[numPlayer2].setImageResource(R.drawable.noiv);
                                numPlayer2 = numPlayer2 + (num1 + 1);
                                if (numPlayer2 > 39 && comtotalrotate == 0) {
                                    chro.stop();
                                    commove[0].setImageResource(com[comchar]);
                                    soundPool.play(soundPlay0, 0, 0, 0, 0, 1f);
                                    soundPool.play(soundPlay1, 0, 0, 0, 0, 1f);
                                    soundPool.play(soundPlay2, 0, 0, 0, 0, 1f);
                                    soundPool.play(soundPlay5, 1f, 1f, 0, 0, 1f);
                                    Intent act1 = new Intent(MainActivity3.this, Lostgame.class);
                                    startActivity(act1);
                                    return;
                                }
                                comtotalrotate--;
                                commove[numPlayer2 - 40].setImageResource(com[comchar]);
                                Glide.with(MainActivity3.this).load(com[comchar]).into(commove[numPlayer2 - 40]);
                                numPlayer2 = numPlayer2 - 40;
                                isMyturn = true;
                                button.setClickable(false);
                                return;

                            }
                        }
                    }
            }.start();
        }

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
            commove[10+(num1+1)].setImageResource(com[comchar]);
            isMyturn=false;
            comescape=2;
        }
        if(num1!=num2&&comescape!=0){
            commove[10].setImageResource(com[comchar]);
            numPlayer2=10;
            comescape--;
            isMyturn=false;

        }
        if(comescape==0){
            commove[10].setImageResource(R.drawable.imgnono);
            commove[10+(num1+1)].setImageResource(com[comchar]);
            isMyturn=false;
            comescape=2;
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2 - 40]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2 - 40]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2 - 40]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2 - 40]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2 - 40]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2]);
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
                Glide.with(MainActivity3.this).load(com[3]).into(commove[numPlayer2 - 40]);
                numPlayer2 = numPlayer2 - 40;
                isMyturn = false;
                button.setClickable(false);
                return;
            }
        }
    }


}



