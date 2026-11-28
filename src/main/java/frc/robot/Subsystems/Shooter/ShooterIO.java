package frc.robot.Subsystems.Shooter;

public interface ShooterIO {

    public class ShooterInputs {
        public double shooterLeftVoltage = 0;
        public double shooterRightVoltage = 0;
        public double shooterPosition = 0;
        public double shooterVelocity = 0;
        public double shootercurrent = 0;

        public double P = 0.20295;
        public double I = 0;
        public double D = 0;
        public double kS = 0.047586;
        public double kV = 0.13459;
        public double kA = 0.020041;


    }

    public default void setVoltage(double voltage) {}

    public abstract void setPosition(double Rotaciones);
    
    public abstract void setVelocity (double RPMs);

    public abstract void update();

    public default ShooterInputs getInputs() {return new ShooterInputs(); }
} 