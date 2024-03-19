package com.koibots.robot;

import com.koibots.robot.controlls.EightBitDo;
import com.koibots.robot.subsystems.Drivetrain;

public class RobotContainer {
    private EightBitDo driverController;

    public RobotContainer() {
        driverController = new EightBitDo(0);
    }

    public void configureButtonBindings() {
        Drivetrain.get().setDefaultCommand(Drivetrain.get().new driveMotorCommand(
            () -> driverController.getLeftY(), 
            () -> driverController.getRightY()));
    }
}
