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

    Inputs in = new Inputs();
    AnalogChannel limits = new AnalogChannel(in.limits);
    Arm arm = new Arm();
    static double topmax = 1.4;
    static double topmin = 1;
    static double slammax = 1.8;
    static double slammin = 1.45;
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
                    if (limits.getVoltage() > topmin && limits.getVoltage() < topmax) {
                        arm.armStop(true);
                        arm.setArm(0.0);
                    } else if (limits.getVoltage() > slammin && limits.getVoltage() < slammax) {
                        arm.slamArm(true);
                        Timer.delay(.4);
                        arm.slamArm(false);
                    } else {
                        arm.slamArm(false);
                        arm.armStop(false);
                    }
                } else {
                    arm.armStop(true);
                    arm.setArm(0.0);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public LimitSwitches() {
        startlimits();
    }

    private void startlimits() {
        m_task = new LimitsThread(this);
        m_task.start();
    }

    public void start() {
        m_enabled = true;
    }

    public void stop() {
        m_enabled = false;
    }

    public boolean enabled() {
        return m_enabled;
    }
}
