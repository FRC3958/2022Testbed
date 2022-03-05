// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArduinoDefault;
import frc.robot.commands.AutonDrivingFullRoutine;
import frc.robot.commands.DriveToDistance;
import frc.robot.commands.DrivingCommand;
import frc.robot.commands.LimitSwitchDefault;
import frc.robot.commands.ShootingCommand;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gateway;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LimitSwitch;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TesterClimber;
import frc.robot.subsystems.UltraSensor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final XboxController m_xc = new XboxController(Constants.Controller0ID);
  /*
  private final Limelight m_limelight = new Limelight(); 
  private final UltraSensor m_UltraSensor = new UltraSensor();

  */
  private final TesterClimber m_climber = new TesterClimber();
  private final AutonDrivingFullRoutine m_autonCommand = new AutonDrivingFullRoutine(m_driveTrain);
  //private final Climbing m_climberCommand = new Climbing(m_climber);

  
//really importanter commenter
  private final DrivingCommand m_drivingCommand = new DrivingCommand(m_driveTrain, m_xc);
  //
  //ppprivate final Compressor m_compressor = new Compressor(Constants.PCMCANID, PneumaticsModuleType.CTREPCM);
  /*private final DoubleSolenoid m_ds = new DoubleSolenoid(Constants.PCMCANID, PneumaticsModuleType.CTREPCM, Constants.PCMForwardChannel, Constants.PCMReverseChannel);
  
  private final CamTurn m_camTurn = new CamTurn(m_limelight);

  private final Shooter m_shooter = new Shooter(); 
  private final Gateway m_gateway = new Gateway();


  private final AutonDrivingFullRoutine m_autonCommand = new AutonDrivingFullRoutine(m_driveTrain);
  private final LimitSwitchDefault m_limitSwitchCommand = new LimitSwitchDefault(m_LimitSwitch, m_arduino); 


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //m_compressor.disable(); 
    /*m_limelight.turnLEDOn();
    SmartDashboard.putNumber("set speed", 0);
    SmartDashboard.putNumber("distance to travel", 0); 
    //SmartDashboard.putNumber("goal angle", 0);
    //SmartDashboard.putData(new ShootingCommand(m_shooter, m_UltraSensor));*/
        // Configure the button bindings
    configureButtonBindings();
    /*
    SmartDashboard.putData(new DriveToDistance(m_driveTrain, SmartDashboard.getNumber("distance to travel", 0)));
    SmartDashboard.putData(new TurnToAngle(m_driveTrain, SmartDashboard.getNumber("goal angle", 0)));*/

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    m_driveTrain.setDefaultCommand(m_drivingCommand);

    /*


    new JoystickButton(m_xc, Constants.StartButtonID)
      .whenPressed(() -> m_limelight.turnLEDOn())
      .whenReleased(() -> m_limelight.turnLEDOff());

    
      */

    new JoystickButton(m_xc, Constants.XButtonID)
      .whenPressed(() -> m_climber.turnArm(.15))
      .whenReleased(() -> m_climber.turnArm(0));

    new JoystickButton(m_xc, Constants.BButtonID)
      .whenPressed(() -> m_climber.turnArm(-.15))
      .whenReleased(() -> m_climber.turnArm(0));

    new JoystickButton(m_xc, Constants.StartButtonID)
      .whenPressed(() -> m_climber.resetEncoders());

      
    new JoystickButton(m_xc, Constants.YButtonID)
      .whenPressed(() -> m_climber.pullUpDown(0.4))
      .whenReleased(() -> m_climber.pullUpDown(0));

    
    new JoystickButton(m_xc, Constants.AButtonID)
      .whenPressed(() -> m_climber.pullUpDown(-0.4))
      .whenReleased(() -> m_climber.pullUpDown(0));

    new JoystickButton(m_xc, Constants.BackButtonID) 
      .whenPressed(() -> m_climber.setArmPosition(0))
      .whenReleased(() -> m_climber.turnArm(0));


  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autonCommand;
  }
  
}
