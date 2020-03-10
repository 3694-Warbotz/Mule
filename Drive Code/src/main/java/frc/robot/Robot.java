
package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
		Rangefinder.init(OI.rangeFinderPort);
	}

	@Override
	public void robotPeriodic() {}

	@Override
	public void autonomousInit() {}

	@Override
	public void autonomousPeriodic() {
	
	}

	public static void fire() {
		if (OI.trigger.get() == true) {
			OI.sole1.set(true);
		} else {
			OI.sole1.set(false);
		}
	}

	@Override
	public void teleopPeriodic() {
		OI.left.set(OI.leftDrive.getY());
		OI.right.set(-OI.rightDrive.getY());
		fire();
	}

	@Override
	public void testPeriodic() {}
}