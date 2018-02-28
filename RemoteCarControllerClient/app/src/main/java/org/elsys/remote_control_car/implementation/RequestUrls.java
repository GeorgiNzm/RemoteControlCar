package org.elsys.remote_control_car.implementation;

/**
 * Created by georgi on 19.02.18.
 */

public class RequestUrls {
    private static final String RPI_URL = "http://169.254.148.138:8080";

    public static final String FORWARD_MOVEMENT_URL = RPI_URL + "/direction/forwardOrBackward/200";
    
    public static final String BACKWARD_MOVEMENT_URL = RPI_URL + "/direction/forwardOrBackward/-200";

    public static final String STOP_MOVEMENT_URL = RPI_URL + "/direction/forwardOrBackward/0";

    public static final String STEERING_RIGHT_URL = RPI_URL + "/direction/leftOrRight/1";

    public static final String STEERING_LEFT_URL = RPI_URL + "/direction/leftOrRight/1";

    public static final String STOP_STEERING_URL = RPI_URL + "/direction/leftOrRight/0";

    public static final String GET_SPEED_VALUE_URL = RPI_URL + "/direction/forwardOrBackward/";

    public static final String GET_STEERING_CONDITION_URL = RPI_URL + "/direction/leftOrRight/";
}
