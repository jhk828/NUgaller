package com.jh.NUgaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Img extends AppCompatActivity {
    ImageView imgVwSelected;

    // 선택한 이미지 삭제
    Button btnImageDelete;

    // 사진 전송
    Button btnImageSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        imgVwSelected = (ImageView)findViewById(R.id.imgVwSelected);

        Intent ImgInten = getIntent(); /*데이터 수신*/
        String filesName = ImgInten.getStringExtra("fileName");
        System.out.println("      filesName in Img Intent =    " + filesName);

        final File imgFile  = new File(filesName);
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        if(imgFile.exists()) {
            ImageView myImage = (ImageView) findViewById(R.id.imgVwSelected);
            myImage.setImageBitmap(myBitmap);
        }

        //여기서 전송
        // 선택된 이미지 전송
        btnImageSend = findViewById(R.id.btnImageSend);
        btnImageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileUploadUtils.goSend(imgFile);
                Toast.makeText(getApplicationContext(), "파일전송 성공: " + imgFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
            }
        });

        // 선택한 이미지 삭제
        btnImageDelete = findViewById(R.id.btnImageDelete);
        btnImageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgFile.exists()) {
                    if (imgFile.delete()) {
                        System.out.println("파일삭제 성공: " + imgFile.getAbsolutePath());
                        Toast.makeText(getApplicationContext(), "파일삭제 성공: " + imgFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

                    } else {
                        System.out.println("파일삭제 실패");
                        Toast.makeText(getApplicationContext(), "파일삭제 실패: " + imgFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    System.out.println("파일이 존재하지 않습니다.");
                    Toast.makeText(getApplicationContext(), "파일이 존재하지 않습니다: " + imgFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                }
            }
        });

    } // end of onCreate

}
