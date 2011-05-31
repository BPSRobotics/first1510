/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.remus.robot;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class Joysticks {
    Inputs in = new Inputs();
    Joystick driver = new Joystick(in.leftjoy);
    Joystick driver2 = new Joystick (in.rightjoy);
    Joystick secondary = new Joystick(in.operaterjoy);
    
}
