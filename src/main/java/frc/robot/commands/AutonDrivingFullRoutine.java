// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonDrivingFullRoutine extends SequentialCommandGroup {
  /** Creates a new AutonDrivingFullRoutine. */
  DriveTrain m_dt; 
  public AutonDrivingFullRoutine(DriveTrain d) {
    m_dt = d; 
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new DriveToDistance(m_dt, 0.75),
                new TurnToAngle(m_dt, 180),
                new DriveToDistance(m_dt, 0.75));
  }
}
