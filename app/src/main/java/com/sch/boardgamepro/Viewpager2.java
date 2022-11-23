package com.sch.boardgamepro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import me.relex.circleindicator.CircleIndicator3;


public class Viewpager2 extends AppCompatActivity {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;
    ImageView ivRuinv;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ex17_viewpager2);

        ivRuinv = (ImageView) findViewById(R.id.ivRuinv);
        findViewById(R.id.ivDibtn).setOnClickListener(mClickListener);
        findViewById(R.id.ivCabtn).setOnClickListener(mClickListener);
        findViewById(R.id.ivRubtn).setOnClickListener(mClickListener);


        /**
         * 가로 슬라이드 뷰 Fragment
         */

        //ViewPager2
        mPager = findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new com.sch.boardgamepro.MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page, 0);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        /**
         * 이 부분 조정하여 처음 시작하는 이미지 설정.
         * 2000장 생성하였으니 현재위치 1002로 설정하여
         * 좌 우로 슬라이딩 할 수 있게 함. 거의 무한대로
         */

        mPager.setCurrentItem(0); //시작 지점
        mPager.setOffscreenPageLimit(4); //최대 이미지 수

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position % num_page);
            }
        });
    }
    Button.OnClickListener mClickListener = new Button.OnClickListener() {
         @Override
         public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivRubtn:
                ivRuinv.setImageResource(R.drawable.ch3);

                break;
            case R.id.ivDibtn:
                ivRuinv.setImageResource(R.drawable.ch1);
                break;
            case R.id.ivCabtn:
                ivRuinv.setImageResource(R.drawable.ch2);
                break;
            }
        }
    };

}