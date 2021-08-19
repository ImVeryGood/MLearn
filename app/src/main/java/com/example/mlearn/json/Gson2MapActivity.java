package com.example.mlearn.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mlearn.R;
import com.example.mlearn.home.PageBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Gson2MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson2_map);
        initView();
    }

    private void initView() {
        JSONObject json = ReadAssetJsonUtil.INSTANCE.readJsonData(this, "base_url.json");
        Log.d("TAG", "readString:2233 " + GsonUtil.toMap2(json.toString()));
        Map<String, Object> map=GsonUtil.toMap2(json.toString());
        Log.d("TAG", "initView: map:"+map.get("test1"));
        String pages= map.get("test1").toString();
        Map<String,Object> objectMap=GsonUtil.toMap2(pages);
        Log.d("TAG", "initView: jsonBean:"+objectMap.get("enableTinker"));
    }


}