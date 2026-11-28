package frc.robot.Subsystems.HOOD;

public interface HoodIO {

    public class HoodInputs {
        public double hoodLeftVoltage = 0;
        public double hoodRightVoltage = 0;
        public double hoodPosition = 0;
        public double hoodVelocity = 0;

        public double P = 0.6;
        public double I = 0;
        public double D = 0.01;
        public double kS = 0;
        public double kV = 0;
        public double kA = 0;


    }

    public default void setVoltage(double voltage) {}//NO SE USA PORQUE EL HOOPER NO SE MANEJA CON VOLTAJE, SE MANEJA CON POSICION Y VELOCIDAD

    public abstract void setPosition(double Rotaciones);
    
    public abstract void setVelocity (double RPMs);//NO SE USA PORQUE EL HOOPER NO SE MANEJA CON VOLTAJE, SE MANEJA CON POSICION Y VELOCIDAD

    public abstract void update();

    public abstract HoodInputs getInputs();

    public default double gradosaRotaciones(double angulo){
        return 0;
    }
} 