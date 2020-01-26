/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import java.lang.reflect.Array;
import edu.wpi.first.wpilibj.Encoder; //imports encoder class
import edu.wpi.first.wpilibj.IterativeRobot; //imports functions for robot
import edu.wpi.first.wpilibj.Victor; //imports the victor (motors)
import edu.wpi.first.wpilibj.Joystick; //imports joysticks
import edu.wpi.first.wpilibj.RobotDrive; //imports the drive class for the robot
import edu.wpi.first.wpilibj.SpeedControllerGroup; //imports class 

//check childrens bible page 451 for motivations 

public class Robot extends IterativeRobot {
	//creates victors
	Victor leftFront = new Victor(0);
	Victor rightFront = new Victor(1);
	Victor leftBack = new Victor(2);
	Victor rightBack = new Victor(3);
	//creates joysticks
	Joystick leftStick = new Joystick(0);
	Joystick rightStick = new Joystick(1);
	//assigns joysticks to right and left side victors for tank drive
	SpeedControllerGroup left = new SpeedControllerGroup(leftFront, leftBack);
	SpeedControllerGroup right = new SpeedControllerGroup(rightFront, rightBack);
	//creates encoders
	Encoder rightFrontEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	Encoder leftFrontEnc = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
	Encoder rightBackEnc = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
	Encoder leftBackEnc = new Encoder(6, 7, false, Encoder.EncodingType.k4X);
	//function for turning left
	public void turnLeft() { 
		for (int z = 0; z < 9; z++) {
			right.tankDrive(0.5, 0.5);
		}
	}
	//function for turning right
	public void turnRight() {
		for (int z = 0; z < 9; z++) {
			left.tankDrive(0.5, 0.5);
		}
	}
	//function for turning around
	public void turnAround() { 
		for (int z = 0; z < 18; z++) {
			left.tankDrive(0.5, 0.5);
		}
	}
	//distances looking to travel in inches (input here), one decimal allowed
	double[] distances = {540, 1956, 1572, 120, 192, 780, 528, 372}; 
	//strings of turns in order
	String[] turns = {"left", "full", "right", "right", "left", "left", "left"}; 
	//diameter of wheels = 6 inches
	//circumference of wheels = 18.84 inches

	@Override
	public void robotInit() {

	}

	@Override
	public void robotPeriodic() {

	}

	@Override
	public void autonomousInit() {

	}
	
	@Override
	public void autonomousPeriodic() { //encoder code in autonomous
		leftFrontEnc.setDistancePerPulse(1 / 48); //pulses per second = 48 (blame michael if wrong)
		leftFrontEnc.setMaxPeriod(0.1); //period after stopping
		leftFrontEnc.setMinRate(10); //idk i copy and pasted
		leftFrontEnc.setReverseDirection(true); //sets it so wheels don't go inverse direction
		leftFrontEnc.setSamplesToAverage(5); //also copied

		//loop used for driving a distance and then turning
		for (int i = 0; i < 7 /*put length of distance array here*/ ; i++) { 
			if(encoder.getDistance < distances[i] ) /*finds distance of encoder traveled from start*/ { 
				drive.tankDrive(0.5, 0.5); //drives at half power
				if (turns[i] == "left") /* turning functions, detects which way to turn and does it */ {
					turnLeft(); //calls functions to turn
				} else if (turns[i] == "right") {
					turnRight();
				} else if  (turns[i] == "left") {
					turnAround();
				} 
			else {
				drive.tankDrive(0, 0); // doesn't drive
			} 
		
			}
		leftFrontEnc.reset(0); //resets encoder
		}
	}

	@Override
	public void teleopPeriodic() {
		left.set(leftStick.getY()); //moves robot with joysticks
		right.set(rightStick.getY());
	}

	@Override
	public void testPeriodic() {

	}
}
