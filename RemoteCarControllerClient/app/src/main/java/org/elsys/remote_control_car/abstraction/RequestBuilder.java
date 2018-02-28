package org.elsys.remote_control_car.abstraction;

import com.android.volley.Request;

/**
 * Created by georgi on 16.02.18.
 */

public interface RequestBuilder {
    Request<String> buildRequest();
}
