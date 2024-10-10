
package Controlador;

import Modelo.Articulo_3597;
import Modelo.Cliente_3597;
import Modelo.DetalleFactura_3597;
import Modelo.Empleado;
import Vista.V_Factura3597;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AB_Factura3597 {
    
    private final String nombreFichero = "Archivos\\Facturas.dat";
    private int correlativo;
    private ArrayList <DetalleFactura_3597> facturas = new ArrayList();
    private ArrayList <Articulo_3597> articulos = new ArrayList();
    private ArrayList <Empleado> empleados = new ArrayList();
    private ArrayList <Cliente_3597> clientes = new ArrayList();
    
    AB_Artículo3597 ab_Articulo3597;
    AB_Empleado ab_Empleado;
    AB_Cliente3597 ab_Cliente3597;
    
    public AB_Factura3597() {
        try {
            ab_Articulo3597 = new AB_Artículo3597();
            ab_Empleado = new AB_Empleado();
            ab_Cliente3597 = new AB_Cliente3597();
            
            ab_Articulo3597.SalvarA_Articulo();
            ab_Empleado.SalvarA_Empleado();
            ab_Cliente3597.SalvarA_Cliente();
            
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList A_invFactura(){
        return facturas;
    }
    
    public ArrayList A_DetalleArticulos(){
        return articulos;
    }
    
    public void G_Array(Articulo_3597 a){
        articulos.add(a);
    }
    
    public void detalle(DetalleFactura_3597 Factura){
        if (Factura.getCodigo() != correlativo){
            int p = Verificar_Factura(Factura);
            articulos = new ArrayList(facturas.get(p).getArrayArticulos());
        }
    }
    
    public int VerificarArticulo(Articulo_3597 a){
        for (int i = 0; i < articulos.size(); i++) {
            if (a.getCodigo() == articulos.get(i).getCodigo()){
                return i;
            }
        }
        return -1;
    }
    
    public void clean(){
        articulos.clear();
    }
    
    public int corelativoF(){
        return correlativo;
    }
    
    public int Correlativo(){
        correlativo++;
        return correlativo;
    }
    
    public void ModificarFactura(DetalleFactura_3597 F){
        int p = Verificar_Factura(F);
        
        Double subtotal = 0.0;
        
        for (Iterator <Articulo_3597> it = articulos.iterator(); it.hasNext();){
            Articulo_3597 a = it.next();
            subtotal += parseDouble(a.getDescripción());
        }
        
        Double total = subtotal * 1.12;
        Double IVA = total - subtotal;
        
        try {
            ab_Articulo3597.SalvarA_Articulo();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AB_Factura3597.class.getName()).log(Level.SEVERE, null, ex);
        }
        ab_Articulo3597.RestaurarArticulosFactura(facturas.get(p).getArrayArticulos());
        
        facturas.get(p).setArrayArticulos(articulos);
        facturas.get(p).setSubtotal(subtotal);
        facturas.get(p).setIVA(IVA);
        facturas.get(p).setTotal(total);
        
        GuardarA_Factura();
        
        ab_Articulo3597.EliminarArticulosFactura(articulos);
        
    }
    
    public void AgregarFactura(DetalleFactura_3597 F){
        Double subtotal = 0.0;
        
        for (Iterator <Articulo_3597> it = articulos.iterator(); it.hasNext();){
            Articulo_3597 a = it.next();
            subtotal += parseDouble(a.getDescripción());
        }
        
        Double total = subtotal * 1.12;
        Double IVA = total - subtotal;
        
        F.setArrayArticulos(articulos);
        F.setSubtotal(subtotal);
        F.setIVA(IVA);
        F.setTotal(total);
        
        facturas.add(F);
        ab_Articulo3597.EliminarArticulosFactura(articulos);
        GuardarA_Factura();
        articulos.clear();
        
//        int op = JOptionPane.showConfirmDialog(null, "Factura agregada correctamente\n¿Desea visualizarla?", "GENERAR FACTURA", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
//        if (op == 0){
//            VisualizarFactura(F);
//        }
    }
    
    public void CancelarFactura(DetalleFactura_3597 F){
        int i = Verificar_Factura(F);
        if (i != -1){
            ab_Articulo3597.RestaurarArticulosFactura(facturas.get(i).getArrayArticulos());
            facturas.remove(i);
            GuardarA_Factura();       
        }
        else{
            JOptionPane.showMessageDialog(null, "Factura no existente según correlativo\nPorfavor, inténtelo de nuevo.");
        }
    }
    
    public int Verificar_Factura(DetalleFactura_3597 F){
        int i = 0;  
        for (Iterator <DetalleFactura_3597> it = facturas.iterator(); it.hasNext(); i++){
            DetalleFactura_3597 f = it.next();
            if (f.getCodigo() == F.getCodigo()){
                return i;
            }
        }
        return -1;
    }
    
    public boolean VisualizarFactura(DetalleFactura_3597 F){
        int i = Verificar_Factura(F);
        if (i != -1){
            DetalleFactura_3597 Factura = facturas.get(i);
            V_Factura3597 DF = new V_Factura3597(Factura);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Factura no existente según correlativo\nPorfavor, inténtelo de nuevo.");
        }
        return false;
    }
    
    public boolean BuscarFactura(DetalleFactura_3597 F){
        int i = Verificar_Factura(F);
        if (i != -1){
            DetalleFactura_3597 Factura = facturas.get(i);
            detalle(Factura);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Factura no existente según correlativo\nPorfavor, inténtelo de nuevo.");
        }
        return false;
    }

    public void GuardarA_Factura(){
        File file = new File(nombreFichero);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        }
        
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(facturas);
            oos.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try {     
            SalvarA_Factura();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AB_Factura3597.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SalvarA_Factura() throws FileNotFoundException, IOException, ClassNotFoundException{
        File file = new File(nombreFichero);

        if (file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                facturas = (ArrayList<DetalleFactura_3597>) ois.readObject();
                if (!facturas.isEmpty())
                    correlativo = facturas.get(facturas.size()-1).getCodigo() + 1;    
                else
                    correlativo = 1;
            }
        }
    }
    
    public String Reporte(){
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("Archivos\\Factura.html");
            myWriter.write("""
                           <!DOCTYPE html>
                           <html lang="en">
                           <head>
                               <meta charset="UTF-8">
                               <meta name="viewport" content="width=device-width, initial-scale=1.0">
                               <title>Asignacion</title>
                               <style type="text/css" media="screen">
                                   table.content {
                                       border-collapse: collapse;
                                       margin: 32px 0;
                                       min-width: 500px;
                                       border-radius: 5px 5px 0 0;
                                       overflow: hidden;
                                       position: relative;
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
                                           <caption>FACTURA</caption>
                                           <tr>
                                              <th>CORRELATIVO</th>
                                              <th>FECHA</th>
                                              <th>ID CAJERO</th>
                                              <th>NOMBRE EMPLEADO</th>
                                              <th>NIT CLIENTE</th>
                                              <th>NOMBRE CLIENTE</th>
                                              <th>CANTIDAD</th>
                                              <th>DESCRIPCIÓN</th>
                                              <th>TOTAL PRODUCTO</th>
                                              <th>SUBTOTAL</th>
                                              <th>IVA</th>
                                              <th>TOTAL A PAGAR</th>
                                           </tr>
                                       </thead>
                                       <tbody>
                           """);
            
            

            for (Iterator<DetalleFactura_3597> it = facturas.iterator(); it.hasNext();) {
                DetalleFactura_3597 D = it.next();
                
                int size = D.getArrayArticulos().size() + 2;
                
                int e = ab_Empleado.VCajeroFac(D.getIDempleado());
                empleados = ab_Empleado.A_invCajero();
                
                int c = ab_Cliente3597.VClienteFac(Integer.toString(D.getNITcliente()));
                clientes = ab_Cliente3597.A_inv();
                
                myWriter.write("                <tr>\n" + 
                                "                   <td rowspan=\"" + size + "\">" + D.getCodigo() + "</td>\n" +
                                "                   <td rowspan=\"" + size + "\">" + D.getFecha() + "</td>\n" + 
                                "                   <td rowspan=\"" + size + "\">" + D.getIDempleado() + "</td>\n" + 
                                "                   <td rowspan=\"" + size + "\">" + empleados.get(e).getNombre() + "</td>\n" + 
                                "                   <td rowspan=\"" + size + "\">" + D.getNITcliente() + "</td>\n" + 
                                "                   <td rowspan=\"" + size + "\">" + clientes.get(c).getNombre() + "</td>\n" + 
                                "               </tr>\n");

                for (Articulo_3597 A : D.getArrayArticulos()) {
                    myWriter.write("                <tr>\n" +
                                "                   <td>" + A.getCantidad() + "</td>\n" +
                                "                   <td>" + A.getNombre() + "</td>\n" +
                                "                   <td>" + A.getDescripción() + "</td>\n" +
                                "               </tr>\n");
                }
                
                myWriter.write("                <tr>\n" +
                                "                   <td></td>\n" +
                                "                   <td></td>\n" +
                                "                   <td></td>\n" +
                                "                   <td>" + D.getSubtotal() + "</td>\n" +
                                "                   <td>" + String.format("%.2f", D.getIVA()) + "</td>\n" +
                                "                   <td>" + String.format("%.2f", D.getTotal()) + "</td>\n" +
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
        return ("Archivos\\Factura.html");
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
