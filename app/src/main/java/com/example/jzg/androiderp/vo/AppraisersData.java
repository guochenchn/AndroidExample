package com.example.jzg.androiderp.vo;
import java.util.List;

/**
 * author: guochen
 * date: 2016/7/7 19:31
 * email: 
 */
public class AppraisersData {

    /**
     * Data : [{"Name":" 哈尔滨晟浩投资总部","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"哈尔滨欧美亚名车1","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"哈尔滨欧美亚名车2","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"哈尔滨晟浩投资分部","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"利全汽车1","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"利全汽车2","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"利全汽车总部","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"欧美亚名车会馆总部","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"武汉评估师3","CityName":"哈尔滨","SixCount":0,"EighteenCount":27,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":72},{"Name":"易鑫哈尔滨","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"易鑫哈尔滨大唐伟业汽车","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"易鑫哈尔滨融达汽车","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"易鑫哈尔滨泰岳汽车","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0},{"Name":"张洪国","CityName":"哈尔滨","SixCount":0,"EighteenCount":7,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":21},{"Name":"钟文","CityName":"哈尔滨","SixCount":0,"EighteenCount":0,"OffLineCount":0,"RejectCount":0,"ConfirmCount":0,"SignCount":0}]
     * status : 100
     * msg : 成功
     */

    private int status;
    private String msg;
    /**
     * Name :  哈尔滨晟浩投资总部
     * CityName : 哈尔滨
     * SixCount : 0
     * EighteenCount : 0
     * OffLineCount : 0
     * RejectCount : 0
     * ConfirmCount : 0
     * SignCount : 0
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

    public static class DataBean {
        private String Name;
        private String CityName;
        private int SixCount;
        private int EighteenCount;
        private int OffLineCount;
        private int RejectCount;
        private int ConfirmCount;
        private int SignCount;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public int getSixCount() {
            return SixCount;
        }

        public void setSixCount(int SixCount) {
            this.SixCount = SixCount;
        }

        public int getEighteenCount() {
            return EighteenCount;
        }

        public void setEighteenCount(int EighteenCount) {
            this.EighteenCount = EighteenCount;
        }

        public int getOffLineCount() {
            return OffLineCount;
        }

        public void setOffLineCount(int OffLineCount) {
            this.OffLineCount = OffLineCount;
        }

        public int getRejectCount() {
            return RejectCount;
        }

        public void setRejectCount(int RejectCount) {
            this.RejectCount = RejectCount;
        }

        public int getConfirmCount() {
            return ConfirmCount;
        }

        public void setConfirmCount(int ConfirmCount) {
            this.ConfirmCount = ConfirmCount;
        }

        public int getSignCount() {
            return SignCount;
        }

        public void setSignCount(int SignCount) {
            this.SignCount = SignCount;
        }
    }
}
