package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.controls.VoltageOut;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.Subsystems.Intake.IntakeIO.IntakeInputs;

public class Intake extends SubsystemBase {

    IntakeIO io;

    private final SysIdRoutine m_sysIdRoutine;

    public Intake(IntakeIO io) {

        m_sysIdRoutine = new SysIdRoutine(
                new SysIdRoutine.Config(
                        null, // Use default ramp rate (1 V/s)
                        Volts.of(4), // Reduce dynamic step voltage to 4 to prevent brownout
                        null, // Use default timeout (10 s)
                              // Log state with Phoenix SignalLogger class
                        (state) -> SignalLogger.writeString("state", state.toString())),
                new SysIdRoutine.Mechanism(
                        (volts) -> io.setVoltage(volts.in(Volts)),
                        null,
                        this));

        this.io = io;
    }

    public Command setVolts(double volts) {
        return run(
                () -> io.setVoltage(volts)

        ).alongWith(Commands.print("EL INTAKE ESTÁ A " + volts));
    }

    public Command setVelocity(double RPMs) {
        return run(
                () -> io.setVelocity(RPMs));
    }

    public Command setPosition(double Rotaciones) {
        return run(
                () -> io.setPosition(Rotaciones)
                );
    }

    public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
        return m_sysIdRoutine.quasistatic(direction);
    }

    public Command sysIdDynamic(SysIdRoutine.Direction direction) {
        return m_sysIdRoutine.dynamic(direction);
    }

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Voltaje Intake", io.getInputs().intakeLeftVoltage);
        SmartDashboard.putNumber("Intake/Posicion", io.getInputs().intakePosition);
        SmartDashboard.putNumber("Intake/Velocidad", io.getInputs().intakeVelocity);

        io.update();
    }
}
