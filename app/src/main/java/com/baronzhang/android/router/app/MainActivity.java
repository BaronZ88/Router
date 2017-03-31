package com.baronzhang.android.router.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baronzhang.android.router.RouterInjector;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

//    @Inject
//    String cityId;
//
//    @InjectUriParam("name")
//    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                ArrayList<Integer> array = new ArrayList<>();
                array.add(123);
                array.add(123234);
                routerService.startThirdActivity("MainActivity", array);
            }
        });

        Button startFifthActivityBtn = (Button) findViewById(R.id.btn_start_fifth_activity);
        startFifthActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Integer> array = new ArrayList<>();
                array.add(123);
                array.add(123234);
                routerService.startThirdActivity("MainActivity", array);
            }
        });
    }
}
