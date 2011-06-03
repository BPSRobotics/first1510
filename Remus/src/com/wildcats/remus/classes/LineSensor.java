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
public class LineSensor {

    Inputs in;
    DigitalInput leftsensor = new DigitalInput(in.leftline);
    DigitalInput midsensor = new DigitalInput(in.midline);
    DigitalInput rightsensor = new DigitalInput(in.rightline);

    public boolean getLeft() {
        return !leftsensor.get();
    }

    public boolean getMid() {
        return !midsensor.get();
    }

    public boolean getRight() {
        return !rightsensor.get();
    }
}
