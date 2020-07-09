
package Farmacia;



public class Comprobante {
    private final Venta venta;
    private final Farmacia farmacia;

    public Comprobante(Venta venta, Farmacia farmacia) {
        this.venta = venta;
        this.farmacia = farmacia;
    }

    public Venta getVenta() {
        return venta;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }
    
    
    
    
}
