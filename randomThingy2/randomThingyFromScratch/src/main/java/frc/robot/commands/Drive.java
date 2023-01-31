// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private DriveTrain m_driveTrain;
  private Supplier<Double> leftSpeed;
  private Supplier<Double> rightSpeed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  public Drive(DriveTrain driveTrain, Supplier<Double> leftSpeed, Supplier<Double> rightSpeed) {
    m_driveTrain = driveTrain;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  public Drive(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;
    addRequirements(driveTrain);
    m_driveTrain.setMotorSpeeds(0, 0);
    leftSpeed = 0;
    rightSpeed = 0;
  }

  public Drive(double leftSpeed, double rightSpeed) {
    this.leftSpeed = leftSpeed;
    this.rigtSpeed = rightSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.setMotorSpeeds(leftSpeed, rightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setMotorSpeeds(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
