package com.appspy.model;

/**
 * Created by guolu on 2017-06-28.
 */

public class AppRuntime {
    public boolean isRunning = false;
    public String packageName;
    public int pid;
    public int uid;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (!isRunning){
            builder.append("应用未运行");
        } else {
            builder.append("Pid: ");
            builder.append(Integer.toString(pid));
            builder.append("\n");
            builder.append("Uid: ");
            builder.append(Integer.toString(uid));
            builder.append("\n");
        }
        return builder.toString();
    }
}
