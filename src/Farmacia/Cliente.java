/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farmacia;

/**
 *
 * @author Diego Sullon
 */
public class Cliente {
    private String nombre;
    private int puntos;
    private final String codigo;

    public Cliente(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getCodigo() {
        return codigo;
    }
    
}
