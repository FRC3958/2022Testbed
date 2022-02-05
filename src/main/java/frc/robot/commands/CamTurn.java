// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

public class CamTurn extends CommandBase {
  Limelight lime = new Limelight();


  /** Creates a new CamTurn. */
  public CamTurn(Limelight limer) {
    lime = limer; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lime);
  }
  // creates camera obj 
  PhotonCamera camera = new PhotonCamera("photonvision");



  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Query the latest result from PhotonVision
    var result = camera.getLatestResult();
    // Get the current best target.
    PhotonTrackedTarget target = result.getBestTarget();
    double yeeYaw = target.getYaw();


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
