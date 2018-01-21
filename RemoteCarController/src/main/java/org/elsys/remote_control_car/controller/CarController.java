package org.elsys.remote_control_car.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    @RequestMapping(method = RequestMethod.GET,
                    value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        return "Heeeeellooooooo";
    }

}
