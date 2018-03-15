package com.appspy.phone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appspy.Apps.AppItem;
import com.appspy.R;
import com.appspy.Utils.PhoneInfoUtils;


public class PhoneInfoFragment extends Fragment {
    private RecyclerView mRecyclerView = null;
    private String mInfoName = null;

    public PhoneInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mInfoName = this.getArguments().getString("infoName");
        new Thread(new Runnable() {
            @Override
            public void run() {
                final PhoneInfoRecyclerViewAdapter adapter;
                Activity activity = PhoneInfoFragment.this.getActivity();
                if ("Build".equals(mInfoName)) {
                    adapter = new PhoneInfoRecyclerViewAdapter(PhoneInfoUtils.getBuild(), PhoneInfoType.BUILD);
                } else if ("Extra".equals(mInfoName)) {
                    adapter = new PhoneInfoRecyclerViewAdapter(PhoneInfoUtils.getExtra(activity), PhoneInfoType.EXTRA);
                } else if ("Processes".equals(mInfoName)) {
                    adapter = new PhoneInfoRecyclerViewAdapter(PhoneInfoUtils.getProcesses(activity), PhoneInfoType.PROCESSES);
                } else if ("Applications".equals(mInfoName)) {
                    adapter = new PhoneInfoRecyclerViewAdapter(PhoneInfoUtils.getApps(activity), PhoneInfoType.APPLICATIONS);
                } else if ("Services".equals(mInfoName)) {
                    adapter = new PhoneInfoRecyclerViewAdapter(PhoneInfoUtils.getServices(activity), PhoneInfoType.SERVICES);
                } else {
                    adapter = null;
                }
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter != null) {
                            mRecyclerView.setAdapter(adapter);
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_phone_info, container, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        return mRecyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(AppItem item);
    }
}
