package com.jh.NUgaller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.jh.NUgaller.FileManageUtils.saveBitmaptoJpg;

public class FileDownloadUtils {

    public static int jsonArrayLen;
    public static String[] fileUril;
    public static String[] fileNameArray;

    static void doGetRequest(final String file_path, String url) throws IOException {
    Request request = new Request.Builder().url(url).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                   @Override
                   public void onFailure(final Call call, IOException e) {
                       // Error
                   } // end of onFailure

                   @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                       String res = response.body().string();

                       try {

                           JSONArray jsonArray = new JSONArray(res);
                           jsonArrayLen = jsonArray.length();

                           System.out.println("         jsonArray 출력    " + jsonArray);
                           System.out.println("jsonArrayLen = " + jsonArrayLen);

                           fileNameArray = new String[jsonArrayLen];
                           fileUril = new String[jsonArrayLen];

                           InputStream[] is = new InputStream[jsonArrayLen];
                           Bitmap[] bitmap = new Bitmap[jsonArrayLen];

                          for (int i = 0; i < jsonArrayLen; i++) {
                           //for (int i = 0; i <= jsonArrayLen; i++) {
                               //if (i != jsonArrayLen) {
                                   System.out.println("i = " + i);
                                   JSONObject imgObj = null;

                                   try {
                                       imgObj = jsonArray.getJSONObject(i);
                                   } catch (JSONException e1) {
                                       e1.printStackTrace();
                                   }

                                   try {
                                       fileNameArray[i] = imgObj.getString("fileName");
                                   } catch (JSONException e1) {
                                       e1.printStackTrace();
                                   }

                                   //System.out.println("fileNameArray[i] = " + fileNameArray[i]);
                                   fileUril[i] = "ip주소/uploads/" + fileNameArray[i];
                                   System.out.println("fileUril[i] :" + fileUril[i]);

                                   is[i] = new java.net.URL(fileUril[i]).openStream();
                                   bitmap[i] = BitmapFactory.decodeStream(is[i]);
                                   saveBitmaptoJpg(bitmap[i], file_path, fileNameArray[i]); // 로컬에 사진 저장
                                   System.out.println("         사진저장 s=    " + i);
                                   }

                       } // end of try
                       catch (JSONException e) {
                           e.printStackTrace();
                       }
                   } } );
                } // end of doGetRequest
}  // end of class
