package com.example.guide;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private int[] textRes = {R.string.text_1, R.string.text_2, R.string.text_3,R.string.text_4};
    private View[] dotRes;
    private TextView[] TextRes;
    private LinearLayout group;
    private boolean isrunning = true;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //让ViewPager滑到下一页
            pager.setCurrentItem(pager.getCurrentItem() + 1);
            //延时，循环调用handler
            if (isrunning) {
                handler.sendEmptyMessageDelayed(0, 4000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);

            init();
            setListener();
            pager.setAdapter(new MyAdapter());
            handler.sendEmptyMessageDelayed(0, 2000);
        }

        private class MyAdapter extends PagerAdapter {

            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(TextRes[position % TextRes.length], 0);
                return TextRes[position % TextRes.length];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(TextRes[position % TextRes.length]);
            }
        }

        private void setListener() {
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    setTextBg(position % dotRes.length);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        private void setTextBg(int index) {
            for (int i = 0; i < dotRes.length; i++) {
                if (i == index) {
                    dotRes[i].setBackgroundResource(R.mipmap.guide_slider_highlighted);
                } else {
                    dotRes[i].setBackgroundResource(R.mipmap.guide_slider);
                }
            }
        }

        private void init() {
            pager = (ViewPager) findViewById(R.id.vp_guide);

            group = (LinearLayout) findViewById(R.id.dots);

            dotRes = new View[textRes.length];

            for (int i = 0; i < dotRes.length; i++) {

                View dotview = new View(this);

//                dotview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                dotRes[i] = dotview;

                if (i == 0) {
                    dotview.setBackgroundResource(R.mipmap.guide_slider_highlighted);
                } else {
                    dotview.setBackgroundResource(R.mipmap.guide_slider);
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(20, 20));
                layoutParams.leftMargin = 20;
                group.addView(dotview, layoutParams);
            }
            TextRes = new TextView[textRes.length];

            for (int i = 0; i < TextRes.length; i++) {

                TextView tv_tuisong = new TextView(this);

                TextRes[i] = tv_tuisong;

                tv_tuisong.setGravity(Gravity.CENTER);
                tv_tuisong.setText(textRes[i]);

            }
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            isrunning=false;
        }

}


