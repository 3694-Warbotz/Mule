//normalised rudimentary PI Control for angular movement

package org.usfirst.frc.team3694.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
	public static final ADIS16448_IMU imu = new ADIS16448_IMU();
	
	Victor left = new Victor(0);
		//victor on robot port 0
	Victor right = new Victor(1);
		//victor on robot port 1
	Joystick leftStick = new Joystick(1);
		//joystick on DS port 1
	Joystick rightStick = new Joystick(2);
		//joystick on DS port 2
	DifferentialDrive robotDrive = new DifferentialDrive(left, right);
	
	double integral = 1; 
		//kp times sum error
	double proportional = 1; 
		//kp times error

	@Override
	public void robotInit() {
	}

	@Override
	public void autonomousInit() {
	}

	public double PI(double setpoint, double kp, double ki, double offset) {
		//setpoint is the degrees you want to turn 
		//kp is the proportional constant found by trial
		//ki is the integral constant found by trial
		//offset is the margin of error
		
		double cumulativeError = 0;
			//initial error values before calculation
		
		double angle = imu.getAngleZ();
		double currentError = setpoint - angle;
			//error = target - actual

		if (Math.abs(setpoint - angle) > offset) {
			//while angle is not correct
				angle = imu.getAngleZ();
					//update angle
				currentError = setpoint - angle; 
				cumulativeError += currentError;
					//update error
				integral = cumulativeError * ki;
					//calculate integral
					//sigma error * integral constant
				proportional = currentError * kp;
					//calculate proportion
					//how far we are off * how much we are changing
					//add the current error to the sum of all past error 	
		}	
		
		return proportional + integral;
			//return the PI output as [0] and the number of iterations as [1] and the previous output is [2]		
	}
	
	@Override
	public void autonomousPeriodic() {
		double out = PI(90, 0.25, 0.1, 1.0);
		double normed = out / 1000;
		robotDrive.arcadeDrive(0, out);
			//set drivetrain to the output
		
		System.out.println("Out: " + out);
		System.out.println("Angle: " + imu.getAngleZ());
		System.out.println("Normed: " + normed);
	}

	public void teleopPeriodic() {
		//basic tank drive
		left.set(leftStick.getY());
		right.set(rightStick.getY());
	}

	@Override
	public void testPeriodic() {
	}
}