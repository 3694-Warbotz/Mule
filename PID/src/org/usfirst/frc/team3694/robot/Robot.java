//rudimentary PI Control for angular movement

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

	public double[] PI(double setpoint, double kp, double ki, double offset) {
		//setpoint is the degrees you want to turn 
		//kp is the proportional constant found by trial
		//ki is the integral constant found by trial
		//offset is the margin of error
		
		double cumulativeError = 0;
			//initial error values before calculation
		double count = 1;
			//'count' is the number of times the output is calculated
		double out = 0;
		
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
					
				out =  proportional + integral;
					
				count++; 
					//updates iteration count for use with mean
		}	
		
		return new double[] {out, count};
			//return the PI output as [0] and the number of iterations as [1] and the previous output is [2]		
	}
	
	public double normalise(double meanOut, double maxOut, double minOut) {
		double results[] = PI(90, 0.25, 0.1, 1.0);
			//gets the output of the PI function to be normalised
		
		double currentOut = results[0];
		double count = results[1];
		
		
		if (isAutonomous()) {
			double cumulativeOut = 0;
			
			if (count == 1) {
				maxOut = currentOut; 
				minOut = currentOut; 
				//when starting there is only one output 
			} else {
				if (Math.max(currentOut, maxOut) != maxOut) {
					maxOut = currentOut; 
				//if the new output is higher than the previous max, update the max
				}
					
				if (Math.min(currentOut, minOut) != minOut) {
					minOut = currentOut; 
				//if the new output is lower than the previous min, update the min
				}
			}
			
			cumulativeOut += currentOut; 
				//sum all the outputs
			meanOut = cumulativeOut / count;
				//find the mean of the outputs
		}
		
		return (currentOut - meanOut) / (maxOut - minOut);
			//normalisation formula
	}
	
	@Override
	public void autonomousPeriodic() {
		double norm = normalise(0, 0, 0);
		
		robotDrive.arcadeDrive(0, norm);
			//set drivetrain to the output
		
		System.out.println("Out: " + norm);
		System.out.println("Angle: " + imu.getAngleZ());
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
