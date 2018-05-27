package org.elsys.remote_control_car.request;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by georgi on 16.02.18.
 */

public final class Request {

    private int method;
    private String url;
    private StringRequest request;
    private int statusCode;

    public Request(int method, String url) {
        this.method = method;
        this.url = url;
    }

    public StringRequest getStringRequest() {
        return new StringRequest(
                this.method,
                this.url,
                response -> {
                    Log.i("ON-SUCCESS", response);
                    Log.i("STATUS", String.valueOf(statusCode));
                },
                error -> Log.i("ON-ERROR", error.getMessage())
                ) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
    }}