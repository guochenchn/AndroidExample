package com.example.jzg.androiderp.ui;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.adapter.ChoosePicAdapter;
import com.example.jzg.androiderp.utils.BitmapUtils;
import com.example.jzg.androiderp.utils.ImgUtils;
import com.example.jzg.androiderp.utils.ScreenUtils;
import com.example.jzg.androiderp.view.FullyGridLayoutManager;
import com.example.jzg.androiderp.vo.ImageItem;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * author: guochen
 * date: 2016/6/19 14:47
 * email:
 */
public class ChoosePicActivity extends BaseActivity implements ChoosePicAdapter.MyOnItemClickListener {
    private static final int MY_PERMISSIONS_1 = 10;//拍照
    private static final int MY_PERMISSIONS_2 = 11;//从相册选取
    private final int REQUEST_CODE_CAMERA = 1000;//打开相机
    private final int REQUEST_CODE_GALLERY = 1001;//打开相册
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    public File tempPath;//拍照的临时文件,每次这个文件都会被替换掉
    @BindView(R.id.rvRealPhoto)
    RecyclerView rvRealPhoto;
    private String[] realPhotoNames = {"左前45度角", "前排座椅", "里程表", "后排座椅", "中控台", "右后45度"};
    private List<ImageItem> realPhotoList;//默认图片的数据
    private List<ImageItem> identifyPhotoList;
    private List<ImageItem> morePhotoList;
    private int picPositin;//照片的位置
    private String photoPath;
    private String thumPath;
    private int[] realPhotoes = {R.mipmap.zq45d, R.mipmap.qpzy, R.mipmap.lcb, R.mipmap.hpzy, R.mipmap.zkt, R.mipmap.yh45d};
    private int currRequestCode = 0;
    private ChoosePicAdapter picAdapter;
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            BitmapUtils utils = new BitmapUtils();
            if (resultList != null) {

                String bigPicPath = createPicPath(photoPath, System.currentTimeMillis());//创建大图文件
                String tubPicPath = createPicPath(thumPath, System.currentTimeMillis()); //创建缩略图文件
                boolean isBigPicSave = utils.saveImage(resultList.get(0).getPhotoPath(), bigPicPath);//保存大图
                //保存缩略图
                boolean isTubPicSave = utils.saveImage(ImgUtils.getImageThumbnail(bigPicPath,
                        ScreenUtils.dip2px(appContext, 60), ScreenUtils.dip2px(appContext, 40)), tubPicPath);
                if (isBigPicSave && isTubPicSave) {
                    realPhotoList.get(picPositin).setImageTub(tubPicPath);   //保存缩略图
                    realPhotoList.get(picPositin).setImagePath(bigPicPath);   //保存大图
                }
//                realPhotoList.get(picPositin).setImageTub(resultList.get(0).getPhotoPath());
                picAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(ChoosePicActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiyt_choosepic);
        ButterKnife.bind(this);
        realPhotoList = initPic();
        initData();
    }

    private void initData() {
        picAdapter = new ChoosePicAdapter(realPhotoList, this, realPhotoes);
        picAdapter.setMyOnItemClickListener(this);
        morePhotoList = new ArrayList<>();
        tempPath = new File(Environment.getExternalStorageDirectory(), "/PicTest/temp.jpg");
        photoPath = Environment.getExternalStorageDirectory() + "/PicTest/" + System.currentTimeMillis() + "/photo_temp/";
        thumPath = Environment.getExternalStorageDirectory() + "/PicTest/" + System.currentTimeMillis() + "/photo_tub/";
        FullyGridLayoutManager realPhotoManager = new FullyGridLayoutManager(this, 3);
        rvRealPhoto.setLayoutManager(realPhotoManager);
        rvRealPhoto.setAdapter(picAdapter);


    }

    /**
     * 初始化图片的默认数据
     *
     * @return
     */
    public ArrayList<ImageItem> initPic() {
        ArrayList<ImageItem> items = new ArrayList<>();
        for (String string : realPhotoNames) {
            items.add(new ImageItem(string));
        }
        return items;
    }

    @Override
    public void onItemClickLister(View view, int position) {
        picPositin = position;
        if (TextUtils.isEmpty(realPhotoList.get(position).getImagePath())) {   //说明要点击是要添加图片

            ActionSheet.createBuilder(ChoosePicActivity.this, getSupportFragmentManager())
                    .setCancelButtonTitle("取消(Cancel)")
                    .setOtherButtonTitles("打开相册", "拍照")
                    .setCancelableOnTouchOutside(true)
                    .setListener(new ActionSheet.ActionSheetListener() {
                        @Override
                        public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                        }

                        @Override
                        public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                            if (index == 0) {
                                //处理6.0权限问题
                                MPermissions.requestPermissions(ChoosePicActivity.this, MY_PERMISSIONS_1, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                            } else {
                                //处理6.0权限问题
                                MPermissions.requestPermissions(ChoosePicActivity.this, MY_PERMISSIONS_2, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            }

                        }
                    }).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @PermissionGrant(MY_PERMISSIONS_1)
    public void requestSdcardSuccess() {
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Uri imageUri = Uri.fromFile(tempPath);
//        // 指定照片保存路径（SD卡），temp.jpg为一个临时文件，每次拍照后这个图片都会被替换
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(cameraIntent, currRequestCode);
        //打开相册
        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHanlderResultCallback);
    }

    @PermissionDenied(MY_PERMISSIONS_2)
    public void requestFailed() {
        Toast.makeText(ChoosePicActivity.this, "授权被拒!", Toast.LENGTH_SHORT).show();
    }

    @PermissionGrant(MY_PERMISSIONS_2)
    public void requestSuccess() {
        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
    }

    @PermissionDenied(MY_PERMISSIONS_1)
    public void requestSdcardFailed() {
        Toast.makeText(ChoosePicActivity.this, "授权被拒!", Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建图片存放地址
     *
     * @param dir
     * @return
     */
    public String createPicPath(String dir, long pos) {
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = new File(dirFile.getPath() + File.separator + pos + ".jpg");
        if (file.exists()) {
            file.delete();
        }
        return dirFile.getPath() + File.separator + pos + ".jpg";
    }

}



