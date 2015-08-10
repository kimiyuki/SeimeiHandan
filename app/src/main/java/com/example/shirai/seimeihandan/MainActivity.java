package com.example.shirai.seimeihandan;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MYNAME = "com.example.shirai.seimeihandan.MyName";
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AdvertiseID(this).execute();
       }

    public void getScore(View view){
        //edittextを取得
        EditText myEditText = (EditText) findViewById(R.id.myEditText);
        //EditTextの中身を取得
        String myName = myEditText.getText().toString().trim();
        //
        if(myName.equals("")){
            //error
            //myEditText.setError("Please Enter Text  ");
            /*
            Toast.makeText(
                    this,
                    "Please enter your text",
                    Toast.LENGTH_LONG
            ).show();
            */
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder
                    .setTitle("Error")
                    .setMessage("Please enter your name")
                    .setPositiveButton("OK",null);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();


        }else{
          //次の画面に
            Intent intent = new Intent(this, MyResult.class);
            intent.putExtra(EXTRA_MYNAME, myName);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
