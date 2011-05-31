/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.thads.robot;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class Pneumatics {
    Inputs in = new Inputs();
    DriveRobot drive = new DriveRobot();
    Joysticks joy = new Joysticks();
    
    Compressor comp = new Compressor(in.pressure,in.compressor);
    
    public Pneumatics(){
        comp.start();
        }
   
    
}
