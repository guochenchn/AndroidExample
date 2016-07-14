package com.example.jzg.androiderp.vo;

import java.io.Serializable;

/**
 * author: guochen
 * date: 2016/6/19 14:47
 * email:
 */
public class ImageItem implements Comparable<ImageItem>, Serializable {
    private String imagePath;//路径
    private int position;//照片所处的位置
    private int picId;//服务器返回的照片id
    private String imageTub;//缩略图路径
    private String picName;//照片部位名称

    public ImageItem(String imagePath, String imageTub) {
        this.imagePath = imagePath;
        this.imageTub = imageTub;
    }

    public ImageItem(String imagePath, String imageTub, int position) {
        this.imageTub = imageTub;
        this.imagePath = imagePath;
        this.position = position;
    }

    public ImageItem(String picName) {
        this.picName = picName;
    }

    public ImageItem() {
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getImageTub() {
        return imageTub;
    }

    public void setImageTub(String imageTub) {
        this.imageTub = imageTub;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public int compareTo(ImageItem another) {
        return 0;
    }


}
