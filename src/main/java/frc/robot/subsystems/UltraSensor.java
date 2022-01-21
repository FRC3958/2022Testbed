// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UltraSensor extends SubsystemBase {

  Ultrasonic bat = new Ultrasonic(0, 0);
  AnalogPotentiometer m_batPotentiometer = new AnalogPotentiometer(0);


  public UltraSensor(){

  }

  public double sendWave(){
    // gives out distance from object in millimeters
    return bat.getRangeMM();
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
