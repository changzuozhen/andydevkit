package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.didi.soda.andy.tools.LogUtils;

public class DemoMainActivity extends Activity {
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
