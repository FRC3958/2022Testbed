// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DrivingCommand extends CommandBase {
  /** Creates a new DrivingCommand. */

  
  private DriveTrain dt;
  private XboxController xc; 
  public DrivingCommand(DriveTrain d, XboxController x) {
    dt = d; 
    xc = x;
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(dt); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // new 
    dt.drivingMethod(-.01*xc.getLeftY(), 0.01*xc.getRightX());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
