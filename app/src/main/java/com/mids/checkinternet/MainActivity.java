package com.mids.checkinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btnCheckInternet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCheckInternet=   findViewById(R.id.checkInternet);
        btnCheckInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });
    }

    private void checkConnection() {
        boolean isConnected=false;
        boolean isWorking=false;
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            isConnected=true;
            try {
                String command="ping -c 1 google.com";
                if(Runtime.getRuntime().exec(command).waitFor()==0){
                    isWorking=true;
                }else{
                    isWorking=false;
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        if(isConnected && isWorking){
            intent.putExtra("STATUS","Connected and Working");
        }else if(isConnected && !isWorking){
            intent.putExtra("STATUS","Connected and NOT Working");
        }else{
            intent.putExtra("STATUS"," NOTConnected and NOT Working");
        }
        startActivity(intent);
    }
}