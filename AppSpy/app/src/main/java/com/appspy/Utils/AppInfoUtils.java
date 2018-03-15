package com.appspy.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.appspy.Apps.AppItem;
import com.appspy.model.AppRuntime;
import com.appspy.phone.PhoneInfoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guolu on 2017-06-25.
 */

public class AppInfoUtils {

    public static List<AppItem> getInstalledApps(Activity act, boolean userAppPOnly) {
        List<PackageInfo> packages = act.getPackageManager().getInstalledPackages(0);
        List<AppItem> itemList = new ArrayList<>();

        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppItem item = new AppItem();
            item.name = packageInfo.applicationInfo.loadLabel(act.getPackageManager()).toString();
            item.packageName = packageInfo.packageName;
            item.version = packageInfo.versionName;
            item.icon = packageInfo.applicationInfo.loadIcon(act.getPackageManager());
            item.isSystem = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0;
            if (userAppPOnly && item.isSystem) {
                itemList.add(item);
            }
        }
        return itemList;
    }

    public static AppRuntime getRuntime(Activity ctx, String packageName) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfoList = am.getRunningAppProcesses();
        AppRuntime apt = new AppRuntime();
        for (ActivityManager.RunningAppProcessInfo procInfo : procInfoList) {
            if (procInfo.processName.equals(packageName)) {
                apt.isRunning = true;
                apt.packageName = packageName;
                apt.pid = procInfo.pid;
                apt.uid = procInfo.uid;
                break;
            }
        }
        return apt;
    }
}
