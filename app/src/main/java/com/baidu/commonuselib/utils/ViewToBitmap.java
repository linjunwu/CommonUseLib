package com.baidu.commonuselib.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ViewToBitmap
 *
 * @author linjunwu
 * @since 2016/6/4
 */
public class ViewToBitmap {

    public static void takeScreenShot(Activity activity, int viewId) {
        View view = activity.getWindow().getDecorView().findViewById(viewId);
        take(activity, view);
    }

    public static void takeScreenShot(Activity activity) {
        take(activity, activity.getWindow().getDecorView());

    }

    private static void take(Activity activity, View view) {

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap obtainBitmap = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        // 获取屏幕长和高
        int width = view.getWidth();
        int height = view.getHeight();

        // 截取当前屏幕，去掉标题栏
        Bitmap bitmap = Bitmap.createBitmap(obtainBitmap, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);
        savePic(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + activity.getClass().getSimpleName() +
                ".png");
    }


    private static void savePic(Bitmap b, String strFileName) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(strFileName);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
