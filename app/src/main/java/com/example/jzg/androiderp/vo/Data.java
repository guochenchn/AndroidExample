package com.example.jzg.androiderp.vo;/**
 * author: gcc
 * date: 2016/6/13 15:13
 * email:
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * author: guochen
 * date: 2016/6/13 15:13
 * email: 
 */
public class Data extends SugarRecord implements Parcelable{

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
    /**
     * title : 我想过去帮忙的但是被捷足先登了
     * thumburl : http://ww3.sinaimg.cn/large/e4e2bea6jw1f2cec66ni3j20ay09oq39.jpg
     * sourceurl : http://down.laifudao.com/images/tupian/2016316202029.jpg
     * height : 348
     * width : 394
     * class : 1
     * url : http://www.laifudao.com/tupian/55354.htm
     */

    @Expose
    private String title;
    @Expose
    private String thumburl;
    @Expose
    private String sourceurl;
    @Expose
    private String height;
    @Expose
    private String width;
    @Expose
    @SerializedName("class")
    private String classX;
    @Expose
    private String url;

    public Data() {

    }

    protected Data(Parcel in) {
        this.title = in.readString();
        this.thumburl = in.readString();
        this.sourceurl = in.readString();
        this.height = in.readString();
        this.width = in.readString();
        this.classX = in.readString();
        this.url = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.thumburl);
        dest.writeString(this.sourceurl);
        dest.writeString(this.height);
        dest.writeString(this.width);
        dest.writeString(this.classX);
        dest.writeString(this.url);
    }

    @Override
    public String toString() {
        return "Data{" +
                "title='" + title + '\'' +
                ", thumburl='" + thumburl + '\'' +
                ", sourceurl='" + sourceurl + '\'' +
                ", height='" + height + '\'' +
                ", width='" + width + '\'' +
                ", classX='" + classX + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
