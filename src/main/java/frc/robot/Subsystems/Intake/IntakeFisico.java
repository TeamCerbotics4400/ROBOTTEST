package frc.robot.Subsystems.Intake;

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

public class IntakeFisico implements IntakeIO {
    private final TalonFX intakeIzquierdo = new TalonFX(21);
    private final TalonFXConfiguration intakeIzquierdConfig = new TalonFXConfiguration();
    private final TalonFX intakeDerecho = new TalonFX(20);
    private final TalonFXConfiguration intakeDereConfig = new TalonFXConfiguration();
    private IntakeInputs inputs = new IntakeInputs();


    public IntakeFisico() {
        // intake config

        intakeIzquierdo.setPosition(0);
        

        intakeIzquierdConfig.Slot0.kP = inputs.P;
        intakeIzquierdConfig.Slot0.kI = inputs.I;
        intakeIzquierdConfig.Slot0.kD = inputs.D;
        intakeIzquierdConfig.Slot0.kS = inputs.kS;
        intakeIzquierdConfig.Slot0.kV = inputs.kV;
        intakeIzquierdConfig.Slot0.kA = inputs.kA;

        intakeDereConfig.Slot0.kP = inputs.P;
        intakeDereConfig.Slot0.kI = inputs.I;
        intakeDereConfig.Slot0.kD = inputs.D;
        intakeDereConfig.Slot0.kS = inputs.kS;
        intakeDereConfig.Slot0.kV = inputs.kV;
        intakeDereConfig.Slot0.kA = inputs.kA;

        intakeDereConfig.CurrentLimits.StatorCurrentLimit = 100;

        intakeIzquierdConfig.CurrentLimits.StatorCurrentLimit = 100;
        intakeIzquierdConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        intakeIzquierdo.getConfigurator().apply(intakeDereConfig);

        intakeDerecho.setControl(new Follower(intakeIzquierdo.getDeviceID(), MotorAlignmentValue.Opposed));

    }

    @Override
    public void setVoltage(double voltage) {
        intakeIzquierdo.setControl(new VoltageOut(voltage));// Se le puede poner "-" para que vaya al revés
        // intakeDerecho.setControl(new VoltageOut(-voltage));
        inputs.intakeLeftVoltage = intakeIzquierdo.getMotorVoltage().getValueAsDouble();

    }

    @Override
    public IntakeInputs getInputs() {
        return inputs;
    }

    @Override
    public void setPosition(double Rotaciones) {
        intakeIzquierdo.setControl(new PositionVoltage(Rotaciones).withSlot(0));
        // intakeDerecho.setControl(new PositionVoltage(Rotaciones).withSlot(0));
    }

    @Override
    public void setVelocity(double RPMs) {
        intakeIzquierdo.setControl(new VelocityVoltage(RPMs).withSlot(0));
        // intakeDerecho.setControl(new VelocityVoltage(RPMs).withSlot(0));
    }

    @Override
    public void update() {
        inputs.intakePosition = intakeIzquierdo.getPosition().getValueAsDouble();
        inputs.intakeVelocity = intakeIzquierdo.getVelocity().getValueAsDouble();
    }
}
