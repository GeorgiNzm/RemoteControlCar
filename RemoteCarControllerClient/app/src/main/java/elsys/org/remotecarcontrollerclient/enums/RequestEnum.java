package elsys.org.remotecarcontrollerclient.enums;

/**
 * Created by georgi on 14.02.18.
 */

public enum RequestEnum {

    FORWARD_MOVEMENT_URL("http://169.254.148.138:8080/direction/forwardOrBackward/200"),
    BACKWARD_MOVEMENT_URL("http://169.254.148.138:8080/direction/forwardOrBackward/-200"),
    STOP_MOVEMENT_URL("http://169.254.148.138:8080/direction/forwardOrBackward/0"),
    STEERING_RIGHT_URL("http://169.254.148.138:8080/direction/leftOrRight/1"),
    STEERING_LEFT_URL("http://169.254.148.138:8080/direction/leftOrRight/1"),
    STOP_STEERING_URL("http://169.254.148.138:8080/direction/leftOrRight/0"),
    GET_SPEED_VALUE_URL("http://169.254.148.138:8080/direction/forwardOrBackward/"),
    GET_STEERING_CONDITION_URL("http://169.254.148.138:8080/direction/leftOrRight/");


    private String url;

    RequestEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
