package com.sy.screenshotdemoxu.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 创建时间： 2017/9/12 0012.
 * 编写人：Tina
 * 邮箱：1208156801@qq.com
 * 功能描述：
 */
public class ScreenShotService extends Service {
    private String LOG_TAG = ScreenShotService.class.getSimpleName();
    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler.postDelayed(mScreenShotRunnable, 1000);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    private final Runnable mScreenShotRunnable = new Runnable()
    {
        @Override
        public void run() {
            takeScreenShot();
        }
    };
    private Object mScreenShotLock = new Object();
    private ServiceConnection mScreenShotConn = null;
    private void takeScreenShot()
    {
        synchronized (mScreenShotLock) {
            if (mScreenShotConn != null) {
                return;
            }
            Intent intent = new Intent();
            intent.setClassName("com.android.systemui","com.android.systemui.screenshot.TakeScreenshotService");
            mScreenShotConn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name,
                                               IBinder service) {
                    Messenger messenger = new Messenger(service);
                    Message msg = Message.obtain(null, 1);
                    final ServiceConnection myConn = this;
                    Handler h = new Handler(mHandler.getLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            Log.i(LOG_TAG,"onServiceConnected handle reply message");
                            if (myConn == mScreenShotConn) {
                                unbindService(mScreenShotConn);
                                mScreenShotConn = null;
                            }
                        }
                    };
                    msg.replyTo = new Messenger(h);
                    msg.arg1 = msg.arg2 = 0;
                    msg.arg1 = 1;
                    try {
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {}
            };
            boolean ret = bindService(intent, mScreenShotConn, BIND_AUTO_CREATE);
            Log.i(LOG_TAG, "bind service success!");
        }
    }
}
