package frc.robot.Subsystems.Intake;

import java.io.Serial;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj.motorcontrol.Talon;

public class IntakeFisico implements IntakeIO{
    private final TalonFX intakeIzquierdo = new TalonFX(21);
    private final TalonFXConfiguration intakeIzquierdConfig = new TalonFXConfiguration();
    private final TalonFX intakeDerecho = new TalonFX(20);
    private final TalonFXConfiguration intakeDereConfig = new TalonFXConfiguration();
    private IntakeInputs inputs = new IntakeInputs();




    public IntakeFisico (){
        //intake config

        intakeDereConfig.CurrentLimits.StatorCurrentLimit = 100;
        intakeDereConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        intakeDerecho.getConfigurator().apply(intakeDereConfig);
        
        intakeIzquierdConfig.CurrentLimits.StatorCurrentLimit = 100;
        intakeIzquierdConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        intakeIzquierdo.getConfigurator().apply(intakeDereConfig);


    
    }

    @Override 
    public void setVoltage(double voltage){
        intakeIzquierdo.setControl(new VoltageOut(voltage));//Se le puede poner "-" para que vaya al revés
        intakeDerecho.setControl(new VoltageOut(-voltage));
        inputs.intakeLeftVoltage = intakeIzquierdo.getMotorVoltage().getValueAsDouble();
        
    
    }

    @Override
    public IntakeInputs getInputs(){
        return inputs;
    }
}
