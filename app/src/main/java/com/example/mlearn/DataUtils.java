package com.example.mlearn;

public class DataUtils {
    private DataGet dataGet;
    public DataUtils(DataGet dataGet) {
        this.dataGet=dataGet;
        dataGet.onGetData(new ModelBean().setName("122").setAge(100));
    }
}
