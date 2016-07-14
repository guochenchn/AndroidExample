package com.example.jzg.androiderp.presenter;


import com.example.jzg.androiderp.vo.Data;
import com.example.jzg.androiderp.app.AppContext;

import java.util.List;

import rx.Observable;

/**
 * Created by jzg on 2015/12/22.
 * 数据层管理类 主要对网络数据，db数据，文件数据，内存数据进行操作
 */

public class DataManager {

    private static DataManager dataManager = new DataManager();

    private DataManager() {

    }

    public static synchronized DataManager getInstance() {
        return dataManager;
    }


    public Observable<List<Data>> loadPic() {
        return AppContext.httpService.loadPic();
    }





 /*   public Observable<FastOnlineData> loadFastOnlineData(String userID, String datetype,
                                                         String status, String Operation, String PageId, String isShowAll, String vin) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userID);
        params.put("datetype", datetype);
        params.put("status", status);
        params.put("Operation", Operation);
        params.put("vin", vin);
        params.put("isShowAll", isShowAll);
        params.put("tokenid", "6");
        if (!PageId.equals("")) {
            params.put("PageId", PageId);
        }
        Map<String, Object> maps = new HashMap<>();
        maps.putAll(params);
        params.put("sign", MD5Utils.getMD5Sign(maps));
        Logger.e(params.toString());
        return AppContext.httpService.loadFastOnlineData(params);
    }*/
/* public Observable<TouXiangInfo> updateImage(String userid, String path) {
     File imgFile = new File(path);
     Map<String, String> params = new HashMap<String, String>();
     params.put("userid", userid);
     params.put("op", "editHeadPic");
     params.put("tokenid", "6");
     Map<String, Object> signMap = new HashMap<>();
     signMap.putAll(params);
     RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), imgFile);
     RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), userid);
     RequestBody p = RequestBody.create(MediaType.parse("text/plain"), "editHeadPic");
     RequestBody sign = RequestBody.create(MediaType.parse("text/plain"), MD5Utils.getMD5Sign(signMap));
     Map<String, RequestBody> params1 = new HashMap<String, RequestBody>();
     params1.put("image\"; filename=\"" + imgFile.getName() + "", fileBody);
     params1.put("userId", userId);
     params1.put("op", p);
     params1.put("sign", sign);
     return AppContext.httpService.updateImage(params1);

 }*/


}
