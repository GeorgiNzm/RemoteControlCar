package org.elsys.remote_control_car.request;

import android.content.Context;

/**
 * Created by georgi on 20.05.18.
 */

public final class Util {
    public static void sendRequest(org.elsys.remote_control_car.request.Request request) {
        com.android.volley.Request<String> stringRequest = request.getStringRequest();

        RequestQueueSingleton.getInstance().getRequestQueue().add(stringRequest);
    }
}
