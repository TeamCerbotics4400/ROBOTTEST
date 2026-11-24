package frc.robot.Subsystems.Shooter;

public interface ShooterIO {

    public class ShooterInputs {
        public double shooterLeftVoltage = 0;
        public double shooterRightVoltage = 0;
        public double shooterPosition = 0;
        public double shooterVelocity = 0;

        public double P = 2.8;
        public double I = 0;
        public double D = 0.2;
        public double kS = 0;
        public double kV = 0;
        public double kA = 0;


    }

    public default void setVoltage(double voltage) {}

    public abstract void setPosition(double Rotaciones);
    
    public abstract void setVelocity (double RPMs);

    public abstract void update();

    public default ShooterInputs getInputs() {return new ShooterInputs(); }
} 