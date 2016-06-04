package com.baidu.commonuselib.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.io.File;
import java.util.List;

/**
 * AppUtils
 *
 * @author linjunwu
 * @since 2016/6/3
 */
public class AppUtils {

    public static void startApp(Context context,
                                String packageName) {

        PackageManager pm = context.getPackageManager();
        try {
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> apps = pm.queryIntentActivities(mainIntent, 0);
            for (ResolveInfo info : apps) {
                if (packageName.equals(info.activityInfo.applicationInfo.packageName)) {
                    ComponentName componentName = new ComponentName(info.activityInfo.applicationInfo.packageName, info.activityInfo.name);
                    mainIntent.setComponent(componentName);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(mainIntent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static  void installAPK(Context context, String apkFilePath) {
        // TODO Auto-generated method stub

        // 创建URI
        Uri uri = Uri.fromFile(new File(apkFilePath));
        // 创建Intent意图
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 设置Uri和类型
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        // 执行意图进行安装
        context.startActivity(intent);
    }

    public void uninstallAPK(Context context, String packageName) {
        // TODO Auto-generated method stub
        // 通过程序的报名创建URI
        Uri packageURI = Uri.parse("package:" + packageName);
        // 创建Intent意图
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(packageURI);
        // 执行卸载程序
        context.startActivity(intent);

    }
}
