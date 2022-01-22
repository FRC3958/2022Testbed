// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.UltraSensor;


public class ShootingCommand extends CommandBase {
  /** Creates a new ShootingCommand. */
  Shooter m_shoot; 
  // way phoenix tuner tells rpm
  double ticks_; 
  long time; 
  // motor that leads ball into shooter 
  //WPI_TalonSRX m_gateway = new WPI_TalonSRX(Constants.m_gatewayHolder);
  boolean done = false; 

  public ShootingCommand(Shooter s,  UltraSensor us) {
    m_shoot = s;
    ticks_ = us.getNativeUnitsFromDistance(); 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shoot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      time = System.currentTimeMillis(); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shoot.setToVelocity(ticks_);

  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      //SmartDashboard.putNumber("time", System.currentTimeMillis()-time); 
      m_shoot.setToVelocity(0);
      //m_gateway.set(0); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ticks_ - m_shoot.getVelocity() < 100;
  }
}
