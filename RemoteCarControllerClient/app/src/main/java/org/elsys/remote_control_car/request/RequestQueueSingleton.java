package org.elsys.remote_control_car.request;

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

    public static synchronized RequestQueueSingleton getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        requestQueue = Volley.newRequestQueue(this);
        mInstance = this;
    }
}