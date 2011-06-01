/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.thads.classes;

import edu.wpi.first.wpilibj.*;
/**
 *
 * @author Thad
 */
public class AnalogUltrasonic {
    AnalogChannel ultrasonic;
    public double voltsperinch = 0.009765625;
    
    public AnalogUltrasonic(int input){
        ultrasonic = new AnalogChannel(input);
    }
    public double getDistance(){
        return ultrasonic.getVoltage() / voltsperinch;
    }
    
}
