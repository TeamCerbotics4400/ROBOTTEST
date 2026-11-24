package frc.robot.Subsystems.Parrilla;


import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class ParrilaFisico implements ParrillaIO {
    private final TalonFX parrillaroller = new TalonFX(41, "canivore");
    private final TalonFXConfiguration parrillTalonFXConfiguration = new TalonFXConfiguration();
    private Parrillainputs inputs = new Parrillainputs();

    private final TalonFX rightfeeder = new TalonFX(40, "canivore");
    private final TalonFXConfiguration rightfeederTalonFXConfiguration = new TalonFXConfiguration();
    private Parrillainputs rightfeederInputs = new Parrillainputs();

    private final TalonFX leftfeeder = new TalonFX(42, "canivore");
    private final TalonFXConfiguration leftfeederTalonFXConfiguration = new TalonFXConfiguration();
    private Parrillainputs leftfeederInputs = new Parrillainputs();

    public ParrilaFisico() {

        //parrillTalonFXConfiguration.CurrentLimits.StatorCurrentLimit = 12;

        parrillTalonFXConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        parrillaroller.getConfigurator().apply(parrillTalonFXConfiguration);

        rightfeeder.setControl(new Follower(parrillaroller.getDeviceID(), MotorAlignmentValue.Opposed));

         leftfeeder.setControl(new Follower(parrillaroller.getDeviceID(), MotorAlignmentValue.Aligned));
        
    }

    @Override
    public void setVoltage(double voltage) {
        parrillaroller.setControl(new VoltageOut(voltage));// Se le puede poner "-" para que vaya al revés
     
        //inputs.parrillaVoltage = parrillaroller.getMotorVoltage().getValueAsDouble();

    }

    @Override
    public Parrillainputs getInputs() {
        return inputs;
    }

   

 

    @Override
    public void update() {

        inputs.parrillaVoltage = parrillaroller.getMotorVoltage().getValueAsDouble();
    }
}
