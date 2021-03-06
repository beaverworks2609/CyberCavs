// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4678.Cybercavs2016Code.subsystems;

import org.usfirst.frc4678.Cybercavs2016Code.Robot;
import org.usfirst.frc4678.Cybercavs2016Code.RobotMap;
import org.usfirst.frc4678.Cybercavs2016Code.commands.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class ManipulatorArmOld extends Subsystem {
	
	enum joints {TIME, WRIST, ELBOW, LEFT, RIGHT}
	
	private double wristPos = 0;
	private double elbowPos = 0;
	private double leftPos = 0;
	private double rightPos = 0;
	private double wristSpeed = 0;
	private double elbowSpeed = 0;
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
	String manipulatorMode;
	int wristStartPosition = 0;
	int elbowStartPosition = 0;
	int count = 0;
	double currentWristPosition = 0;
	double previousWristPosition = 0;
	int portcullisState = 0;
	double wristPosition = 0;
	double elbowPosition = 0;
	double leftEncoder = 0;
	double rightEncoder = 0;
	int comboindex = 0;
	double time = 0;
	double speed_mult = 0;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon manipulatorElbow = RobotMap.manipulatorArmmanipulatorElbow;
    private final CANTalon manipulatorWrist = RobotMap.manipulatorArmmanipulatorWrist;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    int elbowShootPosition = Robot.manipulatorElbowRestPosition() + 61766;
    int elbowPortcullisPosition = Robot.manipulatorElbowRestPosition() + 59846;
    int wristShootPosition = Robot.manipulatorWristRestPosition() - 9450;
    int wristPortcullisPosition = Robot.manipulatorWristRestPosition() - 8887;
    
    double[][] portcullis = new double[][]{
    		{2.0,-9483.0, 68573.0, 110.0, 106.0},
    		{2.0,-9560.0, 62045.0, 110.0, 106.0},
    		{2.0,-9562.0, 55137.0, 110.0, 106.0},
    		{2.0,-11714.0, 50455.0, 110.0, 107.0},
    		{2.0,-15158.0, 49245.0, 110.0, 107.0},
    		{2.0,-10661.0, 40377.0, -103.0, -105.0},
    		{2.0,-8285.0, 25347.0, -278.0, -273.0},
    		{2.0,-6213.0, 8355.0, -454.0, -452.0},
    		{2.0,-3067.0, 2473.0, -528.0, -525.0},
    		{1.0,-3067.0, 2473.0, -528.0, -525.0},
    		};
    
    		
   double[][] cheval = new double[][]{
			{1.5,-4852.0, 62487.0, -24.0, -23.0},
			{1.5,-1910.0, 48322.0, -24.0, -23.0},
			{1.5,-5416.0, 32160.0, -24.0, -23.0},
			{1.5,-5368.0, 45194.0, -106.0, -104.0},
			{1.5,-5143.0, 59423.0, -104.0, -102.0},
			{1.5,-5149.0, 67295.0, -104.0, -101.0},
			{1.5,-5171.0, 68752.0, -284.0, -274.0},
			{1.5,-2699.0, 37567.0, -280.0, -270.0},
			{1.5,2419.0, 2452.0, -544.0, -524.0},
			{1.5,2419.0, 2453.0, -785.0, -782.0},
			{1.5,2419.0, 2454.0, -921.0, -925.0},
			{0.75,2419.0, 2454.0, -921.0, -925.0},		
  	};
  	
  	double[][] sally = new double[][]{
  			{2.5,-5572.0, 63552.0, -11.0, -11.0},
  			{2.5,-3887.0, 51708.0, -11.0, -11.0},
  			{2.5,-9412.0, 32606.0, -11.0, -11.0},
  			{2.5,-16908.0, 42705.0, -11.0, -11.0},
  			{2.5,-13727.0, 40044.0, -10.0, -10.0},
  			{2.5,-12768.0, 38069.0, 273.0, 269.0},
  			{2.5,-13107.0, 34085.0, 545.0, 556.0},
  			{2.5,-12607.0, 32702.0, 604.0, 692.0},
  			{2.5,-10412.0, 32451.0, 435.0, 792.0},
  			{2.5,-10412.0, 32451.0, 524.0, 533.0},
  			{2.5,-10412.0, 32451.0, -159.0, -137.0},
  			{1.25,-10412.0, 32451.0, -159.0, -137.0},
  	};
  	
  	double[][] drawbridge = new double[][]{
  			{1.75,-5240.0, 64136.0, -20.0, -21.0},
  			{1.75,-2133.0, 39439.0, -19.0, -19.0},
  			{1.75,-5425.0, 22567.0, -20.0, -19.0},
  			{1.75,-18011.0, 33501.0, -20.0, -20.0},
  			{1.75,-27922.0, 47721.0, -20.0, -19.0},
  			{1.75,-24754.0, 47413.0, -20.0, -19.0},
  			{1.75,-23377.0, 47665.0, 257.0, 250.0},
  			{1.75,-17893.0, 46463.0, 567.0, 549.0},
  			{1.75,-9020.0, 52448.0, 643.0, 626.0},
  			{1.75,-10279.0, 76674.0, 641.0, 624.0},
  			{1.75,-6888.0, 76388.0, 466.0, 453.0},
  			{1.75,-6896.0, 77334.0, 299.0, 290.0},
  			{1.75,-5850.0, 41067.0, 125.0, 121.0},
  			{1.75,2626.0, 2418.0, -74.0, -78.0},
  			{0.625,2626.0, 2418.0, -74.0, -78.0},
  	};

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new SetManipulatorArm());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
   
	//////////////////////////////////////
	//////////Setter Functions//////////
	/////////////////////////////////////
    
    public void setManipulatorWrist(int position) {
    	manipulatorWrist.configPeakOutputVoltage(+12f, -12f); //max and min power
    	manipulatorWrist.setPID(0.5, 0, 0); //PID values
    	manipulatorWrist.setAllowableClosedLoopErr(20);
    	manipulatorWrist.set(position); // allowable error in the PID position movement
    }
    public void setManipulatorElbow(int position) {
    	manipulatorElbow.configPeakOutputVoltage(+12f, -12f); //max and min power
    	manipulatorElbow.setPID(0.5, 0, 0); //PID values
    	manipulatorElbow.setAllowableClosedLoopErr(20);
    	manipulatorElbow.set(position); // allowable error in the PID position movement
    }
    public void setManipulatorElbowMode(int mode) {
		if (mode == 0) {
			manipulatorElbow.changeControlMode(TalonControlMode.Current);
		}
		if (mode == 1) {
			manipulatorElbow.changeControlMode(TalonControlMode.Disabled);
		}
		if (mode == 2) {
			manipulatorElbow.changeControlMode(TalonControlMode.Follower);
		}
		if (mode == 3) {
			manipulatorElbow.changeControlMode(TalonControlMode.MotionProfile);
		}
		if (mode == 4) {
			manipulatorElbow.changeControlMode(TalonControlMode.PercentVbus);
		}
		if (mode == 5) {
			manipulatorElbow.changeControlMode(TalonControlMode.Position);
		}
		if (mode == 6) {
			manipulatorElbow.changeControlMode(TalonControlMode.Speed);
		}
		if (mode == 7) {
			manipulatorElbow.changeControlMode(TalonControlMode.Voltage);
		}
	}
	public void setManipulatorWristMode(int mode) {
		if (mode == 0) {
			manipulatorWrist.changeControlMode(TalonControlMode.Current);
		}
		if (mode == 1) {
			manipulatorWrist.changeControlMode(TalonControlMode.Disabled);
		}
		if (mode == 2) {
			manipulatorWrist.changeControlMode(TalonControlMode.Follower);
		}
		if (mode == 3) {
			manipulatorWrist.changeControlMode(TalonControlMode.MotionProfile);
		}
		if (mode == 4) {
			manipulatorWrist.changeControlMode(TalonControlMode.PercentVbus);
		}
		if (mode == 5) {
			manipulatorWrist.changeControlMode(TalonControlMode.Position);
		}
		if (mode == 6) {
			manipulatorWrist.changeControlMode(TalonControlMode.Speed);
		}
		if (mode == 7) {
			manipulatorWrist.changeControlMode(TalonControlMode.Voltage);
		}
	}
	public void setManipulatorMode(String mode) {
		manipulatorMode = mode;
	}
	
	//////////////////////////////////////
	//////////Accessor Functions//////////
	/////////////////////////////////////
	
	public int getManipulatorWristPosition() {return manipulatorWrist.getEncPosition();}
	
	public int getManipulatorElbowPosition() {return manipulatorElbow.getEncPosition();}
	
	public int getManipulatorWristAngular() {return manipulatorWrist.getPulseWidthPosition();}
	
	public int getManipulatorElbowAngular() {return manipulatorElbow.getPulseWidthPosition();}
	
	public String getManipulatorMode() {return manipulatorMode;}
	
	//////////////////////////////////////
	//////////Movement Functions//////////
	/////////////////////////////////////
	
	
	public void restMode() {
		setManipulatorWrist(Robot.manipulatorWristRestPosition());
		setManipulatorElbow(Robot.manipulatorElbowRestPosition());
	}
	public void readyToShoot() {
		setManipulatorWrist(wristShootPosition);
		setManipulatorElbow(elbowShootPosition);
	}
	
	public void CalibrateManipulator() {
		wristStartPosition = (manipulatorWrist.getPulseWidthPosition() % 4096);
		elbowStartPosition = (manipulatorElbow.getPulseWidthPosition() % 4096);
		if (wristStartPosition < 0) {
			wristStartPosition = wristStartPosition + 4096;
		}
		if (elbowStartPosition < 0) {
			elbowStartPosition = elbowStartPosition + 4096;
		}
		manipulatorWrist.setPulseWidthPosition(wristStartPosition);
		manipulatorElbow.setPulseWidthPosition(elbowStartPosition);
		manipulatorWrist.setEncPosition(wristStartPosition);
		manipulatorElbow.setEncPosition(elbowStartPosition);
	}
	
	public void resetCount() {
		count = 0;
	}
	public void portcullis() {
		switch(portcullisState) {
		
		case 0:
			setManipulatorElbow(elbowPortcullisPosition);
			setManipulatorWrist(wristPortcullisPosition);
			if(Math.abs(manipulatorElbow.getError()) < 100 && count > 10) {
				portcullisState++;
				manipulatorWrist.changeControlMode(TalonControlMode.Voltage);
				manipulatorElbow.changeControlMode(TalonControlMode.Voltage);
				manipulatorWrist.set(0);
				manipulatorElbow.set(0);
				count = 0;
			}
			count++;
		break;
		case 1:
			if(count > 10) {
				if (Math.abs(wristPortcullisPosition - currentWristPosition) > 300) {
					Robot.robotDrive.resetEncoders();
					count = 0;
					portcullisState++;
				}
				previousWristPosition = currentWristPosition;
			}
			count++;
			currentWristPosition = manipulatorWrist.getPosition();
		break;
		case 2:
			wristPosition = manipulatorWrist.getPosition();
			elbowPosition = manipulatorElbow.getPosition();
			leftEncoder = Robot.robotDrive.getLeftEncoder();
			rightEncoder = Robot.robotDrive.getRightEncoder();
			System.out.println(wristPosition + ", " + elbowPosition + ", " + leftEncoder + ", " + rightEncoder);
		}
	}

	public double interp(double time, double start, double end, double from, double to) { // Do the interpolation calculation
		return((to - from)*((time - start) / (end - start)) + from);
    }
	
	boolean lookup(int dataset, double time, double speed_multiplier){ // do a lookup of the position information and also calculate speed data
		// First, find the correct time offset into the data
		int x, arysize;
		double time_accumulator = 0, find_time = 0;
		double start_time = 0, end_time = 0;
		boolean found,done;
		double start_pos1 = 0, end_pos1 = 0, pos1 = 0, speed1 = 0;
		double start_pos2 = 0, end_pos2 = 0, pos2 = 0, speed2 = 0;
		double start_pos3 = 0, end_pos3 = 0, pos3 = 0, speed3 = 0;
		double start_pos4 = 0, end_pos4 = 0, pos4 = 0, speed4 = 0;
		
		double timeoffset = time * speed_multiplier;
		
		// Now, try to look this up in the correct dataset
		if (dataset == 0)
		    arysize = portcullis.length / Position_tp.numParameters;
		else if (dataset == 1)
		    arysize = cheval.length / Position_tp.numParameters;
		else if (dataset == 2)
		    arysize = sally.length / Position_tp.numParameters;
		else if (dataset == 3)
		    arysize = drawbridge.length / Position_tp.numParameters;
		else
		    return false; // dataset is out of range.  Return false.  Lookup fails
		
		found = false; // Indicate we have not yet found the dataset elements in the desired time range
		done = false;
		x = 0;
		time_accumulator = 0.0;
		find_time = time * speed_multiplier;
		while((!found)&&(!done)) {
		    switch(dataset) {
		        case 0: // portcullis
		            start_time = time_accumulator;
		            end_time = time_accumulator + portcullis[x][joints.TIME.ordinal()];
		            if ((find_time >= start_time)&&(find_time < end_time))
		                { // If we are in the right time space, set up data for each axis
		                start_pos1 = portcullis[x][joints.WRIST.ordinal()];
		                end_pos1 = portcullis[x+1][joints.WRIST.ordinal()];
		                start_pos2 = portcullis[x][joints.ELBOW.ordinal()];
		                end_pos2 = portcullis[x+1][joints.ELBOW.ordinal()];
		                start_pos3 = portcullis[x][joints.LEFT.ordinal()];
		                end_pos3 = portcullis[x+1][joints.LEFT.ordinal()];
		                start_pos4 = portcullis[x][joints.RIGHT.ordinal()];
		                end_pos4 = portcullis[x+1][joints.RIGHT.ordinal()];
		                found = true;
		                }
		            else if (x >= arysize - 2)
		                done = true; // We have reached the end of the dataset.  Motion is done.
		            break;
		        case 1: // cheval
		            start_time = time_accumulator;
		            end_time = time_accumulator + cheval[x][joints.TIME.ordinal()];
		            if ((find_time >= start_time)&&(find_time < end_time))
		                { // If we are in the right time space, set up data for each axis
		                start_pos1 = cheval[x][joints.WRIST.ordinal()];
		                end_pos1 = cheval[x+1][joints.WRIST.ordinal()];
		                start_pos2 = cheval[x][joints.ELBOW.ordinal()];
		                end_pos2 = cheval[x+1][joints.ELBOW.ordinal()];
		                start_pos3 = cheval[x][joints.LEFT.ordinal()];
		                end_pos3 = cheval[x+1][joints.LEFT.ordinal()];
		                start_pos4 = cheval[x][joints.RIGHT.ordinal()];
		                end_pos4 = cheval[x+1][joints.RIGHT.ordinal()];
		                found = true;
		                }
		            else if (x >= arysize - 2)
		                done = true; // We have reached the end of the dataset.  Motion is done.
		            break;
		        case 2: // sally
		            start_time = time_accumulator;
		            end_time = time_accumulator + sally[x][joints.TIME.ordinal()];
		            if ((find_time >= start_time)&&(find_time < end_time))
		                { // If we are in the right time space, set up data for each axis
		                start_pos1 = sally[x][joints.WRIST.ordinal()];
		                end_pos1 = sally[x+1][joints.WRIST.ordinal()];
		                start_pos2 = sally[x][joints.ELBOW.ordinal()];
		                end_pos2 = sally[x+1][joints.ELBOW.ordinal()];
		                start_pos3 = sally[x][joints.LEFT.ordinal()];
		                end_pos3 = sally[x+1][joints.LEFT.ordinal()];
		                start_pos4 = sally[x][joints.RIGHT.ordinal()];
		                end_pos4 = sally[x+1][joints.RIGHT.ordinal()];
		                found = true;
		                }
		            else if (x >= arysize - 2)
		                done = true; // We have reached the end of the dataset.  Motion is done.
		            break;
		        case 3: // drawbridge
		            start_time = time_accumulator;
		            end_time = time_accumulator + drawbridge[x][joints.TIME.ordinal()];
		            if ((find_time >= start_time)&&(find_time < end_time))
		                { // If we are in the right time space, set up data for each axis
		                start_pos1 = drawbridge[x][joints.WRIST.ordinal()];
		                end_pos1 = drawbridge[x+1][joints.WRIST.ordinal()];
		                start_pos2 = drawbridge[x][joints.ELBOW.ordinal()];
		                end_pos2 = drawbridge[x+1][joints.ELBOW.ordinal()];
		                start_pos3 = drawbridge[x][joints.LEFT.ordinal()];
		                end_pos3 = drawbridge[x+1][joints.LEFT.ordinal()];
		                start_pos4 = drawbridge[x][joints.RIGHT.ordinal()];
		                end_pos4 = drawbridge[x+1][joints.RIGHT.ordinal()];
		                found = true;
		                }
		            else if (x >= arysize - 2)
		                done = true; // We have reached the end of the dataset.  Motion is done.
		            break;
		        }
		    if ((!found)&&(!done)) // keep looking till we're done or till we find what we're looking for.
		        {
		        x++;
		        time_accumulator = end_time;
		        }
		    }
		if ((found)&&(!done)) // If we are not done and we found a record, generate data.
		    { // We have valid data points.  Calculate target positions and speeds.
		    pos1 = interp(find_time, start_time, end_time, start_pos1, end_pos1); // interpolate for pos1
		    // Calculate speed assuming we will maintain the same average speed for this section
		    speed1 = (end_pos1 - start_pos1) / (end_time - start_time) * speed_multiplier;
		
		    pos2 = interp(find_time, start_time, end_time, start_pos2, end_pos2); // interpolate for pos2
		    speed2 = (end_pos2 - start_pos2) / (end_time - start_time) * speed_multiplier;
		
		    pos3 = interp(find_time, start_time, end_time, start_pos3, end_pos3); // interpolate for pos2
		    speed3 = (end_pos3 - start_pos3) / (end_time - start_time) * speed_multiplier;
		
		    pos4 = interp(find_time, start_time, end_time, start_pos4, end_pos4); // interpolate for pos2
		    speed4 = (end_pos4 - start_pos4) / (end_time - start_time) * speed_multiplier;
		
		    wristPos = pos1;
		    elbowPos = pos2;
		    leftPos = pos3;
		    rightPos = pos4;
		    wristSpeed = speed1;
		    elbowSpeed = speed2;
		    leftSpeed = speed3;
		    rightSpeed = speed4;
		    return true; // Data has been set.  Return True.
		    }
		else // if we can't find something or we are done, return false.  Motion is to stop now.
		    return false;
		}
	
	public void posPrint() {
		while (lookup(comboindex, time, speed_mult))
        {
        System.out.println("time = "+ (time)+", wrist pos = "+(wristPos)+" speed = "+(wristSpeed) +
                        ", elbow pos = "+(elbowPos)+" speed = "+(elbowSpeed) +
                        ", left pos = "+(leftPos)+" speed = "+(leftSpeed) +
                        ", right pos = "+(rightPos)+" speed = "+(rightSpeed));
        time = time +0.02;

        }
	}

}



