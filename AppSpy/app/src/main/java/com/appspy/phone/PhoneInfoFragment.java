package com.appspy.phone;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appspy.R;
import com.appspy.Utils.AppInfoUtils;
import com.appspy.Utils.PhoneInfoUtils;

public class PhoneInfoFragment extends Fragment {
    private Handler mHandler = new Handler();
    private View mRootView = null;
    private boolean mIsLoaded = false;

    public PhoneInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_phone_info, container, false);
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!mIsLoaded) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    loadPhoneInfo();
                    loadExtraInfo();
                    loadProcessesInfo();
                }
            }).start();
            mIsLoaded = true;
        }
    }

    public void loadPhoneInfo() {
        final String buildInfo = PhoneInfoUtils.getBuild();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                TextView build = (TextView) mRootView.findViewById(R.id.builder_info);
                build.setText(buildInfo);
            }
        });
    }

    public void loadExtraInfo() {
        final StringBuilder extra = new StringBuilder();

        extra.append("Root：");
        extra.append(PhoneInfoUtils.isRooted() ? "Yes" : "No");
        extra.append("\n");

        int screenSize[] = PhoneInfoUtils.getScreenInfo(this.getActivity());
        extra.append("Width：");
        extra.append(Integer.toString(screenSize[0]));
        extra.append("\n");
        extra.append("Height：");
        extra.append(Integer.toString(screenSize[1]));
        extra.append("\n");
        extra.append("Dpi：");
        extra.append(Integer.toString(screenSize[2]));
        extra.append("\n");

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                TextView build = (TextView) mRootView.findViewById(R.id.extra_info);
                build.setText(extra);
            }
        });
    }

    public void loadProcessesInfo() {
        final String processesInfo = AppInfoUtils.getProcesses(getActivity());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                TextView build = (TextView) mRootView.findViewById(R.id.processes_info);
                build.setText(processesInfo);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
