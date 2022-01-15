// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootingCommand extends CommandBase {
  /** Creates a new ShootingCommand. */
  Shooter m_shoot; 
  double ticks_; 
  long time; 
  public ShootingCommand(Shooter s, double t) {
    m_shoot = s;
    ticks_ = t; 
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
      SmartDashboard.putNumber("time", System.currentTimeMillis()-time); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_shoot.getVelocity()-ticks_ < 100;
  }
}
