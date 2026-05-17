package vetcare.model;

public class Paciente {

    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private double peso;
    private Propietario propietario;

    public Paciente(int id, String nombre, String especie, String raza, int edad, double peso, Propietario propietario) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.propietario = propietario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return nombre + " (" + especie + " - " + raza + ")";
    }
}