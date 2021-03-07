package com.jh.NUgaller;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM";

    // 새로운 메시지를 받았을 때 호출되는 메소드.
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData() != null) {
            //Log.d("FCM Log", "알림 메시지: " + remoteMessage.getNotification().getBody());
            //노티로 받던거 전부 데이터로 받음
            Map<String, String> data = remoteMessage.getData();
            String messageTitle = data.get("title");
            String messageBody = data.get("body");
            String requestData = data.get("request");

            // 알람 받자마자 삭제하고 다운 test
            setDirEmpty("/serverImg/");
            makeNewDir("/serverImg/","http://ip주소:port번호/result");

            Intent intent = new Intent(this, FindActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String channelId = "FindActivity";
            String channelName = "FindActivity";
            String channelDescription = "Find Pictures";
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher_nugaller_round)
                            .setContentTitle(messageTitle)
                            .setContentText(messageBody)
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);
            System.out.println("pendingIntent! " + requestData);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            notificationManager.notify(0, notificationBuilder.build());
        }
    } // end of onMessageReceived

    public void makeNewDir(String file_path, String url_path) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + file_path; //폴더 경로
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성
        //if (!Folder.exists()) {
        Folder.mkdir(); //폴더 생성합니다.
        //Toast.makeText(getApplicationContext(), "폴더가 새로고침 되었습니다 : " + path, Toast.LENGTH_LONG).show();
        System.out.println("폴더가 새로고침 되었습니다"  + path );
        try {
            FileDownloadUtils.doGetRequest(file_path, url_path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // end of makeNewDir

    //
    public static void setDirEmpty(String dirName) {
        String path = Environment.getExternalStorageDirectory().toString() + dirName;
        //String path = dirName;
        File dir = new File(path);
        File[] childFileList = dir.listFiles();
        if (dir.exists()) {
            for (File childFile : childFileList) {
                if (childFile.isDirectory()) {
                    setDirEmpty(childFile.getAbsolutePath()); //하위 디렉토리
                } else {
                    childFile.delete(); //하위 파일
                }
            }
            dir.delete();
            System.out.println("           디렉토리 비움       " + dirName);
        }
    }
}