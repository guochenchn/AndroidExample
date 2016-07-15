package com.example.jzg.androiderp.vo;

import java.io.Serializable;
import java.util.List;

/**
 * author: guochen
 * date: 2016/7/7 15:01
 * email: 
 */
public class CityItem implements Serializable{

    /**
     * Data : [{"CityId":201,"CityName":"北京"},{"CityId":1401,"CityName":"长春"},{"CityId":1301,"CityName":"长沙"},{"CityId":2501,"CityName":"成都"},{"CityId":3101,"CityName":"重庆"},{"CityId":1708,"CityName":"大连"},{"CityId":504,"CityName":"东莞"},{"CityId":301,"CityName":"福州"},{"CityId":501,"CityName":"广州"},{"CityId":701,"CityName":"贵阳"},{"CityId":1101,"CityName":"哈尔滨"},{"CityId":3001,"CityName":"杭州"},{"CityId":101,"CityName":"合肥"},{"CityId":1801,"CityName":"呼和浩特"},{"CityId":2101,"CityName":"济南"},{"CityId":2118,"CityName":"济宁"},{"CityId":2901,"CityName":"昆明"},{"CityId":2117,"CityName":"临沂"},{"CityId":1002,"CityName":"洛阳"},{"CityId":1501,"CityName":"南京"},{"CityId":601,"CityName":"南宁"},{"CityId":3002,"CityName":"宁波"},{"CityId":2102,"CityName":"青岛"},{"CityId":2401,"CityName":"上海"},{"CityId":1701,"CityName":"沈阳"},{"CityId":502,"CityName":"深圳"},{"CityId":901,"CityName":"石家庄"},{"CityId":1502,"CityName":"苏州"},{"CityId":2201,"CityName":"太原"},{"CityId":2601,"CityName":"天津"},{"CityId":2105,"CityName":"潍坊"},{"CityId":1201,"CityName":"武汉"},{"CityId":1503,"CityName":"无锡"},{"CityId":302,"CityName":"厦门"},{"CityId":2301,"CityName":"西安"},{"CityId":2103,"CityName":"烟台"},{"CityId":1001,"CityName":"郑州"},{"CityId":702,"CityName":"遵义"}]
     * status : 100
     * msg : 成功
     */

    private int status;
    private String msg;
    /**
     * CityId : 201
     * CityName : 北京
     */

    private List<DataBean> Data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        private int CityId;
        private String CityName;

        public int getCityId() {
            return CityId;
        }

        public void setCityId(int CityId) {
            this.CityId = CityId;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }
    }
}
