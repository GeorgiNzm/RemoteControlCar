package org.elsys.remote_control_car.request;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.Request;

import org.elsys.remote_control_car.R;
import org.elsys.remote_control_car.enums.RequestType;

/**
 * Created by georgi on 28.02.18.
 */

public final class OnTouchListenerImpl implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        org.elsys.remote_control_car.request.Request request = null;

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.performClick();
            request = processRequestWhenClicked(view.getId());
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            request = processRequestWhenReleased(view.getId());
        }

        if (request != null) sendRequest(request, view.getContext());

        return request != null;
    }

    private org.elsys.remote_control_car.request.Request processRequestWhenClicked(int id) {

        switch (id) {
            case R.id.forward_btn:
                return RequestFactory.createRequest(RequestType.MOVE_FORWARD);
            case R.id.backward_btn:
                return RequestFactory.createRequest(RequestType.MOVE_BACKWARD);
            case R.id.right_btn:
                return RequestFactory.createRequest(RequestType.STEER_RIGHT);
            case R.id.left_btn:
                return RequestFactory.createRequest(RequestType.STEER_LEFT);
            default:
                return null;
        }
    }

    private org.elsys.remote_control_car.request.Request processRequestWhenReleased(int id) {
        switch (id) {
            case R.id.forward_btn:
            case R.id.backward_btn:
                return RequestFactory.createRequest(RequestType.STOP_MOVEMENT);

            case R.id.right_btn:
            case R.id.left_btn:
                return RequestFactory.createRequest(RequestType.STOP_STEERING);

            default:
                return null;
        }
    }

    private void sendRequest(org.elsys.remote_control_car.request.Request request, Context context) {
        Request<String> stringRequest = request.getStringRequest();

        RequestQueueSingleton.getInstance(context)
                             .addToRequestQueue(stringRequest);
    }

}
