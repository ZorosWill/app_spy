package com.appspy;

import com.appspy.Apps.AppsFragment;

public class AppsActivity extends BaseActivity {

    protected void initViews(){
        setContentView(R.layout.activity_apps);
        getSupportFragmentManager().beginTransaction().add(R.id.content, new AppsFragment()).commit();
        super.initViews();
    }
}
