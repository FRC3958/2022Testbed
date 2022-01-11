// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.nio.file.FileSystemAlreadyExistsException;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.frontLeftMotorID);
  private final WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.frontRightMotorID);
  private final WPI_TalonSRX backLeft = new WPI_TalonSRX(Constants.backLeftMotorID);
  private final WPI_TalonSRX backRight = new WPI_TalonSRX(Constants.backRightMotorID);

  private final DifferentialDrive diffDrive = new DifferentialDrive(backLeft, backRight);

  public DriveTrain() {
    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    backLeft.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Coast);

    frontLeft.follow(backLeft);
    frontRight.follow(backRight);

    

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drivingMethod(double forward, double turn) {
    diffDrive.arcadeDrive(turn, forward, true);
    
  }
}
