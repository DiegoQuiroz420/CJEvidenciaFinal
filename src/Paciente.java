public class Paciente {
    String nomPaciente;
    String id;
    public Paciente(String nombrePaciente, String id) {
        this.nomPaciente = nombrePaciente;
        this.id = id;
    }
    public String getNombrePaciente() {
        return nomPaciente;
    }
    public String getId() {
        return id;
    }
    public void setNombrePaciente(String nombrePaciente) {
        this.nomPaciente = nombrePaciente;
    }
    public void setId(String id) {
        this.id = id;
    }
}