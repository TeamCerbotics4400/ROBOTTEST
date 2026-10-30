// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Intake.IntakeFisico;
import frc.robot.Subsystems.Intake.IntakeIO;
import frc.robot.Subsystems.Intake.IntakeSimulado;
import frc.robot.Subsystems.Intake.IntakeIO.IntakeInputs;

public class RobotContainer {

  Intake intake;
  IntakeIO intakeIO;

  CommandXboxController controlDriver = new CommandXboxController(0);

  boolean isIntakeActive = false;
  public RobotContainer() {
    if(RobotBase.isReal()){
      intakeIO = new IntakeFisico();
    }else{
      intakeIO = new IntakeSimulado();
    }

    intake = new Intake(intakeIO);
    configureBindings();
  }

  private void configureBindings() {
    controlDriver.a().whileTrue(intake.setVolts(10))
    .whileFalse(intake.setVolts(0));
    controlDriver.b().whileTrue(intake.setVolts(-10))
    .whileFalse(intake.setVolts(0));

    controlDriver.y().onTrue(
      isIntakeActive ? 
              intakeActive(0) :
        intakeActive(10)
      );
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
