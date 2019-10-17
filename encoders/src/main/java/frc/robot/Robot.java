/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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

  Encoder enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);








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
