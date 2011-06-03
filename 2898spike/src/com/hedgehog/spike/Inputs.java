package com.hedgehog.spike;

/**
 *
 * @author Thad
 */
public class Inputs {

    static final boolean JAGUARDRIVE = false; //set if drive is Jaguars or Victors 
    static final boolean FOURMOTORDRIVE = false;//4 motor drive switch
    
    //motor inversions (correct settings for toughboxes/2 stage gearboxes. invert for CIMple's/1 stage
    final int REVERSELEFTDRIVE = 1;//set if left drive is inverted
    final int REVERSERIGHTDRIVE = -1;//set if right drive is inverted
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
    //wheel calculations
    public int WHEEL_SIZE = 6;
    public int DRIVE_SPROCKET = 16;
    public int DRIVEN_SPROCKET = 16;
    public int DriveCPR = 360;
    double DISTANCE_PER_COUNT = ((DRIVE_SPROCKET / DRIVEN_SPROCKET) * WHEEL_SIZE) / DriveCPR;
}
