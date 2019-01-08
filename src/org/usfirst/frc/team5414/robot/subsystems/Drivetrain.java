package org.usfirst.frc.team5414.robot.subsystems;

import org.usfirst.frc.team5414.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

	VictorSPX leftSlave1 = new VictorSPX(11);
	VictorSPX leftSlave2 = new VictorSPX(10);
	VictorSPX rightSlave1 = new VictorSPX(12);
	VictorSPX rightSlave2 = new VictorSPX(13);
	TalonSRX leftMaster = new TalonSRX(14);
	TalonSRX rightMaster = new TalonSRX(16);
	
	Encoder leftEncoder = new Encoder(6,7);
	Encoder rightEncoder = new Encoder(8,9);
	
	public Drivetrain() {
		rightSlave1.setInverted(true);
		rightSlave2.setInverted(true);
		rightMaster.setInverted(true);
		leftSlave1.follow(leftMaster);
		leftSlave2.follow(leftMaster);
		rightSlave1.follow(rightMaster);
		rightSlave2.follow(rightMaster);
		leftEncoder.setReverseDirection(true);
	}
	
	public void driveWithJoystick(double forward, double rotate ) {
		setSpeed(-forward+rotate, -forward-rotate);
	}
	
	public void setSpeed(double leftSpeed, double rightSpeed) {
		setSpeedLeft(leftSpeed);
		setSpeedRight(rightSpeed);
	}
	
	public void setSpeedLeft(double leftSpeed) {
		leftMaster.set(ControlMode.PercentOutput, leftSpeed);
	}
	
	public void setSpeedRight(double rightSpeed) {
		rightMaster.set(ControlMode.PercentOutput, rightSpeed);
	}
	
	public long getLeftEncoder() {
		return leftEncoder.get();
	}
	
	public long getRightEncoder() {
		return rightEncoder.get();
	}
	
	public double getLeftEncoderInches() {
		return leftEncoder.get()/ 128.0 * 2 * Math.PI * 3; //256
	}
	
	public double getRightEncoderInches() {
		return rightEncoder.get()/ 128.0 * 2 * Math.PI * 3; //256
	}
	
	public double getYawEncoder() {
		double initialDifference = Robot.drivetrain.getLeftEncoder() - Robot.drivetrain.getRightEncoder();
		return initialDifference * 360 / 1024;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

