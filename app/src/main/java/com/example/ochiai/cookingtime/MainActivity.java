package com.example.ochiai.cookingtime;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    TextView mTimeTextView;
    TextView mResultTextView;
    TextView start;

    EditText mTimeChange;

    boolean startBotunIsStart;

    Timer mTimer;

    Handler mHandler;

    int mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeTextView = (TextView)findViewById(R.id.textView);
        mResultTextView = (TextView)findViewById(R.id.textView2);
        start = (TextView)findViewById(R.id.start);

        mTimeChange = (EditText)findViewById(R.id.editText);

        startBotunIsStart = true;


        mHandler = new Handler();
    }

    public void start(View v) {
        if (startBotunIsStart == true) {
            start.setText("ストップ");
            mTimeTextView.setText("ここに記録が表示されます");
            mResultTextView.setText("判定！！");

            mTime = Integer.parseInt(mTimeChange.getText().toString());

            mTimer = new Timer(false);
            mTimer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mTime--;
                                }
                            });
                        }
                    }, 0, 1000);
            startBotunIsStart =false;
        } else {
            switch (mTime) {


                case 0:
                    mTimeTextView.setText(mTime + "秒");
                    mResultTextView.setText("時間測定（タイマー）レベル１！");
                    Toast.makeText
                            (getApplicationContext(), "架空の能力です", Toast.LENGTH_SHORT)
                            .show();

                    break;
                default:
                    mTimeTextView.setText("誤差"+mTime+"秒");
                    mResultTextView.setText("あなたは無能力者レベル０です。");

                    break;
            }

            startBotunIsStart = true;

            start.setText("再スタート");
            mTimer.cancel();
            mTime = 0;
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
