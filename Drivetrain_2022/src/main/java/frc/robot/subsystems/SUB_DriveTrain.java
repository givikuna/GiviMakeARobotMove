// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SUB_DriveTrain extends SubsystemBase {

  private WPI_TalonSRX leftLeader = new WPI_TalonSRX(0);
  private WPI_TalonSRX leftFollower = new WPI_TalonSRX(0);

  private WPI_TalonSRX rightLeader = new WPI_TalonSRX(0);
  private WPI_TalonSRX rightFollower = new WPI_TalonSRX(0);

  private MotorControllerGroup leftMotors = new MotorControllerGroup(leftLeader, leftFollower);
  private MotorControllerGroup rightMotors = new MotorControllerGroup(rightLeader, rightFollower);

  private DifferentialDrive m_differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

  /** Creates a new SUB_DriveTrain. */
  public SUB_DriveTrain() {
  
  }

  public void differentialDrive(double leftSpeed, double rightSpeed) {
    m_differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
