package com.sy.screenshotdemoxu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sy.screenshotdemoxu.activity.OneActivityDemo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt_one = (Button) findViewById(R.id.bt_one);
        bt_one.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_one:
                Intent intent = new Intent(this, OneActivityDemo.class);
                startActivity(intent);
                break;
        }
    }
}
