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
    Inputs in = new Inputs();
    Joysticks joy = new Joysticks();
    double DEFAULTBELTSPEED = -1.0;
    Victor conveyerbelt;
    Victor topspinner;
    Relay bottomspinner;
    DigitalInput ballcheck;
    int beltstatus = 0;
    
    public Beltsys(){
        conveyerbelt = new Victor(in.conveyer);
        topspinner = new Victor(in.topspinner);
        bottomspinner = new Relay(in.bottomspinner);
        ballcheck = new DigitalInput(in.ballcheck);
    }
    public void runballs(){
        bottomspinner.set(Relay.Value.kReverse);
            if (joy.driver2.getRawButton(1)) {
                conveyerbelt.set(DEFAULTBELTSPEED);
                beltstatus = 1;
            } // else if button 2 pressed move belt up?
            else if (joy.driver2.getRawButton(2)) {
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
            pickupball();
    }
                
public void pickupball() {
        if (ballcheck.get() == false) //unsure if "get" works for digital sensor but docs are vague
        {
            conveyerbelt.set(DEFAULTBELTSPEED);     //life ball up one slot
            
        } 
        else 
        {
            if (joy.driver2.getRawButton(1) == false && joy.driver.getRawButton(1) == false && joy.driver2.getRawButton(2) == false) {
                conveyerbelt.set(0.0);
            
        }
    }
    }
    
}
