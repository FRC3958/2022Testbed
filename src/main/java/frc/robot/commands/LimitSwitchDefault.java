// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.LimitSwitch;

public class LimitSwitchDefault extends CommandBase {
  /** Creates a new LimitSwitchDefault. */
  LimitSwitch m_ls;
  Arduino m_a; 
  long time; 
  boolean last_value = false; 

  public LimitSwitchDefault(LimitSwitch l, Arduino a) {
    m_ls = l;
    m_a = a; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_ls);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = System.currentTimeMillis();
    last_value = m_ls.isSwitchClosed();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(System.currentTimeMillis()-time > 300) {
      if(last_value != m_ls.isSwitchClosed()) {
        if(m_ls.isSwitchClosed()) {
          m_a.sendMessage(ColorSensor.returnRoundedConcatenatedColorPercents(Color.kYellow));
        
  
        } else {
          m_a.sendMessage(ColorSensor.returnRoundedConcatenatedColorPercents(Color.kBlack));
        }
        last_value = m_ls.isSwitchClosed();
      }
      time = System.currentTimeMillis();

    }  
      

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
