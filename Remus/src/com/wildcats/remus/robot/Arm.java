/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.remus.robot;

import com.wildcats.remus.classes.*;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class Arm {

    final int toppeg = 69;
    final int midpeg = 48;
    Inputs in;
    Joysticks joy;
    Victor armvic = new Victor(in.arm);
    Encoder armenc = new Encoder(in.armencA, in.armencB, in.revarm, Encoder.EncodingType.k4X);
    LimitSwitches limits = new LimitSwitches();
    int encoderset;
    int armcase = 1;
    boolean slam = false;
    boolean stopped = false;
    double armspeed = 0.0;
    static double armlength = 71;
    static double drivesprocket = 15;
    static double drivensprocket = 60;
    static double encodercpr = 360;
    double armheightpercount = (((armlength * Math.PI) / 360) * (drivensprocket / drivesprocket) / encodercpr);

    public Arm() {
        armenc.start();
        armenc.setDistancePerPulse(armheightpercount);
        limits.start();
    }

    public void RunArm(int dashstate) {
        if (joy.secondary.getRawButton(12) && joy.driver.getRawButton(12)) {
            limits.errorswitch();
        }
        if (joy.secondary.getRawButton(4)) {
            armcase = 2;
            encoderset = toppeg;
        } else if (joy.secondary.getRawButton(5)) {
            armcase = 2;
            encoderset = toppeg + 2;
        } else if (joy.secondary.getRawButton(8)) {
            armcase = 2;
            encoderset = midpeg;
        } else if (joy.secondary.getRawButton(9)) {
            armcase = 2;
            encoderset = midpeg + 2;
        } else if (dashstate == 10) {
            armcase = 2;
            encoderset = toppeg;
        } else {
            armcase = 1;
        }
        if (armcase == 1) {
            JoystickArm();
            SetDash(dashstate);
        } else if (armcase == 2) {
            AutoArm();
            if (dashstate > 9) {
            } else {
                SetDash(dashstate);
            }

        }

    }

    public void SetDash(int timestate) {
        if (timestate == 4) {
            SmartDashboard.log("Joystick Arm", "Arm State");
        } else if (armcase == 2) {
            if (timestate == 2) {
                SmartDashboard.log("Top Upper Peg", "Arm State");
            }
        } else {
            if (limits.limitstate == 0) {
                SmartDashboard.log("No limit switches hit", "Limits State");
            }
            else if (limits.limitstate == 1){
                SmartDashboard.log("Top limit switch hit", "Limits State");
            }
            else if (limits.limitstate == 2){
                SmartDashboard.log("slam limit switch hit", "Limits State");
            }
            else if (limits.limitstate == 3){
                SmartDashboard.log("limits error not override", "Limits State");
            }
            else if (limits.limitstate == 4){
                SmartDashboard.log("limits error overrided", "Limits State");

            }
            else if (limits.limitstate == 5){
                SmartDashboard.log("limits thread disabled", "Limits State");
            }
        }
    }

    public void AutoArm() {
        if (armenc.getDistance() > (encoderset + 1)) {
            setArm(0);
        } else if (armenc.getDistance() > (encoderset - 1)) {
            setArm(((0.3) * (armenc.getDistance() / encoderset)) + .3);
        } else if ((encoderset - 1) < armenc.getDistance() && ((encoderset + 1) > armenc.getDistance())) {
            setArm(.195);
        }
    }

    public void JoystickArm() {

        if (joy.secondary.getRawButton(in.stall)) {
            setArm(.195);
        } else {
            if (joy.secondary.getY() < -.3) {
                setArm(-.3);
            } else {
                setArm(joy.secondary.getY());

            }
        }


    }

    public void setArm(double speed) {
        if (slam) {
            armvic.set(-.4);
        } else if (stopped) {
            armvic.set(0.0);
        } else {

            armvic.set(speed);
        }

    }

    public void armStop(boolean stop) {
        stopped = stop;
    }

    public void slamArm(boolean slamit) {
        slam = slamit;
    }
}
