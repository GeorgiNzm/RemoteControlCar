package org.elsys.remote_control_car.controller;

import org.elsys.remote_control_car.service.CarService;
import org.elsys.remote_control_car.service.StreamService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class CarController implements InitializingBean {

    private CarService carService;

    private StreamService streamService;

    @RequestMapping(method = RequestMethod.POST,
                    value = "/direction/forwardOrBackward/{speed}")
    public ResponseEntity runForwardOrBackward(@PathVariable("speed") int speed) {

        carService.runMotorForwardOrBackward(speed);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/direction/forwardOrBackward",
                    produces = MediaType.TEXT_PLAIN_VALUE)
    public Integer getSpeedValue() {
        return carService.getRearMotorSpeed();
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "/direction/leftOrRight/{turn}")
    public ResponseEntity steerLeftOrRight(@PathVariable("turn") int turn) {

        carService.steerMotorLeftOrRight(turn);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/direction/leftOrRight",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public Integer getSteeringCondition() {
        return carService.getSteeringCondition();
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/camera/stream")
    public void openVideoStream() {
        try {
            streamService.createVideoStream();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.carService = new CarService();
        this.streamService = new StreamService();
    }
}