package frc.robot.Subsystems.Intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Intake.IntakeIO.IntakeInputs;

public class Intake extends SubsystemBase{
    
    IntakeIO io;


    public Intake(IntakeIO io){
        this.io = io;
    }

    public Command setVolts(double volts){
        return run(
            () -> io.setVoltage(volts)
            
        ).alongWith( Commands.print("EL INTAKE ESTÁ A " + volts));
    }

    @Override
    public void periodic(){
        
        SmartDashboard.putNumber("Voltaje Intake", io.getInputs().intakeLeftVoltage);
    }
}
