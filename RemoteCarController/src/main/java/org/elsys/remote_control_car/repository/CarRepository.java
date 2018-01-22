package org.elsys.remote_control_car.repository;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import org.elsys.remote_control_car.enums.DirectionEnum;
import org.elsys.remote_control_car.enums.MotorTypeEnum;
import org.elsys.remote_control_car.model.Motor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class CarRepository implements InitializingBean {
    private static GpioController gpioController = GpioFactory.getInstance();
    private Motor rearMotor, frontMotor;


    public CarRepository() {}

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

        this.rearMotor.setSpeed(speed);

        if(direction.toString().equals("FORWARD")) {
            this.rearMotor.getInputA().setState(PinState.LOW);
            this.rearMotor.getInputB().setState(PinState.HIGH);
        }

        if(direction.toString().equals("BACKWARD")) {
            this.rearMotor.getInputA().setState(PinState.HIGH);
            this.rearMotor.getInputB().setState(PinState.LOW);
        }

        if (direction.toString().equals("NONE")) {
            this.rearMotor.getInputA().setState(PinState.LOW);
            this.rearMotor.getInputB().setState(PinState.LOW);
        }
    }

    public void steerFrontMotor(DirectionEnum direction) {

        this.frontMotor.setSpeed(500);

        if (direction.toString().equals("RIGHT")) {
            this.frontMotor.getInputA().setState(PinState.HIGH);
            this.frontMotor.getInputB().setState(PinState.LOW);
        }

        if (direction.toString().equals("LEFT")) {
            this.frontMotor.getInputA().setState(PinState.LOW);
            this.frontMotor.getInputB().setState(PinState.HIGH);
        }

        if (direction.toString().equals("NONE")) {
            this.frontMotor.getInputA().setState(PinState.LOW);
            this.frontMotor.getInputB().setState(PinState.LOW);
            this.frontMotor.setSpeed(0);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.rearMotor = new Motor(MotorTypeEnum.REAR, getGpioController());
        this.frontMotor = new Motor(MotorTypeEnum.FRONT, getGpioController());
    }
}
