package PID;

//place for debugging messages to be printed to the console
public class Monitor {
	public static void monitor() {
		System.out.println("Angle: " + Normalize.angle);
		System.out.println("Normed Angle: " + Normalize.normed);
		System.out.println("Integral: " + IntegralTerm.integral());
		System.out.println("Proportional: " + ProportionalTerm.proportional());
		System.out.println("PID Output: " + PID.PI());
		//System.out.println("Movement Out: " + movementOut);
		//System.out.println("Normed Distance: " + normedDistance);
	} 
}
