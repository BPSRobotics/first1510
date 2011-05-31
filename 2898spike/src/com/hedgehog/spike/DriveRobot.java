/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hedgehog.spike;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Thad
 */
public class DriveRobot {
    
    //Create things
    Inputs in = new Inputs();//create imputs
    Joysticks joy = new Joysticks();//this imports the joysticks
    
    //Create Victors & Spikes
    Victor leftfrontvic;
    Victor rightfrontvic;
    Victor leftrearvic;
    Victor rightrearvic;
    
    double leftdriveinput;
    double rightdriveinput;
    double leftdriveoutput;
    double rightdriveoutput;
    boolean motordriver4;
    
           /**
     *
     * @param moterdrive4 The number of motors on the drive system.
     */
    public DriveRobot(boolean motordrive4){
        motordriver4 = motordrive4;
        if (motordrive4){
        leftfrontvic = new Victor(in.leftfrontdrive);
        rightfrontvic = new Victor(in.rightfrontdrive);
        leftrearvic = new Victor(in.leftreardrive);
        rightrearvic = new Victor(in.rightreardrive);
        }
        else{
        leftfrontvic = new Victor(in.leftfrontdrive);
        rightfrontvic = new Victor(in.rightfrontdrive);
        leftrearvic = null;
        rightrearvic = null;
        }
            
        }

    


    
    public void drivetanksqrtcurve(){ 
            leftdriveinput = -joy.driver.getY();
            if (leftdriveinput >= 0.0){
                leftdriveoutput = (leftdriveinput * Math.sqrt(leftdriveinput));
            }
            else
            {
                leftdriveoutput = (leftdriveinput * Math.sqrt(-leftdriveinput));
            }
            rightdriveinput = -joy.driver2.getY();
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
            leftdriveinput = -(joy.driver.getY());
            if (leftdriveinput >= 0.0){
                leftdriveoutput = (leftdriveinput * leftdriveinput);
            }
            else
            {
                leftdriveoutput = -(leftdriveinput * leftdriveinput);
            }
            rightdriveinput = -(joy.driver2.getY());
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
        leftdriveoutput = -joy.driver.getRawAxis(2);
        rightdriveoutput = -joy.driver2.getRawAxis(2);
        driverobot(leftdriveoutput, rightdriveoutput);
    }

    public void spinleft() {
        while (joy.driver.getRawButton(in.spinleft)) {
            driverobot(-.4, .4);
        }
    }

    public void spinright() {
        while (joy.driver.getRawButton(in.spinright)) {
            driverobot(.4, -.4);
        }
    }

    public void driverobot(double leftval, double rightval) {
        if (!motordriver4){
        rightfrontvic.set(-rightval);
        leftfrontvic.set(leftval);
        }
        else{
                    rightfrontvic.set(-rightval);
                    rightrearvic.set(-rightval);
        leftfrontvic.set(leftval);
        leftrearvic.set(leftval);
        }
    }


}
