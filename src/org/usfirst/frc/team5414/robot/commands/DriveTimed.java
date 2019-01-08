package org.usfirst.frc.team5414.robot.commands;

import org.usfirst.frc.team5414.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimed extends Command {

	double leftSpeed;
	double rightSpeed;
	double seconds;
	double initialTime;
	
    public DriveTimed(double leftSpeed, double rightSpeed, double seconds) {
    	requires(Robot.drivetrain);
    	
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    	this.seconds = seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.setSpeed(leftSpeed,rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (System.currentTimeMillis() - initialTime >= 1000*seconds) return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setSpeed(0, 0);
    }
}
