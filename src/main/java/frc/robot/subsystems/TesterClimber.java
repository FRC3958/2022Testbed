// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TesterClimber extends SubsystemBase {
private final WPI_TalonFX climberLeft = new WPI_TalonFX(Constants.ClimberLeftID);
private final WPI_TalonFX climberRight = new WPI_TalonFX(Constants.ClimberRightID);


  /** Creates a new TesterClimber. */
  public TesterClimber() {
    climberLeft.configFactoryDefault();
    climberRight.configFactoryDefault();

    climberLeft.setNeutralMode(NeutralMode.Brake);
    climberRight.setNeutralMode(NeutralMode.Brake);

    climberLeft.follow(climberRight);
  }

  public void setPerecentOutput(double speed){
    climberRight.set(ControlMode.PercentOutput, speed);

    System.out.print("beast!!");
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
