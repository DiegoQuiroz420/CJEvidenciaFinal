import java.util.Date;
public class Cita {
    private int id;
    private Date fechaCita;
    private int idDoctor;
    private int idPaciente;
    private String motivoCita;
    public Cita(int id, Date fecha, int doctorId, int pacienteId, String motivo) {
        this.id = id;
        fechaCita = fecha;
        idDoctor = doctorId;
        idPaciente = pacienteId;
        motivoCita = motivo;
    }
    public int getId() {
        return id;
    }
    public Date getFechaCita() {
        return fechaCita;
    }
    public int getIdDoctor() {
        return idDoctor;
    }
    public int getIdPaciente() {
        return idPaciente;
    }
    public String getMotivoCita() {
        return motivoCita;
    }
    public static void mostrarCita(Cita[] citas, int idCita) {
        boolean encontrada = false;
        for (Cita cita : citas) {
            if (cita.getId() == idCita) {
                System.out.println("Datos de la cita:");
                System.out.println("ID de la cita: " + cita.getId());
                System.out.println("ID del doctor: " + cita.getIdDoctor());
                System.out.println("ID del paciente: " + cita.getIdPaciente());
                System.out.println("Fecha y hora: " + cita.getFechaCita());
                System.out.println("Motivo: " + cita.getMotivoCita());
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            System.out.println("La cita con ID " + idCita + " no existe.");
        }
    }
}
