package com.example.guide;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 胡钰 on 2016/8/24.
 */
public class RegisterCheck extends AppCompatActivity {

    private TextView mtv_timedown;

    //输入验证码后的一个倒计时

    CountDownTimer timer=new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long l) {
            mtv_timedown.setText(l/1000+" s       ");
        }

        @Override
        public void onFinish() {
            mtv_timedown.setEnabled(true);
            mtv_timedown.setText("发送验证码");
        }
    };



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_2);

        mtv_timedown= (TextView) findViewById(R.id.tv_timedown);

        mtv_timedown.setEnabled(false);
        timer.start();

        mtv_timedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //重新发送验证码短信

//                Intent intent=new Intent();
//                intent.setClass(RegsiterPhone.this,MainActivity.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
