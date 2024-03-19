// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.koibots.robot;

import com.koibots.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;



/**
 * The VM is configured to automatically run this class. If you change the name of this class or the
 * package after creating this project, you must also update the build.gradle file in the project.
 */
public class Robot extends TimedRobot {
    RobotContainer robotContainer;
    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
        Drivetrain.get().setBrakeMode(true);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }
    
    @Override
    public void teleopInit() {
        robotContainer.configureButtonBindings();
    }
    @Override
    public void teleopPeriodic() {
    }
}
