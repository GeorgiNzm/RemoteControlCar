package org.elsys.remote_control_car.repository;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elsys.remote_control_car.enums.DirectionEnum;
import org.elsys.remote_control_car.enums.MotorTypeEnum;
import org.elsys.remote_control_car.model.Motor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class CarRepository {
    private static GpioController gpioController = GpioFactory.getInstance();
    private Motor rearMotor, frontMotor;

    private final Logger log = LogManager.getLogger(CarRepository.class);

    public CarRepository() {
        this.rearMotor = new Motor(MotorTypeEnum.REAR, getGpioController());
        this.frontMotor = new Motor(MotorTypeEnum.FRONT, getGpioController());

        log.info(this.rearMotor.toString());
        log.info(this.frontMotor.toString());
    }

    public GpioController getGpioController() {
        return gpioController;
    }

    public Motor getRearMotor() {
        return rearMotor;
    }

    public Motor getFrontMotor() {
        return frontMotor;
    }

    public void runRearMotor(Integer speed, DirectionEnum direction) {

        switch (direction) {
            case FORWARD:
                this.rearMotor.getInputA().setState(PinState.LOW);
                this.rearMotor.getInputB().setState(PinState.HIGH);
                break;
            case BACKWARD:
                this.rearMotor.getInputA().setState(PinState.HIGH);
                this.rearMotor.getInputB().setState(PinState.LOW);
                break;
            case NONE:
                this.rearMotor.getInputA().setState(PinState.LOW);
                this.rearMotor.getInputB().setState(PinState.LOW);
                break;
        }

        this.rearMotor.setSpeed(speed);
    }

    public void steerFrontMotor(DirectionEnum direction) {

        switch (direction) {
            case RIGHT:
                this.frontMotor.getInputA().setState(PinState.HIGH);
                this.frontMotor.getInputB().setState(PinState.LOW);
                break;
            case LEFT:
                this.frontMotor.getInputA().setState(PinState.LOW);
                this.frontMotor.getInputB().setState(PinState.HIGH);
                break;
            case NONE:
                this.frontMotor.getInputA().setState(PinState.LOW);
                this.frontMotor.getInputB().setState(PinState.LOW);
                break;
        }

        this.frontMotor.setSpeed(500);
    }
}
