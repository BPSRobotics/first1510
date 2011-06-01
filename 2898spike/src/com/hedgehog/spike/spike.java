 /*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.hedgehog.spike;

import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class spike extends SimpleRobot {

    int dashstate = 0;//this is the counter for the number of cycles robot has had
    DriveRobot drive = new DriveRobot(false);//create and initalize drive
    //conditions are false for 2 motor drive or true for 4 motor drive
    Beltsys belts = new Beltsys();//Create and initalize belt system

    public spike() {
        SmartDashboard.init();//initalize SmartDashboard
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
        while (isOperatorControl() && isEnabled()) // loop during enabled teleop mode
        {
            drive.drivetanksquarecurve();//Drive with squared curve
            belts.runballs();//Run the ball system
            dashstate++;//Increment cycle counter
            if (dashstate == 1) {//if on cycle 1
                SmartDashboard.log(drive.leftdriveoutput, "Left Drive Output");//output left drive outputs
                SmartDashboard.log(drive.rightdriveoutput, "Right Drive Output");//output right drive outputs.
            } else if (dashstate == 3) {
                SmartDashboard.log(belts.topspinner.get(), "Top Spinner Speed");
            } else if (dashstate == 5) {//else if on cycle 5
                SmartDashboard.log("test sd", "Testing");//output another variable
            } else if (dashstate > 5) {//if greater then cycle 5
                dashstate = 0;//reset cycles
            }
            //the cycle counters are needed because we do not want to send all of this information every cycle.
            //the cycle information also gets sent to the arm in order to show the state of the buttons for heights

        }


    }
}
