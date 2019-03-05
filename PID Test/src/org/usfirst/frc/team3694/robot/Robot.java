//TODO:
//IF IT DOES NOT WORK:
//1) Increase Proportional Band
//2) Play with hyperparameters
//3A) Replace the //INTEGRAL// section with this:
		/*
		cumulativeError += currentError;
		integral = cumulativeError * ki
		if (integral > maxOut) {
			integral = maxOut;
		}
		*/
//3B) Replace it with this:
		/*
		cumulativeError += currentError;
		integral = cumulativeError * ki
		if (integral > maxOut) {
			integral = 0;
		}
		*/
//4) Try something else
//IF IT DOES WORK:
//1) Add Ramping 
//2) Make it work with the encoders

package org.usfirst.frc.team3694.robot;
import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
	public static final ADIS16448_IMU imu = new ADIS16448_IMU();

	Victor left = new Victor(0); 
	Victor right = new Victor(1);
	
	DifferentialDrive robotDrive = new DifferentialDrive(left, right);
	
	//motor documentation:
	//https://s3.amazonaws.com/andymark-files/Supplemental%20Documentation/am-3775%20RedLine%20Motor%20Performance%20Curves.PDF
	//max RPM 9824
	
	//RIO documentation: 
	//http://www.ni.com/pdf/manuals/375275a.pdf
	//gyro samples 800 S/s
	
	@Override
	public void robotInit() {
	
	}

		@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {
		//HYPERPARAMETERS//
		double ki = 0.5; 
		double kp = 1.5;
		double setpoint = 100; //degrees
		double offset = 0.5;
		double maxOut = 10000; //motor max out
		
		//NORMALIZE ANGLE//
		double angle = imu.getAngleZ(); //degrees
		double x = Math.cos(angle);
		double y = Math.sin(angle);
		double normed = Math.atan2(y, x); //bounded angle in radians
		
		double setRadians = setpoint * (Math.PI / 180); //convert degrees to radians
		
		//INITIALIZE VARIABLES//
		double cumulativeError = 0;
		double proportional = 0;
		double integral = 0;
		
		double currentError = setRadians - normed; //current error in radians
		
		//PROPORTIONAL//
		if (currentError > offset) {
			proportional = currentError * kp;
		}
		
		//INTEGRAL//
		cumulativeError += currentError;
		if (integral < maxOut) {
			integral = cumulativeError * ki;
		}
		
		
		
		double out = proportional + integral;
		
		
		System.out.println("Out: " + out);
		System.out.println("Angle: " + imu.getAngleZ());
		System.out.println("Proportional: " + proportional);
		System.out.println("Integral: " + integral);
		System.out.println("Left Value: " + left.get());
		System.out.println("Right Value: " + right.get());
		
		/*
		//NOT WORKING
		double sigmoid = (1 / (1 + Math.pow(Math.E, (-normed - Math.E))));
		left.set(sigmoid); 
		right.set(sigmoid);
		*/
		
		left.set(out);
		right.set(out);
	}

	@Override
	public void teleopPeriodic() {
	}

	
	@Override
	public void testPeriodic() {
	}
}
