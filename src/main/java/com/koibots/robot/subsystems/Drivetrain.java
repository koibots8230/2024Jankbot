package com.koibots.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.koibots.robot.Constants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private VictorSPX mainLeftMotor;
    private VictorSPX mainRightMotor;
    private VictorSPX secondaryLeftMotor;
    private VictorSPX secondaryRightMotor;

    public static Drivetrain m_Drivetrain;

    public Drivetrain() {
        mainLeftMotor = new VictorSPX(Constants.MAIN_LEFT_MOTOR_ID);
        mainRightMotor = new VictorSPX(Constants.MAIN_RIGHT_MOTOR_ID);
        secondaryLeftMotor = new VictorSPX(Constants.SECONDARY_LEFT_MOTOR_ID);
        secondaryRightMotor = new VictorSPX(Constants.SECONDARY_RIGHT_MOTOR_ID);

        m_Drivetrain = new Drivetrain();
    }

    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
        mainLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
        mainRightMotor.set(ControlMode.PercentOutput, rightSpeed);
        secondaryLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
        secondaryRightMotor.set(ControlMode.PercentOutput, rightSpeed);
    }

    public void setBrakeMode(boolean mode){
        if(mode == true) {
            mainLeftMotor.setNeutralMode(NeutralMode.Brake);
            mainRightMotor.setNeutralMode(NeutralMode.Brake);
            secondaryLeftMotor.setNeutralMode(NeutralMode.Brake);
            secondaryRightMotor.setNeutralMode(NeutralMode.Brake);
        } else {
            mainLeftMotor.setNeutralMode(NeutralMode.Coast);
            mainRightMotor.setNeutralMode(NeutralMode.Coast);
            secondaryLeftMotor.setNeutralMode(NeutralMode.Coast);
            secondaryRightMotor.setNeutralMode(NeutralMode.Coast);
        }
    }

    public static Drivetrain get() {
        return m_Drivetrain;
    }

    public class driveMotorCommand extends Command {
        private DoubleSupplier m_rightSpeed;
        private DoubleSupplier m_leftSpeed;

        public driveMotorCommand(DoubleSupplier rightSpeed, DoubleSupplier leftSpeed) {
            m_rightSpeed = rightSpeed;
            m_leftSpeed = leftSpeed;
            addRequirements(Drivetrain.this);
        }

        @Override
        public void execute() {
            Drivetrain.this.setMotorSpeeds(
                adjustForDeadzone(m_rightSpeed.getAsDouble()), 
                adjustForDeadzone(m_leftSpeed.getAsDouble()));
        }

        private double adjustForDeadzone(double in) {
            if (Math.abs(in) < Constants.THUMBSTICK_DEADZONE) {
                return 0;
            } else {
                return in;
            }
        }
    }
}  
