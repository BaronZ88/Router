package com.baronzhang.android.router.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baronzhang.android.router.RouterInjector;
import com.baronzhang.android.router.app.model.User;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        RouterInjector.inject(this);


        Button startSecondActivityBtn = (Button) findViewById(R.id.btn_start_second_activity);
        startSecondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routerService.startSecondActivity("MainActivity", "181818", 22222);
            }
        });


        Button startThirdActivityBtn = (Button) findViewById(R.id.btn_start_third_activity);
        startThirdActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Integer> array = new ArrayList<>();
                array.add(123);
                array.add(123234);
                routerService.startThirdActivity("MainActivity", array);
            }
        });

        Button startFourthActivityBtn = (Button) findViewById(R.id.btn_start_fourth_activity);
        startFourthActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("小叶子", 17, 165, 88);
                routerService.startFourthActivity("MainActivity", user);
            }
        });

        Button startFifthActivityBtn = (Button) findViewById(R.id.btn_start_fifth_activity);
        startFifthActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> users = new ArrayList<>();
                users.add(new User("小叶子", 17, 165, 88));
                users.add(new User("小叶子", 17, 165, 88));
                routerService.startFifthActivity("MainActivity", users);
            }
        });
    }
}
