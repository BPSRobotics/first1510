/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.thads.robot;

import com.wildcats.thads.classes.Inputs;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class Pneumatics {
    Inputs in = new Inputs();
    Joysticks joy = new Joysticks();
    
    Compressor comp = new Compressor(in.pressure,in.compressor);
    
    public Pneumatics(){
        comp.start();
        }
   
    
}
