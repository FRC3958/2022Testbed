// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.ColorSensor;

public class ArduinoDefault extends CommandBase {
  /** Creates a new ArduinoDefault. */
  Arduino m_a;
  ColorSensor m_cs; 
  XboxController m_xb;
  public ArduinoDefault(Arduino a, ColorSensor cs, XboxController xb) {
    m_a = a; 
    m_cs = cs;
    m_xb = xb; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_a);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_a.sendMessage("000000");
    m_a.sendMessage(m_cs.findBallColor());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_a.sendMessage(m_cs.liveResultFromColorSensor());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
