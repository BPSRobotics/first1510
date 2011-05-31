/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.wildcats.remus.robot;


import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Remus extends SimpleRobot {
    
    int dashstate = 0;//this is the counter for the number of cycles robot has had
    Inputs in = new Inputs(); //create and initalize inputs
    Joysticks joy = new Joysticks();//create and initalize joysticks
    DriveRobot drive = new DriveRobot(false);//create and initalize drive
    //conditions are false for 2 motor drive or true for 4 motor drive
    Pneumatics pneu = new Pneumatics();//create and initalize pneumatics system
    Arm arm = new Arm();//create and initalize arm.


    
    public Remus(){
        SmartDashboard.init();//Initalize SmartDashboard
    }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isOperatorControl() && isEnabled()){//Loop during teleop
            drive.drivetanksquarecurve();//Drive tank with Squared Curve
            pneu.minibot();//Run the minibot pneumatics function
            pneu.claw();//Run the claw pneumatics function
            arm.RunArm(dashstate);//run the arm function (send the cycle counter)
            dashstate++;//Increment cycle counter
        if (dashstate == 1){//if on cycle 1
            SmartDashboard.log(drive.leftdriveoutput, "Left Drive Output");//output left drive outputs
            SmartDashboard.log(drive.rightdriveoutput, "Right Drive Output");//output right drive outputs.
        }
        else if (dashstate == 3){//else if on cycle 3
            SmartDashboard.log(arm.armenc.get(), "Arm Encoder");//output arm encoder value
        }
        else if (dashstate == 5){//else if on cycle 5
            SmartDashboard.log("test sd", "Testing");//output another variable
        }
        else if (dashstate > 5){//if greater then cycle 5
            dashstate = 0;//reset cycles
        }
        //the cycle counters are needed because we do not want to send all of this information every cycle.
        //the cycle information also gets sent to the arm in order to show the state of the buttons for heights
        }

    }
}
