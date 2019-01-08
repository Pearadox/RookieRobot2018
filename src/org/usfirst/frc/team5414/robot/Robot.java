/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5414.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5414.robot.commands.DriveDistanceEncoder;
import org.usfirst.frc.team5414.robot.commands.DriveDistanceGyro;
import org.usfirst.frc.team5414.robot.commands.DriveTimed;
import org.usfirst.frc.team5414.robot.commands.TurnEncoder;
import org.usfirst.frc.team5414.robot.subsystems.Arm;
import org.usfirst.frc.team5414.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5414.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team5414.robot.subsystems.IMU;

public class Robot extends TimedRobot {

	Command autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public static Preferences prefs;
	
	public static OI oi;
	public static Drivetrain drivetrain;
	public static Arm arm;
	public static Compressor compressor;
	public static IMU gyro;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		prefs = Preferences.getInstance();
		drivetrain = new Drivetrain();
		arm = new Arm();
		gyro = new IMU();
		oi = new OI();
		compressor = new Compressor(0);
		
		
		compressor.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		drivetrain.driveWithJoystick(0,0);
		double angle = Robot.arm.getAngle();
		SmartDashboard.putNumber("armAngle", angle);
		double voltage = Robot.arm.getVoltage();
		SmartDashboard.putNumber("armVoltage", voltage);
		SmartDashboard.putNumber("getLeftEncoder", Robot.drivetrain.getLeftEncoder());
		SmartDashboard.putNumber("getRightEncoder", Robot.drivetrain.getRightEncoder());
		SmartDashboard.putNumber("getLeftEncoderInches", Robot.drivetrain.getLeftEncoderInches());
		SmartDashboard.putNumber("getRightEncoderInches", Robot.drivetrain.getRightEncoderInches());
		SmartDashboard.putNumber("Yaw",gyro.getYaw());
		SmartDashboard.putNumber("getYawEncoder", Robot.drivetrain.getYawEncoder());
	}
	
	@Override
	public void autonomousInit() {
		//autonomousCommand = new DriveDistanceEncoder(144);
		autonomousCommand = new TurnEncoder(90);
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		double forwardJoystick = oi.joystick.getRawAxis(1);
		double rotateJoystick = oi.joystick.getRawAxis(2);
		if(Math.abs(forwardJoystick)<.15) {
			forwardJoystick = 0;
		}
		drivetrain.driveWithJoystick(forwardJoystick/2., rotateJoystick/3.);
		double angle = Robot.arm.getAngle();
		SmartDashboard.putNumber("armAngle", angle);
		
		double holdingOutput = Robot.arm.getHoldingOutput();
	    SmartDashboard.putNumber("armOutput", holdingOutput);
		
		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
