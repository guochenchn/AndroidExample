package com.example.jzg.androiderp.Interface;

import com.example.jzg.androiderp.vo.Data;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * author: guochen
 * date: 2016/6/13 15:12
 * email: 
 */
public interface HttpService {
    @GET("/open/tupian.json")
    public Observable<List<Data>> loadPic();
}

