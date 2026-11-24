package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import frc.robot.Subsystems.Intake.IntakeIO.IntakeInputs;

public class ShooterFisico implements ShooterIO {

    private TalonFX ShooterArribaDerecho = new TalonFX(32, "canivore");
    private TalonFX ShooterArribaIzquierdo = new TalonFX(30, "canivore");
    private TalonFX ShooterAbajoDerecho = new TalonFX(33, "canivore");
    private TalonFX ShooterAbajoIzquierdo = new TalonFX(31, "canivore");
    private final TalonFXConfiguration ShooterConfig = new TalonFXConfiguration();
    private IntakeInputs inputs = new IntakeInputs();
    public ShooterFisico(){
        
        ShooterAbajoIzquierdo.setControl(new Follower(ShooterArribaIzquierdo.getDeviceID(), MotorAlignmentValue.Aligned));
        ShooterAbajoDerecho.setControl(new Follower(ShooterArribaDerecho.getDeviceID(),MotorAlignmentValue.Aligned));

        
        

        ShooterConfig.Slot0.kP = inputs.P;
        ShooterConfig.Slot0.kI = inputs.I;
        ShooterConfig.Slot0.kD = inputs.D;
        ShooterConfig.Slot0.kS = inputs.kS;
        ShooterConfig.Slot0.kV = inputs.kV;
        ShooterConfig.Slot0.kA = inputs.kA;

       

        ShooterConfig.CurrentLimits.StatorCurrentLimit = 100;

        ShooterConfig.CurrentLimits.StatorCurrentLimit = 100;
        ShooterConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        
        ShooterArribaIzquierdo.getConfigurator().apply(ShooterConfig);

        ShooterAbajoDerecho.setControl(new Follower(ShooterArribaIzquierdo.getDeviceID(), MotorAlignmentValue.Opposed));
        ShooterArribaDerecho.setControl(new Follower(ShooterArribaIzquierdo.getDeviceID(), MotorAlignmentValue.Opposed));
        ShooterAbajoIzquierdo.setControl(new Follower(ShooterArribaIzquierdo.getDeviceID(), MotorAlignmentValue.Aligned));
    }
    

    @Override
    public void setVoltage(double voltage){
        
        ShooterArribaIzquierdo.setControl(new VoltageOut(voltage));
    }

    @Override
    public void setPosition(double Rotaciones) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
    }

    @Override
    public void setVelocity(double RPMs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVelocity'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }
    
}
