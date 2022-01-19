// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class TurnToAngle extends CommandBase {
  /** Creates a new TurnToAngle. */
  private DriveTrain m_dt ;
  private double startingAngle = 0 ;
  private double goalAngle ;
  private long startTime; 
  public TurnToAngle( DriveTrain m_drt, double ga) {
    m_dt = m_drt; 
    goalAngle = 90; 
    

    // Use addRequirements() here to declare subsystem dependencies
    addRequirements(m_dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startingAngle = m_dt.getHeading();
    startTime = System.currentTimeMillis();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_dt.drivingMethod(0, -0.75*(goalAngle-(m_dt.getHeading()-startingAngle))/Math.abs(goalAngle));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_dt.drivingMethod(0.0, 0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double howFarTurned = m_dt.getHeading()-startingAngle; 
    long timeRunning = System.currentTimeMillis() - startTime; 
    return ((Math.abs(howFarTurned)-Math.abs(goalAngle)) < Constants.AngleTolerance && (Math.abs(howFarTurned)-Math.abs(goalAngle)) > -Constants.AngleTolerance) || timeRunning > 3000;
  }
}
