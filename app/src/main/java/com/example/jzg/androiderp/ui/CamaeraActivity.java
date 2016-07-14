package com.example.jzg.androiderp.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import us.pinguo.edit.sdk.PGEditActivity;
import us.pinguo.edit.sdk.base.PGEditResult;
import us.pinguo.edit.sdk.base.PGEditSDK;
import us.pinguo.edit.sdk.core.utils.BitmapUtils;

/**
 * author: guochen
 * date: 2016/6/17 13:40
 * email:
 */
public class CamaeraActivity extends BaseActivity {
    private static final int REQUEST_CODE_PICK_PICTURE = 1001;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.choose_photo_btn)
    Button choosePhotoBtn;
    @BindView(R.id.start_edit_btn)
    Button startEditBtn;
    private String mPicturePath;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    /**
     * 打开相册选择照片
     */
    public void startChoosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_PICTURE);
    }

    /**
     * 开始编辑照片
     */
    public void startEdit() {
        if (null == mPicturePath) {
            Toast.makeText(this, "Please choose photo first", Toast.LENGTH_SHORT).show();
            return;
        }
        //
        String folderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getAbsolutePath() + File.separator;
        String outFilePath = folderPath + System.currentTimeMillis() + ".jpg";
        if (mPicturePath.toLowerCase().endsWith("png")) {
            outFilePath = outFilePath.replaceAll("jpg", "png");
        }
        PGEditSDK.instance().startEdit(this, PGEditActivity.class, mPicturePath, outFilePath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_PICTURE && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = new String[]{MediaStore.Images.Media.DATA};
            Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            mPicturePath = c.getString(columnIndex);
            c.close();

            if (null != mPicturePath) {
             //   enterEditState();
                Bitmap bitmap = BitmapUtils.scalePicture(mPicturePath, 720, true);
                logo.setImageBitmap(bitmap);
            }

            return;
        }
        if (requestCode == PGEditSDK.PG_EDIT_SDK_REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            PGEditResult editResult = PGEditSDK.instance().handleEditResult(data);// 获取编辑后的缩略图
            Bitmap thumbNail = editResult.getThumbNail();// 获取编辑后的⼤图路径
            String resultPhotoPath = editResult.getReturnPhotoPath();
        }
        if (requestCode == PGEditSDK.PG_EDIT_SDK_REQUEST_CODE && resultCode == PGEditSDK.PG_EDIT_SDK_RESULT_CODE_CANCEL) {// ⽤户取消编辑

        }
        if (requestCode == PGEditSDK.PG_EDIT_SDK_REQUEST_CODE && resultCode == PGEditSDK.PG_EDIT_SDK_RESULT_CODE_NOT_CHANGED) {// 照⽚没有修改

        }
    }

    @OnClick({R.id.choose_photo_btn, R.id.start_edit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_photo_btn:
                startChoosePhoto();
                break;
            case R.id.start_edit_btn:
                startEdit();
                break;
        }
    }


//    private void enterEditState() {
//        mLogo.setVisibility(View.INVISIBLE);
//        mImage.setVisibility(View.VISIBLE);
//
//        mEditBtn.setBackgroundResource(R.drawable.sdk_sample_rect_btn_enable);
//        mEditBtn.setTextColor(Color.WHITE);
//    }
}
