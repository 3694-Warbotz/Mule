//DEPENDECY: 'Variables.java'

package PID;

public class IntegralTerm {
	
	//creates the integral term for the PI control
	//sum of all the past error multiplied by the hyperparameter 'ki'
	//deals with previous error
	public static double integral() {
		Variables.cumulativeError += Variables.error;
		Variables.preClampOutput = Variables.cumulativeError * Variables.ki;
	
		//if the actuator is blocked, the cumulative error will continue to increase, overloading the actuator's max possible output
		//to correct this, the function will need to oscillate wildly
		//by eliminating the integral term if this occurs, the system only needs to deal with current error
		if(Variables.preClampOutput > Variables.maxOutput) {
			Variables.ki = 0;
		}
		
		double integral = Variables.cumulativeError * Variables.ki;
		return integral;
	}
}
