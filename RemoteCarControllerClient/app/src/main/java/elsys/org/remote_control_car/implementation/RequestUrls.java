package elsys.org.remote_control_car.implementation;

/**
 * Created by georgi on 19.02.18.
 */

public class RequestUrls {
    public static final String FORWARD_MOVEMENT_URL = "http://169.254.148.138/direction/forwardOrBackward/200";
    
    public static final String BACKWARD_MOVEMENT_URL = "http://169.254.148.138/direction/forwardOrBackward/-200";

    public static final String STOP_MOVEMENT_URL = "http://169.254.148.138/direction/forwardOrBackward/0";

    public static final String STEERING_RIGHT_URL = "http://169.254.148.138/direction/leftOrRight/1";

    public static final String STEERING_LEFT_URL = "http://169.254.148.138/direction/leftOrRight/1";

    public static final String STOP_STEERING_URL = "http://169.254.148.138/direction/leftOrRight/0";

    public static final String GET_SPEED_VALUE_URL = "http://169.254.148.138/direction/forwardOrBackward/";

    public static final String GET_STEERING_CONDITION_URL = "http://169.254.148.138/direction/leftOrRight/";
}
