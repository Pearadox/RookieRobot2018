package org.usfirst.frc.team5414.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {
	
	TalonSRX arm = new TalonSRX(20);
	AnalogInput pot = new AnalogInput(0);
	DoubleSolenoid pincher = new DoubleSolenoid(2,5);
	
	double lowAngle = 35; 
	double highAngle = 180;
	//double lowVoltage = 4.078;
    //double highVoltage = 1.546;
	double lowVoltage = 3.84;
	double highVoltage = 1.445;
    boolean isPinched = false;
	
	public Arm() {
		
	}
	
	public double getVoltage() {
		double voltage = pot.getVoltage();
		return voltage;
	}
	
	public double getAngle() { 
		double slope = (highAngle-lowAngle)/(highVoltage-lowVoltage);
		double yInt = lowAngle-lowVoltage*slope;
		double angle = slope*getVoltage() + yInt;
		return angle;
		
	}
	
	public double getHoldingOutput() {
		double angle = getAngle();	
		double radians = angle*Math.PI/180.;
		double sine = Math.sin(radians);
		double amplitude;
		if (isPinched) amplitude = .285;
		else amplitude = .15;
		double holdingOutput = amplitude*sine;
		return holdingOutput;
	}
	
	public void openPincher() {
		isPinched = false;
		pincher.set(DoubleSolenoid.Value.kForward);
	}
	
	public void closePincher() {
		isPinched = true;
		pincher.set(DoubleSolenoid.Value.kReverse);
	}

	public void togglePincher() {
		boolean isOpen = pincher.get() == DoubleSolenoid.Value.kForward;
		if(isOpen)closePincher();
		else openPincher();
	}

	public void setSpeed(double speed) {
		arm.set(ControlMode.PercentOutput, speed);
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
