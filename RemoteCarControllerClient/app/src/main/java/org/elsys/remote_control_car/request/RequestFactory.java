package org.elsys.remote_control_car.request;

import org.elsys.remote_control_car.enums.RequestType;

/**
 * Created by georgi on 18.02.18.
 */

public class RequestFactory {
    public RequestFactory() {}

    public static Request createRequest(RequestType type) {
        Request request = null;
        
        switch (type) {
            case MOVE_FORWARD:
                request = new Request(com.android.volley.Request.Method.POST, RequestUrls.FORWARD_MOVEMENT_URL);
                break;
            case MOVE_BACKWARD:
                request = new Request(com.android.volley.Request.Method.POST, RequestUrls.BACKWARD_MOVEMENT_URL);
                break;
            case STEER_RIGHT:
                request = new Request(com.android.volley.Request.Method.POST, RequestUrls.STEERING_RIGHT_URL);
                break;
            case STEER_LEFT:
                request = new Request(com.android.volley.Request.Method.POST, RequestUrls.STEERING_LEFT_URL);
                break;
            case STOP_MOVEMENT:
                request = new Request(com.android.volley.Request.Method.POST, RequestUrls.STOP_MOVEMENT_URL);
                break;
            case STOP_STEERING:
                request = new Request(com.android.volley.Request.Method.POST, RequestUrls.STOP_STEERING_URL);
                break;
            case GET_SPEED_VALUE:
                request = new Request(com.android.volley.Request.Method.GET, RequestUrls.GET_SPEED_VALUE_URL);
                break;
            case GET_STEERING_CONDITION:
                request = new Request(com.android.volley.Request.Method.GET, RequestUrls.GET_STEERING_CONDITION_URL);
                break;
            case CAMERA_START:
                request = new Request(com.android.volley.Request.Method.GET, RequestUrls.CAMERA_START_URL);
                break;
            case CAMERA_STOP:
                request = new Request(com.android.volley.Request.Method.GET, RequestUrls.CAMERA_STOP_URL);
                break;
        }
        return request;
    }
}
