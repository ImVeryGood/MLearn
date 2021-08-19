package com.example.mlearn.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeTextView extends AppCompatTextView {
    private TimeHandler timeHandler;
    private int i;
    private String startTime;
    private SimpleDateFormat simpleDateFormat;
    private Date d1;

    public TimeTextView(@NonNull Context context) {
        super(context);
    }

    public TimeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("SimpleDateFormat")
    public void setCurrentDate(long startTime) {
        timeHandler = new TimeHandler(this);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d1 = simpleDateFormat.parse(simpleDateFormat.format(startTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timeHandler.sendEmptyMessage(0);

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @SuppressLint("SetTextI18n")
    public void upDateTime() {
        try {
            Date d2 = simpleDateFormat.parse(simpleDateFormat.format(new Date().getTime())); //前的时间
            long diff = d2.getTime() - d1.getTime(); //两时间差，精确到毫秒
            long day = diff / (1000 * 60 * 60 * 24);   //以天数为单位取整
            long hour = (diff / (60 * 60 * 1000) - day * 24);    //以小时为单位取整
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60); //以分钟为单位取整
            setText(min + "分钟前");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void removeTimer() {
        if (null != timeHandler) {
            timeHandler.removeMessages(0);
        }
    }

    private static class TimeHandler extends Handler {
        WeakReference<TimeTextView> weakReference;

        public TimeHandler(TimeTextView textView) {
            this.weakReference = new WeakReference<>(textView);
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            weakReference.get().upDateTime();
            sendEmptyMessageDelayed(0, 60000);
        }
    }
}
