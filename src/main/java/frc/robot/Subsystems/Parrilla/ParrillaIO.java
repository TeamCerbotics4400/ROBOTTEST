package frc.robot.Subsystems.Parrilla;

public interface ParrillaIO{

    public class Parrillainputs {
        public double parrillaVoltage = 0;

  
    }

    public default void setVoltage(double voltage) {} 

    public abstract void update();

    public default Parrillainputs getInputs() {return new Parrillainputs(); }
} 
