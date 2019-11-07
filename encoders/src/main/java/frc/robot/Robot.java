/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.lang.reflect.Array;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup; 




public class Robot extends IterativeRobot {
  

  Victor leftFront = new Victor(0);
  Victor rightFront = new Victor(1);
  Victor leftBack = new Victor(2);
  Victor rightBack = new Victor(3);

  Joystick leftStick = new Joystick(0);
  Joystick rightStick = new Joystick(1);

  SpeedControllerGroup left = new SpeedControllerGroup(leftFront, leftBack);
  SpeedControllerGroup right = new SpeedControllerGroup(rightFront, rightBack);

  Encoder rightFrontEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
  Encoder leftFrontEnc = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
  Encoder rightBackEnc = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
  Encoder leftBackEnc = new Encoder(6, 7, false, Encoder.EncodingType.k4X);

  public void turnLeft() { 
    for (int z = 0; z > 9; z++) {
    right.tankDrive( .5, .5);
    }
  }

  public void turnRight() {
    for (int z = 0; z > 9; z++) {
      left.tankDrive(.5, .5);
    }
  }



  double[] distances = {540, 1956, 1572, 120, 192, 780, 528, 372}; 
  
  String[] turns = {left, full, right, right, left, left, left}; 

  double startPoint = 0;

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
  public void autonomousPeriodic() {
    // Configures the encoder to return a distance of 4 for every 256 pulses
    // Also changes the units of getRate
    leftFrontEnc.setDistancePerPulse(1 / 48);
 
    // Configures the encoder to consider itself stopped after .1 seconds
    leftFrontEnc.setMaxPeriod(.1);

    // Configures the encoder to consider itself stopped when its rate is below 10
    leftFrontEnc.setMinRate(10);

    // Reverses the direction of the encoder
    leftFrontEnc.setReverseDirection(true);

    // Configures an encoder to average its period measurement over 5 samples
    // Can be between 1 and 127 samples
    leftFrontEnc.setSamplesToAverage(5);

  
    //for (int i = 0; i < 7; i++) { //distances[i]
      if(encoder.getDistance < 10 ) {
      drive.tankDrive(.5, .5);
    } else {
      drive.tankDrive(0, 0);
    /*  if (turns[i] = left) {
        turnLeft;
      }
      */  

      
    }
  leftFrontEnc.reset(0);  
      
  }

  //  }

  
  @Override
  public void teleopPeriodic() {
    left.set(leftStick.getY());
    right.set(rightStick.getY());

    
  }

  
  @Override
  public void testPeriodic() {
  }
}
