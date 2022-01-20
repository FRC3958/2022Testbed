// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveToDistance extends CommandBase {
  /** Creates a new DriveToDistance. */
  private final DriveTrain m_dt; 
  private final double distanceTravelled = 0; 
  private final double distanceToTravel; 
  private double startingPointX = 0; 
  private double startingPointY = 0; 
  private long startTime;

  public DriveToDistance(DriveTrain d, double dtt) {
    m_dt = d;
    distanceToTravel = dtt; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startingPointX = m_dt.getCurrentX();
    startingPointY = m_dt.getCurrentY();
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
    double x = Math.pow(m_dt.getCurrentX()-startingPointX, 2);
    double y = Math.pow(m_dt.getCurrentY()-startingPointY, 2);
    double distanceTravelled = Math.pow(x+y, 0.5); 
    //System.out.println("a " + distanceTravelled + " b " + distanceToTravel);
    m_dt.drivingMethod(0.9*(distanceToTravel-distanceTravelled)/Math.abs(distanceToTravel), 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
      m_dt.drivingMethod(0, 0);
      
      
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    long runningTime = System.currentTimeMillis() - startTime;
    return Math.abs(distanceToTravel) - Math.abs(distanceTravelled) < 0.1 || runningTime > 6000;
   }
  
  }