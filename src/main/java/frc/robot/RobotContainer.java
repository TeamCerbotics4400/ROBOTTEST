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
  CommandXboxController controlDriver = new CommandXboxController(0);

  boolean isIntakeActive = false;
  public RobotContainer() {
    if(RobotBase.isReal()){
      intakeIO = new IntakeFisico();
      parrillaIO = new ParrilaFisico();
      ShooterIO = new ShooterFisico();

    }else{
    }


    parrillaroller = new Parrilla(parrillaIO);
    Shooter = new Shooter(ShooterIO);
    intake = new Intake(intakeIO);
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
