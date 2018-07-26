package com.example.a501_00.cookingtimer;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_min, editText_sec;
    TextView textView_time;
    Button button_start, button_stop;
    int min, sec;
    CookTimerAsyncTask timer;
    ProgressBar progressBar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setComponents();
    }

    public String printMin(int min) {
        if(min < 10) {
            return "0"+Integer.toString(min);
        }
        return Integer.toString(min);
    }
    public String printSec(int sec) {
        if(sec < 10) {
            return "0"+Integer.toString(sec);
        }
        return Integer.toString(sec);
    }

    public void setComponents() {

        progressBar_main = (ProgressBar)findViewById(R.id.progressBar_main);
        editText_min = (EditText)findViewById(R.id.editText_min);
        editText_sec = (EditText)findViewById(R.id.editText_sec);
        textView_time = (TextView)findViewById(R.id.textView_time);
        button_start = (Button)findViewById(R.id.button_start);
        button_stop = (Button)findViewById(R.id.button_stop);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText_min.getText().toString().equals("")) {
                    min = 0;
                } else {
                    min = Integer.parseInt(editText_min.getText().toString());
                }

                if(editText_sec.getText().toString().equals("")) {
                    sec = 0;
                } else {
                    sec = Integer.parseInt(editText_sec.getText().toString());
                }

                int convert_sec = (min * 60) + sec;
                progressBar_main.setMax(convert_sec);
                progressBar_main.setProgress(0);

                timer = new CookTimerAsyncTask();
                timer.execute();
            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timer != null) {
                    timer.cancel(false);
                }
            }
        });
    }

    public class CookTimerAsyncTask extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            textView_time.setText(printMin(min)+":"+printSec(sec));
        }

        protected Void doInBackground(Void... arg) {
            while(isCancelled() == false) {

                try {
                    Log.d("time_test", String.valueOf(sec));
                    Thread.sleep(1000);
                    sec = sec - 1;
                    if(sec < 0) {
                        min = min - 1;
                        sec = 59;

                    }
                    if(sec == 0 && min == 0){
                        break;
                    }

                    publishProgress();
                } catch(Exception e) {
                    Log.d("CookingTimer","doInBackground()");
                }
            }
            return null;
        }

        protected void onProgressUpdate(Void... arg) {
            progressBar_main.incrementProgressBy(1);
            Log.d("time_test_print", String.valueOf(sec));
            textView_time.setText(printMin(min)+":"+printSec(sec));
        }

        protected void onPostExecute(Void result) {
            progressBar_main.incrementProgressBy(1);
            textView_time.setText("00:00");
            Toast.makeText(getApplicationContext(),
                    "타이머가 완료되었습니다.",
                    Toast.LENGTH_LONG).show();
        }

        protected void onCancelled() {
            textView_time.setText("00:00");

            Toast.makeText(getApplicationContext(),
                    "타이머가 취소 되었습니다.",
                    Toast.LENGTH_LONG).show();
        }
    }

}
