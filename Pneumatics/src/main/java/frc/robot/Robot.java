
package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;

public class Robot extends TimedRobot {
	public static Victor leftFront = new Victor(0);
	public static Victor rightFront = new Victor(1);
	public static Victor leftBack = new Victor(2);
	public static Victor rightBack = new Victor(3);
	public static SpeedControllerGroup left = new SpeedControllerGroup(leftFront, leftBack);
	public static SpeedControllerGroup right = new SpeedControllerGroup(rightFront, rightBack);

	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);

	@Override
	public void robotInit() {
		OI.sole.clearAllPCMStickyFaults(0);
		CameraServer.getInstance().startAutomaticCapture();
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
		left.set(-leftStick.getY());
		right.set(rightStick.getY());

		if (OI.trigger.get() == true) {
			OI.sole.set(true);
		}
	}


	@Override
	public void testPeriodic() {
	}
}
