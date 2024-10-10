/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controlador;

import Modelo.Articulo_3597;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.lang.reflect.Method;

public class AB_Artículo3597 {
    
    private final String nombreFichero = "Archivos\\Articulos.dat";
    private ArrayList <Articulo_3597> articulos;
    AB_Proveedor3597 ab_Proveedor_3597;
    
    public AB_Artículo3597() {
        try {
            ab_Proveedor_3597 = new AB_Proveedor3597();
            ab_Proveedor_3597.SalvarA_Proveedor();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AB_Artículo3597.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int VerificarArticulo(Articulo_3597 articulo){
        for (int cont = 0; cont < articulos.size(); cont++)
        {
            if (articulos.get(cont).getCodigo() == articulo.getCodigo()){
                return cont;
            }
        }
        return -1;
    }
  
    public int VerifNombreArticulo(Articulo_3597 articulo){
        for (int cont = 0; cont < articulos.size(); cont++)
        {
            if (articulos.get(cont).getNombre().equals(articulo.getNombre())){
                return cont;
            }
        }
        return -1;
    }
    
    public int VArticuloFac(int codigo){
        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getCodigo() == codigo)
                return i;
        }
        return -1;
    }
    
    public void EliminarArticulosFactura(ArrayList<Articulo_3597> D_Articulos){
        for (Iterator <Articulo_3597> it = articulos.iterator(); it.hasNext();){
            Articulo_3597 A = it.next();
            for (Iterator <Articulo_3597> dit = D_Articulos.iterator(); dit.hasNext();){
                Articulo_3597 a = dit.next();
                
                if (A.getCodigo() == a.getCodigo()){
                    A.setCantidad(A.getCantidad() - a.getCantidad());
                }
            }
        }
        try {
            GuardarA_Articulo();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void RestaurarArticulosFactura(ArrayList<Articulo_3597> D_Articulos){
        for (Iterator <Articulo_3597> it = articulos.iterator(); it.hasNext();){
            Articulo_3597 A = it.next();
            for (Iterator <Articulo_3597> dit = D_Articulos.iterator(); dit.hasNext();){
                Articulo_3597 a = dit.next();
                
                if (A.getCodigo() == a.getCodigo()){
                    A.setCantidad(A.getCantidad() + a.getCantidad());
                }
            }
        }
        try {
            GuardarA_Articulo();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList A_inv(){
        return articulos;
    }
    
    public void A_CrearArticulos(){
        articulos = new ArrayList();
    }
    
    public boolean A_InsertarArticulo(Articulo_3597 articulo){
        if (VerificarArticulo(articulo) == -1 && ab_Proveedor_3597.VerificarCP(articulo) != -1
                && VerifNombreArticulo(articulo) == -1){
            articulos.add(articulo);  
            return true;
        }
        else if (VerificarArticulo(articulo) != -1){
            JOptionPane.showMessageDialog(null, "Artículo ya existente según código\n\nPor favor, inténtelo de nuevo");
        }
        else if (VerifNombreArticulo(articulo) != -1){
            JOptionPane.showMessageDialog(null,"Nombre de artículo ya existente\n\nPor favor, inténtelo de nuevo");
        }
        else if (ab_Proveedor_3597.VerificarCP(articulo) == -1){
            JOptionPane.showMessageDialog(null, "Proveedor no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        return false;
    }
    
    public boolean A_ModificarArticulo(Articulo_3597 articulo){
        if (VerificarArticulo(articulo) != -1 && ab_Proveedor_3597.VerificarCP(articulo) != -1){
            int i = VerificarArticulo(articulo);

            String Nombre = articulos.get(i).getNombre();
            articulos.get(i).setNombre("");
            
            if(VerifNombreArticulo(articulo) == -1){
                articulos.get(i).setNombre(articulo.getNombre());
                articulos.get(i).setDescripción(articulo.getDescripción());
                articulos.get(i).setCantidad(articulo.getCantidad());
                articulos.get(i).setPrecioUnitario(articulo.getPrecioUnitario());    
               
                return true;
            }
            else{
                articulos.get(i).setNombre(Nombre);
                JOptionPane.showMessageDialog(null, "Nombre de artículo ya existente\nInténtelo de nuevo");
            }
        }
        else if (VerificarArticulo(articulo) == -1){
            JOptionPane.showMessageDialog(null, "Artículo no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        else if (ab_Proveedor_3597.VerificarCP(articulo) == -1){
            JOptionPane.showMessageDialog(null, "Proveedor no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        return false;
    }
    
    
    public boolean A_EliminarArticulo(Articulo_3597 articulo){
        if (VerificarArticulo(articulo) != -1){
            
            int i = VerificarArticulo(articulo);
            int op = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere eliminar el artículo " + articulos.get(i).getNombre() + "?",
                                        "Eliminar artículo", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
            if (op==0){
                articulos.remove(i); 
                return true;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Articulo no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        return false;
    }
    
    public String A_leerArrray(){
        
        String reporte="";
        Iterator<Articulo_3597> it = articulos.iterator();
        
        while(it.hasNext()){
            reporte += it.next().toString() + "\n";
        }
        return reporte;
    }
    
    public void GuardarA_Articulo() throws IOException{
        File file = new File(nombreFichero);
        if (!file.exists()){
            file.createNewFile();
        }
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        
        oos.writeObject(articulos);
        
        oos.close();
            
    }
    
    public void SalvarA_Articulo() throws FileNotFoundException, IOException, ClassNotFoundException{
        File file = new File(nombreFichero);

        if (file.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            articulos = (ArrayList<Articulo_3597>) ois.readObject();
            ois.close();
        }
    }
    
    public String Reporte(){
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
            
            

            for (Iterator<Articulo_3597> it = articulos.iterator(); it.hasNext();){
                Articulo_3597 A = it.next();
                myWriter.write( "               <tr>\n" +
                                "                   <td>"+ A.getCodigo() + "</td>\n" +
                                "                   <td>"+ A.getNombre() + "</td>\n" +
                                "                   <td>"+ A.getDescripción() + "</td>\n" +
                                "                   <td>"+ A.getCantidad() + "</td>\n" +
                                "                   <td>"+ A.getPrecioUnitario() + "</td>\n" +
                                "                   <td>"+ A.getcProveedor() + "</td>\n" +
                                "               </tr>\n");
            }           
 
            myWriter.write( "            </tbody>\n" +
                            "       </table>\n" +
                            "   </section>\n" +
                            "</body>\n" +
                            "</html>");
            
            myWriter.close(); 
            
        } catch (IOException ex) {
            ex.printStackTrace();
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

