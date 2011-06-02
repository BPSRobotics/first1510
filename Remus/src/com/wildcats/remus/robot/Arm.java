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

    Inputs in = new Inputs();
    Joysticks joy = new Joysticks();
    Victor armvic = new Victor(in.arm);
    Encoder armenc = new Encoder(in.armencA, in.armencB, in.revarm, Encoder.EncodingType.k4X);
    LimitSwitches limits = new LimitSwitches();
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
        if (joy.secondary.getRawButton(4)) {
            armcase = 2;
        } else if (joy.secondary.getRawButton(5)) {
            armcase = 3;
        } else {
            armcase = 1;
        }
        JoystickArm(dashstate);

    }

    public void JoystickArm(int timestate) {
        if (armcase == 1) {
            if (joy.secondary.getRawButton(in.stall)) {
                setArm(.195);
            } else {
                if (joy.secondary.getY() < -.3) {
                    setArm(-.3);
                } else if (armenc.get() > 60) {
                    setArm((joy.secondary.getY()) * (joy.secondary.getY()));
                } else {
                    setArm(joy.secondary.getY());

                }
            }
        }



        if (timestate == 4) {
            SmartDashboard.log("Joystick Arm", "Arm State");
        } else if (armcase == 2) {
            if (timestate == 2) {
                SmartDashboard.log("Top Upper Peg", "Arm State");
            }
        } else if (armcase == 3) {
            if (timestate == 2) {
                SmartDashboard.log("Top Lower Peg", "Arm State");
            }
        } else {
            if (timestate == 4) {
                SmartDashboard.log("Arm Error", "Arm State");
            }
        }
    }

    public void setArm(double speed) {
        if (slam){
            armvic.set(-.3);
        if (stopped) {
            armvic.set(0.0);
        } else {

            armvic.set(speed);
        }
        }
    }

    public void armStop(boolean stop) {
        stopped = stop;
    }
    public void slamArm(boolean slamit){
        slam = slamit;
    }
}
