package elsys.org.remote_control_car.abstraction;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by georgi on 16.02.18.
 */

public abstract class AbstractRequest implements RequestBuilder {
    protected int method;
    protected String url;
    protected StringRequest request;

    public AbstractRequest(int method, String url) {
        this.method = method;
        this.url = url;
    }

    @Override
    public abstract Request<String> buildRequest();
}
