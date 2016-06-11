package com.baidu.commonuselib.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CustomPackageChangedReceiver
 *
 * @author linjunwu
 * @since 2016/6/6
 */
public class CustomPackageChangedReceiver extends BroadcastReceiver {

    private static final String TAG = "CustomPackageChangedReceiver";

    private CopyOnWriteArrayList<PackageChangedListener> mPackageChangedListeners = new CopyOnWriteArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        final String packageName = intent.getData().getSchemeSpecificPart();
        Log.i(TAG, "CustomPackageChangedReceiver-packageName:" + packageName);
        if (packageName == null || packageName.length() == 0) {
            // they sent us a bad intent
            return;
        }

        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            for (PackageChangedListener packageChangedListener:mPackageChangedListeners) {
                packageChangedListener.packageAdded(packageName);
            }
        }


    }

    public void addPackageChangedListener(PackageChangedListener packageChangedListener) {
        mPackageChangedListeners.add(packageChangedListener);
    }

    public void removePackageChangedListener(PackageChangedListener packageChangedListener) {
        mPackageChangedListeners.remove(packageChangedListener);
    }

    public void removeAllPackageChangedListeners() {
        mPackageChangedListeners.removeAll(mPackageChangedListeners);
    }

    public static interface PackageChangedListener {
        public void packageAdded(String packageName);
    }
}
