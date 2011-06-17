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
public class LimitSwitches {

    Inputs in;
    AnalogChannel limits = new AnalogChannel(in.limits);
    Arm arm;
    static double topmax = 1.5;//the maximum value for the upper limit switch
    static double topmin = 1.3;//the minimum value for the upper limit switch
    static double slammax = 1.1;//the maximum value for the lower(slam on romulus) limit switch
    static double slammin = .9;//the minimum value for the lower(slam on romulus) limit switch
    static double errormax = 2.1;//the maximum value for limit switch errors.
    static double errormin = .9;//the minimum value for limit switch errors.
    boolean errortoggle = false;//toggle if you want the drivers to bypass limit switch errors
    int limitstate = 0;//state of limit switches
    private Thread m_task;
    private boolean m_enabled = false;

    private class LimitsThread extends Thread {

        LimitSwitches m_limits;

        LimitsThread(LimitSwitches limit) {
            m_limits = limit;
        }

        public void run() {
            while (true) {
                if (m_limits.enabled()) {
                    if (limits.getVoltage() > topmin && limits.getVoltage() < topmax) {//if hitting top limit switch.
                        arm.armStop(true);//Tell program arm is stopped.
                        arm.setArm(0.0);//set arm to 0.
                        limitstate = 1;
                    } else if (limits.getVoltage() > slammin && limits.getVoltage() < slammax) {//if hittim bottom limit switch.
                        arm.slamArm(true);//tell program to slam arm
                        Timer.delay(.4);//wait .4 seconds to let arm move
                        arm.slamArm(false);//tell program to not slam arm.
                        limitstate = 2;
                    } else if (limits.getVoltage() > errormax && limits.getVoltage() < errormin) {//if limit switches are disconnected
                        if (!errortoggle) {//if drivers have not told program to ignore
                            arm.armStop(true);//stop the arm.
                            arm.setArm(0.0);//set arm to 0 speed
                            limitstate = 3;
                        } else {//if driver have told program to ignore limit switches
                            arm.slamArm(false);//set arm slam to false
                            arm.armStop(false);//set arm stop to false
                            limitstate = 4;
                        }
                    } else {//if no limit switches are pressed and no errors
                        arm.slamArm(false);//set arm slam to false.
                        arm.armStop(false);//set arm stop to false.
                        limitstate = 0;
                    }
                } else {//if limit thread disabled
                    arm.armStop(true);//stop the arm
                    arm.setArm(0.0);//set arm to 0 speed
                    limitstate = 5;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public LimitSwitches() {//when initalized
        startlimits();//initalize limit switch thread
    }

    private void startlimits() {
        m_task = new LimitsThread(this);
        m_task.start();
    }

    public void start() {//start limit switches
        m_enabled = true;
    }

    public void stop() {//stop limit switches
        m_enabled = false;
    }

    public boolean enabled() {//check if thread is enabled
        return m_enabled;
    }

    public void errorswitch() {//switch between limit switch errors.
        if (errortoggle) {//if toggle is on.
            errortoggle = false;//turn toggle off.
        } else {//if toggle is off.
            errortoggle = true;//turn toggle on.
        }
    }
}
