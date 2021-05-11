package com.mids.checkinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView internetConnectivityStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        internetConnectivityStatus=findViewById(R.id.internetConnectivityStatus);
        internetConnectivityStatus.setText(getIntent().getStringExtra("STATUS"));
    }
}