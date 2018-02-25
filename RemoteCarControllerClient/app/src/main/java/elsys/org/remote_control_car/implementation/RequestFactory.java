package elsys.org.remote_control_car.implementation;

import com.android.volley.Request;

import elsys.org.remote_control_car.abstraction.AbstractRequest;
import elsys.org.remote_control_car.enums.RequestType;

/**
 * Created by georgi on 18.02.18.
 */

public class RequestFactory {
    public RequestFactory() {}

    public static AbstractRequest getInstance(RequestType type) {
        AbstractRequest request = null;
        
        switch (type) {
            case MOVE_FORWARD:
                request = new RequestImpl(Request.Method.POST, RequestUrls.FORWARD_MOVEMENT_URL);
            case MOVE_BACKWARD:
                request = new RequestImpl(Request.Method.POST, RequestUrls.BACKWARD_MOVEMENT_URL);
            case STEER_RIGHT:
                request = new RequestImpl(Request.Method.POST, RequestUrls.STEERING_RIGHT_URL);
            case STEER_LEFT:
                request = new RequestImpl(Request.Method.POST, RequestUrls.STEERING_LEFT_URL);
            case STOP_MOVEMENT:
                request = new RequestImpl(Request.Method.POST, RequestUrls.STOP_MOVEMENT_URL);
            case STOP_STEERING:
                request = new RequestImpl(Request.Method.POST, RequestUrls.STOP_STEERING_URL);
        }
        return request;
    }
}
