package frc.robot.Subsystems.Intake;

public interface IntakeIO {

    public class IntakeInputs {
        public double intakeLeftVoltage = 0;
        public double intakeRightVoltage = 0;
    }

    public default void setVoltage(double voltage) {}

    public default IntakeInputs getInputs() {return new IntakeInputs(); }
} 