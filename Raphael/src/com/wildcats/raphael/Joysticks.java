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
public class Joysticks {

    Inputs in = new Inputs();
    Joystick driver = new Joystick(in.LEFT_JOYSTICK);
    Joystick driver2 = new Joystick(in.RIGHT_JOYSTICK);
}
