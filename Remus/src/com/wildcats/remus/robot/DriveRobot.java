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
public class DriveRobot {
    
    //Create things
    Inputs in = new Inputs();//create imputs
    Joysticks joy = new Joysticks();
    
    //Create Victors & Spikes
    Victor leftvic;
    Victor rightvic;
    
    
    
    double leftdriveinput;
    double rightdriveinput;
    double leftdriveoutput;
    double rightdriveoutput;
    
    
    public DriveRobot(){
        
        leftvic = new Victor(in.leftdrive);
        rightvic = new Victor(in.rightdrive);
        }

    


    
    public void drivetanksqrtcurve(){ 
            leftdriveinput = joy.driver.getY();
            if (leftdriveinput >= 0.0){
                leftdriveoutput = (leftdriveinput * Math.sqrt(leftdriveinput));
            }
            else
            {
                leftdriveoutput = (leftdriveinput * Math.sqrt(-leftdriveinput));
            }
            rightdriveinput = joy.driver2.getY();
            if (rightdriveinput >= 0.0)
            {
                rightdriveoutput = (rightdriveinput * Math.sqrt(rightdriveinput)); 
            }
            else
            {
                rightdriveoutput = (rightdriveinput * Math.sqrt(-rightdriveinput)); 
            }
            leftvic.set(-leftdriveoutput);
            rightvic.set(rightdriveoutput);
    }
    public void drivetanksquarecurve(){ 
            leftdriveinput = joy.driver.getY();
            if (leftdriveinput >= 0.0){
                leftdriveoutput = (leftdriveinput * leftdriveinput);
            }
            else
            {
                leftdriveoutput = -(leftdriveinput * leftdriveinput);
            }
            rightdriveinput = joy.driver2.getY();
            if (rightdriveinput >= 0.0)
            {
                rightdriveoutput = (rightdriveinput * rightdriveinput); 
            }
            else
            {
                rightdriveoutput = -(rightdriveinput * rightdriveinput); 
            }
            leftvic.set(-leftdriveoutput);
            rightvic.set(rightdriveoutput);
    }
     
     
    public void drivetank(){
        rightvic.set(joy.driver2.getRawAxis(2));
        leftvic.set(-(joy.driver.getRawAxis(2)));
    }

    public void spinleft() {
        while (joy.driver.getRawButton(in.spinleft)) {
            driverobot(-.75, .75);
        }
    }

    public void spinright() {
        while (joy.driver.getRawButton(in.spinright)) {
            driverobot(.75, -.75);
        }
    }

    public void driverobot(double leftval, double rightval) {
        rightvic.set(-rightval);
        leftvic.set(leftval);
    }


}
