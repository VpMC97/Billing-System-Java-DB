
package Controlador;

import Modelo.Cliente_3597;
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

public class AB_Cliente3597 {
    
    private final String nombreFichero = "Archivos\\Clientes.dat";
    private ArrayList <Cliente_3597> clientes;
    private int contador;
    
    public AB_Cliente3597() {

    }
    
    public int VerificarCliente(Cliente_3597 cliente){
        for (int cont = 0; cont < clientes.size(); cont++)
        {
            if (clientes.get(cont).getNIT().equals(cliente.getNIT())){
                return cont;
            }
        }
        return -1;
    }
    
    public int VerificarCodigoCliente(Cliente_3597 cliente){
        for (int cont = 0; cont < clientes.size(); cont++)
        {
            if (clientes.get(cont).getCodigo() == cliente.getCodigo()){
                return cont;
            }
        }
        return -1;
    }
    
    public int VClienteFac(String nit){
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNIT().equals(nit))
                return i;
        }
        return -1;
    }
    
    public int CodigoContador(){
        return contador;
    }
  
    
    public ArrayList A_inv(){
        return clientes;
    }
    
    public void A_CrearClientes(){
        clientes = new ArrayList();
    }
        
    
    public void A_InsertarCliente(Cliente_3597 cliente){
        if (VerificarCliente(cliente) == -1){
            clientes.add(cliente);  
            int i = VerificarCliente(cliente);
            clientes.get(i).setCodigo(contador);
            contador++;
        }
        else{
            JOptionPane.showMessageDialog(null, "Cliente  ya existente según NIT\n\nPor favor, inténtelo de nuevo");
        }
    }
    
    public boolean A_ModificarCliente(Cliente_3597 cliente){
        if (VerificarCodigoCliente(cliente) != -1){
            int i = VerificarCodigoCliente(cliente);           
            String Nit = clientes.get(i).getNIT();
            clientes.get(i).setNIT("");
            if (VerificarCliente(cliente) == -1){
                clientes.get(i).setNIT(cliente.getNIT());
                clientes.get(i).setNombre(cliente.getNombre());
                clientes.get(i).setApellido(cliente.getApellido());
                clientes.get(i).setDPI(cliente.getDPI());
                clientes.get(i).setDireccion(cliente.getDireccion());
                clientes.get(i).setEdad(cliente.getEdad());
                clientes.get(i).setTelefono(cliente.getTelefono());  
                return true;
            }
            else{
                clientes.get(i).setNIT(Nit);
                JOptionPane.showConfirmDialog(null, "NIT de cliente ya existente, inténtelo de nuevo.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Cliente no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        return false;
    }
    
    
    public boolean A_EliminarCliente(Cliente_3597 cliente){
        if (VerificarCliente(cliente) != -1){
            int i = VerificarCliente(cliente);
            int op = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere eliminar el cliente " + clientes.get(i).getNombre() + "?",
                                        "Eliminar cliente", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
            if (op == 0){
                if(CodigoContador() - 1 == clientes.get(i).getCodigo()){
                    contador--;
                }
                clientes.remove(i);
                return true;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Cliente no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        return false;
    }
    
    public String A_leerArrray(){
        
        String reporte="";
        Iterator<Cliente_3597> it = clientes.iterator();
        
        while(it.hasNext()){
            reporte += it.next().toString() + "\n";
        }
        return reporte;
    }
    
    public void GuardarA_Cliente() throws IOException{
        File file = new File(nombreFichero);
        if (!file.exists()){
            file.createNewFile();
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(clientes);
        }  
    }
    
    public void SalvarA_Cliente() throws FileNotFoundException, IOException, ClassNotFoundException{
        File file = new File(nombreFichero);

        if (file.exists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                clientes = (ArrayList<Cliente_3597>) ois.readObject();
                if (!clientes.isEmpty()){
                    contador = clientes.get(clientes.size()-1).getCodigo() + 1;
                }
                ois.close();
            }
        }
    }
    
    public String Reporte(){
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("Archivos\\Cliente.html");
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
            
            
            for (Iterator<Cliente_3597> it = clientes.iterator(); it.hasNext();) {
                Cliente_3597 C = it.next();
                myWriter.write( "               <tr>\n" +
                                "                   <td>"+ C.getCodigo() + "</td>\n" +
                                "                   <td>"+ C.getNIT() + "</td>\n" +
                                "                   <td>"+ C.getNombre() + "</td>\n" +
                                "                   <td>"+ C.getApellido() + "</td>\n" +
                                "                   <td>"+ C.getDPI() + "</td>\n" +
                                "                   <td>"+ C.getDireccion() + "</td>\n" +
                                "                   <td>"+ C.getEdad() + "</td>\n" +
                                "                   <td>"+ C.getTelefono() + "</td>\n" +
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
