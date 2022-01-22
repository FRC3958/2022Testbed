// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int frontLeftMotorID = 10;
    public static final int backLeftMotorID = 11;
    public static final int frontRightMotorID = 12;
    public static final int backRightMotorID = 13;
    public static final int LeftJoystickYAxis = 1;
    public static final int RightJoystickXAxis = 4;
    public static final int Controller0ID = 0;
    public static final int PCMCANID = 15;
    public static final int Solenoid0Channel = 0;
    public static final int AButtonID = 1;
    public static final int BButtonID = 2;
    public static final int PCMForwardChannel = 0;
    public static final int PCMReverseChannel = 1; 
    public static final int StartButtonID = 8; 
    public static final double LimelightHeight = 10.625;
    public static final double TargetHeight = 29;
    public static final double HeightOffset = 18.375; 
    public static final double LimelightFixedAngle = 7.487; 
    public static final double kF = 0.0; 
    public static final double kP = 0.1; 
    public static final double kI = 0.0; 
    public static final double kD = 1.5;
    public static final int FXCANID = 21;
    public static final int XButtonID = 3;
    public static final int YButtonID = 4;
    public static final int kTimeout = 30;
    public static final double QuadEncoderResolution = 2048; //TODO this should be 4096, but the robot was going double the distance
    public static final double WheelDiameter = 0.1524; 
    public static final double AngleTolerance = 5; 
    public static final int SonicChannel = 0;
    
}
