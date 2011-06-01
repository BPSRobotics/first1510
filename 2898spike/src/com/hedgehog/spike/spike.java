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

    int dashstate = 0;//dashboard cycle state
    DriveRobot drive = new DriveRobot(false);//create and initalize drive(2 motor drive)
    Beltsys belts = new Beltsys();//create and initalize belt system

    public spike() {
        SmartDashboard.init();//initalize smart dashboard
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
            drive.drivetanksquarecurve();//drive with square curve
            belts.runballs();//run the ball system
            dashstate++;//advance dash state
            if (dashstate == 1) {//on dashstate 1
                //log drive values
                SmartDashboard.log(drive.leftdriveoutput, "Left Drive Output");
                SmartDashboard.log(drive.rightdriveoutput, "Right Drive Output");
            } else if (dashstate == 3) {//on dash state 3
                //log top spinner speed
                SmartDashboard.log(belts.topspinner.get(), "Top Spinner Speed");
            } else if (dashstate == 5) {//on dash state 5
                //log state of ball sensor
                SmartDashboard.log(belts.ballcheck.get(), "Ball checker status");
            } else if (dashstate > 6) {//greater then dash state 6
                dashstate = 0;//reset dash state to 0
            }
        }
    }
}