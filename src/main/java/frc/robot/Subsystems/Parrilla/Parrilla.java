package frc.robot.Subsystems.Parrilla;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Parrilla extends SubsystemBase {

    ParrillaIO io;

    public Parrilla(ParrillaIO io) {
        this.io = io;
    }
    public Command setVolts(double volts) {
        return run(
                () -> io.setVoltage(volts)
        ).alongWith(Commands.print("LA PARRILLA ESTÁ A " + volts));
    }
    @Override
    public void periodic() {

        SmartDashboard.putNumber("Voltaje Parrilla", io.getInputs().parrillaVoltage);
        io.update();
    }
}
