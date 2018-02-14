package org.elsys.remote_control_car.service;

import com.pi4j.io.gpio.PinState;
import org.elsys.remote_control_car.enums.DirectionEnum;
import org.elsys.remote_control_car.repository.MotorRepository;
import org.springframework.stereotype.Component;

@Component
public class CarService {
    private MotorRepository motorRepository = new MotorRepository();

    public CarService() {}

    public void runMotorForwardOrBackward(Integer speed) {
        if (speed != 0) {
            if (speed > 0) {
                motorRepository.runRearMotor(speed, DirectionEnum.FORWARD);
                return;
            }

            motorRepository.runRearMotor(speed, DirectionEnum.BACKWARD);
            return;
        }

        motorRepository.runRearMotor(speed, DirectionEnum.NONE);
    }

    public Integer getRearMotorSpeed() {
        return motorRepository.getRearMotor().getSpeed();
    }

    public void steerMotorLeftOrRight(Integer turn) {
        if (turn != 0) {
            if (turn == 1) {
                motorRepository.steerFrontMotor(DirectionEnum.RIGHT);
                return;
            }

            motorRepository.steerFrontMotor(DirectionEnum.LEFT);
            return;
        }

        motorRepository.steerFrontMotor(DirectionEnum.NONE);
    }

    public Integer getSteeringCondition() {
        PinState stateA = motorRepository.getFrontMotor()
                                    .getInputA()
                                    .getState();

        PinState stateB = motorRepository.getFrontMotor()
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
