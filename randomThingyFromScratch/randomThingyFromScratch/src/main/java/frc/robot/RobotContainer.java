// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// edu
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;  

// commands
import frc.robot.commands.*; // imports both Drive & ExampleCommand

// subsystems
import frc.robot.subsystems.*; // imports both DriveTrain & ExampleSubsystem

// other libraries
import java.util.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // subsystems
  private ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private DriveTrain m_driveTrain = new DriveTrain();

  // commands
  private ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private Drive m_drive;

  //joysticks
  private final Joystick leftJoystick = new Joystick(0);
  private final Joystick rightJoystick = new Joystick(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drive = new Drive(m_driveTrain);
    m_driveTrain.setDefaultCommand(new Drive(m_driveTrain, () -> leftJoystick.geY() / 5.0, () -> rightJoystick.getY() / 5.0))
  }
  /* 
  public void getDriveCmd() {
    m_drive = new Drive(m_driveTrain, leftJoystick.geY() / 5.0, rightJoystick.getY() / 5.0);
    m_drive.execute();
  }
  */

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
