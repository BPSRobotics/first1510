/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.wildcats.raphael;

import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Raphael extends SimpleRobot {

    Inputs in = new Inputs();
    Victor leftfrontvic = new Victor(in.LEFT_FRONT_PWM);
    Victor rightfrontvic = new Victor(in.RIGHT_FRONT_PWM);
    Victor leftrearvic = new Victor(in.LEFT_REAR_PWM);
    Victor rightrearvic = new Victor(in.RIGHT_REAR_PWM);
    RobotDrive drive = new RobotDrive(leftfrontvic, leftrearvic, rightfrontvic, rightrearvic);
    Joysticks joy = new Joysticks();
    BallGrab ballgrab = new BallGrab();
    double magnitude;
    double rotation;
    double direction;

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {//Loop during teleop
            magnitude = (joy.driver.getMagnitude() + joy.driver2.getMagnitude()) / 2;
            direction = (joy.driver.getDirectionDegrees() + joy.driver2.getDirectionDegrees()) / 2;
            rotation = (joy.driver.getY() + joy.driver2.getY()) / 2;
            drive.mecanumDrive_Polar(magnitude, direction, rotation);
            ballgrab.GrabBall();
        }
    }
}
