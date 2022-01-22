// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UltraSensor extends SubsystemBase {

  private final AnalogInput bat = new AnalogInput(0);
  

  public UltraSensor(){

  }

  public double sendWave(){
    // gives out distance from object in millimeters
    return  bat.getValue();
    
    
    
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double rawValue = sendWave(); //fetches voltage from analog input 0

    double voltage_scale_factor = 5/RobotController.getVoltage5V(); 
    double currentDistanceCentimeters = rawValue*voltage_scale_factor * 0.125;
    //double currentDistanceInches = rawValue*voltage_scale_factor * 0.0492;
    SmartDashboard.putNumber("Distance From Sensor", currentDistanceCentimeters);

  }
}
