/*---------------------------------------
 * Version 0.1
 * Changes:
 * Created Drive Command
 * Created DriveTrain Subsystem w/ both motors
 * Created OI with gamepad and controls for left & right side
 * Created Map for both motors
 */
package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3694.robot.commands.Drive;
import org.usfirst.frc.team3694.robot.commands.defaultAuto;
import org.usfirst.frc.team3694.robot.commands.joystick;
import org.usfirst.frc.team3694.robot.commands.visionAuto;
import org.usfirst.frc.team3694.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
	public static OI m_oi;
	public static DriveTrain DriveTrain;

	Command m_autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();
	
	Command teleopCommand;
	SendableChooser<Command> teleopChooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		m_oi = new OI();
		DriveTrain = new DriveTrain();
		
		autoChooser.addDefault("No Vision", new defaultAuto());
		autoChooser.addObject("Vision", new visionAuto());
		SmartDashboard.putData("Auto mode", autoChooser);
		
		teleopChooser.addDefault("GamePad", new Drive());
		teleopChooser.addObject("Joystick Tank Drive", new joystick());
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		m_autonomousCommand = autoChooser.getSelected();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		teleopCommand = teleopChooser.getSelected();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		teleopCommand.start();
	}

	@Override
	public void testPeriodic() {
	}
}
