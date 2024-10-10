
package Controlador;

import Modelo.Articulo_3597;
import Modelo.Proveedor_3597;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class AB_Proveedor3597 {

    private final String nombreFichero = "Archivos\\Provedores.dat";
    private ArrayList <Proveedor_3597> proveedores;
    
    
    public AB_Proveedor3597() {
    }
    
    public int VerificarProveedor(Proveedor_3597 proveedor){
        for (int cont = 0; cont < proveedores.size(); cont++)
        {
            if (proveedores.get(cont).getCodigo() == proveedor.getCodigo()){
                return cont;
            }
        }
        return -1;
    }
    
    public int VerifNombreProveedor(Proveedor_3597 proveedor){
        for (int cont = 0; cont < proveedores.size(); cont++)
        {
            if (proveedores.get(cont).getNombre().equals(proveedor.getNombre())){
                return cont;
            }   
        }
        return -1;
    }
    
    public int VerificarCP(Articulo_3597 articulo){
        for (int cont = 0; cont < proveedores.size(); cont++)
        {
            if (proveedores.get(cont).getCodigo() == articulo.getcProveedor()){
                return cont;
            }
        }
        return -1;
    }
    
    public ArrayList A_inv(){
        return proveedores;
    }
    
    public void A_CrearProveedor(){
        proveedores = new ArrayList();  
    }
        
    public void CopiarArray(ArrayList <Proveedor_3597> p){
        proveedores.addAll(p);
    }
    
    public boolean A_InsertarProveedor(Proveedor_3597 proveedor){
        if (VerificarProveedor(proveedor) == -1 && VerifNombreProveedor(proveedor) == -1){
            proveedores.add(proveedor);  
            return true;
        }
        else if (VerificarProveedor(proveedor) != -1){
            JOptionPane.showMessageDialog(null, "Código de proveedor ya existente\n\nInténtelo de nuevo");
        }
        else if (VerifNombreProveedor(proveedor) != -1){
            JOptionPane.showMessageDialog(null, "Nombre de proveedor ya existente\n\nInténtelo de nuevo");
        }
        return false;
    }
    
    public boolean A_ModificarProveedor(Proveedor_3597 proveedor){
        if (VerificarProveedor(proveedor) != -1){
            int i = VerificarProveedor(proveedor);
            
            String Nombre = proveedores.get(i).getNombre();
            proveedores.get(i).setNombre("");
            if (VerifNombreProveedor(proveedor) == -1){
                proveedores.get(i).setNombre(proveedor.getNombre());
                proveedores.get(i).setNIT(proveedor.getNIT());
                proveedores.get(i).setDirección(proveedor.getDirección());
                proveedores.get(i).setTelefono(proveedor.getTelefono());   
               
                return true;
            }
            else{
               proveedores.get(i).setNombre(Nombre); 
               JOptionPane.showMessageDialog(null, "Nombre de proveedor ya existente\nInténtelo de nuevo");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Proveedor no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        return false;
    }
    
    public boolean A_EliminarProveedor(Proveedor_3597 proveedor){
        if (VerificarProveedor(proveedor) != -1){
            int i = VerificarProveedor(proveedor);
             int op = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere eliminar el proveedor " + proveedores.get(i).getNombre() + "?",
                                        "Eliminar proveedor", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
             if (op == 0){
                proveedores.remove(i);  
                return true;
            } 
        }
        else{
            JOptionPane.showMessageDialog(null, "Proveedor no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        return false;
    }
    
    public String A_leerArrray(){
        String reporte="";
        for(int cont = 0; cont < proveedores.size(); cont++)
        {
            reporte += proveedores.get(cont).toString() + "\n";
        }
        return reporte;
    }
    
    public void GuardarA_Proveedor() throws IOException{
        File file = new File(nombreFichero);
        if (!file.exists()){
            file.createNewFile();
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(proveedores);
        }
            
    }
    
    public void SalvarA_Proveedor() throws FileNotFoundException, IOException, ClassNotFoundException{
        File file = new File(nombreFichero);

        if (file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                proveedores = (ArrayList<Proveedor_3597>) ois.readObject();
            }
        }
    }
    
    public String Reporte(){
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("Archivos\\Proveedor.html");
            myWriter.write("""
                           <!DOCTYPE html>
                           <html lang="en">
                           <head>
                               <meta charset="UTF-8">
                               <meta name="viewport" content="width=device-width, initial-scale=1.0">
                               <title>Reporte Art\u00edculos</title>
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
            
            

            for (Iterator<Proveedor_3597> it = proveedores.iterator(); it.hasNext();) {
                Proveedor_3597 P = it.next();
                myWriter.write( """
                                               <tr>
                                                   <td>"""+ P.getCodigo() + "</td>\n" +
                                "                   <td>"+ P.getNombre() + "</td>\n" +
                                "                   <td>"+ P.getNIT() + "</td>\n" +
                                "                   <td>"+ P.getDirección() + "</td>\n" +
                                "                   <td>"+ P.getTelefono() + "</td>\n" +
                                "               </tr>\n");
            }
                  
 
            myWriter.write("""
                                       </tbody>
                                  </table>
                              </section>
                           </body>
                           </html>""");
            myWriter.close();            
        } catch (IOException ex) {
            ex.printStackTrace();
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
