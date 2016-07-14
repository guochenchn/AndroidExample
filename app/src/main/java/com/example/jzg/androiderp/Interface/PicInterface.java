package com.example.jzg.androiderp.Interface;/**
 * author: gcc
 * date: 2016/6/13 15:56
 * email:
 */

import com.example.jzg.androiderp.Base.MvpView;
import com.example.jzg.androiderp.vo.Data;

import java.util.List;

/**
 * author: guochen
 * date: 2016/6/13 15:56
 * email: 
 */
public interface PicInterface extends MvpView {
    void showDatas(List<Data> datas);
    void showMoreDatas(List<Data> datas);
    void showRefreshDatas(List<Data> datas);

}
