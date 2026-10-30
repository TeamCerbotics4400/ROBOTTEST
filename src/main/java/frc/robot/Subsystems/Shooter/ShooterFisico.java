package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

public class ShooterFisico implements ShooterIO {

    private TalonFX ShooterArribaIzquierdo = new TalonFX(32);
    private TalonFX ShoooterArribaDerecho = new TalonFX(30);
    private TalonFX shooterAbajoIzquierdo = new TalonFX(33);
    private TalonFX shooterAbajoDerecho = new TalonFX(31);

    public ShooterFisico(){
        
        shooterAbajoDerecho.setControl(new Follower(ShoooterArribaDerecho.getDeviceID(), MotorAlignmentValue.Aligned));
        shooterAbajoIzquierdo.setControl(new Follower(ShooterArribaIzquierdo.getDeviceID(),MotorAlignmentValue.Aligned));
    }

    @Override
    public void setVoltage(double voltage){
        ShoooterArribaDerecho.setControl(new VoltageOut(-voltage));
        ShooterArribaIzquierdo
    }
    
}
