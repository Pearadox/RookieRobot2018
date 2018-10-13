package org.usfirst.frc.team5414.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

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
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Drivetrain() {
		leftSlave1.setInverted(true);
		leftSlave2.setInverted(true);
		leftMaster.setInverted(true);
		leftSlave1.follow(leftMaster);
		leftSlave2.follow(leftMaster);
		rightSlave1.follow(rightMaster);
		rightSlave2.follow(rightMaster);
		
	}
	
	public void driveWithJoystick(double forward, double rotate ) {
		leftMaster.set(ControlMode.PercentOutput, forward-rotate);
		rightMaster.set(ControlMode.PercentOutput, forward+rotate);
		
		
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

