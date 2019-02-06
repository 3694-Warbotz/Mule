//DEPENDENCY: 'PID.java' 'RobotMap.java' 'Variables.java' 'Monitor.java'

package org.usfirst.frc.team3694.robot;

import PID.Monitor;
import PID.PID;
import PID.Variables;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Robot extends IterativeRobot {
	
	Victor left = new Victor(RobotMap.leftMotor); 
	Victor right = new Victor(RobotMap.rightMotor);
	Joystick leftStick = new Joystick(RobotMap.leftStick);
	Joystick rightStick = new Joystick(RobotMap.rightStick);
	//ports given in RobotMap.java 
	
	Encoder rightEncoder = new Encoder(RobotMap.encoderPort1, RobotMap.encoderPort2, false, Encoder.EncodingType.k4X);
	//initialises encoder on ports 0 and 1 at the highest possible accuracy 
	
	DifferentialDrive robotDrive = new DifferentialDrive(left, right);
	
	@Override
	public void robotInit() {
		
	}

	@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {
		if (Variables.error > Variables.reference) {
			double out = PID.PI();
			
			robotDrive.arcadeDrive(out, out);
			Monitor.monitor();
		}	
	}


	@Override
	public void teleopPeriodic() {
		//basic tank drive
		left.set(leftStick.getY());
		right.set(rightStick.getY());
	}

	
	@Override
	public void testPeriodic() {
	}
}
