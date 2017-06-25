package com.appspy.Utils;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.appspy.Apps.AppItem;

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
}
