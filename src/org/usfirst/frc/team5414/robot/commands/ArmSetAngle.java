package org.usfirst.frc.team5414.robot.commands;

import org.usfirst.frc.team5414.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmSetAngle extends Command {

	double setpoint;
	double kp = 0.06;
	double ki = 0.00015;
	double kd = .225;
	double lastError = 0;
	double integralError = 0;
	
    public ArmSetAngle(double setpoint) {
    	requires(Robot.arm);
    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.prefs = Preferences.getInstance();
        kp = Robot.prefs.getDouble("kp",kp);
        ki = Robot.prefs.getDouble("ki",ki);
        kd = Robot.prefs.getDouble("kd",kd);
        integralError = 0;
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = Robot.arm.getAngle();
    	
    	double error = setpoint - currentAngle;
    	double P = error * kp;
    	
    	integralError += error;
    	double I = integralError * ki;
    	
    	double changeError = error - lastError;
    	double D = changeError * kd;
    	
    	double F = Robot.arm.getHoldingOutput();
    	
    	double output = P + I + D + F;
    	Robot.arm.setSpeed(output);
    	
    	lastError = error;
    	
    	SmartDashboard.putNumber("setAngleError", error);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(lastError) < 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.setSpeed(Robot.arm.getHoldingOutput());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.setSpeed(0);
    }
}
