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
public class Kicker {

    Inputs in = new Inputs();
    Joysticks joy = new Joysticks();
    Victor kickvic = new Victor(in.KICKER_PWM);
    Relay clutch = new Relay(in.CLUTCH_RELAY);
    DigitalInput kicklimit = new DigitalInput(in.KICK_LIMIT);
    DigitalInput clutchoutlimit = new DigitalInput(in.CLUTCH_OUT_LIMIT);
    DigitalInput clutchinlimit = new DigitalInput(in.CLUTCH_IN_LIMIT);
    int kickercase = 0;

    public void kickball() {
    }
}
