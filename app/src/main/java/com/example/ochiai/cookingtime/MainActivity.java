package com.example.ochiai.cookingtime;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    TextView mTimeTextView;
    TextView mResultTextView;

    Timer mTimer;

    Handler mHandler;

    int mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeTextView = (TextView)findViewById(R.id.textView);
        mResultTextView = (TextView)findViewById(R.id.textView2);

        mTime = 0;

        mHandler = new Handler();
    }

    public void start(View v){
        mTime = 10;

        mTimer =new Timer(false);
        mTimer.schedule(new TimerTask(){
            @Override
            public void run () {
                mHandler.post(new Runnable(){
                    @Override
                    public void run() {
                        mTime--;
                    }
                });
            }
        },0,1000);
    }

    public void stop (View v){

        switch (mTime){
            case 0:
                mResultTextView.setText(String.valueOf(mTime));
                mResultTextView.setText("君には時間を計る能力がある！！ちなみに level5(天才)だ");
                break;

            default:
                mTimeTextView.setText(String.valueOf(mTime));
                mResultTextView.setText("あーほ　バーカ");
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
