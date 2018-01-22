package org.elsys.remote_control_car.model;

import com.pi4j.io.gpio.*;
import org.elsys.remote_control_car.enums.MotorTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class Motor {
    private MotorTypeEnum type;
    private Integer speed;
    private GpioPinDigitalOutput inputA, inputB;
    private GpioPinPwmOutput enablePin;

    public Motor(MotorTypeEnum type, GpioController gpioController) {
        this.type = type;
        if (this.type.toString().equals("REAR")) {
            this.inputA = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "1A", PinState.LOW);
            this.inputB = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "1B", PinState.LOW);
            this.enablePin = gpioController.provisionPwmOutputPin(RaspiPin.GPIO_23, "E1", 0);
        } else if(this.type.toString().equals("FRONT")) {
            this.inputA = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_06, "2A", PinState.LOW);
            this.inputB = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_27, "2B", PinState.LOW);
            this.enablePin = gpioController.provisionPwmOutputPin(RaspiPin.GPIO_26, "E2", 0);
        }
        this.speed = 0;
    }

    public MotorTypeEnum getType() {
        return type;
    }

    public GpioPinDigitalOutput getInputA() {
        return inputA;
    }

    public void setInputA(GpioPinDigitalOutput inputA) {
        this.inputA = inputA;
    }

    public GpioPinDigitalOutput getInputB() {
        return inputB;
    }

    public void setInputB(GpioPinDigitalOutput inputB) {
        this.inputB = inputB;
    }

    public GpioPinPwmOutput getEnablePin() {
        return enablePin;
    }

    public void setEnablePin(GpioPinPwmOutput enablePin) {
        this.enablePin = enablePin;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
        getEnablePin().setPwm(this.speed);
    }
}
