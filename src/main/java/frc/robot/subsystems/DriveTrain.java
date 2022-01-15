// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.nio.file.FileSystemAlreadyExistsException;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.frontLeftMotorID);
  private final WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.frontRightMotorID);
  private final WPI_TalonSRX backLeft = new WPI_TalonSRX(Constants.backLeftMotorID);
  private final WPI_TalonSRX backRight = new WPI_TalonSRX(Constants.backRightMotorID);

  private final DifferentialDrive diffDrive = new DifferentialDrive(backLeft, backRight);
  long encoder1start = 0;
  long encoder2start = 0; 

  private final AHRS m_ahrs = new AHRS(Port.kMXP);

  private final DifferentialDriveOdometry m_odometry; 

  public DriveTrain() {
    TalonSRXConfiguration configs1 = new TalonSRXConfiguration();
		configs1.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;

    TalonSRXConfiguration configs2 = new TalonSRXConfiguration();
		configs2.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;

    backLeft.configAllSettings(configs1); 
    backRight.configAllSettings(configs2); 
    frontLeft.configFactoryDefault();
    frontRight.configFactoryDefault(); 


    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    backLeft.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Coast);

    frontLeft.follow(backLeft);
    frontRight.follow(backRight);

    resetEncoders();
    resetHeading();

    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_odometry.update(
      Rotation2d.fromDegrees(getHeading()), 
      getLeftDistanceMeters(),
      getRightDistanceMeters()
    );
    SmartDashboard.putNumber("heading", getHeading());
    SmartDashboard.putNumber("y traveled in meter", m_odometry.getPoseMeters().getY());
    SmartDashboard.putNumber("x traveled in meter", m_odometry.getPoseMeters().getX());
  }

  public void drivingMethod(double forward, double turn) {
    diffDrive.arcadeDrive(turn, forward, true);
   
  }

  public double getCurrentY() {
    return m_odometry.getPoseMeters().getY();
  }
  
  public double getCurrentX() {
    return m_odometry.getPoseMeters().getX();
  }


  public double getMetersFromNative(double QuadEncoderInput) {
    return QuadEncoderInput*(Constants.WheelDiameter*Math.PI)/Constants.QuadEncoderResolution;
  }

  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  public double getLeftDistanceMeters() {
    return getMetersFromNative(frontLeft.getSelectedSensorPosition());
  }

  public double getRightDistanceMeters() {
    return getMetersFromNative(backRight.getSelectedSensorPosition());
  }

  public void resetEncoders() {
    frontLeft.getSensorCollection().setQuadraturePosition(0, Constants.kTimeout);
    backRight.getSensorCollection().setQuadraturePosition(0, Constants.kTimeout);
  }

  /**
   * Gets the angle of the bot
   * @return
   */
  public double getHeading() {
    return -m_ahrs.getAngle();
  }

  public void resetHeading() {
    m_ahrs.zeroYaw();
  }
}
