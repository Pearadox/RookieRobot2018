/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5414.robot;

import org.usfirst.frc.team5414.robot.commands.ArmDownManual;
import org.usfirst.frc.team5414.robot.commands.ArmHold;
import org.usfirst.frc.team5414.robot.commands.ArmSetAngle;
import org.usfirst.frc.team5414.robot.commands.ArmUpManual;
import org.usfirst.frc.team5414.robot.commands.PincherClose;
import org.usfirst.frc.team5414.robot.commands.PincherOpen;
import org.usfirst.frc.team5414.robot.commands.PincherToggle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick joystick = new Joystick(0);
	Button btn1 = new JoystickButton(joystick, 1);
	Button btn3 = new JoystickButton(joystick, 3);
	Button btn4 = new JoystickButton(joystick, 4);
	Button btn6 = new JoystickButton(joystick, 6);
	Button btn7 = new JoystickButton(joystick, 7);
	Button btn8 = new JoystickButton(joystick, 8);
	Button btn11 = new JoystickButton(joystick, 11);
	Button btn12 = new JoystickButton(joystick, 12);
	
	public OI() {
		btn1.whenPressed(new PincherToggle());
		
		btn3.whenPressed(new ArmSetAngle(43));
		
		btn4.whenPressed(new ArmSetAngle(90));
		
		btn7.whileHeld(new ArmDownManual());
		btn7.whenReleased(new ArmHold());
		
		btn8.whileHeld(new ArmUpManual());
		btn8.whenReleased(new ArmHold()); 

		btn6.whenPressed(new ArmSetAngle(135));
		
	}
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
