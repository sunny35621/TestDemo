package com.example.administrator.testapplication.util;

import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;

import com.example.administrator.testapplication.bean.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/6/26.
 */

public class SDUtil {

    public static void saveDataToSDcard(String fileName, List<Test> list) {
        boolean isAvailable = false;
        FileOutputStream fileOutputStream = null;
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        List<String> cache = new ArrayList<String>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Test test = list.get(i);
            map.put("explain", test.explain);
            map.put("question", test.question);
            map.put("a", test.a);
            map.put("b", test.b);
            map.put("c", test.c);
            map.put("d", test.d);
            map.put("url", test.url);
            map.put("daan", test.daan);
            map.put("flag", test.flag);
        }
        mapList.add(map);
        for (int i = 0; i < mapList.size(); i++) {
            JSONObject obj = new JSONObject(mapList.get(i));
            cache.add(obj.toString());
        }
        String listStr = cache.toString();

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isAvailable = true;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(listStr.getBytes());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static List<Test> getDataFromSDcard(String fileName){
        //读取文件内容保存到resultStr
        String resultStr = null;
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            resultStr = new String(b);
            if(fileInputStream != null){
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读文件出错");
        }
        //将读取的String结果转化成List<>
        List<Test> tempList = new ArrayList<Test>();
        try {
            JSONArray jsonArray = new JSONArray(resultStr);
            if (jsonArray.length()>0) {
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                    Test test=new Test(
                            jsonObject.getString("explain"),
                            jsonObject.getString("question"),
                            jsonObject.getString("a"),
                            jsonObject.getString("b"),
                            jsonObject.getString("c"),
                            jsonObject.getString("d"),
                            jsonObject.getString("url"),
                            jsonObject.getString("daan"),
                            Integer.parseInt(jsonObject.getString("flag"))

                    );
                    tempList.add(test);
                }
            }
        } catch (JSONException e){
            e.printStackTrace();

        }
        return tempList;
    }
}
