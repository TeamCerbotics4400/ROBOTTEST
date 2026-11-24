package frc.robot.Subsystems.HOOD;

import static edu.wpi.first.units.Units.RPM;
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

public class HoodFisico implements HoodIO {
    private final TalonFX hooper = new TalonFX(34);//ID's provisonales
    private final TalonFXConfiguration hooperConfig = new TalonFXConfiguration();
    private HooperInputs inputs = new HooperInputs();


    public HoodFisico() {
        // hooper config

        hooper.setPosition(0);
        

        hooperConfig.Slot0.kP = inputs.P;
        hooperConfig.Slot0.kI = inputs.I;
        hooperConfig.Slot0.kD = inputs.D;
        
        hooperConfig.CurrentLimits.StatorCurrentLimit = 100;

        hooperConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        hooper.getConfigurator().apply(hooperConfig);

        

    }

    @Override
    public void setVoltage(double voltage) {
        hooper.setControl(new VoltageOut(voltage));// Se le puede poner "-" para que vaya al revés
        // hooperDerecho.setControl(new VoltageOut(-voltage));
        inputs.hooperLeftVoltage = hooper.getMotorVoltage().getValueAsDouble();

    }

    @Override
    public HooperInputs getInputs() {
        return inputs;
    }

    @Override
    public void setPosition(double Rotaciones) {
        hooper.setControl(new PositionVoltage(Rotaciones).withSlot(0));
        // hooperDerecho.setControl(new PositionVoltage(Rotaciones).withSlot(0));
    }

    @Override
    public void setVelocity(double RPMs) {
        hooper.setControl(new VelocityVoltage(RPMs/60).withSlot(0));
        // hooperDerecho.setControl(new VelocityVoltage(RPMs).withSlot(0));
    }

    @Override
    public void update() {
        inputs.hooperPosition = hooper.getPosition().getValueAsDouble();
        inputs.hooperVelocity = hooper.getVelocity().getValueAsDouble();
        inputs.hooperLeftVoltage = hooper.getMotorVoltage().getValueAsDouble();
        inputs.hooperRightVoltage = hooper.getMotorVoltage().getValueAsDouble();
    }
}
