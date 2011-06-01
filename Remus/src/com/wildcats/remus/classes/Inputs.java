package com.wildcats.remus.classes;

/**
 *
 * @author Thad
 */
public class Inputs {

    static final boolean PRACTICE = false; //practice robot switch
    static final boolean JAGUARDRIVE = false; //set if drive is Jaguars or Victors 
    //PWM outputs
    public int rightfrontdrive = 4;
    public int leftfrontdrive = 3;
    public int rightreardrive = 7;
    public int leftreardrive = 8;
    public int arm = 8;
    //Joysticks
    public int leftjoy = 1;
    public int rightjoy = 2;
    public int operaterjoy = 3;
    //Joystick Buttons
    public int forwarddrive = 1;
    public int clawtrigger1 = 1;
    public int clawtrigger2 = 3;
    public int minibotjoy1 = 7;
    public int minibotjoy2 = 10;
    public int spinright = 5;
    public int spinleft = 4;
    public int stall = 2;
    //Digital Inputs
    public int leftencA;
    public int leftencB;
    public int rightencA;
    public int rightencB;
    public int armencA;
    public int armencB;
    public int pressure = 7;
    public int leftline = 12;
    public int midline = 13;
    public int rightline = 14;
    //Relay's
    public int compressor = 1;
    //Solenoids
    public int minibots1 = 5;
    public int minibots2 = 6;
    public int claws1 = 3;
    public int claws2 = 4;
    //Analog Inputs
    public int gyro = 1;
    public int limits = 2;
    public int ultrasonic = 3;
    //Encoder CPR's
    public int DriveCPR;
    //Programming Switches
    public boolean revleft;
    public boolean revright;
    public boolean revarm;
    //Robot Control Numbers
    public double Kp = .003;//Gyro Gains
    public int WHEEL_SIZE = 6;
    public int DRIVE_SPROCKET = 16;
    public int DRIVEN_SPROCKET = 16;
    public double Autonlength = 40;//set auton movement length (in inches)
    public double DISTANCE_PER_COUNT = ((DRIVE_SPROCKET / DRIVEN_SPROCKET) * WHEEL_SIZE) / DriveCPR;

    public Inputs() {

        //Switch between Competiton and Practice Robots
        if (!PRACTICE) {
            //competition
            leftencA = 1;
            leftencB = 2;

            rightencA = 3;
            rightencB = 4;

            armencA = 5;
            armencB = 6;

            DriveCPR = 250;

            revleft = false;
            revright = false;
            revarm = false;
        } else {
            //practice
            leftencA = 2;
            leftencB = 1;

            rightencA = 4;
            rightencB = 3;

            DriveCPR = 250;

            revleft = true;
            revright = true;
        }

    }
}
