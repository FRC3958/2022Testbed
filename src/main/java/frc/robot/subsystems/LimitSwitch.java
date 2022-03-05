// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LimitSwitch extends SubsystemBase {

  private DigitalInput limitswitch = new DigitalInput(Constants.LimitSwitchPort);

  /** Creates a new LimitSwitch. */
  public LimitSwitch() {

  

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public boolean isSwitchClosed() {
    return !limitswitch.get();
  }

}
