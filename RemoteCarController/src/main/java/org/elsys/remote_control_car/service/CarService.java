package org.elsys.remote_control_car.service;

import com.pi4j.io.gpio.PinState;
import org.elsys.remote_control_car.enums.DirectionEnum;
import org.elsys.remote_control_car.repository.CarRepository;
import org.springframework.stereotype.Component;

@Component
public class CarService {
    private CarRepository carRepository = new CarRepository();

    public CarService() {}

    public void runMotorForwardOrBackward(Integer speed) {
        if (speed != 0) {
            if (speed > 0) {
                carRepository.runRearMotor(speed, DirectionEnum.FORWARD);
                return;
            }

            carRepository.runRearMotor(speed, DirectionEnum.BACKWARD);
            return;
        }

        carRepository.runRearMotor(speed, DirectionEnum.NONE);
    }

    public Integer getRearMotorSpeed() {
        return carRepository.getRearMotor().getSpeed();
    }

    public void steerMotorLeftOrRight(Integer turn) {
        if (turn != 0) {
            if (turn == 1) {
                carRepository.steerFrontMotor(DirectionEnum.RIGHT);
                return;
            }

            carRepository.steerFrontMotor(DirectionEnum.LEFT);
            return;
        }
        
        carRepository.steerFrontMotor(DirectionEnum.NONE);
    }

    public Integer getSteeringCondition() {
        PinState stateA = carRepository.getFrontMotor()
                                    .getInputA()
                                    .getState();

        PinState stateB = carRepository.getFrontMotor()
                                    .getInputB()
                                    .getState();

        boolean isRight = stateA.isHigh() && stateB.isLow();
        boolean isLeft = stateA.isLow() && stateB.isHigh();

        if (isRight) {
            return 1;
        } else if(isLeft) {
            return -1;
        }

        return 0;
    }
}
