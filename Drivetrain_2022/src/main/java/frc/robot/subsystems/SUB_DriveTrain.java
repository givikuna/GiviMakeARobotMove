// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.COM_Drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.util.function.*;

public class SUB_DriveTrain extends SubsystemBase {

  private CANSparkMax leftLeader = new CANSparkMax(0, MotorType.kBrushless);
  private CANSparkMax leftFollower = new CANSparkMax(0, MotorType.kBrushless);

  private CANSparkMax rightLeader = new CANSparkMax(0, MotorType.kBrushless);
  private CANSparkMax rightFollower = new CANSparkMax(0, MotorType.kBrushless);

  private MotorControllerGroup leftMotors = new MotorControllerGroup(leftLeader, leftFollower);
  private MotorControllerGroup rightMotors = new MotorControllerGroup(rightLeader, rightFollower);

  private DifferentialDrive m_differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

  /** Creates a new SUB_DriveTrain. */
  public SUB_DriveTrain() {
    //
  }


  /*
    // THIS IS WRONG 
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      setDefaultCommand(new COM_Drive(new SUB_DriveTrain(), () -> xboxController.getLeftY(), () -> xboxController.getRightY()));
    }
    //
  */

  public void differentialDrive(Supplier<Double> leftSpeed, Supplier<Double> rightSpeed) {
    m_differentialDrive.tankDrive(leftSpeed.get(), rightSpeed.get());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
