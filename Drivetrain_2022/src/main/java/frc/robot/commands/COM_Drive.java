// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SUB_DriveTrain;
import java.util.function.*;

public class COM_Drive extends CommandBase {
  // THIS IS WRONG
  private SUB_DriveTrain m_SUB_DriveTrain;
  //
  private Supplier<Double> leftSpeed;
  private Supplier<Double> rightSpeed;
  private double leftSpeedDouble;
  private double rightSpeedDouble;

  /** Creates a new COM_Drive. */
  public COM_Drive(SUB_DriveTrain driveTrain, Supplier<Double> leftSpeed, Supplier<Double> rightSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_SUB_DriveTrain = driveTrain;
    addRequirements(m_SUB_DriveTrain);
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_SUB_DriveTrain.differentialDrive(leftSpeed, rightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
