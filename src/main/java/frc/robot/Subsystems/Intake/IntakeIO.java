package frc.robot.Subsystems.Intake;

public interface IntakeIO {

    public class IntakeInputs {
        public double intakeLeftVoltage = 0;
        public double intakeRightVoltage = 0;
        public double intakePosition = 0;
        public double intakeVelocity = 0;

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

    public default IntakeInputs getInputs() {return new IntakeInputs(); }
} 