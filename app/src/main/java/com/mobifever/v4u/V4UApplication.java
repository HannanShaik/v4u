package com.mobifever.v4u;

import android.app.Application;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class V4UApplication extends Application {

    public static String TAG = V4UApplication.class.getSimpleName();
    private static V4UApplication instance;
    private RequestQueue mRequestQueue;

    public V4UApplication() {
        super();
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Set Debug Logging on or off
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public static V4UApplication getInstance() {
        return instance;
    }

}
