/**
 * Project Name:JZGPingGuShiWangLuo
 * File Name:BaseAdapter.java
 * Package Name:com.gc.jzgpgswl.adapter
 * Date:2014-11-29下午5:03:57
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 */

package com.example.jzg.androiderp.Base;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName:BaseAd<br/>
 * Function:适合有图片异步加载的adapter使用. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-11-29 下午5:03:57 <br/>
 *
 * @author 汪渝栋
 * @see
 * @since JDK 1.6
 */
public class MBaseAdapter extends BaseAdapter {

    protected ImageLoadingListener mAnimateFirstListener = new AnimateFirstDisplayListener();

    protected DisplayImageOptions mOptions;

    public MBaseAdapter() {
        super();
        mOptions = new DisplayImageOptions.Builder()
              //  .showImageOnLoading(R.drawable.jingzhengu_moren)
               // .showImageForEmptyUri(R.drawable.jingzhengu_moren)
               // .showImageOnFail(R.drawable.jingzhengu_moren).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)//设置为RGB565比起默认的ARGB_8888要节省大量的内存
                .delayBeforeLoading(100)//载入图片前稍做延时可以提高整体滑动的流畅度
                .displayer(new RoundedBitmapDisplayer(0)).build();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private static class AnimateFirstDisplayListener extends
            SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

}
