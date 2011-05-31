package com.hedgehog.spike;



/**
 *
 * @author Thad
 */
public class Inputs {

    
    //PWM outputs
    public int rightfrontdrive = 6;
    public int leftfrontdrive = 5;
    public int rightreardrive = 7;
    public int leftreardrive = 8;
    public int topspinner = 1;
    public int conveyer = 2;
    
    //Joysticks
    public int leftjoy = 1;
    public int rightjoy = 2;
    public int operaterjoy = 3;
    
    //Joystick Buttons
    public int spitballs = 1;
    public int conveyerup = 1;
    public int conveyerdown = 2;
    public int spinleft = 4;
    public int spinright = 5;

    
    //Digital Inputs
    public int ballcheck = 1;
    //Relay's
    public int bottomspinner = 1;
    
    public int WHEEL_SIZE = 6;
    public int DRIVE_SPROCKET = 16;
    public int DRIVEN_SPROCKET = 16;
    public int DriveCPR = 360;
    
    double DISTANCE_PER_COUNT = ((DRIVE_SPROCKET / DRIVEN_SPROCKET) * WHEEL_SIZE) / DriveCPR;


    
    
    
}
