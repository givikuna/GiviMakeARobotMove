package frc.robot.subsystems;

import static frc.robot.Constants.DriveTrainConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    public final WPI_TalonFX leftParent;
    public final WPI_TalonFX rightParent;
    private final WPI_TalonFX leftChild;
    private final WPI_TalonFX rightChild;
    private final DifferentialDrive driveBase;
  
    private NetworkTableEntry m_xEntry =
        NetworkTableInstance.getDefault().getTable("troubleshooting").getEntry("X");
    private NetworkTableEntry m_yEntry =
        NetworkTableInstance.getDefault().getTable("troubleshooting").getEntry("Y");
    private final DifferentialDriveOdometry odometry;
    private final AHRS gyro;

    public DriveTrain() {
        leftParent = new WPI_TalonFX(LEFT_PARENT_ID);
        rightParent = new WPI_TalonFX(RIGHT_PARENT_ID);
        leftChild = new WPI_TalonFX(LEFT_CHILD_ID);
        rightChild = new WPI_TalonFX(RIGHT_CHILD_ID);
    
        configureMotor(leftParent, true);
        configureMotor(rightParent, false);
        configureMotor(leftChild, true);
        configureMotor(rightChild, false);
    
        rightChild.set(ControlMode.Follower, rightParent.getDeviceID());
        leftChild.set(ControlMode.Follower, leftParent.getDeviceID());
    
        driveBase = new DifferentialDrive(leftParent, rightParent);
        driveBase.setDeadband(DEADBAND);
        driveBase.setSafetyEnabled(false);
    
        // setAllToBrake();
    
        gyro = new AHRS(SPI.Port.kMXP);
        gyro.enableLogging(true);
        odometry = new DifferentialDriveOdometry(gyro.getRotation2d());
    }

    /**
    * Configure the moters to make sure that their settings are correct
    *
    * @param motor Your moter
    * @param left Wether its located on the left side of the robot in the direction it is facing
    */
    private void configureMotor(WPI_TalonFX motor, boolean left) {
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

    public void tankDrive(double speed) {
        leftParent.set(speed);
        rightParent.set(speed);
    }
}
