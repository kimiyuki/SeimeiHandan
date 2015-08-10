package com.example.shirai.seimeihandan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import org.w3c.dom.Text;

import java.util.Random;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;


public class MyResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_result);
        new AdvertiseID(this).execute();

        Intent intent = getIntent();
        String myName = intent.getStringExtra(MainActivity.EXTRA_MYNAME);
        TextView nameLabel = (TextView) findViewById(R.id.nameLabel);
        nameLabel.setText(myName + "の点数は...");

        Random randomGenerator = new Random();
        int score = randomGenerator.nextInt(101); //0-100

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        scoreLabel.setText(String.valueOf(score) + "点");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_result, menu);
        return true;
    }

    public void getAdId(){
        Thread adIdThread=new Thread(new Runnable(){
            @Override
            public void run(){
                AdvertisingIdClient.Info adInfo=null;
                try{
                    adInfo=AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                    final String id=adInfo.getId();
                    final boolean isLAT=adInfo.isLimitAdTrackingEnabled();
                    Log.d("DEBUG", "AndroidAdID : " + id);
                    Log.d("DEBUG","OptoutFlag : "+String.valueOf(isLAT));
                }catch(Exception e){
                }
            }
        });
        adIdThread.start();
    }



}
