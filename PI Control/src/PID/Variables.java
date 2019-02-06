//DEPENDENCY: 'Normalize.java'

package PID;

import com.analog.adis16448.frc.ADIS16448_IMU;

public class Variables {
	public static final ADIS16448_IMU imu = new ADIS16448_IMU();
	
	public static double kp = 1;
	public static double ki = 1;
	public static double kd = 0;
	
	public static double currentValue = 0;
	public static double cumulativeError = 0;
	public static double reference = 10; //Let's say a target velocity of 10 m/s
	public static double preClampOutput = 0;
	
	public static double error = reference - Normalize.normed; 
	
	//set for specific motor max value
	public static double maxOutput = 1000;
}
