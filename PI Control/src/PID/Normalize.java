package PID;

import com.analog.adis16448.frc.ADIS16448_IMU;

public class Normalize {
	public static final ADIS16448_IMU imu = new ADIS16448_IMU();
		
	static double angle  = imu.getAngleZ();
		
	static double x = Math.cos(angle);
	static double y = Math.sin(angle);
		
	static double normed = Math.atan2(y, x);
}
