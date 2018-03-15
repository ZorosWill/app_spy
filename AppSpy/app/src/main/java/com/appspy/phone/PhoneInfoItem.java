package com.appspy.phone;

/**
 * Created by guolu on 2018-03-04.
 */

public class PhoneInfoItem {
    public String attribute = null;
    public String value = null;

    public String processName = null;
    public String pid = null;
    public String uid = null;

    public String packageName = null;
    public String applicationName = null;
    public String minSdk = null;
    public String targetSdk = null;

    public String serviceName = null;
    public String foreground = null;
    public String started = null;


    public String toApplicaiton() {
        return "Name: " + applicationName + "\r\nMinSdk: " + minSdk + "\r\nTargetSdk: " + targetSdk + "\r\n";
    }

    public String toProcess() {
        return "Name: " + processName + "\r\nPid: " + pid + "\r\nUid: " + uid + "\r\n";
    }

    public String toService() {
        return "Name: " + serviceName + "\r\nForeground: " + foreground + "\r\nStarted: " + started + "\r\nPid: " + pid + "\r\nUid: " + uid + "\r\n";
    }
}
