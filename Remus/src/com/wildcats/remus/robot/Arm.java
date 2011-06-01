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
    AnalogChannel limits = new AnalogChannel(in.limits);
    Timer time = new Timer();
    int armcase = 1;
    static double topmax = 1.4;
    static double topmin = 1;
    static double slammax = 1.8;
    static double slammin = 1.45;
    static double armlength = 71;
    static double drivesprocket = 15;
    static double drivensprocket = 60;
    static double encodercpr = 360;
    double armheightpercount = (((armlength * Math.PI) / 360) * (drivensprocket / drivesprocket) / encodercpr);

    public Arm() {
        armenc.start();
        armenc.setDistancePerPulse(armheightpercount);
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
                armvic.set(.195);
            } else {
                if (limits.getVoltage() > topmin && limits.getVoltage() < topmax) {
                    armvic.set(0);
                } else if (limits.getVoltage() > slammin && limits.getVoltage() < slammax) {
                    armvic.set(-.4);
                } else {
                    if (joy.secondary.getY() < -.3) {
                        armvic.set(-.3);
                    } else if (armenc.get() > 60) {
                        armvic.set((joy.secondary.getY()) * (joy.secondary.getY()));
                    } else {
                        armvic.set(joy.secondary.getY());
                    }
                }
            }
            if (timestate == 4) {
                SmartDashboard.log("Joystick Arm", "Arm State");
            }
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
}
