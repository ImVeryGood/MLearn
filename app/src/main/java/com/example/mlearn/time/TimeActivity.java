package com.example.mlearn.time;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.mlearn.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeActivity extends AppCompatActivity {
    private SimpleDateFormat simpleDateFormat;
    private Date d1;
    private TextView textView;
    private TimeHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        initView();
    }

    private void initView()  {
        textView = findViewById(R.id.text);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        handler = new TimeHandler();
        handler.sendEmptyMessage(0);
        try {
            d1 = simpleDateFormat.parse("2021-08-11 22:44:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private class TimeHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            updateTime();
            sendEmptyMessageDelayed(0, 600);
        }
    }

    private void updateTime() {
        Log.e("WFQ", "当前时间是：" + simpleDateFormat.format(new Date()));

        try {

            Date d2 = simpleDateFormat.parse(simpleDateFormat.format(new Date().getTime())); //前的时间
            Long diff = d2.getTime() - d1.getTime(); //两时间差，精确到毫秒

            Long day = diff / (1000 * 60 * 60 * 24);   //以天数为单位取整
            Long hour=(diff/(60*60*1000)-day*24);    //以小时为单位取整
            Long min=((diff/(60*1000))-day*24*60-hour*60); //以分钟为单位取整
            textView.setText("当前时间差：" + min);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != handler) {
            handler.removeMessages(0);
        }
    }
}