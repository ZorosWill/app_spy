package com.appspy.Utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import java.lang.reflect.Field;

/**
 * Created by guolu on 2017-06-25.
 */
public class PhoneInfoUtils {

    private static final String TAG = PhoneInfoUtils.class.getName();

    public static String getBuild() {
        try {
            Class cls = ClassLoader.getSystemClassLoader().loadClass("android.os.Build");
            try {
                Object build = cls.newInstance();
                Field[] fields = cls.getFields();
                final StringBuilder builderInfo = new StringBuilder();
                for (Field field : fields) {
                    Object value = field.get(build);
                    builderInfo.append(field.getName());
                    builderInfo.append("：");
                    builderInfo.append(value == null ? "UNKNOWN" : value.toString());
                    builderInfo.append("\n");
                }
                return builderInfo.toString();
            } catch (java.lang.InstantiationException e) {
                Log.e(TAG, e.getMessage());
            } catch (IllegalAccessException e) {
                Log.e(TAG, e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static boolean isRooted() {
        return (new Root()).isDeviceRooted();
    }

    public static int[] getScreenInfo(Activity act) {
        DisplayMetrics dm = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return new int[]{dm.widthPixels, dm.heightPixels, dm.densityDpi};
    }
}
