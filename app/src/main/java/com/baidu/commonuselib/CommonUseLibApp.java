package com.baidu.commonuselib;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.DisplayMetrics;
import android.util.Log;

import com.baidu.commonuselib.services.CustomPackageChangedReceiver;


/**
 * CommonUseLibApp
 *
 * @author linjunwu
 * @since 2016/5/23
 */
public class CommonUseLibApp extends Application {

    private static final String TAG = "CommonUseLibApp";

    private static CommonUseLibApp mInstance;

    private CustomPackageChangedReceiver mCustomPackageChangedReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        printScreenInfos();
        registerPackageChangedReceiver();
    }

    private void printScreenInfos() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.i(TAG, "DisplayMetrics:" + displayMetrics);
        Log.i(TAG, "densityDpi:" + displayMetrics.densityDpi);
    }



    private void registerPackageChangedReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        // 注意！！这句必须要加，否则接收不到BroadCast
        filter.addDataScheme("package");
        mCustomPackageChangedReceiver = new CustomPackageChangedReceiver();
        registerReceiver(mCustomPackageChangedReceiver, filter);
    }

    public static CommonUseLibApp getInstance() {
        return mInstance;
    }




    public void addPackageChangedListener(CustomPackageChangedReceiver.PackageChangedListener packageChangedListener) {
        mCustomPackageChangedReceiver.addPackageChangedListener(packageChangedListener);
    }

    public void removePackageChangedListener(CustomPackageChangedReceiver.PackageChangedListener packageChangedListener) {
        mCustomPackageChangedReceiver.removePackageChangedListener(packageChangedListener);
    }

    public void removeAllPackageChangedListeners() {
        mCustomPackageChangedReceiver.removeAllPackageChangedListeners();
    }

}
