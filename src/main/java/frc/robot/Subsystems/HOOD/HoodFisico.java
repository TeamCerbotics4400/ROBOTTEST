package frc.robot.Subsystems.HOOD;

import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;


public class HoodFisico implements HoodIO {

    public double minangle = 27;
    public double maxangle = 54;

    private final TalonFX hood = new TalonFX(34, "canivore");//ID's provisonales
    private final TalonFXConfiguration hoodConfig = new TalonFXConfiguration();
    private HoodInputs inputs = new HoodInputs();

    TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(3, 3);
    ProfiledPIDController pid = new ProfiledPIDController(inputs.P,inputs.I, inputs.D , constraints);




    public HoodFisico() {
        // hooper config

        hood.setPosition(0);

        hoodConfig.SoftwareLimitSwitch.ForwardSoftLimitThreshold = 5;
        hoodConfig.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;

        hoodConfig.SoftwareLimitSwitch.ReverseSoftLimitThreshold = 0;
        hoodConfig.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
        

        hoodConfig.Slot0.kP = inputs.P;
        hoodConfig.Slot0.kI = inputs.I;
        hoodConfig.Slot0.kD = inputs.D;
        

        hoodConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        hood.getConfigurator().apply(hoodConfig);

        

    }

    

    @Override
    public void setVoltage(double voltage) {
        hood.setControl(new VoltageOut(voltage));// Se le puede poner "-" para que vaya al revés
        // hooperDerecho.setControl(new VoltageOut(-voltage));
        inputs.hoodLeftVoltage = hood.getMotorVoltage().getValueAsDouble();

    }

    @Override
    public HoodInputs getInputs() {
        return inputs;
    }

    public void setPositionDegrees(double angle){
        double rotaciones = gradosaRotaciones(angle);
        setPosition(rotaciones);
    }

    @Override
    public void setPosition(double Rotaciones) {
        pid.setGoal(Rotaciones);
        double salida = pid.calculate(hood.getPosition().getValueAsDouble());


        hood.setControl(new DutyCycleOut(salida));
        // hooperDerecho.setControl(new PositionVoltage(Rotaciones).withSlot(0));
    }

    @Override
    public void setVelocity(double RPMs) {
        hood.setControl(new VelocityVoltage(RPMs/60).withSlot(0));
        // hooperDerecho.setControl(new VelocityVoltage(RPMs).withSlot(0));
    }

    @Override
    public double gradosaRotaciones(double angulo){
             return (angulo - 27)*(5 - 0)/(54 - 27);

    }

    @Override
    public void update() {
        inputs.hoodPosition = hood.getPosition().getValueAsDouble();
        inputs.hoodVelocity = hood.getVelocity().getValueAsDouble();
        inputs.hoodLeftVoltage = hood.getMotorVoltage().getValueAsDouble();
        inputs.hoodRightVoltage = hood.getMotorVoltage().getValueAsDouble();
    }
}
