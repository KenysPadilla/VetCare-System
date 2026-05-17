package vetcare.model;

import java.util.ArrayList;

public class Propietario extends Persona {

    private ArrayList<Paciente> mascotas;

    public Propietario(String cedula, String nombre, String apellido, String telefono, String email) {
        super(cedula, nombre, apellido, telefono, email);
        this.mascotas = new ArrayList<>();
    }

    @Override
    public String getRol() {
        return "PROPIETARIO";
    }

    public void agregarMascota(Paciente paciente) {
        mascotas.add(paciente);
    }

    public ArrayList<Paciente> getMascotas() {
        return mascotas;
    }
}