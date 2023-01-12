// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  // private final DriveTrain m_driveTrain = new DriveTrain();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final GoForward goForward = new GoForward();

  public DriveTrain driveTrain = new DriveTrain();

  private Joystick leftDriveJoystick = new Joystick(0); // should be in port 0
  private Joystick rightDriveJoystick = new Joystick(1); // should be in port 1
  public XboxController xboxController = new XboxController(2); // should be in port 2

    // xbox controller buttons
    public JoystickButton aButton = new JoystickButton(xboxController, 1);
    public JoystickButton bButton = new JoystickButton(xboxController, 2);
    public JoystickButton xButton = new JoystickButton(xboxController, 3);
    public JoystickButton yButton = new JoystickButton(xboxController, 4);
    public JoystickButton lbButton = new JoystickButton(xboxController, 5);
    public JoystickButton rbButton = new JoystickButton(xboxController, 6);
    public JoystickButton startButton = new JoystickButton(xboxController, 8);
    public JoystickButton backButton = new JoystickButton(xboxController, 7);
    // xbox trigger
    public Trigger rightTrigger = new Trigger(() -> (xboxController.getRightTriggerAxis() > 0.75));
    public Trigger leftTrigger = new Trigger(() -> (xboxController.getLeftTriggerAxis() > 0.75));

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    aButton.whileHeld(new m_goForward(driveTrain)); // comment
    /*
    aButton.whenHeld(
      new IndexIntakeCommand(
          indexer, intake, shooter, IntakeSystemMotors.IndexerIntakeForward, ShouldStop.No));
    bButton.whenHeld(
      new IndexIntakeCommand(
          indexer, intake, shooter, IntakeSystemMotors.IndexerIntakeBackward, ShouldStop.No));
  
    xButton.whenHeld(new SpinUpCommand(shooter, limelight));
    */
  }

  public double getLeftJoystickY() {
    return -leftDriveJoystick.getY();
  }

  public double getRightJoystickY() {
    return rightDriveJoystick.getX();
  }

  public double getJoysticksVal(boolean rightSide) {
    // right joystick x
    if (rightSide) {
      return 1 * rightDriveJoystick.getRawAxis(1);
    }
    return 1 * leftDriveJoystick.getRawAxis(1);
  }

  public double getControllerLeftY() {
    return -xboxController.getLeftY();
  }

  public double getControllerLeftX() {
    return xboxController.getLeftX();
  }

  public double getControllerRightY() {
    return -xboxController.getRightY();
  }

  public double getControllerRightX() {
    return xboxController.getRightX();
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
