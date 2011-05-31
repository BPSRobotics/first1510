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
    
    
    DriverStation ds = DriverStation.getInstance();
    Inputs in = new Inputs();
    Joysticks joy = new Joysticks();
    DriveRobot drive = new DriveRobot();
    Pneumatics pneu = new Pneumatics();
    Arm arm = new Arm();
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isOperatorControl() && isEnabled()){
            drive.drivetanksquarecurve();
            drive.spinleft();
            drive.spinright();
            pneu.minibot();
            pneu.claw();
            arm.JoystickArm();
        }

    }
}
