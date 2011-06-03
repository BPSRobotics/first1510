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
public class Gyroscope {
    Inputs in;
    Gyro gyro = new Gyro(in.gyro);
    
    
    /**
     * Return the actual angle in degrees that the robot is currently facing.
     *
     * The angle is based on the current accumulator value corrected by the oversampling rate, the
     * gyro type and the A/D calibration values.
     * The angle is continuous, that is can go beyond 360 degrees. This make algorithms that wouldn't
     * want to see a discontinuity in the gyro output as it sweeps past 0 on the second time around.
     *
     * @return the current heading of the robot in degrees. This heading is based on integration
     * of the returned rate from the gyro.
     */
    public double getGyro(){
        return gyro.getAngle();
    }
    public void reset(){
        gyro.reset();
    }
}
