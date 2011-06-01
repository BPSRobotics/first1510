/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.thads.classes;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.*;
/**
 *
 * @author Thad
 */
public class DriveEncoder {
    Inputs in = new Inputs();
    Encoder leftenc = new Encoder(in.leftencA,in.leftencB,in.revleft,EncodingType.k4X);
    Encoder rightenc = new Encoder(in.rightencA,in.rightencB,in.revright,EncodingType.k4X);
    
    public DriveEncoder(){
        leftenc.setDistancePerPulse(in.DISTANCE_PER_COUNT);
        rightenc.setDistancePerPulse(in.DISTANCE_PER_COUNT);
    }
    
        /**
     * Get the distance of the left encoder.
     *
     * @return The distance driven by the left encoder.
     */
    public double getLeft(){
        return leftenc.getDistance();
    }
    
        /**
     * Get the distance driven by the right encoder.
     *
     * @return The distance driven by the right encoder.
     */
    public double getRight(){
        return rightenc.getDistance();
    }
    public void reset(){
        leftenc.reset();
        rightenc.reset();
    }
    
    public double getAverage(){
        return (leftenc.getDistance() + rightenc.getDistance())/2;
    }
}
