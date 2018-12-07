//normalised PI Control for angular movement and one encoder

package org.usfirst.frc.team3694.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Encoder;
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

	@Override
	public void robotInit() {
	}

	@Override
	public void autonomousInit() {
	}

	public double anglePI(double angleSetpoint, double kp, double ki, double angleOffset, double angularProportional, double angularIntegral) {
		//angleSetpoint is the degrees you want to turn 
		//kp is the proportional constant found by trial
		//ki is the integral constant found by trial
		//angleOffset is the margin of error acceptable for turning
		// angularProportional and angularIntegral are initialised here and should be set to 0
		
		double cumulativeAngularError = 0;
			//initial error values before calculation
		
		double angle = imu.getAngleZ();
		
		if (Math.abs(angleSetpoint - angle) > angleOffset) {
			//while angle is not correct
			
				angle = imu.getAngleZ();
					//update angle
				double currentAngularError = angleSetpoint - angle; 
					//update error
				cumulativeAngularError += currentAngularError;
					//sum of all past error
				
				angularIntegral = cumulativeAngularError * ki;
					//calculate integral
					//sigma error * integral constant
				angularProportional = currentAngularError * kp;
					//calculate proportional
					//how far we are off * how much we are changing
		}	
		return angularProportional + angularIntegral; 
	}
	
	
	public double distancePI(double distanceSetpoint, double kp, double ki, double distanceOffset, double movementProportional, double movementIntegral, double distanceTraveled) {
		//distanceSetpoint is the distance you want to travel
		//kp is the proportional constant found by trial
		//ki is the integral constant found by trial
		//distanceOffset is the margin of error acceptable for movement
		// movementProportional, movementIntegral, and distanceTraveled are initialised here and should be set to 0
		
		double cumulativeMovementError = 0;
		
		if (Math.abs(distanceSetpoint - distanceTraveled) > distanceOffset) {
			//while distance is not correct
			
			Encoder rightEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
				//initialises encoder on ports 0 and 1 at the highest possible accuracy 
			rightEncoder.setDistancePerPulse(5);
			
			distanceTraveled = rightEncoder.getDistance();
				//update distance
			double currentMovementError = distanceSetpoint - distanceTraveled;
				//update error
			cumulativeMovementError += currentMovementError;
				//sum past error
			
			movementIntegral = cumulativeMovementError * ki;
				//calculate integral
				//sigma error * integral constant
			movementProportional = currentMovementError * kp;
				//calculate proportional
				//how far we are off * how much we are changing
			
		}
		return movementProportional + movementIntegral;
	}
	
	@Override
	public void autonomousPeriodic() {
		
		double angleOut= anglePI(90, 0.25, 0.1, 1.0, 0, 0);
			//turn 90 (+/-1) degrees (kp = 0.25 and ki = 0.1) 
		double normedAngle = angleOut / 1000;
			//normalise angle output to give to victor
		
		double movementOut = distancePI(36, 0.25, 0.1, 1.0, 0, 0, 0);
			//wheel moves 10 inches (?) per rotation
		double normedDistance = movementOut / 1000;
			//normalise distance output to give to victor
		
		robotDrive.arcadeDrive(0, normedAngle);
		robotDrive.arcadeDrive(normedDistance, 0);
			//victor on 1 is set to the gyro for testing
			//victor on 0 is set to the encoder for testing
	
		System.out.println("Angle Out: " + angleOut);
		System.out.println("Angle: " + imu.getAngleZ());
		System.out.println("Normed Angle: " + normedAngle);
		System.out.println("Movement Out: " + movementOut);
		System.out.println("Normed Distance: " + normedDistance);
	}

	public void teleopPeriodic() {
		left.set(leftStick.getY());
		right.set(rightStick.getY());
			//basic tank drive
	}

	@Override
	public void testPeriodic() {
	}
}