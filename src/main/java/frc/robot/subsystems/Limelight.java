// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  //static ip: 10:39:58:11
  NetworkTable limetable = NetworkTableInstance.getDefault().getTable("limelight");

  public Limelight() {
      
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    final NetworkTableEntry verticalAngleOffset = limetable.getEntry("ty");
    double d_vAO = verticalAngleOffset.getDouble(5.00);

    SmartDashboard.putNumber("vAO", d_vAO); 

    double distance = Constants.HeightOffset/Math.tan(Math.toRadians(Constants.LimelightFixedAngle+d_vAO));
    SmartDashboard.putNumber("distance estimate", distance); 
  }

  public void turnLEDOn() {
    limetable.getEntry("ledMode").setNumber(0); //force led on 
    System.out.println("IT WORKS");
  }
  
  public void turnLEDOff() {
    limetable.getEntry("ledMode").setNumber(1); //force led off
  }

}
