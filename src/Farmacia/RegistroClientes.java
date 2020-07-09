/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farmacia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Diego Sullon
 */
public class RegistroClientes {
    int cod=1;
    private ArrayList<Empresa> empresas = new ArrayList<Empresa>();
    private ArrayList<Persona> personas = new ArrayList<Persona>();

    public RegistroClientes() {
        actualizarListaEmpresas();
        actualizarListaPersonas();
    }
    /**
     * agrega en ArrayList
     */
    public void agregarEmpresa_Array(String ruc, String nombre) {
        Empresa e = new Empresa(ruc, nombre, codigoCliente());
        empresas.add(e);
        cod++;
    }
    /**
     * agrega en base de datos conectada
     */
    public void agregarEmpresa(String ruc, String nombre){
        try {
            //Se crea la conexión con la base de datos
            Connection conexion = Farmacia.getConexion();
            //modelo de sentencia
            PreparedStatement ingreso = conexion.prepareStatement("INSERT INTO clientes_empresa VALUES (?,?,?)");
            //formato para la sentencia
            ingreso.setString(1, codigoCliente());ingreso.setString(2, ruc);ingreso.setString(3, nombre);
            ingreso.executeUpdate();

            actualizarListaEmpresas();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }       
    }
    /**
     * agrega en ArrayList
     */
    public void agregarPersona_Array(String dni, String nombre) {
        Persona e = new Persona(dni, nombre, codigoCliente());
        personas.add(e);
        cod++;
    }
    /**
     * agrega en base de datos conectada
     */
    public void agregarPersona(String dni, String nombre){
        try {
            //Se crea la conexión con la base de datos
            Connection conexion = Farmacia.getConexion();
            //modelo de sentencia
            PreparedStatement ingreso = conexion.prepareStatement("INSERT INTO clientes_persona VALUES (?,?,?)");
            //formato para la sentencia
            ingreso.setString(1, codigoCliente());ingreso.setString(2, dni);ingreso.setString(3, nombre);
            ingreso.executeUpdate();

            actualizarListaPersonas();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión1");
        }       
    }
    public void actualizarListaEmpresas() {
        try {
            empresas.clear();
            Connection conexion = Farmacia.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM clientes_empresa");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Empresa e = new Empresa(rs.getString("ruc"),rs.getString("nombre"),rs.getString("codigo"));
                empresas.add(e);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }
    public void actualizarListaPersonas() {
        try {
            personas.clear();
            Connection conexion = Farmacia.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM clientes_persona");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Persona e = new Persona(rs.getString("dni"),rs.getString("nombre"),rs.getString("codigo"));
                personas.add(e);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }
    

    public Empresa buscarEmpresa(String ruc) {

        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getRuc().equals(ruc)) {
                return empresas.get(i);
            }
        }
        return null;
    }

    public Persona buscarPersona(String dni) {

        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getDni().equals(dni)) {
                return personas.get(i);
            }
        }
        return null;
    }
    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }
    public String codigoCliente() {
        String codC="000";
        int length;
        length=String.valueOf(cod).length();
        switch(length){
            case 1:
                codC="00"+String.valueOf(cod);
                break;
            case 2:
                codC="0"+String.valueOf(cod);
                break;
            case 3:
                codC=String.valueOf(cod);
                break;
        }
        return codC;
    }
    
    
}
