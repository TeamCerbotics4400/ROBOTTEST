// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.SignalLogger;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.Subsystems.HOOD.Hood;
import frc.robot.Subsystems.HOOD.HoodFisico;
import frc.robot.Subsystems.HOOD.HoodIO;
import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Intake.IntakeFisico;
import frc.robot.Subsystems.Intake.IntakeIO;
import frc.robot.Subsystems.Intake.IntakeSimulado;
import frc.robot.Subsystems.Parrilla.ParrilaFisico;
import frc.robot.Subsystems.Parrilla.Parrilla;
import frc.robot.Subsystems.Parrilla.ParrillaIO;
import frc.robot.Subsystems.Shooter.Shooter;
import frc.robot.Subsystems.Shooter.ShooterFisico;
import frc.robot.Subsystems.Shooter.ShooterIO;
import frc.robot.Subsystems.Intake.IntakeIO.IntakeInputs;

public class RobotContainer {

  Intake intake;
  IntakeIO intakeIO;
  
  Parrilla parrillaroller;
  ParrillaIO parrillaIO;


  Shooter Shooter;
  ShooterIO ShooterIO;

  Hood hood;
  HoodIO hoodIO;

  CommandXboxController controlDriver = new CommandXboxController(0);

  boolean isIntakeActive = false;
  public RobotContainer() {
      intakeIO = new IntakeFisico();
      parrillaIO = new ParrilaFisico();
      ShooterIO = new ShooterFisico();
      hoodIO = new HoodFisico();



    parrillaroller = new Parrilla(parrillaIO);
    Shooter = new Shooter(ShooterIO);
    intake = new Intake(intakeIO);
    hood = new Hood(hoodIO);
    configureBindings();
  }

  private void configureBindings() {
    controlDriver.a()
    .whileTrue(intake.setVolts(10))
    .whileFalse(intake.setVolts(0));


    controlDriver.y()
    .whileTrue(parrillaroller.setVolts(10))
    .whileFalse(parrillaroller.setVolts(0));

    controlDriver.x()
    .whileTrue(Shooter.setVolts(3))
    .whileFalse(Shooter.setVolts(0));

     controlDriver.leftBumper().onTrue(Shooter.setVelocity(3000)).onFalse(Shooter.setVelocity(0)) ;

     controlDriver.rightBumper().onTrue(hood.setPosition(3)).onFalse(hood.setPosition(0));
  

/* 
    controlDriver.leftBumper().onTrue(Commands.runOnce(SignalLogger::start).andThen(Commands.print("Graba datos de prueba")));//Iniciar Record
    
    controlDriver.rightBumper().onTrue(Commands.runOnce(SignalLogger::stop).andThen(Commands.print("Termina de grabar datos")) );//Terminar Record

    controlDriver.povLeft().whileTrue(Shooter.sysIdDynamic(Direction.kForward));
    controlDriver.povUp().whileTrue(Shooter.sysIdDynamic(Direction.kReverse));

    controlDriver.povRight().whileTrue(Shooter.sysIdQuasistatic(Direction.kForward));
    controlDriver.povDown().whileTrue(Shooter.sysIdQuasistatic(Direction.kReverse));

    */
    }
    

  public Command intakeActive(double volts){
    if(volts == 0){
      isIntakeActive = false;
    }else{
      isIntakeActive = true;
    }
    return intake.setVolts(volts);
  }

  

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
