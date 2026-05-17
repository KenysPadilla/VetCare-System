package vetcare.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private int id;
    private Paciente paciente;
    private Veterinario veterinario;
    private LocalDate fecha;
    private LocalTime hora;
    private TipoCita tipoCita;
    private EstadoCita estadoCita;
    private String motivo;
    private String observaciones;

    public Cita(int id, Paciente paciente, Veterinario veterinario,
            LocalDate fecha, LocalTime hora, TipoCita tipoCita, String motivo) {
        this.id = id;
        this.paciente = paciente;
        this.veterinario = veterinario;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoCita = tipoCita;
        this.motivo = motivo;
        this.estadoCita = EstadoCita.PROGRAMADA;
    }

    public void iniciar() {
        this.estadoCita = EstadoCita.EN_CURSO;
    }

    public void cancelar() {
        this.estadoCita = EstadoCita.CANCELADA;
        veterinario.liberarHora(hora.toString());
    }

    public void completar() {
        this.estadoCita = EstadoCita.COMPLETADA;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public TipoCita getTipoCita() {
        return tipoCita;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Cita #" + id + " - " + paciente.getNombre() + " con "
                + veterinario.getNombre() + " el " + fecha + " a las " + hora;
    }
}