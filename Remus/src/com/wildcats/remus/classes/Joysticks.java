/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.remus.classes;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class Joysticks {

    Inputs in = new Inputs();
    public Joystick driver = new Joystick(in.leftjoy);
    public Joystick driver2 = new Joystick(in.rightjoy);
    public Joystick secondary = new Joystick(in.operaterjoy);
}
