package org.elsys.remote_control_car.controller;

import org.elsys.remote_control_car.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    private CarService carService = new CarService();

    @RequestMapping(method = RequestMethod.POST,
                    value = "/direction/forwardOrBackward/{speed}")
    public ResponseEntity runForwardOrBackward(@PathVariable("speed") String speed) {

        Integer motorSpeed = Integer.parseInt(speed);

        carService.runMotorForwardOrBackward(motorSpeed);

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
    public ResponseEntity steerLeftOrRight(@PathVariable("turn") String turn) {

        Integer turnNumber = Integer.parseInt(turn);

        carService.steerMotorLeftOrRight(turnNumber);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/direction/leftOrRight",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public Integer getSteeringCondition() {
        return carService.getSteeringCondition();
    }
}
