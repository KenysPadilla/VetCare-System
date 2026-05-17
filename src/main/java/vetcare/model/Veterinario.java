package vetcare.model;

import java.util.HashSet;

public class Veterinario extends Persona {

    private String especialidad;
    private String numeroLicencia;
    private HashSet<String> horasOcupadas;

    public Veterinario(String cedula, String nombre, String apellido, String telefono, String email,
            String especialidad, String numeroLicencia) {
        super(cedula, nombre, apellido, telefono, email);
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
        this.horasOcupadas = new HashSet<>();
    }

    @Override
    public String getRol() {
        return "VETERINARIO";
    }

    public boolean tieneDisponibilidad(String hora) {
        return !horasOcupadas.contains(hora);
    }

    public void ocuparHora(String hora) {
        horasOcupadas.add(hora);
    }

    public void liberarHora(String hora) {
        horasOcupadas.remove(hora);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public HashSet<String> getHorasOcupadas() {
        return horasOcupadas;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}