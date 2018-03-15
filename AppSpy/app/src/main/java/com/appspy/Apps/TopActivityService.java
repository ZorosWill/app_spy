package com.appspy.Apps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.appspy.AppActivity;
import com.appspy.R;
import com.appspy.SpyApplication;

public class TopActivityService extends Service {

    private WindowManager mWM = null;
    private ImageView mFloatView = null;
    private WindowManager.LayoutParams mWindowParams = null;
    private int mMoveX;
    private int mMoveY;

    @Override
    public void onCreate() {
        super.onCreate();

        initFloatView();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mWM.removeView(mFloatView);
    }

    public void initFloatView() {
        mWM = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

        mFloatView = (ImageView) View.inflate(this, R.layout.view_float, null);
        mFloatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpyApplication.TopActivityName != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("packageName", SpyApplication.TopActivityPackageName);
                    bundle.putString("topActivityName", SpyApplication.TopActivityName);

                    Intent intent = new Intent(v.getContext(), AppActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "请前往 系统设置->无障碍 中开启Get TopActivity 服务", Toast.LENGTH_LONG).show();
                }
            }
        });
        mFloatView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int startX = (int) event.getRawX();
                int startY = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        mMoveX = (int) event.getX();
                        mMoveY = (int) event.getY();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        mWindowParams.x = startX - mMoveX;
                        mWindowParams.y = startY - mMoveY;
                        //mWM.updateViewLayout(mFloatView, mWindowParams);
                        mMoveX = startX;
                        mMoveY = startY;
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.gravity = Gravity.TOP;
        mWindowParams.type = getFloatWindowType();

        mWM.addView(mFloatView, mWindowParams);
    }

    private int getFloatWindowType() {
        int type = WindowManager.LayoutParams.TYPE_PHONE;
      /*  if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            type = WindowManager.LayoutParams.TYPE_TOAST;
        } else */if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        return type;
    }

    public void showFloatView() {
        mFloatView.setVisibility(View.VISIBLE);
    }

    public void hideFloatView() {
        mFloatView.setVisibility(View.GONE);
    }
}
