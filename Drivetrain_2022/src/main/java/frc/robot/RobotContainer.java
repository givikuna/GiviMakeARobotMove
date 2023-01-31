// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.COM_Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SUB_DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final SUB_DriveTrain m_driveTrain = new SUB_DriveTrain();

  private final ExampleCommand m_exampleCommand = new ExampleCommand(m_exampleSubsystem);
  private final COM_Drive m_drive = new COM_Drive(m_driveTrain, 0, 0);
  // private final Autos m_autos = new Autos();

  // private Joystick leftJoystick = new Joystick(0);
  // private Joystick rightJoystick = new Joystick(1);
  private XboxController xboxController = new XboxController(2);

  Trigger aButton = new JoystickButton(xboxController, XboxController.Button.kA.value);
  Trigger bButton = new JoystickButton(xboxController, XboxController.Button.kB.value);
  Trigger xButton = new JoystickButton(xboxController, XboxController.Button.kX.value);
  Trigger yButton = new JoystickButton(xboxController, XboxController.Button.kY.value);
  JoystickButton leftStick = new JoystickButton(xboxController, XboxController.Button.kLeftStick.value);
  JoystickButton rightStick = new JoystickButton(xboxController, XboxController.Button.kRightStick.value);

  /*

  private JoystickButton a = new JoystickButton(xboxController, 1);
  private JoystickButton b = new JoystickButton(xboxController, 2);
  private JoystickButton x = new JoystickButton(xboxController, 3);
  private JoystickButton y = new JoystickButton(xboxController, 4);

  */

  /*
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
      */

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    aButton.whileTrue(new COM_Drive(m_driveTrain, -1.0, -1.0));
    bButton.whileTrue(new COM_Drive(m_driveTrain, -1.0, 1.0));
    xButton.whileTrue(new COM_Drive(m_driveTrain, 1.0, 1.0));
    yButton.whileTrue(new COM_Drive(m_driveTrain, 1.0, 1.0));

    /*
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
