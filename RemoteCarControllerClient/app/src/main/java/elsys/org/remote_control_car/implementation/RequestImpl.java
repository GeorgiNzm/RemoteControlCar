package elsys.org.remote_control_car.implementation;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import elsys.org.remote_control_car.abstraction.AbstractRequest;

/**
 * Created by georgi on 16.02.18.
 */

public final class RequestImpl extends AbstractRequest {
    public RequestImpl(int method, String url) {
        super(method, url);
    }

    public Request<String> buildRequest() {
        return new StringRequest(
            this.method,
                this.url,
                response -> Log.d("ON-SUCCESS", response),
                error -> Log.d("ON-ERROR", error.getMessage())
        );
    }
}
