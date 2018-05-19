package org.elsys.remote_control_car.controller;

import java.util.Arrays;

import org.elsys.remote_control_car.service.CarService;
import org.elsys.remote_control_car.service.ProcessService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CarController implements InitializingBean {

    private static final String RPI_STATIC_IP = "192.168.43.173";

    private CarService carService;

    private ProcessService processService;
    private int motionPid = 0;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String hello() {
        return "Hello, Spring application";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/direction/forwardOrBackward/{speed}")
    public ResponseEntity runForwardOrBackward(@PathVariable("speed") int speed) {

        carService.runMotorForwardOrBackward(speed);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/direction/forwardOrBackward", produces = MediaType.TEXT_PLAIN_VALUE)
    public Integer getSpeedValue() {
        return carService.getRearMotorSpeed();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/direction/leftOrRight/{turn}")
    public ResponseEntity steerLeftOrRight(@PathVariable("turn") int turn) {

        carService.steerMotorLeftOrRight(turn);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/direction/leftOrRight", produces = MediaType.TEXT_PLAIN_VALUE)
    public Integer getSteeringCondition() {
        return carService.getSteeringCondition();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/camera/start")
    public HttpEntity<String> openVideoStream() {
        final String START_MOTION_COMMAND = "motion -c /home/pi/.motion/motion.conf";

        if (motionPid == 0) {
            try {
                motionPid = processService.executeCommand(START_MOTION_COMMAND);
                Thread.sleep(7 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
                return new HttpEntity<String>("Error occured during start of motion process.");
            }
        }

        return getResponseEntity();
    }

    private HttpEntity<String> getResponseEntity() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("no-cache", "private"));
        headers.put("Connection", Arrays.asList("close"));
        headers.put("Content-Type", Arrays.asList("multipart/x-mixed-replace", "boundary=BoundaryString"));
        headers.put("Expires", Arrays.asList("0"));
        headers.put("Max-Age", Arrays.asList("0"));
        headers.put("Pragma", Arrays.asList("no-cache"));
        headers.put("Server", Arrays.asList("Motion/4.1.1"));

        String body = "<body><img style=\"-webkit-user-select: none;\" src=\"http://" + RPI_STATIC_IP + ":58081/\" width=\"640\" height=\"480\"></body>";

        return new HttpEntity<>(body, headers);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/camera/stop")
    public ResponseEntity stopMotionProcesses() {
        if (motionPid == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        String command = "sudo kill " + motionPid + " -9";
        try {
            int killPid = processService.executeCommand(command);
            motionPid = 0;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.carService = new CarService();
        this.processService = new ProcessService();
    }
}
