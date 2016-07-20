package com.example.jzg.androiderp.utils;

import android.content.Context;

import com.example.jzg.androiderp.vo.AppraisersData;
import com.example.jzg.androiderp.vo.CityItem;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 读取assents/data.json文件下的数据
 */
public class DataUtil {

    public static CityItem getCityData (Context context) {
        try {
            InputStream in = context.getResources().getAssets().open("Citydata.json");//打开assets文件
            String res = convertStreamToString(in);
            Gson gson = new Gson();
            //将字符串转换为集合对象
//            List<CityItem> list = gson.fromJson(res, new TypeToken<ArrayList<CityItem>>(){}.getType());
            CityItem cityItem = gson.fromJson(res,CityItem.class);
            return cityItem;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static AppraisersData getContentData (Context context) {
        try {
            InputStream in = context.getResources().getAssets().open("ContentData.json");//打开assets文件
            String res = convertStreamToString(in);
            Gson gson = new Gson();
            //将字符串转换为集合对象
//            List<CityItem> list = gson.fromJson(res, new TypeToken<ArrayList<CityItem>>(){}.getType());
            AppraisersData appraisersData = gson.fromJson(res,AppraisersData.class);
            return appraisersData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static AppraisersData getContentDatas (Context context) {
        try {
            InputStream in = context.getResources().getAssets().open("ContentDatas.json");//打开assets文件
            String res = convertStreamToString(in);
            Gson gson = new Gson();
            //将字符串转换为集合对象
//            List<CityItem> list = gson.fromJson(res, new TypeToken<ArrayList<CityItem>>(){}.getType());
            AppraisersData appraisersData = gson.fromJson(res,AppraisersData.class);
            return appraisersData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取文件
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}
