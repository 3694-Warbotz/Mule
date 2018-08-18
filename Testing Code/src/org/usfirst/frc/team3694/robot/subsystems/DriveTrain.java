/*------------------------------------------------
 * Version 0.1
 * Changes:
 * Created left and right Victors
 * Set Default Command to Drive
 --------------------------------------------------*/

package org.usfirst.frc.team3694.robot.subsystems;

import org.usfirst.frc.team3694.robot.RobotMap;
import org.usfirst.frc.team3694.robot.commands.Drive;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

    public static Victor leftVictor = new Victor(RobotMap.leftMotor);
    public static Victor rightVictor =  new Victor(RobotMap.rightMotor);

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    }
}

