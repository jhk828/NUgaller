package com.jh.NUgaller;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManageUtils {

    public static void saveBitmaptoJpg(Bitmap bitmap, String file_path, String name) {

        String ex_storage = Environment.getExternalStorageDirectory().getAbsolutePath(); // Get Absolute Path in External Sdcard
        String _file_path = file_path;
        String _name = name;
        String string_path = ex_storage + _file_path;

        try {
            FileOutputStream out = new FileOutputStream(string_path + _name);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
            out.close();
        } catch (FileNotFoundException exception) {
            Log.e("FileNotFoundException", exception.getMessage());
        } catch (IOException exception) {
            Log.e("IOException", exception.getMessage());
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

    } // end of saveBitmaptoJpg

    public static void setDirEmpty(String dirName){
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
            } dir.delete();
            System.out.println("           디렉토리 비움       " + dirName);
        }
    }



}
