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

  

  

  

  double[] distances = {540, 1956, 1572, 120, 192, 780, 528, 372}; 
  double[] turns = {-90, 180, 90, 90, -90, -90, -90}; 

  double startPoint;

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
    leftFrontEnc.setDistancePerPulse(4./256.);

    // Configures the encoder to consider itself stopped after .1 seconds
    leftFrontEnc.setMaxPeriod(.1);

    // Configures the encoder to consider itself stopped when its rate is below 10
    leftFrontEnc.setMinRate(10);

    // Reverses the direction of the encoder
    leftFrontEnc.setReverseDirection(true);

    // Configures an encoder to average its period measurement over 5 samples
    // Can be between 1 and 127 samples
    leftFrontEnc.setSamplesToAverage(5);



    for (int i = 0; i < 6; i++) {
      double startPoint = 0;
      double distance = distances[i];
      double turn = turns[i];

      
  

    }
  
  }
 

  
  @Override
  public void teleopPeriodic() {
    left.set(leftStick.getY());
    right.set(rightStick.getY());

    


    
  }

  
  @Override
  public void testPeriodic() {
  }
}
