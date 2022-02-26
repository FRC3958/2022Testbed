// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TesterClimber extends SubsystemBase {
private final WPI_TalonFX climberLeft = new WPI_TalonFX(Constants.ClimberLeftID);
private final WPI_TalonFX climberRight = new WPI_TalonFX(Constants.ClimberRightID);
private final WPI_TalonSRX climberTurning = new WPI_TalonSRX(Constants.TurningSRXID);
TalonFXConfiguration rightConfig = new TalonFXConfiguration();
TalonFXConfiguration leftConfig = new TalonFXConfiguration();
TalonSRXConfiguration turnConfig = new TalonSRXConfiguration();

  /** Creates a new TesterClimber. */
  public TesterClimber() {
    climberLeft.configFactoryDefault();
    climberRight.configFactoryDefault();
    climberTurning.configFactoryDefault();

    rightConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    leftConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
		turnConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;


    rightConfig.slot0.kP = 1;
    rightConfig.slot0.kI = 0.01;
    rightConfig.slot0.kD = 5;

    rightConfig.slot1.kP = 2; 

    rightConfig.remoteFilter1.remoteSensorDeviceID = climberLeft.getDeviceID(); //Device ID of Remote Source
		rightConfig.remoteFilter1.remoteSensorSource = RemoteSensorSource.TalonFX_SelectedSensor; //Remote Source Type

    
    climberLeft.configAllSettings(leftConfig);
    climberRight.configAllSettings(rightConfig);
    climberTurning.configAllSettings(turnConfig);

    climberLeft.setNeutralMode(NeutralMode.Brake);
    climberRight.setNeutralMode(NeutralMode.Brake);
    climberTurning.setNeutralMode(NeutralMode.Brake);

    climberLeft.follow(climberRight, FollowerType.AuxOutput1);

    resetEncoders();

  }

  public void setVerticalOutput(double speed){
    climberRight.set(ControlMode.PercentOutput, speed);

  }

  public void setTurnOutput(double speed) {
    climberTurning.set(ControlMode.PercentOutput, speed);
  }

  public void setToAngle(double a) {
    double ca = Constants.getNativeUnitsFromDegrees(a);
    climberTurning.set(ControlMode.Position, ca);
  }

  public void resetEncoders() {
    climberLeft.getSensorCollection().setIntegratedSensorPosition(0, 30); //30 ms timeout for a response 
    climberRight.getSensorCollection().setIntegratedSensorPosition(0, 30);
    climberTurning.getSensorCollection().setQuadraturePosition(0, 30);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //SmartDashboard.putNumber("Climber angle", Constants.getDegreesFromNativeUnits(climberTurning.getSelectedSensorPosition()));
    SmartDashboard.putNumber("climber pos", climberTurning.getSelectedSensorPosition());
    
  }
}