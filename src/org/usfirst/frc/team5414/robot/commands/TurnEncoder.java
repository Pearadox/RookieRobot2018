package org.usfirst.frc.team5414.robot.commands;

import org.usfirst.frc.team5414.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnEncoder extends Command {

	double degrees = 0;
	double initialYaw = 0;
	
    public TurnEncoder(double degrees) {
    	requires(Robot.drivetrain);
    	this.degrees = degrees;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialYaw = Robot.drivetrain.getYawEncoder();
    	//256 = 90 degrees
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (degrees < 0) {
    		Robot.drivetrain.setSpeedLeft(-0.3);
    		Robot.drivetrain.setSpeedRight(0.3);
    	}
    	else {
    		Robot.drivetrain.setSpeedLeft(0.3);
    		Robot.drivetrain.setSpeedRight(-0.3);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if (degrees < 0)
    	{
    		if (Robot.drivetrain.getYawEncoder() - initialYaw <= degrees) return true;
    	}
    	else 
    	{
    		if (Robot.drivetrain.getYawEncoder() - initialYaw >= degrees) return true;
    	}
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
