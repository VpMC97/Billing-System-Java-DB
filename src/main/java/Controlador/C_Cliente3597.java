/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente_3597;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Isa
 */
public class C_Cliente3597 {
    
    private String sql = "";
    
    public void AgregarCliente(Cliente_3597 c){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "insert into Clientes (NIT, nombre, apellido, DPI, direccion, edad, telefono) values ('" +
            c.getNIT() + "','" + c.getNombre() + "','" + c.getApellido() + "','"+
            c.getDPI() + "','" + c.getDireccion() + "','" + c.getEdad() + "','" + c.getTelefono() + "');";
        
        try {
            Statement consulta = conexion.establecerConexion().createStatement();
            consulta.executeUpdate(sql);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } finally {
            conexion.closeConexion();
        }
    }
    
    public void ModificarCliente(Cliente_3597 c){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "update Clientes set Clientes.NIT = '" + c.getNIT() + "', Clientes.nombre = '" + c.getNombre() +
               "', Clientes.apellido = '" + c.getApellido() + "', Clientes.DPI = '" + c.getDPI() +
               "', Clientes.direccion = '" + c.getDireccion() + "', Clientes.edad = '" + c.getEdad() +
               "', Clientes.telefono = '" + c.getTelefono() + "' where Clientes.codigo = " + c.getCodigo() + ";";
        
        try {
            Statement consulta = conexion.establecerConexion().createStatement();
            consulta.executeUpdate(sql);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } finally {
            conexion.closeConexion();
        }
    }
    
    public void EliminarCliente(String cod){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "delete from Clientes where Clientes.codigo = " + cod + ";";
        
        try {
            Statement consulta = conexion.establecerConexion().createStatement();
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } finally {
            conexion.closeConexion();
        }
    }
    
    public String CodigoContador(){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "select codigo from Clientes where codigo = (select max(codigo) from Clientes);";
        String contador = "1";
        int c;
        
        try {
            Statement comando = conexion.establecerConexion().createStatement();
            ResultSet registro = comando.executeQuery(sql);
            
            if (registro.next()){
                contador = registro.getString("codigo");
                c = Integer.parseInt(contador) + 1;
                contador = Integer.toString(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConexion();
        }
        return contador;
    }
   
    public String Reporte(){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "select * from Clientes;";
        
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("Archivos\\Cliente.html");
            myWriter.write( "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>Reporte Clientes</title>\n" +
                            "    <style type=\"text/css\" media=\"screen\">\n" +
                            "        table.content {\n" +
                            "            border-collapse: collapse;\n" +
                            "            margin: 32px 0;\n" +
                            "            min-width: 500px;\n" +
                            "            border-radius: 5px 5px 0 0;\n" +
                            "            overflow: hidden;\n" +
                            "            position: relative;\n" +
                            "            left: 75px;\n" +
                            "        }\n" +
                            "        \n" +
                            "        \n" +
                            "        table.content thead tr, \n" +
                            "        table.content caption {\n" +
                            "            padding: 12px 16px;\n" +
                            "            font-size: 20px;\n" +
                            "            background-color: rgb(102, 0, 102);\n" +
                            "            color: white;\n" +
                            "            font-weight: bold;\n" +
                            "            text-align: center;\n" +
                            "        }\n" +
                            "        \n" +
                            "        table.content th,\n" +
                            "        table.content td{\n" +
                            "            padding: 12px 16px;\n" +
                            "            font-size: 12px;\n" +
                            "        }\n" +
                            "        \n" +
                            "        table.content tbody tr {\n" +
                            "            border-bottom: 1px solid #ccc;\n" +
                            "        }\n" +
                            "        \n" +
                            "        table.content tbody tr:last-of-type {\n" +
                            "            border-bottom: 2px solid rgb(102, 0, 102);\n" +
                            "        }\n" +
                            "        </style>\n" +
                            "</head>\n");
            myWriter.write( "<body>\n" +
                            "    <section class=\"struct\" id=\"estructura\">\n" +
                            "        <table class=\"content\">\n" +
                            "            <thead>\n" +
                            "                <caption>CLIENTES</caption>\n" +
                            "                <tr>\n" +
                            "                   <th>CODIGO</th>\n" +
                            "                   <th>NIT</th>\n" +
                            "                   <th>NOMBRE</th>\n" +
                            "                   <th>APELLIDO</th>\n" +
                            "                   <th>DPI</th>\n" +
                            "                   <th>DIRECCIÓN</th>\n" +
                            "                   <th>EDAD</th>\n" +
                            "                   <th>TELÉFONO</th>\n" +
                            "                </tr>\n" +
                            "            </thead>\n" + 
                            "            <tbody>\n");
           
            Statement comando = conexion.establecerConexion().createStatement();
            ResultSet registro = comando.executeQuery(sql);
            
            while (registro.next()) {
                myWriter.write( "               <tr>\n" +
                                "                   <td>"+ registro.getString("codigo") + "</td>\n" +
                                "                   <td>"+ registro.getString("NIT") + "</td>\n" +
                                "                   <td>"+ registro.getString("nombre") + "</td>\n" +
                                "                   <td>"+ registro.getString("apellido") + "</td>\n" +
                                "                   <td>"+ registro.getString("DPI") + "</td>\n" +
                                "                   <td>"+ registro.getString("direccion") + "</td>\n" +
                                "                   <td>"+ registro.getString("edad") + "</td>\n" +
                                "                   <td>"+ registro.getString("telefono") + "</td>\n" +
                                "               </tr>\n");
            }
         
 
            myWriter.write( "            </tbody>\n" +
                            "       </table>\n" +
                            "   </section>\n" +
                            "</body>\n" +
                            "</html>");
            myWriter.close();            
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } finally{
            conexion.closeConexion();
        }
        return("Archivos\\Cliente.html");
    }
    
    public void AbrirURL(String url){
        
        String nombreSO = System.getProperty("os.name");
       
        try {
            if (nombreSO.startsWith("Mac OS")){
                Class manager = Class.forName("com.apple.eio.FileManager");
                Method openURL = manager.getDeclaredMethod("openURL", new Class[] {String.class});
                openURL.invoke(null, new Object[] {url});
            }
            if (nombreSO.startsWith("Windows"))             
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);           
            else{
                //se asume  Unix or Linux
                String[] navegadores = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
                String navegador = null;
                for (int cont = 0; cont < navegadores.length && navegador == null; cont++){                   
                    if (Runtime.getRuntime().exec( new String[] {"which", navegadores[cont]}).waitFor() == 0)
                        navegador = navegadores[cont];
                }
               
                if (navegador == null) throw new Exception("No se encuentra navegador web");
                else
                    Runtime.getRuntime().exec(new String[] {navegador, url});
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Navegador no econtrado:\n" + e.getLocalizedMessage());
        }
    }
    
}
