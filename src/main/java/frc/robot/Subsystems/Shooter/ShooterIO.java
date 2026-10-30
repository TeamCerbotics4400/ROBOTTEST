package frc.robot.Subsystems.Shooter;

public interface ShooterIO {

    public class ShooterInputs{
    public double shooterDWRGTH_VLTG = 0;
    public double shooterDWLFT_VLTG = 0;

    public double shooterUPRGTH_VLTG = 0;
    public double shooterUPLFT_VLTG = 0;
    }

    public default void setVoltage(double voltage){}

    public default ShooterInputs getInputs(){return new ShooterInputs();}
} 
