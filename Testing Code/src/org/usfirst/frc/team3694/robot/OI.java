/*----------------------------------------------------------------------------
	Version 0.1                                                                 
	Changes:	
	Create GamePad
	Create doubles for left and right side drive
----------------------------------------------------------------------------*/

package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick GamePad = new Joystick(0); //GamePadObject---------USB(0)
	public static double leftSide = GamePad.getRawAxis(0); //Drive for left motor. Axis int temporary, wil be changed once seen in DS.
	public static double rightSide = GamePad.getRawAxis(0); //Drive for right motor. Axis int temporary, wil be changed once seen in DS.
	
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static double leftY = leftStick.getY();
	public static double rightY = rightStick.getY();
	}
