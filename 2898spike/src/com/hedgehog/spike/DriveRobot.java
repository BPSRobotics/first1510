/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hedgehog.spike;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class DriveRobot {

    //Create things
    Inputs in;//create imputs
    Joysticks joy;//this imports the joysticks
    //Create Victors & Spikes
    SpeedController leftfrontdrive;
    SpeedController rightfrontdrive;
    SpeedController leftreardrive;
    SpeedController rightreardrive;
    //create value systems
    public double leftdriveinput;
    public double rightdriveinput;
    public double leftdriveoutput;
    public double rightdriveoutput;
    //create constructer inputs
    boolean fourmotordrive = Inputs.FOURMOTORDRIVE;
    boolean jaguardrive = Inputs.JAGUARDRIVE;


    public DriveRobot() {
        if (fourmotordrive && (jaguardrive == false)) {//if 4 motor drive and victor drive
            leftfrontdrive = new Victor(in.leftfrontdrive);
            rightfrontdrive = new Victor(in.rightfrontdrive);
            leftreardrive = new Victor(in.leftreardrive);
            rightreardrive = new Victor(in.rightreardrive);

        } else if (fourmotordrive && jaguardrive) {//if 4 motor drive and jaguar drive
            leftfrontdrive = new Jaguar(in.leftfrontdrive);
            rightfrontdrive = new Jaguar(in.rightfrontdrive);
            leftreardrive = new Jaguar(in.leftreardrive);
            rightreardrive = new Jaguar(in.rightreardrive);
        } else if (!fourmotordrive && !jaguardrive) {//if 2 motor drive and victor drive
            leftfrontdrive = new Victor(in.leftfrontdrive);
            rightfrontdrive = new Victor(in.rightfrontdrive);
            leftreardrive = null;
            rightreardrive = null;
        } else {//if 2 motor drive and jaguar drive
            leftfrontdrive = new Jaguar(in.leftfrontdrive);
            rightfrontdrive = new Jaguar(in.rightfrontdrive);
            leftreardrive = null;
            rightreardrive = null;
        }

    }



    public void drivetanksqrtcurve() {//Tank drive with a square rooted curve
        leftdriveinput = -joy.driver.getY();
        if (leftdriveinput >= 0.0) {
            leftdriveoutput = (leftdriveinput * Math.sqrt(leftdriveinput));
        } else {
            leftdriveoutput = (leftdriveinput * Math.sqrt(-leftdriveinput));
        }
        rightdriveinput = -joy.driver2.getY();
        if (rightdriveinput >= 0.0) {
            rightdriveoutput = (rightdriveinput * Math.sqrt(rightdriveinput));
        } else {
            rightdriveoutput = (rightdriveinput * Math.sqrt(-rightdriveinput));
        }
        driverobot(leftdriveoutput, rightdriveoutput);
    }

    public void drivetanksquarecurve() {//tank drive with a squared curve
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

    public void drivetank() {//tank drive with no curve
        leftdriveoutput = -joy.driver.getRawAxis(2);
        rightdriveoutput = -joy.driver2.getRawAxis(2);
        driverobot(leftdriveoutput, rightdriveoutput);
    }

    public void spinleft() {//spin the robot left at 40%
        while (joy.driver.getRawButton(in.spinleft)) {
            driverobot(-.4, .4);
        }
    }

    public void spinright() {//spin the robot right at 40%
        while (joy.driver.getRawButton(in.spinright)) {
            driverobot(.4, -.4);
        }
    }

    public void drive(double outputMagnitude, double curve) {//imput speed and curve and set motors
        double leftOutput, rightOutput;

        if (curve < 0) {
            double value = MathUtils.log(-curve);
            double ratio = (value - .5) / (value + .5);
            if (ratio == 0) {
                ratio = .0000000001;
            }
            leftOutput = outputMagnitude / ratio;
            rightOutput = outputMagnitude;
        } else if (curve > 0) {
            double value = MathUtils.log(curve);
            double ratio = (value - .5) / (value + .5);
            if (ratio == 0) {
                ratio = .0000000001;
            }
            leftOutput = outputMagnitude;
            rightOutput = outputMagnitude / ratio;
        } else {
            leftOutput = outputMagnitude;
            rightOutput = outputMagnitude;
        }
        driverobot(leftOutput, rightOutput);
    }

    public void driverobot(double leftval, double rightval) {
        if (!fourmotordrive) {
            rightfrontdrive.set(rightval * in.REVERSERIGHTDRIVE);
            leftfrontdrive.set(leftval * in.REVERSELEFTDRIVE);
        } else {
            rightfrontdrive.set(rightval * in.REVERSERIGHTDRIVE);
            rightreardrive.set(rightval * in.REVERSERIGHTDRIVE);
            leftfrontdrive.set(leftval * in.REVERSELEFTDRIVE);
            leftreardrive.set(leftval * in.REVERSELEFTDRIVE);
        }
        spinleft();//Spin left if joy pressed
        spinright();//Spin right if joy pressed
    }
}
