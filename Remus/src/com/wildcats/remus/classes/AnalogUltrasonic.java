/*
 * This is a package for an Analog Ultrasonic sensor that 1510 uses
 */
package com.wildcats.remus.classes;

import edu.wpi.first.wpilibj.*;
/**
 *
 * @author Thad
 */
public class AnalogUltrasonic {
    AnalogChannel ultrasonic; 
    public double voltsperinch = 0.009765625; //set the volts outputed per inch traveled
    
    public AnalogUltrasonic(int input){
        ultrasonic = new AnalogChannel(input);
    }
    public double getDistance(){
        return ultrasonic.getVoltage() / voltsperinch;//use to get distance (in inches) that the ultrasonic has.
    }
    
}
