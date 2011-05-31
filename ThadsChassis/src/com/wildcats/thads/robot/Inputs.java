package com.wildcats.thads.robot;

/**
 *
 * @author Thad
 */
public class Inputs {

    static final boolean PRACTICE = false; //practice robot switch
    static final boolean JAGUARDRIVE = true; //set if drive is Jaguars or Victors 
    
    //PWM outputs
    public int rightfrontdrive = 3;
    public int rightreardrive = 5;
    public int leftfrontdrive = 4;
    public int leftreardrive = 6;
    
    //Joysticks
    public int leftjoy = 1;
    public int rightjoy = 2;
    public int operaterjoy = 3;
    
    //Joystick Buttons
    public int forwarddrive = 1;
    public int spinright = 5;
    public int spinleft = 4;

    
    //Digital Inputs
    public int leftencA;
    public int leftencB;
    public int rightencA;
    public int rightencB;
    public int pressure = 7;
    
    
    //Relay's
    public int compressor = 1;
    



    
    //Encoder CPR's
    public int DriveCPR;


    
    //Robot Control Numbers
    public double Kp = .003;//Gyro Gains
    
    public int WHEEL_SIZE = 6;
    public int DRIVE_SPROCKET = 13;
    public int DRIVEN_SPROCKET = 24;
    
    public double Autonlength = 40;//set auton movement length (in inches)
    

    
    
    public Inputs() {
        
        //Switch between Competiton and Practice Robots
        if(!PRACTICE){
            //competition
            leftencA = 1;
            leftencB = 2;
            
            rightencA = 3;
            rightencB = 4;

            
            DriveCPR = 360;

        }
        else{
            //practice
            leftencA = 2;
            leftencB = 1;
            
            rightencA = 4;
            rightencB = 3;
            
            DriveCPR = 360;

        }
    
    }
    
    
}
