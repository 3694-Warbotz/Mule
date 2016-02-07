//Defines stuff
package org.usfirst.frc.team3694.robot;

//Import stuff here
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

//ROBOT CODE FROM THIS POINT ON
public class Robot extends SampleRobot {
	//Camera and Object-Tracking Objects and Variables
	public USBCamera camera0;

	//SmartDashboard Objects and Variables
	public static char c;
	public static String selected = "Selected";
	public static String blank = "";
	
	//Drive and Chassis Objects and Variables
	public static RobotDrive chassis;
	public final Victor frontDrive = new Victor(0);
	public final Victor rearDrive = new Victor(1);
	public final Victor roller = new Victor(3);
	public final Victor rollerTilt = new Victor(4);
	
	//Joystick Objects and Variables
	public final Joystick driveStick = new Joystick(0);
	public final Joystick shootStick = new Joystick(1);
	public double shootY;

//ROBOT INIZILIZATION
    public void robotInit() {
        //Stream and Adjust Image
        CameraServer.getInstance().startAutomaticCapture("cam0");
        camera0.setFPS(10);
       	
    	//Invert Chassis Motors so that they roll in the right direction
    	frontDrive.setInverted(true);
    	rearDrive.setInverted(true);
    	
    	//Configure motors present in Chassis, configure safety
    	chassis = new RobotDrive(frontDrive, rearDrive);
    	chassis.setExpiration(0.1);
    }   
//WHEN DISABLED    
    public void disabled(){
        //Initialize SmartDashboard Autonomous Defense Chooser with Options
    	if(c == 0){
    		SmartDashboard.putString("Nothing (0)", selected);
    	}else{
    		SmartDashboard.putString("Nothing (0)", blank);
    	}
    	if(c == 1){
        	SmartDashboard.putString("Lowbar (1)", selected);
    	}else{
        	SmartDashboard.putString("Lowbar (1)", blank);
    	}
    	if(c == 2){
        	SmartDashboard.putString("Portcullis (2)" ,selected);
    	}else{
        	SmartDashboard.putString("Portcullis (2)" ,blank);
    	}
    	if(c == 3){
        	SmartDashboard.putString("Cheval de Frise (3)" ,selected);
    	}else{
        	SmartDashboard.putString("Cheval de Frise (3)" ,blank);
    	}
    	if(c == 4){
        	SmartDashboard.putString("Moat (4)",selected);
    	}else{
        	SmartDashboard.putString("Moat (4)",blank);
    	}
    	if(c == 5){
        	SmartDashboard.putString("Ramparts (5)" ,selected);
    	}else{
        	SmartDashboard.putString("Ramparts (5)" ,blank);
    	}
    	if(c == 6){
    		SmartDashboard.putString("Drawbridge (6)" ,selected);
    	}else{
     		SmartDashboard.putString("Drawbridge (6)" ,blank);
    	}
    	if(c == 7){
    		SmartDashboard.putString("Sally Port (7)" ,selected);
    	}else{
    		SmartDashboard.putString("Sally Port (7)" ,blank);
    	}
    	if(c == 8){
        	SmartDashboard.putString("Rock Wall (8)" ,selected);
    	}else{
        	SmartDashboard.putString("Rock Wall (8)" ,blank);
    	}
    	if(c == 9){
        	SmartDashboard.putString("Rough Terrain (9)" ,selected);
    	}else{
        	SmartDashboard.putString("Rough Terrain (9)" ,blank);
    	}
    }
//AUTONOMOUS
    public void autonomous() { 	
    	//Start of loops to figure out which defense selected
    	if(c == 1){
    		//Lowbar autonomous
    		chassis.setSafetyEnabled(false);
    		
    		//Drive forward for two seconds at half speed, the stop
    	    chassis.drive(0.5, 0.0);	
    	    Timer.delay(2.0);
    	    chassis.drive(0.0, 0.0);		
    	}else if(c == 2){
    		//Portcullis autonomous
    	}else if(c == 3){
    		//Cheval de Frise autonomous
    	}else if(c == 4){
    		//Moat autonomous
    	}else if(c == 5){
    		//Rampart autonomous
    	}else if(c == 6){
    		//Drawbridge autonomous
    	}else if(c == 7){
    		//Sallyport autonomous
    	}else if(c == 8){
    		//Rock Wall autonomous
    	}else if(c == 9){
    		//Rough Terrain autonomous
    	}else{
    		//If none of the above are the selected defense, then there is an error
    		SmartDashboard.putString("Error", "No defenses match");
    	}	
    }
//TELEOPERATED
    public void operatorControl() {
    	//Enable Chassis Safety
    	chassis.setSafetyEnabled(true);
        
    	//While in Teleoperated mode.
        while (isOperatorControl() && isEnabled()) {
            //Slight delay required
        	Timer.delay(0.005);
            
        	//Drive chassis using Arcade Drive (One Joystick)
            chassis.arcadeDrive(driveStick);
            
            //Set the roller's tilt to be equal to the Shooting Joystick's Y
            shootY = shootStick.getAxis(Joystick.AxisType.kY);
            rollerTilt.set(shootY);
        }
    }
}
//END BRACKET, ROBOT CODE ENDS