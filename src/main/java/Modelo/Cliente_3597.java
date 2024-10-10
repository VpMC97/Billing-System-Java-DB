
package Modelo;

import java.io.Serializable;

public class Cliente_3597 extends Persona_3597 implements Serializable{
    private int codigo;
    private String NIT;

    public Cliente_3597() {
    }

    public Cliente_3597(int codigo, String NIT, String nombre, String apellido, String DPI, String direccion, String edad, String telefono) {
        super(nombre, apellido, DPI, direccion, edad, telefono);
        this.NIT = NIT;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    
    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    @Override
    public String toString() {
        return  "NIT: " + NIT + "codigo: " + codigo + "nombre: " + getNombre() + 
                ", apellido: " + getApellido() + ",\n DPI:" + getDPI() +
                ", direccion: " + getDireccion() + ", edad: " + getEdad() + ", telefono: " + getTelefono();
    }
    
    
}
