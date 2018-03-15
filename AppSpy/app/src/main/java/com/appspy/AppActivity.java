package com.appspy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.appspy.Utils.PhoneInfoUtils;
import com.appspy.phone.PhoneInfoItem;

import java.util.List;

public class AppActivity extends BaseActivity {

    private String mPackageName;
    private String mTopActivityName;

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_app);
        super.initViews();

        loadApplicationInfo(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        loadApplicationInfo(intent);
    }

    private void loadApplicationInfo(Intent intent) {
        Bundle bundle = intent.getExtras();
        mPackageName = bundle.getString("packageName");
        mTopActivityName = bundle.getString("topActivityName");

        new Thread(new Runnable() {
            @Override
            public void run() {

                final PhoneInfoItem appInfo = PhoneInfoUtils.getApplication(AppActivity.this, mPackageName);
                final StringBuilder appInfoText = new StringBuilder();
                appInfoText.append("TopActivity: ");
                appInfoText.append(mTopActivityName);
                appInfoText.append("\r\n");
                appInfoText.append(appInfo.toApplicaiton());

                final TextView appContentView = (TextView) findViewById(R.id.application_content);
                appContentView.post(new Runnable() {
                    @Override
                    public void run() {
                        getSupportActionBar().setTitle(appInfo.applicationName);
                        appContentView.setText(appInfoText.toString());
                    }
                });

                final List<PhoneInfoItem> processInfos = PhoneInfoUtils.getProcesses(AppActivity.this, mPackageName);
                final StringBuilder processInfoText = new StringBuilder();
                for (PhoneInfoItem item : processInfos) {
                    processInfoText.append(item.toProcess());
                }
                final TextView processContentView = (TextView) findViewById(R.id.process_content);
                processContentView.post(new Runnable() {
                    @Override
                    public void run() {
                        processContentView.setText(processInfoText.toString());
                    }
                });

                final List<PhoneInfoItem> serviceInfos = PhoneInfoUtils.getServices(AppActivity.this, mPackageName);
                final StringBuilder serviceInfoText = new StringBuilder();
                for (PhoneInfoItem item : serviceInfos) {
                    serviceInfoText.append(item.toService());
                }

                final TextView serviceContentView = (TextView) findViewById(R.id.service_content);
                serviceContentView.post(new Runnable() {
                    @Override
                    public void run() {
                        serviceContentView.setText(serviceInfoText.toString());
                    }
                });

            }
        }).start();
    }
}
