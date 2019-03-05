package org.usfirst.frc.team3694.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import cern.colt.*;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	
	public static final ADIS16448_IMU imu = new ADIS16448_IMU();
	Encoder rightEncoder = new Encoder(RobotMap.encoderPort1, RobotMap.encoderPort2, false, Encoder.EncodingType.k4X);
	Timer timer = new Timer();
	
	public int kalman() {
		
		double angle = imu.getAngle();
		double position = rightEncoder.getDistance();
		double velocity = position / timer.get();
		
		double timeInterval = 1; 
		//delta t
		
		double[][] currentState = {{position}, 
								   {velocity}};
		DoubleMatrix2D prediction = null;
		prediction.assign(currentState);
		             
		double[][] transformation1 = {{1, timeInterval},
						 			 {0, 1}};
		DoubleMatrix2D transformation = null;
		transformation.assign(transformation1);
		
		DoubleMatrix2D bestEstimate = null;
		
		transformation.zMult(prediction, bestEstimate);
		
		
		//DoubleFactory2D prediction = Double 
		//x^ k = best estimate (simpleState)
		//Pk = covariance matrix
		
	}
	
	@Override
	public void robotInit() {
		timer.start();
	}


	@Override
	public void autonomousInit() {
		
	}
	
	@Override
	public void autonomousPeriodic() {
		
	}

	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void testPeriodic() {
	}
}
