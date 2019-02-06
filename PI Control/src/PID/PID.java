//DEPENDENCY: 'IntegralTerm.java' 'ProportionalTerm.java'

package PID;

//actual PID function
//uses only the proportional and integral terms
public class PID {
	public static double PI() {
		return ProportionalTerm.proportional() + IntegralTerm.integral();
	}
}
