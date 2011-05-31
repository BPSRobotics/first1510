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

    int dashstate = 0;
    DriveRobot drive = new DriveRobot(false);
    Beltsys belts = new Beltsys();
    public spike(){
        SmartDashboard.init();
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
        drive.drivetanksquarecurve();
        belts.runballs();
        drive.spinleft();
        drive.spinright();
        dashstate++;
        if (dashstate == 1){
            SmartDashboard.log(drive.leftdriveoutput, "Left Drive Output");
            SmartDashboard.log(drive.rightdriveoutput, "Right Drive Output");
        }
        else if (dashstate == 3){
            SmartDashboard.log(belts.topspinner.get(), "Top Spinner Speed");
        }
        else if (dashstate > 5){
            dashstate = 0;
        }
    }
    }
}
