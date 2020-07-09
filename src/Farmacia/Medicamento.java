
package Farmacia;

import java.util.Date;




public class Medicamento {
    private String nombre;
    private final String codigo;
    private float precio;
    private String marca;
    private int cantidad;    
    private Date fVencimiento;
    private Date fIngreso;

    public Medicamento(String nombre, String codigo, float precio, String marca, int cantidad, Date fVencimiento) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.marca = marca;
        this.fVencimiento = fVencimiento;
        this.cantidad = cantidad;
        
        Date fechaActual = new Date();
        this.fIngreso = fechaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getfVencimiento() {
        return fVencimiento;
    }

    public Date getfIngreso() {
        return fIngreso;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
 
}
