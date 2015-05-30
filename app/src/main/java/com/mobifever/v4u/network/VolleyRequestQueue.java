package com.mobifever.v4u.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mobifever.v4u.V4UApplication;

/**
 * Created by hannan on 19/11/14.
 */
public class VolleyRequestQueue {

    public static final String TAG = V4UApplication.class
            .getSimpleName();

    private RequestQueue mRequestQueue;

    private static VolleyRequestQueue mInstance;


    public VolleyRequestQueue() {
        mInstance = this;
    }

    public static synchronized VolleyRequestQueue getInstance() {
        if(mInstance == null){
            mInstance = new VolleyRequestQueue();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag, Context context) {
        Log.e(TAG, "adding to req queue");
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue(context).add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, Context context) {
        req.setTag(TAG);
        getRequestQueue(context).add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
