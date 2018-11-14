//rudimentary PI Control for angular movement

package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Gyro;


public class Robot extends IterativeRobot {
	
	Victor left = new Victor(0);
		//victor on robot port 0
	Victor right = new Victor(1);
		//victor on robot port 1
	Joystick leftStick = new Joystick(1);
		//joystick on DS port 1
	Joystick rightStick = new Joystick(2);
		//joystick on DS port 2
	
	double integral = 1;
	double proportional = 1;
	double out = proportional + integral;

	@Override
	public void robotInit() {
	}

	@Override
	public void autonomousInit() {
	}

	public double PI(double setpoint, double kp, double ki) {
		//setpoint is the degrees you want to turn 
		//kp is the proportional constant found by trial
		//ki is the integral constant found by trial
		
		Gyro gyro = new AnalogGyro(1);
		
		//initial error values before calculation
		double previousError = 0;
		
		double angle = gyro.getAngle();
		double error = setpoint - angle;
			//error = target - actual
		
		while (angle < setpoint || angle > setpoint) {
			//while angle is not correct
				angle -= gyro.getAngle();
					//update angle
				error -= setpoint - angle; 
					//update error
				integral += previousError * (error * ki);
					//calculate integral
					//sigma error * integral constant
				proportional += error * kp;
					//calculate proportion
					//how far we are off * how much we are changing
				previousError += error; 
					//add the current error to the sum of all past error 	
		}
		return out;
	}
	
	@Override
	public void autonomousPeriodic() {
		PI(90, 0.25, 0.1);
		left.set(out);
		right.set(out);
		//set drivetrain to the output
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
