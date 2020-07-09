
package Farmacia;

import java.util.ArrayList;
import java.util.Date;


public class Venta {
    private final String codigo;
    private static int sumar;
    private float tiempo;
    private boolean tipoDePago;
    private final Date fechaD = new Date();
    private final String fecha;
    private float total;
    private String doc;
    private String nombreC;
    
    private Factura factura;
    private Boleta boleta;
    private final Pedido pedido;
    
    
    
    ArrayList<String>detalles=new ArrayList<String>();
    
    public Venta(Pedido pedido,String nombreC,String doc, float total, float tiempo, boolean tipoDePago) {
        this.nombreC=nombreC;
        this.pedido=pedido;
        this.doc=doc;
        this.total=total;
        this.tiempo = tiempo;
        this.tipoDePago = tipoDePago;
        this.fecha=String.valueOf(fechaD);
        sumar++;
        codigo=String.valueOf(sumar);
    }
    public void generarComprobante(Farmacia farmacia, boolean tipoPago){
        
        if(tipoPago){
            factura = new Factura(this,pedido.getEmpleado().getSedeFarmacia(),codigo, pedido.docC);

        }else{
            boleta = new Boleta(this, farmacia, codigo, pedido.docC);

        }
        
    
    }

    public float getTotal() {
        return total;
    }

    public String getDoc() {
        return doc;
    }

    public String getNombreC() {
        return nombreC;
    }
    
    public void registrarVenta(){
        pedido.getEmpleado().getSedeFarmacia().getR_ventas().registrar(this);
    }
    public String getCodigo() {
        return codigo;
    }

    public Pedido getPedido() {
        return pedido;
    }
    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(boolean tipoDePago) {
        this.tipoDePago = tipoDePago;
    }
    

    public boolean getTipoDePago() {
        return tipoDePago;
    }
    public Factura getFactura() {
        return factura;
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public Date getFechaD() {
        return fechaD;
    }
    
}
