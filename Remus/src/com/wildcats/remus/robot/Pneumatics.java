/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wildcats.remus.robot;

import com.wildcats.remus.classes.*;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class Pneumatics {

    Inputs in;
    Joysticks joy;
    Compressor comp = new Compressor(in.pressure, in.compressor);
    //Create Solenoid
    DoubleSolenoid minibot = new DoubleSolenoid(in.minibots1, in.minibots2);
    DoubleSolenoid claw = new DoubleSolenoid(in.claws1, in.claws2);

    public Pneumatics() {
        comp.start();
        minibot.set(DoubleSolenoid.Value.kForward);
        claw.set(DoubleSolenoid.Value.kReverse);
    }

    public void closeclaw(){
        claw.set(DoubleSolenoid.Value.kReverse);
    }
    public void openclaw(){
        claw.set(DoubleSolenoid.Value.kForward);
    }
    public void minibotout(){
        minibot.set(DoubleSolenoid.Value.kReverse);
    }
    public void minibotin(){
        minibot.set(DoubleSolenoid.Value.kForward);
    }
    public void minibot() {
        if (joy.secondary.getRawButton(in.minibotjoy1) && joy.secondary.getRawButton(in.minibotjoy2)) {
            minibotout();
        } else {
            minibotin();
        }
    }

    public void claw() {
        if (joy.secondary.getRawButton(in.clawtrigger1)) {
            closeclaw();
        }
        if (joy.secondary.getRawButton(in.clawtrigger2)) {
            openclaw();
        }

    }
}
