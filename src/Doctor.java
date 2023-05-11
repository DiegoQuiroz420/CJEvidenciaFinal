public class Doctor {
    String id;
    String nomDoctor;
    String esp;
    public Doctor(String id, String nombreDoctor, String esp) {
        this.id = id;
        this.nomDoctor = nombreDoctor;
        this.esp = esp;
    }
    public String getId() {
        return id;
    }
    public String getNombreDoctor() {
        return nomDoctor;
    }
    public String getEsp() {
        return esp;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setNombreDoctor(String nombreDoctor) {
        this.nomDoctor = nombreDoctor;
    }
    public void setEsp(String esp) {
        this.esp = esp;
    }
}