package vetcare.model;

import java.time.LocalDate;
import vetcare.exception.StockInsuficienteException;

public class Medicamento {

    private int id;
    private String nombre;
    private String descripcion;
    private String fabricante;
    private double precio;
    private int stockDisponible;
    private LocalDate fechaVencimiento;

    public Medicamento(int id, String nombre, String descripcion, String fabricante,
            double precio, int stockDisponible, LocalDate fechaVencimiento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean hayStock(int cantidad) {
        return stockDisponible >= cantidad;
    }

    public void reducirStock(int cantidad) throws StockInsuficienteException {
        if (!hayStock(cantidad)) {
            throw new StockInsuficienteException("Stock insuficiente para: " + nombre);
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

    public String getDescripcion() {
        return descripcion;
    }

    public String getFabricante() {
        return fabricante;
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

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return nombre + " (Stock: " + stockDisponible + ")";
    }
}