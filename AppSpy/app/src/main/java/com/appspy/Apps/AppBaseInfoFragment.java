package com.appspy.Apps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appspy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppBaseInfoFragment extends Fragment {


    public AppBaseInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_base_info, container, false);
    }

}