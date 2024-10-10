
package Modelo;

import java.io.Serializable;

public class Empleado extends Persona_3597 implements Serializable {
    private int codigo;
    private int ID;
    private double salario;
    private String puesto;

    public Empleado() {
    }

    public Empleado(int codigo, int ID, double salario, String puesto, String nombre, String apellido, String DPI, String direccion, String edad, String telefono) {
        super(nombre, apellido, DPI, direccion, edad, telefono);
        this.codigo = codigo;
        this.ID = ID;
        this.salario = salario;
        this.puesto = puesto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return "codigo:" + codigo + ", ID:" + ID + ", salario:" + salario + ", puesto:" + puesto + 
                ", nombre: " + getNombre() + ", apellido: " + getApellido() + ",\nDPI: " + 
                getDPI() + ", dirección: " + getDireccion() + ", edad: " + getEdad() + ", teléfono: " + getTelefono();
    }
    
    
    
}
