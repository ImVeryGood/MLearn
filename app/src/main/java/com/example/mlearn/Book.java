package com.example.mlearn;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private String bookName;
    private String bookDescribe;

    public Book(String bookName, String bookDescribe){
        this.bookName = bookName;
        this.bookDescribe =bookDescribe;
    }

    protected Book(Parcel in) {
        bookName =in.readString();
        bookDescribe =in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeString(bookDescribe);
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDescribe() {
        return bookDescribe;
    }

    public void setBookDescribe(String bookDescribe) {
        this.bookDescribe = bookDescribe;
    }
}
