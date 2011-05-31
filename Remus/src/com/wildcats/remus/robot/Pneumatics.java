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
public class Pneumatics {
    Inputs in = new Inputs();
    Joysticks joy = new Joysticks();
    
    Compressor comp = new Compressor(in.pressure,in.compressor);
    
    //Create Solenoid
    DoubleSolenoid minibot = new DoubleSolenoid(in.minibots1, in.minibots2);
    DoubleSolenoid claw = new DoubleSolenoid(in.claws1,in.claws2);
    
    public Pneumatics(){
        comp.start();
        minibot.set(DoubleSolenoid.Value.kForward);
        claw.set(DoubleSolenoid.Value.kReverse);
        }
    public void minibot(){
        if (joy.secondary.getRawButton(in.minibotjoy1) && joy.secondary.getRawButton(in.minibotjoy2)){
            minibot.set(DoubleSolenoid.Value.kReverse);
        }
        else
        {
            minibot.set(DoubleSolenoid.Value.kForward);
        }
    }
    public void claw(){
        if (joy.secondary.getRawButton(in.clawtrigger1)){
            claw.set(DoubleSolenoid.Value.kForward);
        }
        if (joy.secondary.getRawButton(in.clawtrigger2)){
            claw.set(DoubleSolenoid.Value.kReverse);
        }
        
    }
    
}
