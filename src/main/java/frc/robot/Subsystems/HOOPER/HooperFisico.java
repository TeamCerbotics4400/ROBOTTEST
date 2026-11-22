package frc.robot.Subsystems.HOOPER;

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

public class HooperFisico implements HooperIO {
    private final TalonFX hooperIzquierdo = new TalonFX(110);//ID's provisonales
    private final TalonFXConfiguration hooperIzquierdConfig = new TalonFXConfiguration();
    private final TalonFX hooperDerecho = new TalonFX(99);//ID's provisonales
    private final TalonFXConfiguration hooperDereConfig = new TalonFXConfiguration();
    private HooperInputs inputs = new HooperInputs();


    public HooperFisico() {
        // hooper config

        hooperIzquierdo.setPosition(0);
        

        hooperIzquierdConfig.Slot0.kP = inputs.P;
        hooperIzquierdConfig.Slot0.kI = inputs.I;
        hooperIzquierdConfig.Slot0.kD = inputs.D;
        hooperDereConfig.Slot0.kS = inputs.kS;
        hooperDereConfig.Slot0.kV = inputs.kV;
        hooperDereConfig.Slot0.kA = inputs.kA;

        hooperDereConfig.Slot0.kP = inputs.P;
        hooperDereConfig.Slot0.kI = inputs.I;
        hooperDereConfig.Slot0.kD = inputs.D;
        hooperDereConfig.Slot0.kS = inputs.kS;
        hooperDereConfig.Slot0.kV = inputs.kV;
        hooperDereConfig.Slot0.kA = inputs.kA;

        hooperDereConfig.CurrentLimits.StatorCurrentLimit = 100;
        hooperIzquierdConfig.CurrentLimits.StatorCurrentLimit = 100;

        hooperIzquierdConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        hooperIzquierdo.getConfigurator().apply(hooperDereConfig);

        hooperDerecho.setControl(new Follower(hooperIzquierdo.getDeviceID(), MotorAlignmentValue.Opposed));

    }

    @Override
    public void setVoltage(double voltage) {
        hooperIzquierdo.setControl(new VoltageOut(voltage));// Se le puede poner "-" para que vaya al revés
        // hooperDerecho.setControl(new VoltageOut(-voltage));
        inputs.hooperLeftVoltage = hooperIzquierdo.getMotorVoltage().getValueAsDouble();

    }

    @Override
    public HooperInputs getInputs() {
        return inputs;
    }

    @Override
    public void setPosition(double Rotaciones) {
        hooperIzquierdo.setControl(new PositionVoltage(Rotaciones).withSlot(0));
        // hooperDerecho.setControl(new PositionVoltage(Rotaciones).withSlot(0));
    }

    @Override
    public void setVelocity(double RPMs) {
        hooperIzquierdo.setControl(new VelocityVoltage(RPMs/60).withSlot(0));
        // hooperDerecho.setControl(new VelocityVoltage(RPMs).withSlot(0));
    }

    @Override
    public void update() {
        inputs.hooperPosition = hooperIzquierdo.getPosition().getValueAsDouble();
        inputs.hooperVelocity = hooperIzquierdo.getVelocity().getValueAsDouble();
        inputs.hooperLeftVoltage = hooperIzquierdo.getMotorVoltage().getValueAsDouble();
        inputs.hooperRightVoltage = hooperDerecho.getMotorVoltage().getValueAsDouble();
    }
}
