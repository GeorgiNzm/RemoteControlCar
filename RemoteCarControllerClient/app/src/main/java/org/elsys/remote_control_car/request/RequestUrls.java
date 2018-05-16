package org.elsys.remote_control_car.request;

/**
 * Created by georgi on 19.02.18.
 */

public final class RequestUrls {

    public static final String RPI_STATIC_IP = "192.168.43.173";

    public static final String RPI_RESOURCE = "http://" + RPI_STATIC_IP + ":8080/api/v1";

    public static final String FORWARD_MOVEMENT_URL = RPI_RESOURCE + "/direction/forwardOrBackward/200";
    
    public static final String BACKWARD_MOVEMENT_URL = RPI_RESOURCE + "/direction/forwardOrBackward/-200";

    public static final String STOP_MOVEMENT_URL = RPI_RESOURCE + "/direction/forwardOrBackward/0";

    public static final String STEERING_RIGHT_URL = RPI_RESOURCE + "/direction/leftOrRight/1";

    public static final String STEERING_LEFT_URL = RPI_RESOURCE + "/direction/leftOrRight/1";

    public static final String STOP_STEERING_URL = RPI_RESOURCE + "/direction/leftOrRight/0";

    public static final String GET_SPEED_VALUE_URL = RPI_RESOURCE + "/direction/forwardOrBackward/";

    public static final String GET_STEERING_CONDITION_URL = RPI_RESOURCE + "/direction/leftOrRight/";

    public static final String CAMERA_START_URL = RPI_RESOURCE + "/camera/start";
}
