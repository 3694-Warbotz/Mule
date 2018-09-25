/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3694.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends IterativeRobot {
	CameraServer process;
	double centerX = 0.0;
	final Object imgLock = new Object();
	VisionThread visionThread;
	Gyro gyro;
	UsbCamera camera;
	final ADIS16448_IMU imu = new ADIS16448_IMU();
	@Override
	public void robotInit() {
	    camera = CameraServer.getInstance().startAutomaticCapture();
	    camera.setResolution(320, 240);
	    
	    visionThread = new VisionThread(camera, new Pipeline(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                centerX = r.x + (r.width / 2);
	            }
	        }
	    });
	    visionThread.start();
	}

	@Override
	public void autonomousInit() {

	}
	
	@Override
	public void autonomousPeriodic() {

	}
 
	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void testPeriodic() {
	}
}
