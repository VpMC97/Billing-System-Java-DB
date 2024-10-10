
package Modelo;

import java.io.Serializable;

public class Persona_3597 implements Serializable{
    private String nombre;
    private String apellido;
    private String DPI;
    private String direccion;
    private String edad;
    private String telefono;

    public Persona_3597() {
    }

    public Persona_3597(String nombre, String apellido, String DPI, String direccion, String edad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DPI = DPI;
        this.direccion = direccion;
        this.edad = edad;
        this.telefono = telefono;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
}
