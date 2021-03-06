// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4678.Cybercavs2016Code.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4678.Cybercavs2016Code.Robot;

/**
 *
 */
public class AutoAim extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutoAim() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=RO--BOTBUILDER ID=REQUIRES
        requires(Robot.robotDrive);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.camera.cameraLightsOn();
    	Robot.robotDrive.autoAimInit();
    	Robot.manipulatorArm.setManipulatorMode("ShootMode"); //moves manipulator out of the way
    	Robot.pickupArm.setArmMode("ShootMode"); // moves pickup arm out of the way
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.robotDrive.autoAim()) {
    		Robot.camera.cameraLightsOff(); //if auto aim on target turns light off
    	}
    	Robot.camera.cameraLightsOn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.camera.cameraLightsOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
