package com.example.mlearn.json;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 *
 * @author: honghu
 * @date: 2019/4/16-11:32
 * @description: 将json字符串转化为Map对象
 * @modifyUser:
 * @modifyDate:
 **/
public class GsonUtil {


    private static final Gson GSON = new Gson();

    public static String toStr(Object o) {
        return GSON.toJson(o);
    }

    public static String toStr(Object o, Type type) {
        return GSON.toJson(o, type);
    }

    public static <T> T toObj(String json, Type typeof) {
        try {
            return GSON.fromJson(json, typeof);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toObj(JsonElement json, Type typeof) {
        return GSON.fromJson(json, typeof);
    }


    /**
     * 将 字符串转为 Map对象
     *
     * @param json
     * @return
     */
    public static Map<String, String> toMap(String json) {
        Map<String, String> result = null;
        try {
            result = new Gson().fromJson(json,
                    new TypeToken<Map<String, String>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == result) {
            result = new HashMap<>();
        }
        return result;
    }

    /**
     * 泛型的方式来转化,支持复杂类型 将字符串转为 Map对象
     * <p>
     * 需要gson解析的类型 , 重写他的deserialize方法, 就是将其中json手动解析成map , 不对数据进行处理
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap2(String json) {

        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer<HashMap<String, Object>>) (json1, typeOfT, context) -> {
            HashMap<String, Object> hashMap = new HashMap<>();
            JsonObject jo = json1.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = jo.entrySet();

            for (Map.Entry<String, JsonElement> entry : entrySet) {
                hashMap.put(entry.getKey(), entry.getValue());
            }

            return hashMap;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(type, jsonDeserializer)
                .create();

        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>(4);
        }
    }

    /**
     * @param json  传递进来的数据必须是jsonArray格式的字符串
     * @param clazz jsonArray中包装对象类型的类型
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T[]> clazz) {
        T[] arr = null;
        try {
            if (TextUtils.isEmpty(json) || !json.startsWith("[")) {

                Log.d("honghu_log", "GsonUtil:convertList:78 ->" + "json字符串必须是 []包裹的数据");

                return null;
            }
            arr = GSON.fromJson(json, clazz);
            if (arr != null && arr.length > 0) {
                return Arrays.asList(arr);
            }
        } catch (JsonSyntaxException e) {
            return null;
        }
        return null;
    }


}
