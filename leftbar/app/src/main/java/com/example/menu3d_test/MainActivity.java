package com.example.menu3d_test;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//控制了所有主界面间的联系，所有的Activity要用继承自fragment的形式存在，然后会一个个new出来
//如果这个residemenu和其他控件有冲突，可以用residemenu的方法在打开其他界面的时候忽略这个控件

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ResideMenu mresideMenu;
    private MainActivity mContext;
    private ResideMenuItem leftbar_manager,leftbar_setting,leftbar_news,leftbar_clear,leftbar_help,leftbar_about;
    private int leftbar_icon=R.mipmap.leftbar_sign;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new HomeFragment());
    }

    private void setUpMenu() {

        // attach to current activity;
        mresideMenu = new ResideMenu(this);
//        resideMenu.setUse3D(true);
       mresideMenu.setBackground(R.color.leftbar_background);
        //绑定当前Activity
        mresideMenu.attachToActivity(this);
        //设置监听事件
        mresideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        //设置内容比例的缩放
        mresideMenu.setScaleValue(0.55f);

        // create menu items;
        leftbar_manager = new ResideMenuItem(this,leftbar_icon,"后台管理");
        leftbar_setting = new ResideMenuItem(this,leftbar_icon, "账户设置");
        leftbar_news = new ResideMenuItem(this, leftbar_icon,"新消息通知");
        leftbar_clear = new ResideMenuItem(this,leftbar_icon, "清除缓存");
        leftbar_help = new ResideMenuItem(this, leftbar_icon,"帮助反馈");
        leftbar_about = new ResideMenuItem(this,leftbar_icon, "关于");

        //设置点击事件及将刚创建的子菜单添加到侧换菜单栏中，通过常量来添加子菜单栏的位置
        leftbar_manager.setOnClickListener(this);
        leftbar_setting.setOnClickListener(this);
        leftbar_news.setOnClickListener(this);
        leftbar_clear.setOnClickListener(this);
        leftbar_help.setOnClickListener(this);
        leftbar_about.setOnClickListener(this);

        mresideMenu.addMenuItem(leftbar_manager, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_setting, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_news, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_clear, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_help, ResideMenu.DIRECTION_LEFT);
        mresideMenu.addMenuItem(leftbar_about, ResideMenu.DIRECTION_LEFT);



        // You can disable a direction by setting ->
        //去控制禁止一边的菜单栏开启
         mresideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        //设置了title button
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mresideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }

    //重写dispatchTouchEvent方法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mresideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == leftbar_manager){
            changeFragment(new HomeFragment());
        }
        else if (view == leftbar_setting){
            changeFragment(new HomeFragment());
        }else if (view == leftbar_news){
            changeFragment(new HomeFragment());
        }else if (view == leftbar_clear){
            changeFragment(new HomeFragment());
        }else if (view == leftbar_news){
            changeFragment(new HomeFragment());
        }else if (view == leftbar_news){
            changeFragment(new HomeFragment());
        }

        //菜单关闭方法
        mresideMenu.closeMenu();
    }

    //可以监听打开和关闭的状态
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        mresideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return mresideMenu;
    }
}
