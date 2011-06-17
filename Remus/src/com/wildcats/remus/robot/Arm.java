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

    final int toppeg = 69;//encoder value for toppeg
    final int midpeg = 48;//encoder value for middle peg
    Inputs in;
    Joysticks joy;
    Victor armvic = new Victor(in.arm);//create arm victor
    Encoder armenc = new Encoder(in.armencA, in.armencB, in.revarm, Encoder.EncodingType.k4X);//create arm encoder
    LimitSwitches limits = new LimitSwitches();//initalize limit switches
    int encoderset;//what we want encoder to go to
    int armcase = 1;//what case the arm is in
    boolean slam = false;//if arm is in slam mode
    boolean stopped = false;//if arm is in stopped mode
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
        if (joy.secondary.getRawButton(12) && joy.driver.getRawButton(12)) {//if button 12 on both driver and secondary joysticks pushed
            limits.errorswitch();//switch limit switch error state
        }
        if (joy.secondary.getRawButton(4)) {//if button 4
            armcase = 2;//set arm to auto mode
            encoderset = toppeg;//say we want top peg
        } else if (joy.secondary.getRawButton(5)) {//if button 5
            armcase = 2;//set arm to auto mode
            encoderset = toppeg + 2;//say we want top center peg
        } else if (joy.secondary.getRawButton(8)) {//if button 8
            armcase = 2;//set arm to auto mode
            encoderset = midpeg;//say we want middle peg
        } else if (joy.secondary.getRawButton(9)) {//if button 9
            armcase = 2;//set arm to auto mode
            encoderset = midpeg + 2;//say we want middle center peg
        } else if (dashstate == 10) {//if autonomous mode
            armcase = 2;//set arm to auto mode
            encoderset = toppeg + 2;//say we want top center peg
        } else {//if no autosettings
            armcase = 1;//set arm to joystick mode
        }
        if (armcase == 1) {//if set to joystick mode
            JoystickArm();//run arm with joystick
            SetDash(dashstate);//run the dashboard updates
        } else if (armcase == 2) {//if set to automode
            AutoArm();//run arm with auto mode
            if (dashstate > 9) {//if in autonomous dont update dashboard
            } else {
                SetDash(dashstate);//update dashboard
            }

        }

    }

    public void SetDash(int timestate) {//setting the dashboard states.
        if (timestate == 4) {//if on cycle 4
            if (armcase == 1) {//if joystick arm
                SmartDashboard.log("Joystick Arm", "Arm State");
            } else if (armcase == 2) {//if auto arm
                SmartDashboard.log("AutoArm", "Arm State");
            }
        }
        if (timestate == 2) {//limit switch dashboard states
            if (limits.limitstate == 0) {
                SmartDashboard.log("No limit switches hit", "Limits State");
            } else if (limits.limitstate == 1) {
                SmartDashboard.log("Top limit switch hit", "Limits State");
            } else if (limits.limitstate == 2) {
                SmartDashboard.log("slam limit switch hit", "Limits State");
            } else if (limits.limitstate == 3) {
                SmartDashboard.log("limits error not override", "Limits State");
            } else if (limits.limitstate == 4) {
                SmartDashboard.log("limits error overrided", "Limits State");

            } else if (limits.limitstate == 5) {
                SmartDashboard.log("limits thread disabled", "Limits State");
            }
        }
    }

    public void AutoArm() {//auto arm setting
        if (armenc.getDistance() > (encoderset + 1)) {//if above height
            setArm(0);//drop arm
        } else if (armenc.getDistance() > (encoderset - 1)) {//if below height
            setArm(((0.3) * (armenc.getDistance() / encoderset)) + .3);//move arm up using curve
        } else if ((encoderset - 1) < armenc.getDistance() && ((encoderset + 1) > armenc.getDistance())) {//if arm at right height
            setArm(.195);//stall the arm
        }
    }

    public void JoystickArm() {//run arm with joystick

        if (joy.secondary.getRawButton(in.stall)) {//if stall button pushed set arm to stall
            setArm(.195);
        } else {
            if (joy.secondary.getY() < -.3) {//if joystick pushed up dont let power go above .3
                setArm(-.3);
            } else {
                setArm(joy.secondary.getY());//if joystick pushed down move arm.

            }
        }


    }

    public void setArm(double speed) {//set the arm speed.
        if (slam) {//if limit switches have told arm to slam
            armvic.set(-.4);//set arm to -.4 to slam it down.
        } else if (stopped) {//if limit switches have told arm to stop
            armvic.set(0.0);//set arm to 0 speed to stop it from moving.
        } else {//if not slam or stopped
            armvic.set(speed);//set arm to inputed speed
        }

    }

    public void armStop(boolean stop) {
        stopped = stop;
    }

    public void slamArm(boolean slamit) {
        slam = slamit;
    }
}
