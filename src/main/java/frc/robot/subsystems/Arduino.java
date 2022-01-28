// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arduino extends SubsystemBase {
  /** Creates a new Arduino. */
  private I2C arduino = new I2C(Port.kMXP, 10);
  public Arduino() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void sendMessage(String message) {
    byte[] recieveBuffer = new byte[10];
    arduino.transaction(message.getBytes(), message.getBytes().length, recieveBuffer, 0);
  }
}