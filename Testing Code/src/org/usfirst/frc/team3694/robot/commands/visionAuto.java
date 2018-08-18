package org.usfirst.frc.team3694.robot.commands;

import org.usfirst.frc.team3694.robot.Robot;
import org.usfirst.frc.team3694.robot.subsystems.DriveTrain;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.usfirst.frc.team3694.robot.Camera;

public class visionAuto extends Command {
	private static int IMG_HEIGHT = 240;
	public static int IMG_WIDTH = 320;
	
	private static VisionThread visionThread;
	
	public final static Object imgLock = new Object();
	private double centerX = 0.0;
    public visionAuto() {
        requires(Robot.DriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                centerX = r.x + (r.width / 2);
	            }
	        }
	    });
	    visionThread.start();
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
		}
		double turn = ((centerX - (IMG_WIDTH /2)) * .005);
		
		DriveTrain.leftVictor.set(0.75);
		DriveTrain.rightVictor.set(0.75);
		
		while(turn < 0) {
			DriveTrain.leftVictor.set(0.75);
			DriveTrain.rightVictor.set(0.15);
		}
		while(turn > 0) {
			DriveTrain.rightVictor.set(0.75);
			DriveTrain.leftVictor.set(0.15);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
