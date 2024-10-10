/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Proveedor_3597;
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
public class C_Proveedor3597 {
    
    private String sql = "";
    
    public void AgregarProveedor(Proveedor_3597 p){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "insert into Proveedores (nombre, NIT, direccion, telefono) values ('" +
        p.getNombre() + "','" + p.getNIT() + "','" + p.getDirección() + "','"+ p.getTelefono() + "');";
        
        try {
            Statement comando = conexion.establecerConexion().createStatement();
            comando.executeUpdate(sql);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } 
        finally {
            conexion.closeConexion();
        }
    }
    
    public void ModificarProveedor(Proveedor_3597 p){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "update Proveedores set Proveedores.nombre = '" + p.getNombre() +
              "', Proveedores.NIT = '" + p.getNIT() +"', Proveedores.direccion = '" + 
              p.getDirección() +"',Proveedores.telefono = '" + p.getTelefono() +
              "' where Proveedores.codigo =" + p.getCodigo() + ";";
        
        try {
            Statement comando = conexion.establecerConexion().createStatement();
            comando.executeUpdate(sql);
       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } finally {
            conexion.closeConexion();
        }
    }
    
    public void EliminarProveedor(String cod){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "delete from Proveedores where Proveedores.codigo =" + cod +";";
        
        try {
            Statement comando = conexion.establecerConexion().createStatement();
            comando.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } finally {
            conexion.closeConexion();
        } 
    }
    
    public String CodigoContador(){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "select codigo from Proveedores where codigo = (select max(codigo) from Proveedores);";
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
        
        sql = "select * from Proveedores;";
        
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("Archivos\\Proveedor.html");
            myWriter.write("""
                           <!DOCTYPE html>
                           <html lang="en">
                           <head>
                               <meta charset="UTF-8">
                               <meta name="viewport" content="width=device-width, initial-scale=1.0">
                               <title>Reporte Proveedores</title>
                               <style type="text/css" media="screen">
                                   table.content {
                                       border-collapse: collapse;
                                       margin: 32px 0;
                                       min-width: 500px;
                                       border-radius: 5px 5px 0 0;
                                       overflow: hidden;
                                       position: relative;
                                       left: 75px;
                                   }
                                   
                                   table.content thead tr, 
                                   table.content caption {
                                       padding: 12px 16px;
                                       font-size: 20px;
                                       background-color: rgb(102, 0, 102);
                                       color: white;
                                       font-weight: bold;
                                       text-align: center;
                                   }
                                   
                                   table.content th,
                                   table.content td{
                                       padding: 12px 16px;
                                       font-size: 12px;
                                   }
                                   
                                   table.content tbody tr {
                                       border-bottom: 1px solid #ccc;
                                   }
                                   
                                   table.content tbody tr:last-of-type {
                                       border-bottom: 2px solid rgb(102, 0, 102);
                                   }
                                   </style>
                           </head>
                           """);
            myWriter.write("""
                           <body>
                               <section class="struct" id="estructura">
                                   <table class="content">
                                       <thead>
                                           <caption>PROVEEDORES</caption>
                                           <tr>
                                              <th>CODIGO</th>
                                              <th>NOMBRE</th>
                                              <th>NIT</th>
                                              <th>DIRECCI\u00d3N</th>
                                              <th>TEL\u00c9FONO</th>
                                           </tr>
                                       </thead>
                                       <tbody>
                           """);
            

            Statement comando = conexion.establecerConexion().createStatement();
            ResultSet registro = comando.executeQuery(sql);
            
            while (registro.next()) {
                myWriter.write( """
                                               <tr>
                                                   <td>"""+ registro.getString("codigo") + "</td>\n" +
                                "                   <td>"+ registro.getString("nombre") + "</td>\n" +
                                "                   <td>"+ registro.getString("NIT") + "</td>\n" +
                                "                   <td>"+ registro.getString("direccion") + "</td>\n" +
                                "                   <td>"+ registro.getString("telefono") + "</td>\n" +
                                "               </tr>\n");
            }
                  
 
            myWriter.write("""
                                       </tbody>
                                  </table>
                              </section>
                           </body>
                           </html>""");
            myWriter.close();            
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } finally {
            conexion.closeConexion();
        }
        return ("Archivos\\Proveedor.html");
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
                for (int contador = 0; contador < navegadores.length && navegador == null; contador++){                   
                    if (Runtime.getRuntime().exec( new String[] {"which", navegadores[contador]}).waitFor() == 0)
                        navegador = navegadores[contador];
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
