package org.elsys.remote_control_car.implementation;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by georgi on 15.02.18.
 * Idea for the Sigleton pattern and
 * code fragment is taken from https://developer.android.com/training/volley/requestqueue.html
 */

public class RequestQueueSingleton extends Application {
    private static RequestQueueSingleton mInstance;
    private RequestQueue requestQueue;
    private Context context;

    private RequestQueueSingleton(Context context) {
        context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestQueueSingleton(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}