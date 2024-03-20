package com.koibots.robot;

import com.koibots.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private CommandXboxController driverController;

    public RobotContainer() {
        driverController = new CommandXboxController(0);
    }

    public void configureButtonBindings() {
        Drivetrain.get().setDefaultCommand(Drivetrain.get().new driveMotorCommand(
            () -> driverController.getLeftY() * -1.0, 
            () -> driverController.getRightY() * -1.0));
    }
}
