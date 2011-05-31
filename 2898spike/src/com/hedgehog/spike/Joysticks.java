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
public class Joysticks {
    Inputs in = new Inputs();
    Joystick driver;
    Joystick driver2;
    Joystick secondary;
    
    public Joysticks(){
        driver = new Joystick(in.leftjoy);
        driver2 = new Joystick (in.rightjoy);
        secondary = new Joystick(in.operaterjoy);
    }
    
}
