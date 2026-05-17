package vetcare.model;

import java.util.HashSet;

public class Estilista extends Persona {

    private HashSet<String> horasOcupadas;

    public Estilista(String cedula, String nombre, String apellido, String telefono, String email) {
        super(cedula, nombre, apellido, telefono, email);
        this.horasOcupadas = new HashSet<>();
    }

    @Override
    public String getRol() {
        return "ESTILISTA";
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

    public HashSet<String> getHorasOcupadas() {
        return horasOcupadas;
    }
}