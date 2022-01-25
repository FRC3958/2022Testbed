// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private WPI_TalonFX m_talon = new WPI_TalonFX(Constants.FXCANID); 
  public Shooter() {
    m_talon.configFactoryDefault();
    TalonFXConfiguration configs = new TalonFXConfiguration();
		configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

    configs.slot0.kF = Constants.kF;
    configs.slot0.kP = Constants.kP;
    configs.slot0.kI = Constants.kI;

    

		m_talon.configAllSettings(configs);
    m_talon.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double velocity = m_talon.getSelectedSensorVelocity();

    //SmartDashboard.putNumber("FX Velocity", 9560-velocity); 
  }

  public void setToPercentSpeed(double speed) {
    m_talon.set(ControlMode.PercentOutput, speed); 
  }

  public double getVelocity() {
    return  m_talon.getSelectedSensorVelocity(); 
  }

  public void setToVelocity(double s) {
    m_talon.set(ControlMode.Velocity, s);
  }
}
