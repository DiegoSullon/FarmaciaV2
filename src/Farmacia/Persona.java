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
public class Persona extends Cliente{
    private String dni;
    

    public Persona(String dni, String nombre, String codigo) {
        super(nombre, codigo);
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }
    
   
}
