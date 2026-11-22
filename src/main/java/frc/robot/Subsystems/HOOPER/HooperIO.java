package frc.robot.Subsystems.HOOPER;

public interface HooperIO {

    public class HooperInputs {
        public double hooperLeftVoltage = 0;
        public double hooperRightVoltage = 0;
        public double hooperPosition = 0;
        public double hooperVelocity = 0;

        public double P = 0;
        public double I = 0;
        public double D = 0;
        public double kS = 0;
        public double kV = 0;
        public double kA = 0;


    }

    public default void setVoltage(double voltage) {}//NO SE USA PORQUE EL HOOPER NO SE MANEJA CON VOLTAJE, SE MANEJA CON POSICION Y VELOCIDAD

    public abstract void setPosition(double Rotaciones);
    
    public abstract void setVelocity (double RPMs);//NO SE USA PORQUE EL HOOPER NO SE MANEJA CON VOLTAJE, SE MANEJA CON POSICION Y VELOCIDAD

    public abstract void update();

    public default HooperInputs getInputs() {return new HooperInputs(); }
} 