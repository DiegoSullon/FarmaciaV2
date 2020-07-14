/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farmacia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Diego Sullon
 */
public class RegistroEmpleados {

    int n_empleados;

    ArrayList<Empleado> empleados = new ArrayList<Empleado>();

    private Farmacia sedeFarmacia;

    public RegistroEmpleados(Farmacia sedeFarmacia) {
        actualizarLista(sedeFarmacia);
        this.sedeFarmacia = sedeFarmacia;
    }

    /**
     * agrega empleado en memoria(ArrayList)
     */
    public void agregarEmpleado_array(String dni, String cargo, String nombre, String telefono, String correo, boolean genero, float sueldo, Farmacia sedeFarmacia) {
        Empleado e = new Empleado(dni, cargo, nombre, telefono, correo, genero, sueldo, sedeFarmacia);
        empleados.add(e);
    }

    /**
     * registra empleado en Base de datos
     */
    public void agregarEmpleado(String dni, String cargo, String nombre, String telefono, String correo, boolean genero, float sueldo, Farmacia sedeFarmacia) {
        String g = genero ? "Masculino" : "Femenino";
        try {
            //Se crea la conexión con la base de datos
            Connection conexion = Farmacia.getConexion();
            //modelo de sentencia
            PreparedStatement ingreso = conexion.prepareStatement("INSERT INTO empleados VALUES (?,?,?,?,?,?,?)");
            //formato para la sentencia
            ingreso.setString(1, dni);
            ingreso.setString(2, nombre);
            ingreso.setString(3, cargo);
            ingreso.setString(4, telefono);
            ingreso.setString(5, correo);
            ingreso.setString(6, g);
            ingreso.setFloat(7, sueldo);
            ingreso.executeUpdate();
            //cierre de conexión
            actualizarLista(sedeFarmacia);
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión2");
        }
    }

    /**
     * elimina empleado en memoria(ArrayList)
     */
    public void eliminarEmpleado_array(String cod) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getDni().equals(cod)) {
                empleados.remove(i);
            }
        }
    }

    /**
     * elimina empleado en Base de datos
     */
    public void eliminarEmpleado(String dni, Farmacia sedeFarmacia) {
        try {
            Connection conexion = Farmacia.getConexion();
            PreparedStatement delete = conexion.prepareStatement("DELETE FROM `empleados` WHERE `empleados`.`dni`=?");
            delete.setString(1, dni);
            System.out.println(dni);
            delete.executeUpdate();
            actualizarLista(sedeFarmacia);
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }

    public void modificarEmpleado(String dni, String column, String cambio) {
        try {
            Connection conexion = Farmacia.getConexion();
            PreparedStatement update;
            switch (column) {
                case "nombre":
                    update = conexion.prepareStatement("UPDATE `empleados` SET `nombre` = ? WHERE `empleados`.`dni` = ?");
                    update.setString(1, cambio);
                    update.setString(2, dni);
                    update.executeUpdate();
                    break;
                case "cargo":
                    if(cambio.equalsIgnoreCase("gerente")){
                        cambio="Gerente";
                    }else if(cambio.equalsIgnoreCase("asistente")){
                        cambio="Asistente";
                    }else if(cambio.equalsIgnoreCase("cajero")){
                        cambio="Cajero";
                    }else {
                        break;
                    }
                    update = conexion.prepareStatement("UPDATE `empleados` SET `cargo` = ? WHERE `empleados`.`dni` = ?");
                    update.setString(1, cambio);
                    update.setString(2, dni);
                    update.executeUpdate();
                    break;
                case "telefono":
                    update = conexion.prepareStatement("UPDATE `empleados` SET `telefono` = ? WHERE `empleados`.`dni` = ?");
                    update.setString(1, cambio);
                    update.setString(2, dni);
                    update.executeUpdate();
                    break;
                case "sueldo":
                    update = conexion.prepareStatement("UPDATE `empleados` SET `sueldo` = ? WHERE `empleados`.`dni` = ?");
                    update.setFloat(1, Float.parseFloat(cambio));
                    update.setString(2, dni);
                    update.executeUpdate();
                    break;
            }

            actualizarLista(sedeFarmacia);
        } catch (Exception e) {
            System.out.println("Fallo modificarEmpleado");
        }
    }

    public void actualizarLista(Farmacia sedeFarmacia) {
        try {
            empleados.clear();
            Connection conexion = Farmacia.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM empleados");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                boolean t = (rs.getString("genero").equalsIgnoreCase("Masculino"));
                Empleado e = new Empleado(rs.getString("dni"), rs.getString("cargo"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("correo"), t, rs.getFloat("sueldo"), sedeFarmacia);
                empleados.add(e);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }

    public void actualizarDatos(String cod) {
    }

    public void mostrarEmpleados() {
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println(empleados.get(i).getDni());
        }
    }

    public Empleado obtenerEmpleado(String dni) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getDni().equals(dni)) {
                return empleados.get(i);
            }
        }
        return null;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

}
