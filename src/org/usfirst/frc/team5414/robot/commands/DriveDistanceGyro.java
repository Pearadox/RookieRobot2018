package org.usfirst.frc.team5414.robot.commands;

import org.usfirst.frc.team5414.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceGyro extends Command {

	double kp = 0.015;
	double kd = 0.06;
	double lastError = 0;
	
	double inches;
	boolean leftFinished;
	boolean rightFinished;
	double leftEncoderInitial;
	double rightEncoderInitial;
	double initialYaw;
	
    public DriveDistanceGyro(double inches) {
    	this.inches = inches;
    	requires(Robot.drivetrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftEncoderInitial = Robot.drivetrain.getLeftEncoderInches();
    	rightEncoderInitial = Robot.drivetrain.getRightEncoderInches();
    	
    	initialYaw = Robot.gyro.getYaw();
    	
    	Robot.prefs = Preferences.getInstance();
        kp = Robot.prefs.getDouble("kpDriveYawStriaght",kp);
        kd = Robot.prefs.getDouble("kdDriveYawStriaght",kd);
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftFinished = Robot.drivetrain.getLeftEncoderInches() - leftEncoderInitial >= inches;
    	rightFinished = Robot.drivetrain.getRightEncoderInches() - rightEncoderInitial >= inches;
    	
    	double error =  initialYaw - Robot.gyro.getYaw();
    	
    	double P = error * kp;
    	double D = (error - lastError) * kd;
    	
    	double output = P + D;
    	
    	if (leftFinished) Robot.drivetrain.setSpeedLeft(0);
    	else Robot.drivetrain.setSpeedLeft(0.25 + output);
    	if (rightFinished) Robot.drivetrain.setSpeedRight(0);
    	else Robot.drivetrain.setSpeedRight(0.25 - output);
    	
    	lastError = error;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (leftFinished && rightFinished) return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setSpeed(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setSpeed(0,0);
    }
}
