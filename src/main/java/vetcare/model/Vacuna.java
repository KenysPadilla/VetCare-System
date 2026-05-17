package vetcare.model;

import java.time.LocalDate;
import vetcare.exception.StockInsuficienteException;

public class Vacuna {

    private int id;
    private String nombre;
    private String laboratorio;
    private String lote;
    private double precio;
    private int stockDisponible;
    private LocalDate fechaVencimiento;

    public Vacuna(int id, String nombre, String laboratorio, String lote,
            double precio, int stockDisponible, LocalDate fechaVencimiento) {
        this.id = id;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.lote = lote;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean hayStock(int cantidad) {
        return stockDisponible >= cantidad;
    }

    public void reducirStock(int cantidad) throws StockInsuficienteException {
        if (!hayStock(cantidad)) {
            throw new StockInsuficienteException("Stock insuficiente para vacuna: " + nombre);
        }
        this.stockDisponible -= cantidad;
    }

    public void aumentarStock(int cantidad) {
        this.stockDisponible += cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public String getLote() {
        return lote;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return nombre + " - Lote: " + lote + " (Stock: " + stockDisponible + ")";
    }
}