/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.wildcats.remus.robot;



import edu.wpi.first.wpilibj.*;
import com.wildcats.remus.classes.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Remus extends SimpleRobot {

    int dashstate = 0;//this is the counter for the number of cycles robot has had
    Inputs in = new Inputs(); //create and initalize inputs
    Joysticks joy = new Joysticks();//create and initalize joysticks
    DriveRobot drive = new DriveRobot();//create and initalize drive
    Pneumatics pneu = new Pneumatics();//create and initalize pneumatics system
    Arm arm = new Arm();//create and initalize arm.
    DriveEncoder encoders = new DriveEncoder();
    AnalogUltrasonic ultra = new AnalogUltrasonic(in.ultrasonic);
    Gyroscope gyro = new Gyroscope();

    public Remus() {
        SmartDashboard.init();//Initalize SmartDashboard
    }

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        pneu.closeclaw();//close the claw
        pneu.minibotin();//push minibot deployment in
        gyro.reset();//reset gyro
        encoders.reset();//reset encoders
        double speed;
        /*
        //move robot code with ultrasonic and gyro
        while (ultra.getDistance() > 35 && isEnabled() && isAutonomous()) {//while ultrasonic is greater then 35 inches out and is in auton mode.
            arm.RunArm(10);//move arm to top peg (10 means use auton mode for arm)
            if (encoders.getAverage() < 100) {//use different speeds depending on encoder counts.
                speed = .7;
            } else if (encoders.getAverage() < 150) {
                speed = .5;
            } else if (encoders.getAverage() < 200) {
                speed = .45;
            } else {
                speed = .35;
            }
            double angle = gyro.getGyro();//input gyro angle
            drive.drive(speed, -angle * in.Kp); //drive robot at 75% speed using gyro to correct angle
            Timer.delay(.004);//delay and correct every .004 seconds
            
        }
         * 
         */
        //move robot code with encoders and gyro
        while (encoders.getAverage() < 216 && isEnabled() && isAutonomous()) {//while encoders are less then 216 inches out and is in auton mode.
            arm.RunArm(10);//move arm to top peg (10 means use auton mode for arm)
            if (encoders.getAverage() < 100) {//use different speeds depending on encoder counts.
                speed = .7;
            } else if (encoders.getAverage() < 150) {
                speed = .5;
            } else if (encoders.getAverage() < 200) {
                speed = .45;
            } else {
                speed = .35;
            }
            double angle = gyro.getGyro();//input gyro angle
            drive.drive(speed, -angle * in.Kp); //drive robot at set speed using gyro to correct angle
            Timer.delay(.004);//delay and correct every .004 seconds
            
        }
        //once at 35 inches stop robot
        arm.setArm(0.0);//set arm to 0
        drive.driverobot(0, 0);//stop the robot
        Timer.delay(0.5);
        pneu.openclaw();//open claw
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {//Loop during teleop
            drive.drivetanksquarecurve();//Drive tank with Squared Curve
            pneu.minibot();//Run the minibot pneumatics function
            pneu.claw();//Run the claw pneumatics function
            dashstate++;//Increment cycle counter
            arm.RunArm(dashstate);//run the arm function (send the cycle counter)
            if (dashstate == 1) {//if on cycle 1
                SmartDashboard.log(drive.leftdriveoutput, "Left Drive Output");//output left drive outputs
                SmartDashboard.log(drive.rightdriveoutput, "Right Drive Output");//output right drive outputs.
            } else if (dashstate == 3) {//else if on cycle 3
                SmartDashboard.log(arm.armenc.getDistance(), "Arm Encoder");//output arm encoder value
            } else if (dashstate == 5) {//else if on cycle 5
                SmartDashboard.log("test sd", "Testing");//output another variable
            } else if (dashstate > 5) {//if greater then cycle 5
                dashstate = 0;//reset cycles
            }
            //the cycle counters are needed because we do not want to send all of this information every cycle.
            //the cycle information also gets sent to the arm in order to show the state of the buttons for heights
        }

    }
}
