package com.example.guide;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by 胡钰 on 2016/8/24.
 */
public class RegisterPW extends AppCompatActivity {

    private ImageButton mbt_pass_change;
    private EditText met_password;
    private boolean mcansee=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_3);

        mbt_pass_change= (ImageButton) findViewById(R.id.ib_pass_change);
        met_password= (EditText) findViewById(R.id.et_password);

        mbt_pass_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mcansee){
                    mbt_pass_change.setImageDrawable(getResources().getDrawable(R.mipmap.login_password_cansee));
                    //设置密码可见
                    met_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    met_password.setSelection(met_password.length());
                    mcansee=true;
                }else {
                    mbt_pass_change.setImageDrawable(getResources().getDrawable(R.mipmap.login_password_cantsee));
                    //设置密码不可见
                    met_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    met_password.setSelection(met_password.length());
                    mcansee=false;
                }

            }
        });
    }
}
