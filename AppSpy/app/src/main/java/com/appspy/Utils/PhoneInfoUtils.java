package com.appspy.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import com.appspy.phone.PhoneInfoItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guolu on 2017-06-25.
 */
public class PhoneInfoUtils {

    private static final String TAG = PhoneInfoUtils.class.getName();

    public static List<PhoneInfoItem> getBuild() {
        List<PhoneInfoItem> phoneInfoItems = new ArrayList<>();

        try {
            Class cls = ClassLoader.getSystemClassLoader().loadClass("android.os.Build");
            try {
                Object build = cls.newInstance();
                Field[] fields = cls.getFields();
                for (Field field : fields) {
                    Object value = field.get(build);

                    PhoneInfoItem item = new PhoneInfoItem();
                    item.attribute = field.getName();
                    item.value = value == null ? "UNKNOWN" : value.toString();
                    phoneInfoItems.add(item);
                }
            } catch (java.lang.InstantiationException e) {
                Log.e(TAG, e.getMessage());
            } catch (IllegalAccessException e) {
                Log.e(TAG, e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return phoneInfoItems;
    }

    public static List<PhoneInfoItem> getExtra(Activity activity) {
        List<PhoneInfoItem> phoneInfoItems = new ArrayList<>();
        PhoneInfoItem item = new PhoneInfoItem();
        item.attribute = "Root";
        item.value = PhoneInfoUtils.isRooted() ? "Yes" : "No";
        phoneInfoItems.add(item);

        int screenSize[] = PhoneInfoUtils.getScreenInfo(activity);

        item = new PhoneInfoItem();
        item.attribute = "Width";
        item.value = Integer.toString(screenSize[0]);
        phoneInfoItems.add(item);

        item = new PhoneInfoItem();
        item.attribute = "Height";
        item.value = Integer.toString(screenSize[1]);
        phoneInfoItems.add(item);

        item = new PhoneInfoItem();
        item.attribute = "Height";
        item.value = Integer.toString(screenSize[2]);
        phoneInfoItems.add(item);

        item = new PhoneInfoItem();
        item.attribute = "Sdk";
        item.value = Integer.toString(Build.VERSION.SDK_INT);
        phoneInfoItems.add(item);

        return phoneInfoItems;
    }

    private static boolean isRooted() {
        return (new Root()).isDeviceRooted();
    }

    private static int[] getScreenInfo(Activity act) {
        DisplayMetrics dm = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return new int[]{dm.widthPixels, dm.heightPixels, dm.densityDpi};
    }

    public static List<PhoneInfoItem> getProcesses(Activity ctx) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> proInfoList = am.getRunningAppProcesses();
        List<PhoneInfoItem> phoneInfoItems = new ArrayList<>();

        PhoneInfoItem item = new PhoneInfoItem();
        item.processName = "进程名";
        item.pid = "Pid";
        item.uid = "Uid";
        phoneInfoItems.add(item);

        for (ActivityManager.RunningAppProcessInfo proInfo : proInfoList) {
            item = new PhoneInfoItem();
            item.processName = proInfo.processName;
            item.pid = Integer.toString(proInfo.pid);
            item.uid = Integer.toString(proInfo.uid);
            phoneInfoItems.add(item);
        }
        return phoneInfoItems;
    }

    public static List<PhoneInfoItem> getProcesses(Activity ctx, String packageName) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> proInfoList = am.getRunningAppProcesses();
        List<PhoneInfoItem> phoneInfoItems = new ArrayList<>();

        for (ActivityManager.RunningAppProcessInfo proInfo : proInfoList) {
            for (String name : proInfo.pkgList) {
                if (name.equals(packageName)) {
                    PhoneInfoItem item = new PhoneInfoItem();
                    item.processName = proInfo.processName;
                    item.pid = Integer.toString(proInfo.pid);
                    item.uid = Integer.toString(proInfo.uid);
                    phoneInfoItems.add(item);
                }
            }
        }
        return phoneInfoItems;
    }

    public static List<PhoneInfoItem> getApps(Activity ctx) {
        List<ApplicationInfo> applicationInfoList = ctx.getPackageManager().getInstalledApplications(PackageManager.MATCH_ALL);
        List<PhoneInfoItem> phoneInfoItems = new ArrayList<>();

        PhoneInfoItem item = new PhoneInfoItem();
        item.applicationName = "包名";
        item.minSdk = "MinSdk";
        item.targetSdk = "TargetSdk";
        phoneInfoItems.add(item);

        for (ApplicationInfo appInfo : applicationInfoList) {
            item = new PhoneInfoItem();
            item.packageName = appInfo.packageName;
            item.applicationName = appInfo.packageName;
            item.minSdk = Integer.toString(appInfo.minSdkVersion);
            item.targetSdk = Integer.toString(appInfo.targetSdkVersion);
            phoneInfoItems.add(item);
        }
        return phoneInfoItems;
    }

    public static PhoneInfoItem getApplication(Activity ctx, String packageName) {
        List<ApplicationInfo> applicationInfoList = ctx.getPackageManager().getInstalledApplications(PackageManager.MATCH_ALL);

        PhoneInfoItem item = new PhoneInfoItem();
        for (ApplicationInfo appInfo : applicationInfoList) {
            if (appInfo.packageName.equals(packageName)) {
                item.packageName = appInfo.packageName;
                item.applicationName = appInfo.packageName;
                item.minSdk = Integer.toString(appInfo.minSdkVersion);
                item.targetSdk = Integer.toString(appInfo.targetSdkVersion);
                break;
            }
        }
        return item;
    }

    public static List<PhoneInfoItem> getServices(Activity ctx) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> svrInfoList = am.getRunningServices(Integer.MAX_VALUE);
        List<PhoneInfoItem> phoneInfoItems = new ArrayList<>();

        PhoneInfoItem item = new PhoneInfoItem();
        item.serviceName = "服务名";
        item.pid = "Pid";
        item.uid = "Uid";
        item.foreground = "Foreground";
        item.started = "Started";

        phoneInfoItems.add(item);

        for (ActivityManager.RunningServiceInfo svrInfo : svrInfoList) {
            item = new PhoneInfoItem();
            item.serviceName = svrInfo.service.getClassName();
            item.pid = Integer.toString(svrInfo.pid);
            item.uid = Integer.toString(svrInfo.uid);
            item.foreground = svrInfo.foreground ? "Y" : "N";
            item.started = svrInfo.started ? "Y" : "N";
            phoneInfoItems.add(item);
        }
        return phoneInfoItems;
    }

    public static List<PhoneInfoItem> getServices(Activity ctx, String packageName) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> svrInfoList = am.getRunningServices(Integer.MAX_VALUE);
        List<PhoneInfoItem> phoneInfoItems = new ArrayList<>();

        for (ActivityManager.RunningServiceInfo svrInfo : svrInfoList) {
            if (svrInfo.service.getPackageName().equals(packageName)) {
                PhoneInfoItem item = new PhoneInfoItem();
                item.serviceName = svrInfo.service.getClassName();
                item.pid = Integer.toString(svrInfo.pid);
                item.uid = Integer.toString(svrInfo.uid);
                item.foreground = svrInfo.foreground ? "Y" : "N";
                item.started = svrInfo.started ? "Y" : "N";
                phoneInfoItems.add(item);
            }
        }
        return phoneInfoItems;
    }
}
