package com.jh.NUgaller;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

// FCM 푸시 알림 추가
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import static com.jh.fileupload.FileManageUtils.setDirEmpty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //FCM Alarm
    private static final String TAG = "FCM";

    static final int PICK_FROM_CAMERA = 1; //카메라 촬영으로 사진 가져오기
    static final int PICK_FROM_ALBUM = 2; //앨범에서 사진 가져오기

    // 앨범4개 화면 추가
    ImageView iv_main1, iv_main2, iv_main3, iv_main4;

    // 텍스트뷰 4개
    TextView textView1, textView2, textView3, textView4;
    TextView textView1_1, textView2_1, textView3_1, textView4_1;

    // 갤러리에서 선택된 전송 사진 이미지뷰
    ImageView imgVwSelected;

    // 전송용 파일 임시 저장
    File tempSelectFile;

    // 선택한 이미지 삭제
    Button btnImageDelete;

    // 사진 전송
    Button btnImageSend;

    // 사진 선택
    Button btnImageSelection;

    // 사진 촬영
    Button btnTakePic;


    //FindActivity로 Intent 변환
    Context context = this;
    Button btnFind;
    Drawable drawableSelfie;

    // 누르면 selfie폴더 생성
    Button makenewdir;

    // 앨범 사진 보기
    Button btnAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_main1 = (ImageView)findViewById(R.id.iv_main1);
        iv_main2 = (ImageView)findViewById(R.id.iv_main2);
        iv_main3 = (ImageView)findViewById(R.id.iv_main3);
        iv_main4 = (ImageView)findViewById(R.id.iv_main4);

        textView1 =(TextView)findViewById(R.id.textView1);
        textView2 =(TextView)findViewById(R.id.textView2);
        textView3 =(TextView)findViewById(R.id.textView3);
        textView4 =(TextView)findViewById(R.id.textView4);

        textView1_1 =(TextView)findViewById(R.id.textView1_1);
        textView2_1 =(TextView)findViewById(R.id.textView2_1);
        textView3_1 =(TextView)findViewById(R.id.textView3_1);
        textView4_1 =(TextView)findViewById(R.id.textView4_1);

        imgVwSelected = (ImageView)findViewById(R.id.imgVwSelected);

        iv_main1.setOnClickListener(this);
        iv_main2.setOnClickListener(this);
        iv_main3.setOnClickListener(this);

        iv_main4.setOnClickListener(this);
        iv_main4.setEnabled(false); // 폴더 만들어지기 전까진 버튼 비활성화

        textView4.setVisibility(View.INVISIBLE);
        textView4_1.setVisibility(View.INVISIBLE);


        Task<InstanceIdResult> id = FirebaseInstanceId.getInstance().getInstanceId();
        id.addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                // 토큰 확인
                if (task.isSuccessful()) {
                    InstanceIdResult result = task.getResult();
                    String id = result.getId();
                    String token = result.getToken();

                    Log.d(TAG, "Token id : " + id);
                    Log.d(TAG, "Token : " + token);

                    System.out.println(" Token id : "  + id  + "  Token : " + token);

                } else {
                    Log.d(TAG, "Token Exception : " + task.getException().toString());
                }
            }
        });

        // 누르면 selfie 폴더 생김
        // drawable 리소스 객체 가져오기
        drawableSelfie = getResources().getDrawable(R.drawable.selfie_thumb);
        makenewdir = findViewById(R.id.makenewdir);
        makenewdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 앨범 잘 만들어지는지
                textView4.setVisibility(View.VISIBLE);
                textView4_1.setVisibility(View.VISIBLE);

                iv_main4.setImageDrawable(drawableSelfie);
                iv_main4.setEnabled(true);
                setDirEmpty("selfie");
                makeNewDir("/selfie/","http://rpi-kyc.iptime.org:9999/my_selfie");
            }
        });

        /**************************** 버튼 클릭 시 *******************************************/

        // 선택된 이미지 전송
        btnImageSend = findViewById(R.id.btnImageSend);
        //btnImageSend.setEnabled(false);
        btnImageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileUploadUtils.goSend(tempSelectFile);
            }
        });

        // 선택한 이미지 삭제
        btnImageDelete = findViewById(R.id.btnImageDelete);
        btnImageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tempSelectFile.exists()) {
                    if (tempSelectFile.delete()) {
                        System.out.println("파일삭제 성공: " + tempSelectFile.getAbsolutePath());
                        //Toast.makeText(getApplicationContext(), "파일삭제 성공: " + tempSelectFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

                    } else {
                        System.out.println("파일삭제 실패");
                        //Toast.makeText(getApplicationContext(), "파일삭제 실패: " + tempSelectFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    System.out.println("파일이 존재하지 않습니다.");
                    //Toast.makeText(getApplicationContext(), "파일이 존재하지 않습니다: " + tempSelectFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                }
            }
        });

        // 사진 촬영 btnTakePic
        btnTakePic = findViewById(R.id.btnTakePic);
        btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 앨범 4개의 이미지뷰 가리기기
                iv_main1.setVisibility(View.INVISIBLE);
                iv_main2.setVisibility(View.INVISIBLE);
                iv_main3.setVisibility(View.INVISIBLE);
                iv_main4.setVisibility(View.INVISIBLE);

                // 텍스트뷰도 가리기
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);
                textView4.setVisibility(View.INVISIBLE);

                textView1_1.setVisibility(View.INVISIBLE);
                textView2_1.setVisibility(View.INVISIBLE);
                textView3_1.setVisibility(View.INVISIBLE);
                textView4_1.setVisibility(View.INVISIBLE);

                btnAlbum.findViewById(View.VISIBLE);

                sendTakePhotoIntent();
            }
        });

        //앨범에서 이미지 선택
        btnImageSelection = findViewById(R.id.btnImageSelection);
        btnImageSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 앨범 4개의 이미지뷰 가리기기
                iv_main1.setVisibility(View.INVISIBLE);
                iv_main2.setVisibility(View.INVISIBLE);
                iv_main3.setVisibility(View.GONE);
                iv_main4.setVisibility(View.GONE);
                //iv_main5.setVisibility(View.GONE);

                // 텍스트뷰도 가리기
                textView1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);

                textView1_1.setVisibility(View.INVISIBLE);
                textView2_1.setVisibility(View.INVISIBLE);
                textView3_1.setVisibility(View.INVISIBLE);
                textView4_1.setVisibility(View.INVISIBLE);

                // 뒤로가기 보이게 하기
                btnAlbum.setVisibility(View.VISIBLE);

                // 인텐트 화면 이동
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_FROM_ALBUM);
            }
        });

       // 앨범 사진 보기
       btnAlbum = findViewById(R.id.btnAlbum);
       btnAlbum.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               imgVwSelected.setImageResource(0);

               // 앨범 4개의 이미지뷰 보여주기
               iv_main1.setVisibility(View.VISIBLE);
               iv_main2.setVisibility(View.VISIBLE);
               iv_main3.setVisibility(View.VISIBLE);

               iv_main4.setImageDrawable(drawableSelfie);
               iv_main4.setEnabled(true);
               iv_main4.setVisibility(View.VISIBLE);

               textView1.setVisibility(View.VISIBLE);
               textView2.setVisibility(View.VISIBLE);
               textView3.setVisibility(View.VISIBLE);
               textView4.setVisibility(View.VISIBLE);

               textView1_1.setVisibility(View.VISIBLE);
               textView2_1.setVisibility(View.VISIBLE);
               textView3_1.setVisibility(View.VISIBLE);
               textView4_1.setVisibility(View.VISIBLE);
           }
       });

        // FindActivity로 전환
        btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setDirEmpty("/serverImg/");
                //makeNewDir("/serverImg/","http://rpi-kyc.iptime.org:9999/result");
                Intent FindIntent = new Intent(context, FindActivity.class);
                startActivity(FindIntent);
            }
        });
    }

    // 사진 찍기
    private void sendTakePhotoIntent() {
        btnAlbum.findViewById(View.VISIBLE);
        // 앨범 4개의 이미지뷰 가리기기
        iv_main1.setVisibility(View.INVISIBLE);
        iv_main2.setVisibility(View.INVISIBLE);
        iv_main3.setVisibility(View.INVISIBLE);
        iv_main4.setVisibility(View.INVISIBLE);

        // 텍스트뷰도 가리기
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);

        textView1_1.setVisibility(View.INVISIBLE);
        textView2_1.setVisibility(View.INVISIBLE);
        textView3_1.setVisibility(View.INVISIBLE);
        textView4_1.setVisibility(View.INVISIBLE);

        btnAlbum.findViewById(View.VISIBLE);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, PICK_FROM_CAMERA);
        }
    }

    //선택된 이미지 파일명 가져오는 함수
    public String getImageNameToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int coluum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(coluum_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        return imgName;
    }

    // 서버 알람이 호출되면 새 폴더 만들기
    private void makeNewDir(String file_path, String url_path) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + file_path; //폴더 경로
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
                Folder.mkdir(); //폴더 생성합니다.
                //Toast.makeText(getApplicationContext(), "폴더가 생성되었습니다 : " + path, Toast.LENGTH_LONG).show();
                System.out.println("폴더가 생성되었습니다"  + path );
                try {
                    // 아예 폴더를 만들 때 사진을 다운함
                    FileDownloadUtils.doGetRequest(file_path, url_path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }else {
            iv_main4.setImageDrawable(drawableSelfie);
            iv_main4.setEnabled(true);
            System.out.println("이미 폴더가 생성되어 있습니다"  + path);
            //Toast.makeText(getApplicationContext(), "이미 폴더가 생성되어 있습니다 : " + path, Toast.LENGTH_LONG).show();
        }
    } // end of makeNewDir

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case PICK_FROM_CAMERA: { // 사진 촬영
                Bundle extras = data.getExtras();
                Bitmap image = (Bitmap) extras.get("data");
                ((ImageView) findViewById(R.id.imgVwSelected)).setImageBitmap(image);

                try {
                    // 선택한 이미지 임시 저장하여 서버로 보내기
                    //파일명은 파일의 "현재 경로 + 날짜 + jpg 확장자"
                    String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
                    // tempSelectFile = new File(Environment.getExternalStorageDirectory() + "/pic01.jpg");
                    tempSelectFile = new File(Environment.getExternalStorageDirectory() + "/selfie/" + date + ".jpg");
                    OutputStream out = new FileOutputStream(tempSelectFile);
                    image.compress(Bitmap.CompressFormat.JPEG, 100, out);

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                //btnImageSend.setEnabled(true);
                break;
            }

            case PICK_FROM_ALBUM: { //갤러리에서 가져오기

                try {
                    //Uri에서 이미지 이름을 얻어온다
                    String name_Str = getImageNameToUri(data.getData());
                    System.out.println("갤러리에서 가져온 사진 저장명:  " + name_Str);

                    // 이미지 데이터를 비트맵으로 받아온다
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    imgVwSelected.setImageBitmap(image_bitmap);

                    // 선택한 이미지 임시 저장하여 서버로 보내기
                    // 파일명은 파일의 "현재 경로 + 파일명" 추출
                    tempSelectFile = new File(Environment.getExternalStorageDirectory() + "/pic/" + name_Str);
                    //tempSelectFile = new File(Environment.getExternalStorageDirectory() + name_Str);
                    OutputStream out = new FileOutputStream(tempSelectFile);
                    image_bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "파일전송 실패: " + tempSelectFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "파일전송 실패: " + tempSelectFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "파일전송 실패: " + tempSelectFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                }
                //btnImageSend.setEnabled(true);
                //Toast.makeText(getApplicationContext(), "파일전송 성공: " + tempSelectFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                break;
            }
        }
    }// end onCreate

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_main1:
                Intent Album1 = new Intent(context, Album1.class);
                startActivity(Album1);
                break;
            case R.id.iv_main2:
                Intent Album2 = new Intent(context, Album2.class);
                startActivity(Album2);
                break;
            case R.id.iv_main3:
                Intent Album3 = new Intent(context, Album3.class);
                startActivity(Album3);
                break;
            case R.id.iv_main4:
                Intent Album4 = new Intent(context, Album4.class);
                startActivity(Album4);
                break;
        }
    }
}
