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
public class Beltsys {

    Inputs in = new Inputs();//create and initalize inputs
    Joysticks joy = new Joysticks();//create and initalize joysticks
    double DEFAULTBELTSPEED = -1.0;//set the default conveyer belt speed
    Victor conveyerbelt;//create conveyer belt victor
    Victor topspinner;//create spinner victor
    Relay bottomspinner;//create spinner relay
    DigitalInput ballcheck;//create digital inputs 
    int beltstatus = 0;//set the belt state

    public Beltsys() {
        //initalize victors and relays and digital inputs
        conveyerbelt = new Victor(in.conveyer);
        topspinner = new Victor(in.topspinner);
        bottomspinner = new Relay(in.bottomspinner);
        ballcheck = new DigitalInput(in.ballcheck);
    }

    public void runballs() {//run the ball system
        bottomspinner.set(Relay.Value.kReverse);//set the button spinner to reverse
        //if trigger presses move belt up
        if (joy.driver2.getRawButton(in.conveyerup)) {
            conveyerbelt.set(DEFAULTBELTSPEED);
            beltstatus = 1;
        } // else if button 2 pressed move belt down?
        else if (joy.driver2.getRawButton(in.conveyerdown)) {
            conveyerbelt.set(-1 * DEFAULTBELTSPEED);
            beltstatus = -1;
        }
        //if trigger pushed move belt up and start top spinner
        if (joy.driver.getRawButton(1)) {
            topspinner.set(-1.0);
            conveyerbelt.set(DEFAULTBELTSPEED);
            beltstatus = 1;
        } // if trigger false, stop top spinner
        else if (joy.driver.getRawButton(1) == false) {
            topspinner.set(0.0);
        }
        pickupball();//run ball pickup
    }
//testing
    public void pickupball() {
        if (!ballcheck.get()) //if ball input is triggered
        {
            conveyerbelt.set(DEFAULTBELTSPEED);     //lift ball up one slot

        } else {//else set conveyer to button status.
            if (joy.driver2.getRawButton(1) == false && joy.driver.getRawButton(1) == false && joy.driver2.getRawButton(2) == false) {
                conveyerbelt.set(0.0);

            }
        }
    }
}
