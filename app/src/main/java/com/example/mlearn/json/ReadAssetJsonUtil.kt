package com.myweimai.doctor.utils.tool

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.myweimai.base.util.GsonUtil
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * @Author: mingcong.
 * @CreateDate: 2021/8/19
 * @Description: 读取json
 * @UpdateUser: 更新者
 * @UpdateDate:
 * @UpdateRemark: 更新说明
 */
object ReadAssetJsonUtil {
    fun readJsonData(mContext: Context, fileName: String): String {
        val stringBuilder = StringBuilder()
        val assetManager = mContext.assets
        val bufferedReader = BufferedReader(InputStreamReader(assetManager.open(fileName)))
        while (bufferedReader.readLine() != null) {
            val line: String = bufferedReader.readLine()
            stringBuilder.append(line)
        }
        readToMap(stringBuilder.toString())
        return stringBuilder.toString()
    }

    fun readToMap(json:String) {

        Log.d("TAG", "readToMap: 1json="+GsonUtil.toMap2(Gson().toJson("{$json}")))
       Log.d("TAG", "readToMap: ");
    }


}