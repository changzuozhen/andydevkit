package com.example.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.didi.soda.andy.tools.LogUtils;

public class DemoMainActivity extends AppCompatActivity {
    private static final String TAG = "DemoMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_main);
    }

    public void btnClick(View view) {
        LogUtils.d(TAG, "⚠️btnClick() called with: view = [" + view + "]");
    }
}
