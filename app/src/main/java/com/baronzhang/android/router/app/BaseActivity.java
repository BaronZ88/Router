package com.baronzhang.android.router.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baronzhang.android.router.Router;
import com.baronzhang.android.router.app.service.RouterService;

public class BaseActivity extends AppCompatActivity {

    public RouterService routerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        routerService = new Router(this).create(RouterService.class);
    }
}
