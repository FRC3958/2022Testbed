// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;


import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {

  private final I2C.Port i2c = I2C.Port.kOnboard;
  private final ColorSensorV3 ColorSenser = new ColorSensorV3(i2c);

  /** Creates a new ColorSensor. */
  public ColorSensor() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    Color detectedColor = ColorSenser.getColor();

    //SmartDashboard.putNumber("Red", detectedColor.red);
    //SmartDashboard.putNumber("Green", detectedColor.green);
    //SmartDashboard.putNumber("Blue", detectedColor.blue);
    
  }

  public String liveResultFromColorSensor() {
    return returnRoundedConcatenatedColorPercents(ColorSenser.getColor());
  }

  public String findBallColor() {
    SmartDashboard.putNumber("greeneeee", ColorSenser.getGreen());
    if(ColorSenser.getRed() > ColorSenser.getBlue() && ColorSenser.getRed() > ColorSenser.getGreen()) {
      return returnRoundedConcatenatedColorPercents(Color.kRed);
    } else if (ColorSenser.getGreen() > ColorSenser.getRed() && ColorSenser.getGreen()*.75 > ColorSenser.getBlue()) {
      return returnRoundedConcatenatedColorPercents(Color.kGreen);
    }

    return returnRoundedConcatenatedColorPercents(Color.kBlue);
  }

  public String returnRoundedConcatenatedColorPercents(Color detectedColor) {
    String result = "";
    //Color detectedColor = ColorSenser.getColor();
    double red =  detectedColor.red*100-1;
    double green = detectedColor.green*100-1;
    double blue =  detectedColor.blue*100-1;

    SmartDashboard.putNumber("red", red);
    SmartDashboard.putNumber("g", green);
    SmartDashboard.putNumber("b", blue);

    String resultr = String.valueOf( (int) red); 
    String resultg = String.valueOf( (int) green); 
    String resultb = String.valueOf( (int) blue); 

    resultr = (resultr.length() == 1 ? "0" : "") + resultr;
    resultg = (resultg.length() == 1 ? "0" : "") + resultg;
    resultb = (resultb.length() == 1 ? "0" : "") + resultb;

    result = resultr + resultg + resultb;

    SmartDashboard.putString("color output", result); 

    return result; 
    
  }
}
