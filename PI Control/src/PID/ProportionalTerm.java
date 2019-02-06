//DEPENDECY: 'Variables.java'

package PID;
import PID.Variables;

//creates the proportional term of the PID control
//proportional hyperparameter (kp) multiplied by the error (error)
//accounts for the current error

public class ProportionalTerm {	
	public static double proportional() {
		double proportional = Variables.kp * Variables.error;
		return proportional;
	}
}
