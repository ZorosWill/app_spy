package com.appspy.Apps;


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
import com.appspy.model.AppRuntime;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppRuntimeFragment extends Fragment {

    private Handler mHandler = new Handler();
    private boolean mIsLoaded = false;
    private View mRootView = null;
    private String mPackageName;

    public AppRuntimeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_app_runtime, container, false);
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPackageName = this.getArguments().getString("packageName");
        if (!mIsLoaded) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    loadApps();
                }
            }).start();
            mIsLoaded = true;
        }
    }

    public void loadApps() {
        final AppRuntime apt =  AppInfoUtils.getRuntime(getActivity(), mPackageName);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                TextView runtimeView = (TextView) mRootView.findViewById(R.id.runtime_info);
                runtimeView.setText(apt.toString());
            }
        });
    }
}
