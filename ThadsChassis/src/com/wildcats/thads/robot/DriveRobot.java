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
public class DriveRobot {
    
    //Create things
    Inputs in = new Inputs();//create imputs
    Joysticks joy = new Joysticks();
    
    //Create Victors & Spikes
    Victor leftfrontvic;
    Victor rightfrontvic;
    Victor rightrearvic;
    Victor leftrearvic;
    
    double leftdriveinput;
    double rightdriveinput;
    double leftdriveoutput;
    double rightdriveoutput;
    
    
    public DriveRobot(){
        
        leftfrontvic = new Victor(in.leftfrontdrive);
        rightfrontvic = new Victor(in.rightfrontdrive);
        leftrearvic = new Victor(in.leftreardrive);
        rightrearvic = new Victor(in.rightreardrive);
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
            driverobot(leftdriveoutput, rightdriveoutput);
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
            driverobot(leftdriveoutput, rightdriveoutput);
    }
     
     
    public void drivetank(){
        leftdriveoutput = joy.driver.getRawAxis(2);
        rightdriveoutput = joy.driver2.getRawAxis(2);
        driverobot(leftdriveoutput, rightdriveoutput);
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
        rightfrontvic.set(rightval);
        rightrearvic.set(rightval);
        leftfrontvic.set(-leftval);
        leftrearvic.set(-leftval);
    }


}
