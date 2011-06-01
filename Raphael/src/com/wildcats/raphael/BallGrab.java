/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.raphael;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class BallGrab {

    Inputs in = new Inputs();
    Joysticks joy = new Joysticks();
    Relay spinner = new Relay(in.ROLLER_RELAY);
    boolean spinnerstate = false;

    public void GrabBall() {
        if (joy.driver2.getRawButton(in.GRAB_BALL_BUTTON)) {
            if (!spinnerstate) {
                spinner.set(Relay.Value.kReverse);
                spinnerstate = true;
            } else {
                spinner.set(Relay.Value.kOff);
                spinnerstate = false;
            }
        }
    }
}
