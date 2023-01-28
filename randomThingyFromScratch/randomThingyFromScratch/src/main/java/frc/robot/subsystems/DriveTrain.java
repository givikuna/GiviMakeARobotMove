// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.motorIDs.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveTrain extends SubsystemBase {

    public final WPI_TalonFX frontRight;
    public final WPI_TalonFX frontLeft;
    private final WPI_TalonFX backRight;
    private final WPI_TalonFX backLeft;

    /** Creates a new ExampleSubsystem. */
    public DriveTrain() {
        frontRight = new WPI_TalonFX(frontRightID);
        frontLeft = new WPI_TalonFX(frontLeftID);
        backRight = new WPI_TalonFX(frontRightID);
        backLeft = new WPI_TalonFX(backLeftID);

        configureMotor(frontRight, false);
        configureMotor(frontLeft, true);
        configureMotor(backRight, false);
        configureMotor(backLeft, true);

        backRight.set(ControlMode.Follower, frontRight.getDeviceID());
        backLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    }

    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
        frontRight.set(rightSpeed);
        backRight.set(rightSpeed);

        frontLeft.set(leftSpeed);
        backLeft.set(leftSpeed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    private void configureMotor(WPI_TalonFX motor, boolean left) { // I kidnapped this from the old project
        motor.configFactoryDefault(); // Resetting the motors to make sure there's no junk on there
        // before configuring
        // motor.configVoltageCompSaturation(DRIVE_MAX_VOLTAGE); // only use 12.3 volts regardless of
        // battery voltage
        // motor.enableVoltageCompensation(true); // enable ^
        motor.setInverted(!left);
        motor.setNeutralMode(
            NeutralMode.Coast); // set it so that when the motor is getting no input, it stops
        motor.configSelectedFeedbackSensor(
            FeedbackDevice.IntegratedSensor); // configure the encoder (it's inside)
        motor.setSelectedSensorPosition(0); // reset the encoder to have a value of 0
        motor.configOpenloopRamp(RAMP_RATE); // how long it takes to go from 0 to the set speed
        motor.setSensorPhase(true);
        // motor.config_kP(0, 0.001);
        // motor.config_kI(0, 0);
        // motor.config_kD(0, 0);
        // motor.config_kF(0, 0);
        // Make sure that both sides' encoders are getting positive values when going
        // forward
      }
}
