/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Isa
 */
public class C_Conexion3597 {
    Connection conectar = null;
    
    String usuario = "root";
    String contraseña = "panquesitodemiel";
    String bd = "bdsupermercado";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    
    public Connection establecerConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos: " + e);
        } 
        return conectar;
    }
    
    public void closeConexion(){
        try {
            if (conectar != null && !conectar.isClosed()){
                conectar.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo cerar la conexión con la BD " + e);
        }
    }
}
