//Defines stuff
package org.usfirst.frc.team3694.robot;

//import stuff here
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

//ROBOT CODE FROM THIS POINT ON
public class Robot extends SampleRobot {
	CameraServer server;
	Image image;
	RobotDrive chassis;
    Joystick driveStick;
    Joystick shootStick;
    Victor leftDriveMotor = new Victor(0);
    Victor rightDriveMotor = new Victor(1);
    Victor skidMotor = new Victor(2);
    Victor liftMotor = new Victor(3);

//ROBOT INIZILIZATION
    public void robotInit() {
    	server = CameraServer.getInstance();
    	server.setQuality(0);
    	server.startAutomaticCapture("cam0");
    	chassis = new RobotDrive(leftDriveMotor, rightDriveMotor);
    	chassis.setExpiration(0.1);
    	driveStick = new Joystick(0);
    	shootStick = new Joystick(1);
    }

//AUTONOMOUS
    public void autonomous() {
            chassis.setSafetyEnabled(false);
            chassis.drive(0.5, 0.0);	// drive forwards half speed
            Timer.delay(2.0);		//    for 2 seconds
            chassis.drive(0.0, 0.0);	// stop robot
    }

//TELEOPERATED
    public void operatorControl() {
    	 chassis.setSafetyEnabled(true);
        //while under in Teleoperation mode.
        while (isOperatorControl() && isEnabled()) {
            Timer.delay(0.005);
            chassis.arcadeDrive(driveStick);
            double shootY = shootStick.getAxis(Joystick.AxisType.kY);
            liftMotor.set(shootY);
        }
    }
}
//END BRACKET, ROBOT CODE ENDS