/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Articulo_3597;
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
public class C_Articulo3597 {
    private String sql = "";
    
    public void AgregarArticulo(Articulo_3597 a){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "insert into Articulos (nombre, descripcion, cantidad, precio, fkproveedor) values ('" +
        a.getNombre() + "','" + a.getDescripción() + "','" + a.getCantidad() + "','"+ a.getPrecioUnitario() + "','" + a.getcProveedor() + "');";
        
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
    
        public void ModificarArticulo(Articulo_3597 a){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "update Articulos set Articulos.nombre = '" + a.getNombre() +
              "', Articulos.descripcion = '" + a.getDescripción() +"', Articulos.cantidad = '" + 
              a.getCantidad() +"', Articulos.precio = '" + a.getPrecioUnitario() +
              "', Articulos.fkproveedor = '" + a.getcProveedor() + "' where Articulos.codigo = '" + a.getCodigo() + "';";
        
        try {
            Statement comando = conexion.establecerConexion().createStatement();
            comando.executeUpdate(sql);
       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar esta acción: " + e);
        } finally {
            conexion.closeConexion();
        }
    }
     
    public void EliminarArticulo(String cod){
        C_Conexion3597 conexion = new C_Conexion3597();
        
        sql = "delete from Articulos where Articulos.codigo =" + cod +";";
        
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
        
        sql = "select codigo from Articulos where codigo = (select max(codigo) from Articulos);";
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
        sql = "select * from Articulos;";
        
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("Archivos\\Articulo.html");
            myWriter.write( "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>Reporte Artículos</title>\n" +
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
                            "                <caption>ARTÍCULOS</caption>\n" +
                            "                <tr>\n" +
                            "                   <th>CODIGO</th>\n" +
                            "                   <th>NOMBRE</th>\n" +
                            "                   <th>DESCRIPCIÓN</th>\n" +
                            "                   <th>CANTIDAD</th>\n" +
                            "                   <th>PRECIO UNITARIO</th>\n" +
                            "                   <th>COD. PROVEEEDOR</th>\n" +
                            "                </tr>\n" +
                            "            </thead>\n" + 
                            "            <tbody>\n");
            
            Statement comando = conexion.establecerConexion().createStatement();
            ResultSet registro = comando.executeQuery(sql);

            while (registro.next()){
                myWriter.write( "               <tr>\n" +
                                "                   <td>"+ registro.getString("codigo") + "</td>\n" +
                                "                   <td>"+ registro.getString("nombre") + "</td>\n" +
                                "                   <td>"+ registro.getString("descripcion") + "</td>\n" +
                                "                   <td>"+ registro.getString("cantidad") + "</td>\n" +
                                "                   <td>"+ registro.getString("precio") + "</td>\n" +
                                "                   <td>"+ registro.getString("fkproveedor") + "</td>\n" +
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
        } finally {
            conexion.closeConexion();
        }
        return "Archivos\\Articulo.html";
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
