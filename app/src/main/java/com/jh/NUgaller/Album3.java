package com.jh.NUgaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Album3 extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.swiperefresh_album3layout)
    SwipeRefreshLayout swipeRefresh_Album3Layout;

    Context context = this;
    //Button btnBack;
    TextView textView;

    // 이미지뷰들
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11, iv12, iv13, iv14, iv15, iv16, iv17, iv18, iv19, iv20;

    // 이미지 인덱스 오류
    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/음식";
    File directory = new File(path);
    File[] files = directory.listFiles();
    List<String> filesNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album3);
        ButterKnife.bind(this);

        textView =(TextView)findViewById(R.id.textView);
        // 이미지뷰들
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);
        iv6 = (ImageView) findViewById(R.id.iv6);
        iv7 = (ImageView) findViewById(R.id.iv7);
        iv8 = (ImageView) findViewById(R.id.iv8);
        iv9 = (ImageView) findViewById(R.id.iv9);
        iv10 = (ImageView) findViewById(R.id.iv10);
        iv11 = (ImageView) findViewById(R.id.iv11);
        iv12 = (ImageView) findViewById(R.id.iv12);
        iv13 = (ImageView) findViewById(R.id.iv13);
        iv14 = (ImageView) findViewById(R.id.iv14);
        iv15 = (ImageView) findViewById(R.id.iv15);
        iv16 = (ImageView) findViewById(R.id.iv16);
        iv17 = (ImageView) findViewById(R.id.iv17);
        iv18 = (ImageView) findViewById(R.id.iv18);
        iv19 = (ImageView) findViewById(R.id.iv19);
        iv20 = (ImageView) findViewById(R.id.iv20);

        //  찾은 사진 눌러서 크게 보기
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        iv7.setOnClickListener(this);
        iv8.setOnClickListener(this);
        iv9.setOnClickListener(this);
        iv10.setOnClickListener(this);
        iv11.setOnClickListener(this);
        iv12.setOnClickListener(this);
        iv13.setOnClickListener(this);
        iv14.setOnClickListener(this);
        iv15.setOnClickListener(this);
        iv16.setOnClickListener(this);
        iv17.setOnClickListener(this);
        iv18.setOnClickListener(this);
        iv19.setOnClickListener(this);
        iv20.setOnClickListener(this);

        // 일단 버튼 비활성화
        iv1.setEnabled(false); // 버튼 비활성화
        iv2.setEnabled(false); // 버튼 비활성화
        iv3.setEnabled(false); // 버튼 비활성화
        iv4.setEnabled(false); // 버튼 비활성화
        iv5.setEnabled(false); // 버튼 비활성화
        iv6.setEnabled(false); // 버튼 비활성화
        iv7.setEnabled(false); // 버튼 비활성화
        iv8.setEnabled(false); // 버튼 비활성화
        iv9.setEnabled(false); // 버튼 비활성화
        iv10.setEnabled(false); // 버튼 비활성화
        iv11.setEnabled(false); // 버튼 비활성화
        iv12.setEnabled(false); // 버튼 비활성화
        iv13.setEnabled(false); // 버튼 비활성화
        iv14.setEnabled(false); // 버튼 비활성화
        iv15.setEnabled(false); // 버튼 비활성화
        iv16.setEnabled(false); // 버튼 비활성화
        iv17.setEnabled(false); // 버튼 비활성화
        iv18.setEnabled(false); // 버튼 비활성화
        iv19.setEnabled(false); // 버튼 비활성화
        iv20.setEnabled(false); // 버튼 비활성화

        /**************************************파일 목룍 조회**********************************************/
        for (int i = 0; i < files.length; i++) {
            filesNameList.add(path + "/" + files[i].getName());
            System.out.println("i= " + i + " files[i].getName() = " + files[i].getName());
        } // end of for

        System.out.println("        filesNameList     " + filesNameList);

        try {
            /**************************************파일 목룍 조회**********************************************/
            for (int i = 0; i < files.length; i++) {
                filesNameList.add(path + "/" + files[i].getName());
                System.out.println("i= " + i + " files[i].getName() = " + files[i].getName());
            } // end of for

            System.out.println("        filesNameList     " + filesNameList);


            /***************************파일 이미지 경로를 이미지뷰에 넣기기*********************************/
            File[] imgFile  = new File[files.length];
            Bitmap[] myBitmap = new Bitmap[files.length];

            for (int j=0 ; j < files.length; j++) {
                imgFile[j] = new File(filesNameList.get(j));
                if(imgFile[j].exists()){
                    myBitmap[j] = BitmapFactory.decodeFile(imgFile[j].getAbsolutePath());

                    if (j==0) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv1);
                        iv1.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[0]);
                    }
                    else if (j==1) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv2);
                        iv2.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[1]);
                    }
                    else if (j==2) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv3);
                        iv3.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[2]);
                    }
                    else if (j==3) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv4);
                        iv4.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[3]);
                    }
                    else if (j==4) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv5);
                        iv5.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[4]);
                    }
                    else if (j==5) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv6);
                        iv6.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[5]);
                    }
                    else if (j==6) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv7);
                        iv7.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[6]);
                    }
                    else if (j==7) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv8);
                        iv8.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[7]);
                    }
                    else if (j==8) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv9);
                        iv9.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[8]);
                    }
                    else if (j==9) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv10);
                        iv10.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[9]);
                    }
                    else if (j==10) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv11);
                        iv11.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[10]);
                    }
                    else if (j==11) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv12);
                        iv12.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[11]);
                    }
                    else if (j==12) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv13);
                        iv13.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[12]);
                    }
                    else if (j==13) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv14);
                        iv14.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[13]);
                    }
                    else if (j==14) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv15);
                        iv15.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[14]);
                    }
                    else if (j==15) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv16);
                        iv16.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[15]);
                    }
                    else if (j==16) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv17);
                        iv17.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[16]);
                    }
                    else if (j==17) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv18);
                        iv18.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[17]);
                    }
                    else if (j==18) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv19);
                        iv19.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[18]);
                    }
                    else if (j==19) {
                        ImageView myImage = (ImageView) findViewById(R.id.iv20);
                        iv20.setEnabled(true); // 버튼 활성화
                        myImage.setImageBitmap(myBitmap[19]);
                    }
                }
            } // end of for
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        //refresh 리스너 등록
        swipeRefresh_Album3Layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // 파일 목록 새로 받아오기
                File directory = new File(path);
                File[] files = directory.listFiles();
                List<String> filesNameList = new ArrayList<>();

                System.out.println("           selfie Album3 새로고침");

                iv1.setImageResource(0);
                iv2.setImageResource(0);
                iv3.setImageResource(0);
                iv4.setImageResource(0);
                iv5.setImageResource(0);
                iv6.setImageResource(0);
                iv7.setImageResource(0);
                iv8.setImageResource(0);
                iv9.setImageResource(0);
                iv10.setImageResource(0);
                iv11.setImageResource(0);
                iv12.setImageResource(0);
                iv13.setImageResource(0);
                iv14.setImageResource(0);
                iv15.setImageResource(0);
                iv16.setImageResource(0);
                iv17.setImageResource(0);
                iv18.setImageResource(0);
                iv19.setImageResource(0);
                iv20.setImageResource(0);

                try {
                    /**************************************파일 목룍 조회**********************************************/
                    for (int i = 0; i < files.length; i++) {
                        filesNameList.add(path + "/" + files[i].getName());
                        System.out.println("i= " + i + " files[i].getName() = " + files[i].getName());
                    } // end of for

                    System.out.println("        filesNameList     " + filesNameList);

                    /***************************파일 이미지 경로를 이미지뷰에 넣기기*********************************/
                    File[] imgFile  = new File[files.length];
                    Bitmap[] myBitmap = new Bitmap[files.length];

                    for (int j=0 ; j < files.length; j++) {
                        imgFile[j] = new File(filesNameList.get(j));
                        if(imgFile[j].exists()){
                            myBitmap[j] = BitmapFactory.decodeFile(imgFile[j].getAbsolutePath());

                            if (j==0) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv1);
                                iv1.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[0]);
                            }
                            else if (j==1) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv2);
                                iv2.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[1]);
                            }
                            else if (j==2) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv3);
                                iv3.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[2]);
                            }
                            else if (j==3) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv4);
                                iv4.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[3]);
                            }
                            else if (j==4) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv5);
                                iv5.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[4]);
                            }
                            else if (j==5) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv6);
                                iv6.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[5]);
                            }
                            else if (j==6) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv7);
                                iv7.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[6]);
                            }
                            else if (j==7) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv8);
                                iv8.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[7]);
                            }
                            else if (j==8) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv9);
                                iv9.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[8]);
                            }
                            else if (j==9) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv10);
                                iv10.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[9]);
                            }
                            else if (j==10) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv11);
                                iv11.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[10]);
                            }
                            else if (j==11) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv12);
                                iv12.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[11]);
                            }
                            else if (j==12) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv13);
                                iv13.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[12]);
                            }
                            else if (j==13) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv14);
                                iv14.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[13]);
                            }
                            else if (j==14) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv15);
                                iv15.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[14]);
                            }
                            else if (j==15) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv16);
                                iv16.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[15]);
                            }
                            else if (j==16) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv17);
                                iv17.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[16]);
                            }
                            else if (j==17) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv18);
                                iv18.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[17]);
                            }
                            else if (j==18) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv19);
                                iv19.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[18]);
                            }
                            else if (j==19) {
                                ImageView myImage = (ImageView) findViewById(R.id.iv20);
                                iv20.setEnabled(true); // 버튼 활성화
                                myImage.setImageBitmap(myBitmap[19]);
                            }
                        }
                    } // end of for
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
                catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

                // animation을 멈추려면, fasle로 설정
                swipeRefresh_Album3Layout.setRefreshing(false);
            }
        });

    } // end of onCreate

    @Override
    public void onClick(View v) {

        Intent ImgIntent = new Intent(context, Img.class);

        directory = new File(path);
        files = directory.listFiles();

        filesNameList = new ArrayList<>();

        /**************************************파일 목룍 조회**********************************************/
        for (int i = 0; i < files.length; i++) {
            filesNameList.add(path + "/" + files[i].getName());
            System.out.println("i= " + i + " files[i].getName() = " + files[i].getName());
        } // end of for

        System.out.println("                     onclick        filesNameList     " + filesNameList);

        try {
            switch (v.getId()) {
                case R.id.iv1:
                    ImgIntent.putExtra("fileName",filesNameList.get(0));
                    System.out.println("Intent Img      filesNameList.get(0) "  +   filesNameList.get(0));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv2:
                    ImgIntent.putExtra("fileName",filesNameList.get(1));
                    System.out.println("Intent Img      filesNameList.get(1) "  +   filesNameList.get(1));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv3:
                    ImgIntent.putExtra("fileName",filesNameList.get(2));
                    System.out.println("Intent Img      filesNameList.get(2) "  +   filesNameList.get(2));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv4:
                    ImgIntent.putExtra("fileName",filesNameList.get(3));
                    System.out.println("Intent Img      filesNameList.get(3) "  +   filesNameList.get(3));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv5:
                    ImgIntent.putExtra("fileName",filesNameList.get(4));
                    System.out.println("Intent Img      filesNameList.get(4) "  +   filesNameList.get(4));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv6:
                    ImgIntent.putExtra("fileName",filesNameList.get(5));
                    System.out.println("Intent Img      filesNameList.get(5) "  +   filesNameList.get(5));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv7:
                    ImgIntent.putExtra("fileName",filesNameList.get(6));
                    System.out.println("Intent Img      filesNameList.get(6) "  +   filesNameList.get(6));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv8:
                    ImgIntent.putExtra("fileName",filesNameList.get(7));
                    System.out.println("Intent Img      filesNameList.get(7) "  +   filesNameList.get(7));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv9:
                    ImgIntent.putExtra("fileName",filesNameList.get(8));
                    System.out.println("Intent Img      filesNameList.get(8) "  +   filesNameList.get(8));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv10:
                    ImgIntent.putExtra("fileName",filesNameList.get(9));
                    System.out.println("Intent Img      filesNameList.get(9) "  +   filesNameList.get(9));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv11:
                    ImgIntent.putExtra("fileName",filesNameList.get(10));
                    System.out.println("Intent Img      filesNameList.get(10) "  +   filesNameList.get(10));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv12:
                    ImgIntent.putExtra("fileName",filesNameList.get(11));
                    System.out.println("Intent Img      filesNameList.get(11) "  +   filesNameList.get(11));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv13:
                    ImgIntent.putExtra("fileName",filesNameList.get(12));
                    System.out.println("Intent Img      filesNameList.get(12) "  +   filesNameList.get(12));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv14:
                    ImgIntent.putExtra("fileName",filesNameList.get(13));
                    System.out.println("Intent Img      filesNameList.get(13) "  +   filesNameList.get(13));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv15:
                    ImgIntent.putExtra("fileName",filesNameList.get(14));
                    System.out.println("Intent Img      filesNameList.get(14) "  +   filesNameList.get(14));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv16:
                    ImgIntent.putExtra("fileName",filesNameList.get(15));
                    System.out.println("Intent Img      filesNameList.get(15) "  +   filesNameList.get(15));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv17:
                    ImgIntent.putExtra("fileName",filesNameList.get(16));
                    System.out.println("Intent Img      filesNameList.get(16) "  +   filesNameList.get(16));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv18:
                    ImgIntent.putExtra("fileName",filesNameList.get(17));
                    System.out.println("Intent Img      filesNameList.get(17) "  +   filesNameList.get(17));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv19:
                    ImgIntent.putExtra("fileName",filesNameList.get(18));
                    System.out.println("Intent Img      filesNameList.get(18) "  +   filesNameList.get(18));
                    startActivity(ImgIntent);
                    break;

                case R.id.iv20:
                    ImgIntent.putExtra("fileName",filesNameList.get(19));
                    System.out.println("Intent Img      filesNameList.get(19) "  +   filesNameList.get(19));
                    startActivity(ImgIntent);
                    break;

            } // end of switch
        } // end of try
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    } // end of onClick

} // end of class
