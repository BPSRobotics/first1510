/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hedgehog.spike;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class DriveRobot {

    //Create things
    Inputs in = new Inputs();//create imputs
    Joysticks joy = new Joysticks();//this imports the joysticks
    //Create Victors & Spikes
    Victor leftfrontvic;
    Victor rightfrontvic;
    Victor leftrearvic;
    Victor rightrearvic;
    //Joysticks inputs
    double leftdriveinput;
    double rightdriveinput;
    //Joystick Outputs
    double leftdriveoutput;
    double rightdriveoutput;
    //drive motor number switch
    boolean motordriver4;

    /**
     *
     * @param moterdrive4 The number of motors on the drive system.
     */
    public DriveRobot(boolean motordrive4) {
        motordriver4 = motordrive4;//set inputed boolean to class boolean
        if (motordrive4) {//if set to 4 motor drive
            leftfrontvic = new Victor(in.leftfrontdrive);
            rightfrontvic = new Victor(in.rightfrontdrive);
            leftrearvic = new Victor(in.leftreardrive);
            rightrearvic = new Victor(in.rightreardrive);
        } else {//else if not set to 4 motor drive
            leftfrontvic = new Victor(in.leftfrontdrive);
            rightfrontvic = new Victor(in.rightfrontdrive);
            //set secondary motors to null
            leftrearvic = null;
            rightrearvic = null;
        }

    }

    public void drivetanksqrtcurve() {//Tank drive with a square rooted curve
        leftdriveinput = -joy.driver.getY();//set left drive input to joystick 1 y axis

        //this system keeps square root values negative if needed
        if (leftdriveinput >= 0.0) {
            leftdriveoutput = (leftdriveinput * Math.sqrt(leftdriveinput));
        } else {
            leftdriveoutput = (leftdriveinput * Math.sqrt(-leftdriveinput));
        }
        rightdriveinput = -joy.driver2.getY();//set right drive input to joystick 2 y axis
        if (rightdriveinput >= 0.0) {
            rightdriveoutput = (rightdriveinput * Math.sqrt(rightdriveinput));
        } else {
            rightdriveoutput = (rightdriveinput * Math.sqrt(-rightdriveinput));
        }
        driverobot(leftdriveoutput, rightdriveoutput);//Set left and right outputs.
    }

    public void drivetanksquarecurve() {//Tank drive with squared values curve
        leftdriveinput = -(joy.driver.getY());
        if (leftdriveinput >= 0.0) {
            leftdriveoutput = (leftdriveinput * leftdriveinput);
        } else {
            leftdriveoutput = -(leftdriveinput * leftdriveinput);
        }
        rightdriveinput = -(joy.driver2.getY());
        if (rightdriveinput >= 0.0) {
            rightdriveoutput = (rightdriveinput * rightdriveinput);
        } else {
            rightdriveoutput = -(rightdriveinput * rightdriveinput);
        }
        driverobot(leftdriveoutput, rightdriveoutput);
    }

    public void drivetank() {//Tank drive with no curve
        leftdriveoutput = -joy.driver.getRawAxis(2);
        rightdriveoutput = -joy.driver2.getRawAxis(2);
        driverobot(leftdriveoutput, rightdriveoutput);
    }

    public void spinleft() {//spin the robot left while button held.
        while (joy.driver.getRawButton(in.spinleft)) {
            driverobot(-.4, .4);
        }
    }

    public void spinright() {//spin the robot right while button held.
        while (joy.driver.getRawButton(in.spinright)) {
            driverobot(.4, -.4);
        }
    }

    public void driverobot(double leftval, double rightval) {//take inputs and convert to motor values
        if (!motordriver4) {
            rightfrontvic.set(-rightval);
            leftfrontvic.set(leftval);
        } else {
            rightfrontvic.set(-rightval);
            rightrearvic.set(-rightval);
            leftfrontvic.set(leftval);
            leftrearvic.set(leftval);
        }
        spinleft();//Spin left if joy pressed
        spinright();//Spin right if joy pressed
    }
}
