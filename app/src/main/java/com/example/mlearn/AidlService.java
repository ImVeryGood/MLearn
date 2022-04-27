package com.example.mlearn;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class AidlService extends Service {
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private Binder mBinder=new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addbook(Book book) throws RemoteException {
            mBookList.add(book);

        }
    };

    public AidlService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book("西游记","西天取经"));
        mBookList.add(new Book("水浒传","梁山好汉"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
