package com.baronzhang.android.router.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.baronzhang.android.router.RouterInjector;
import com.baronzhang.android.router.annotation.inject.Inject;
import com.baronzhang.android.router.annotation.inject.InjectUriParam;

import java.util.ArrayList;

public class ThirdActivity extends BaseActivity {


    @InjectUriParam
    String preActivity;

    @Inject("array")
    ArrayList<Integer> array;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        RouterInjector.inject(this);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);

        textView1.setText("preActivityName: " + preActivity);
        textView2.setText("user: " + array);
    }
}
