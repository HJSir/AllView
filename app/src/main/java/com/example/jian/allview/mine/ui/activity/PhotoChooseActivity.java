package com.example.jian.allview.mine.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.jian.allview.R;

import java.io.File;

public class PhotoChooseActivity extends FragmentActivity {

              private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
        private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
      private static final int PHOTO_REQUEST_CUT = 3;// 结果



               /* 头像名称 */
           private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
      private File tempFile;

            @Override
     protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_photo_choose);

                Intent getIntent = getIntent();
                int mEmail = getIntent.getIntExtra("mEmail",2);
                System.out.println("memail"+mEmail);
                if(mEmail==0){
                    gallery();
                }else{
                    camera();
                }
            }

             /*
      * 从相册获取
     */
              public void gallery() {
             // 激活系统图库，选择一张图片
         Intent intent = new Intent(Intent.ACTION_PICK);
             intent.setType("image/*");
          // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
              startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
          }

              /*
     * 从相机获取
    */
             public void camera() {
            // 激活相机
               Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
           // 判断存储卡是否可以用，可用进行存储
             if (hasSdcard()) {
                    tempFile = new File(Environment.getExternalStorageDirectory(),
                                  PHOTO_FILE_NAME);
                  // 从文件中创建uri
                   Uri uri = Uri.fromFile(tempFile);
                 intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
           // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
            startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
          }
       /*
     * 剪切图片
      */
          private void crop(Uri uri) {
            // 裁剪图片意图
            Intent intent = new Intent("com.android.camera.action.CROP");
           intent.setDataAndType(uri, "image/*");
             intent.putExtra("crop", "true");
              // 裁剪框的比例，1：1
              intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                // 裁剪后输出图片的尺寸大小
                 intent.putExtra("outputX", 250);
             intent.putExtra("outputY", 250);

                 intent.putExtra("outputFormat", "JPEG");// 图片格式
                intent.putExtra("noFaceDetection", true);// 取消人脸识别
                 intent.putExtra("return-data", true);
                 // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
                startActivityForResult(intent, PHOTO_REQUEST_CUT);
             }

               /*
      * 判断sdcard是否被挂载
      */
                private boolean hasSdcard() {
                if (Environment.getExternalStorageState().equals(
                         Environment.MEDIA_MOUNTED)) {
                        return true;
                    } else {
                         return false;
                     }
            }

                @Override
         protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                                         SharedPreferences preferences=this.getSharedPreferences("user", Context.MODE_PRIVATE);
                     SharedPreferences.Editor editor=preferences.edit();
                 if (requestCode == PHOTO_REQUEST_GALLERY) {
                         // 从相册返回的数据
                         if (data != null) {
                                // 得到图片的全路径
                             Uri uri = data.getData();
//                                crop(uri);

                    editor.putString("Surl", String.valueOf(uri));
                    editor.commit();
                    finish();
                             }

                     } else if (requestCode == PHOTO_REQUEST_CAREMA) {
                         // 从相机返回的数据
                        if (hasSdcard()) {
//                                 crop(Uri.fromFile(tempFile));
                            // 将图片地址存入sharepreference中
                            editor.putString("Surl", String.valueOf(Uri.fromFile(tempFile)));
                            editor.commit();
                            finish();
                             } else {
                                Toast.makeText(PhotoChooseActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                           }
                    }
//                else if (requestCode == PHOTO_REQUEST_CUT) {
                        // 从剪切图片返回的数据
//                        if (data != null) {
             //                   Bitmap bitmap = data.getParcelableExtra("data");
//                                 this.iv_image.setImageBitmap(bitmap);
//
//                            }
//                        try {
//                         // 将临时文件删除
//                                tempFile.delete();
//                             } catch (Exception e) {
//                         e.printStackTrace();
//                            }
//                     SharedPreferences preferences=this.getSharedPreferences("user", Context.MODE_PRIVATE);
//                     SharedPreferences.Editor editor=preferences.edit();
//                     Uri uri = data.getData();
//                     editor.putString("Surl", String.valueOf(uri));
//                     editor.commit();
//                     finish();
//                  }

                super.onActivityResult(requestCode, resultCode, data);
             }
     }


