package Farmacia;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Farmacia {

    private String nombre = "Farmacia La morgue";
    private String ciudad;
    private String sede;
    private String direccion;
    private String telefono;
    private final String codigo;
    private final String ruc = "20566345658";

    private static Connection conexion;
    private static Connection conexion_local;

    Map<String, String> database = new HashMap<String, String>();
    Map<String, String> database_local = new HashMap<String, String>();
    private RegistroVentas rVentas;
    private Inventario inventario;
    private RegistroEmpleados r_empleados;

    public Farmacia(String ciudad, String sede, String direccion, String telefono, String codigo) {
        this.ciudad = ciudad;
        this.sede = sede;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigo = codigo;
        actualizarConexion("bwfvy0vhvjg6ubvv52vs-mysql.services.clever-cloud.com", "bwfvy0vhvjg6ubvv52vs", "3306", "utosyldwfdhnnlqu", "UyLT9X6DQx1p16jahDke");
//        actualizarConexion_local("localhost", "farmacia", "3308", "root", "");
        this.rVentas = new RegistroVentas();
        this.inventario = new Inventario();
        this.r_empleados = new RegistroEmpleados(this);
    }
    public Farmacia(String ciudad, String sede, String direccion, String telefono, String codigo,String dif) {
        this.ciudad = ciudad;
        this.sede = sede;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigo = codigo;
        this.rVentas = new RegistroVentas();
        this.inventario = new Inventario();
        this.r_empleados = new RegistroEmpleados(this);
    }

    public Empleado login(String dni) {
        return r_empleados.obtenerEmpleado(dni);
    }

    public void actualizarConexion(String host, String name, String port, String user, String password) {
        database.put("host", host);
        database.put("name", name);
        database.put("port", port);
        database.put("user", user);
        database.put("password", password);
        this.conexion = conectarBBDD();
    }
    public void actualizarConexion_local(String host, String name, String port, String user, String password) {
        database_local.put("host", host);
        database_local.put("name", name);
        database_local.put("port", port);
        database_local.put("user", user);
        database_local.put("password", password);
        this.conexion_local = conectarBBDD_local();
    }

    public void cerrarConexion() {
        try {
            //cierre de conexión
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger("no se cerró la conexión");
        }
    }
    public void cerrarConexion_local() {
        try {
            //cierre de conexión
            conexion_local.close();
        } catch (SQLException ex) {
            Logger.getLogger("no se cerró la conexión local");
        }
    }

    private Connection conectarBBDD() {
        String c = "jdbc:mysql://" + database.get("host") + ":" + database.get("port") + "/" + database.get("name");
        try {
            Connection miConexion = DriverManager.getConnection(c, database.get("user"), database.get("password"));
            return miConexion;
        } catch (SQLException ex) {
            System.out.println("Sin conexión");
            return null;
        }
    }
    private Connection conectarBBDD_local() {
        String c = "jdbc:mysql://" + database_local.get("host") + ":" + database_local.get("port") + "/" + database_local.get("name");
        try {
            Connection miConexion = DriverManager.getConnection(c, database_local.get("user"), database_local.get("password"));
            return miConexion;
        } catch (SQLException ex) {
            System.out.println("Sin conexión local");
            return null;
        }
    }

    public static Connection getConexion() {
        return conexion;
    }

    public static Connection getConexion_local() {
        return conexion_local;
    }
    

    public RegistroVentas getR_ventas() {
        return rVentas;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public RegistroEmpleados getR_empleados() {
        return r_empleados;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

}
