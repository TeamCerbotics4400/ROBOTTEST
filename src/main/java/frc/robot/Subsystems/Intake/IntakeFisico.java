package frc.robot.Subsystems.Intake;

import java.io.Serial;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

public class IntakeFisico implements IntakeIO{
    private TalonFX intakeIzquierdo = new TalonFX(21);
    private IntakeInputs inputs = new IntakeInputs();

    public IntakeFisico (){

    }

    @Override 
    public void setVoltage(double voltage){
        intakeIzquierdo.setControl(new VoltageOut(-voltage));//Se le puede poner "-" para que vaya al revés
        inputs.intakeLeftVoltage = intakeIzquierdo.getMotorVoltage().getValueAsDouble();
    }

    @Override
    public IntakeInputs getInputs(){
        return inputs;
    }
}
