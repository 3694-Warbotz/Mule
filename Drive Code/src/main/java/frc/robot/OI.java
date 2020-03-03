package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static Victor leftFront = new Victor(1);
	public static Victor leftBack = new Victor(3);
	public static Victor rightFront = new Victor(0);
	public static Victor rightBack = new Victor(2);
	public static Victor turretRotation = new Victor(4);
	public static Victor turretElevation = new Victor(5);
	public static SpeedControllerGroup left = new SpeedControllerGroup(leftFront, leftBack);
	public static SpeedControllerGroup right = new SpeedControllerGroup(rightFront, rightBack);

	public static Solenoid sole1 = new Solenoid(0);
	public static Solenoid sole2 = new Solenoid(1);
	
	public static Compressor compressor = new Compressor();

	public static Joystick leftDrive = new Joystick(0);
	public static Joystick rightDrive = new Joystick(1);
	public static Joystick turret = new Joystick(2);

	public static JoystickButton safety = new JoystickButton(turret, 2);
	public static JoystickButton trigger = new JoystickButton(turret, 1);
	
	public static byte rangeFinderPort = 0; //address for rangefinder 
	public static byte rangeFinderAddress = 0; //address for rangefinder 

}
