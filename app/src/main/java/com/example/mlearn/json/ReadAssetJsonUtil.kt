package com.example.mlearn.json

import android.content.Context
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException


/**
 * @Author: mingcong.
 * @CreateDate: 2021/8/19
 * @Description: 读取json
 * @UpdateUser: 更新者
 * @UpdateDate:
 * @UpdateRemark: 更新说明
 */
object ReadAssetJsonUtil {

    fun readJsonData(mContext: Context, fileName: String): JSONObject? {
        var line: String? = null
        var jsonObject: JSONObject? = null
        try {
            //打开存放在assets文件夹下面的json格式的文件并且放在文件输入流里面
            val inputStreamReader = InputStreamReader(mContext.assets.open(fileName), "UTF-8")
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = java.lang.StringBuilder()
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            bufferedReader.close()
            inputStreamReader.close()
            jsonObject = JSONObject(stringBuilder.toString())
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }
}