
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class DetalleFactura_3597 extends Factura_3597 implements Serializable{
    private ArrayList<Articulo_3597> ArrayArticulos;
    private int caja;
    private Double subtotal;
    private Double IVA;
    private Double total;

    public DetalleFactura_3597() {
    }

    public DetalleFactura_3597(ArrayList<Articulo_3597> ArrayArticulos, int caja, Double subtotal, Double IVA, Double total, int codigo, String fecha, int IDempleado, int NITcliente) {
        super(codigo, fecha, IDempleado, NITcliente);
        this.ArrayArticulos = ArrayArticulos;
        this.caja = caja;
        this.subtotal = subtotal;
        this.IVA = IVA;
        this.total = total;
    }

    public ArrayList<Articulo_3597> getArrayArticulos() {
        return ArrayArticulos;
    }

    public void setArrayArticulos(ArrayList<Articulo_3597> ArrayArticulos) {
        this.ArrayArticulos = ArrayArticulos;
    }

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIVA() {
        return IVA;
    }

    public void setIVA(Double IVA) {
        this.IVA = IVA;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetalleFactura_3597{" + "ArrayArticulos=" + ArrayArticulos + ", caja=" + caja + ", subtotal=" + subtotal + ", IVA=" + IVA + ", total=" + total + '}';
    }
    
}
