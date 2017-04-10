package com.baronzhang.android.router.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.baronzhang.android.router.RouterInjector;
import com.baronzhang.android.router.annotations.inject.Inject;
import com.baronzhang.android.router.annotations.inject.InjectUriParam;
import com.baronzhang.android.router.app.model.User;

public class FourthActivity extends BaseActivity {

    @InjectUriParam
    String preActivity;

    @Inject
    User user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        RouterInjector.inject(this);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);

        textView1.setText("preActivityName: " + preActivity);
        textView2.setText("user: " + user);
    }
}
