package org.elsys.remote_control_car.request;

import android.util.Log;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by georgi on 16.02.18.
 */

public final class Request {

    private int method;
    private String url;
    private StringRequest request;

    public Request(int method, String url) {
        this.method = method;
        this.url = url;
    }

    public StringRequest getStringRequest() {
        return new StringRequest(
            this.method,
                this.url,
                response -> Log.d("ON-SUCCESS", response),
                error -> Log.d("ON-ERROR", error.getMessage())
        );
    }
}
