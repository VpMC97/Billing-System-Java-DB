
package Modelo;

import java.io.Serializable;

public class Articulo_3597 implements Serializable{
    private int codigo;
    private String nombre;
    private String descripción;
    private int cantidad;
    private double precioUnitario;
    private int cProveedor;

    public Articulo_3597() {
    }

    public Articulo_3597(int codigo, String nombre, String descripción, int cantidad, double precioUnitario, int cProveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripción = descripción;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.cProveedor = cProveedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getcProveedor() {
        return cProveedor;
    }

    public void setcProveedor(int cProveedor) {
        this.cProveedor = cProveedor;
    }

    @Override
    public String toString() {
        return "Codigo=" + codigo + ", nombre=" + nombre + ", descripci\u00f3n=" + descripción + ", cantidad=" + cantidad + ", precio unitario=" + precioUnitario + ", código de proveedor=" + cProveedor;
    }
    
    
    
}
