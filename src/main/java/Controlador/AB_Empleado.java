
package Controlador;


import Modelo.Empleado;
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

public class AB_Empleado {

    private final String nombreFichero = "Archivos\\Empleados.dat";
     
    private int cCajero=1, cVendedor=1, cDespachador=1;
    
    private ArrayList <Empleado> empleados, cajeros, vendedores, despachadores;
    private int[] codigos = {cCajero, cVendedor, cDespachador} ;

    
    public AB_Empleado() {
    }
    
    public int VerificarEmpleado(Empleado empleado){
        for (int cont = 0; cont < empleados.size(); cont++)
        {
            if (empleados.get(cont).getCodigo() == empleado.getCodigo()){
                return cont;
            }
        }
        return -1;
    }
    
    public int V_Cajero(Empleado empleado){
        for (int i = 0; i < cajeros.size(); i++) {
            if (cajeros.get(i).getCodigo() == empleado.getCodigo()){
                return i;
            }
        }
        return -1;
    }
    
    public int VCajeroFac(int ID){
        for (int i = 0; i < cajeros.size(); i++) {
            if (cajeros.get(i).getID() == ID)
                return i;
        }
        return -1;
    }
    
    public int V_Vendedor(Empleado empleado){
        for (int i = 0; i < vendedores.size(); i++) {
            if (vendedores.get(i).getCodigo() == empleado.getCodigo()){
                return i;
            }
        }
        return -1;
    }
    
    public int V_Despachador(Empleado empleado){
        for (int i = 0; i < despachadores.size(); i++) {
            if (despachadores.get(i).getCodigo() == empleado.getCodigo()){
                return i;
            }
        }
        return -1;
    }
    
    public int VerificarPuesto(Empleado empleado){
        int i = VerificarEmpleado(empleado);
        switch (empleados.get(i).getPuesto()) {
            case "Cajero":
                return 1;
            case "Vendedor":
                return 2;
            case "Despachador":
                return 3;
            default:
                break;
        }
        return 0;
    }
    
    public int CC(String i){
        switch (i) {
            case "Cajero":
                return 1;
            case "Vendedor":
                return 2;
            case "Despachador":
                return 3;
            default:
                break;
        }
        return 0;
    }
    

    public int CodigoContador(String i){
        switch (i){
            case "Cajero":
                return cCajero;
            case "Vendedor":
                return cVendedor;
            case "Despachador":
                return cDespachador;
            default:
                break;
        }
        return 0;
    }
    
    public ArrayList A_inv(){
        return empleados;
    }
    
    public ArrayList A_invCajero(){
        return cajeros;
    }
    
    public void A_CrearEmpleado(){
        empleados = new ArrayList();  
        cajeros = new ArrayList();
        vendedores = new ArrayList();
        despachadores = new ArrayList();
    }
        
    
    public boolean A_InsertarEmpleado(Empleado empleado){
        if (VerificarEmpleado(empleado) == -1){
            empleados.add(empleado);  
            
            switch(CC(empleado.getPuesto())){
                case 1:
                    cCajero++;
                    codigos[0] = cCajero;
                    cajeros.add(empleado);
                    break;
                case 2:
                    cVendedor++;
                    codigos[1] = cVendedor;
                    vendedores.add(empleado);
                    break;
                case 3:
                    cDespachador++;
                    codigos[2] = cDespachador;
                    despachadores.add(empleado);
                    break;
                default:
                    break;
            }
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Código de empleado ya existente\n\nInténtelo de nuevo");
            return false;
        }
    }
    
    public boolean A_ModificarEmpleado(Empleado empleado){
        if (VerificarEmpleado(empleado) != -1){
            int i = VerificarEmpleado(empleado);
            
            switch(CC(empleados.get(i).getPuesto())){
                case 1:
                    if (V_Cajero(empleado) != -1){
                        int c = V_Cajero(empleado);
                        cajeros.get(c).setID(empleado.getID());
                        cajeros.get(c).setSalario(empleado.getSalario());
                        cajeros.get(c).setPuesto(empleado.getPuesto());
                        cajeros.get(c).setNombre(empleado.getNombre());
                        cajeros.get(c).setApellido(empleado.getApellido());
                        cajeros.get(c).setDPI(empleado.getDPI());
                        cajeros.get(c).setDireccion(empleado.getDireccion());
                        cajeros.get(c).setTelefono(empleado.getTelefono());  
                        cajeros.get(c).setEdad(empleado.getEdad());
                    }
                    break;
                case 2:
                    if (V_Vendedor(empleado) != -1){
                        int c = V_Vendedor(empleado);
                        vendedores.get(c).setID(empleado.getID());
                        vendedores.get(c).setSalario(empleado.getSalario());
                        vendedores.get(c).setPuesto(empleado.getPuesto());
                        vendedores.get(c).setNombre(empleado.getNombre());
                        vendedores.get(c).setApellido(empleado.getApellido());
                        vendedores.get(c).setDPI(empleado.getDPI());
                        vendedores.get(c).setDireccion(empleado.getDireccion());
                        vendedores.get(c).setTelefono(empleado.getTelefono());   
                        vendedores.get(c).setEdad(empleado.getEdad());
                    }
                    break;
                case 3:
                    if (V_Despachador(empleado) != -1){
                        int c = V_Despachador(empleado);
                        despachadores.get(c).setID(empleado.getID());
                        despachadores.get(c).setSalario(empleado.getSalario());
                        despachadores.get(c).setPuesto(empleado.getPuesto());
                        despachadores.get(c).setNombre(empleado.getNombre());
                        despachadores.get(c).setApellido(empleado.getApellido());
                        despachadores.get(c).setDPI(empleado.getDPI());
                        despachadores.get(c).setDireccion(empleado.getDireccion());
                        despachadores.get(c).setTelefono(empleado.getTelefono()); 
                        despachadores.get(c).setEdad(empleado.getEdad());
                    }
                    break;
                default:
                    break;
            }
            empleados.get(i).setID(empleado.getID());
            empleados.get(i).setSalario(empleado.getSalario());
            empleados.get(i).setPuesto(empleado.getPuesto());
            empleados.get(i).setNombre(empleado.getNombre());
            empleados.get(i).setApellido(empleado.getApellido());
            empleados.get(i).setDPI(empleado.getDPI());
            empleados.get(i).setDireccion(empleado.getDireccion());
            empleados.get(i).setTelefono(empleado.getTelefono()); 
            empleados.get(i).setEdad(empleado.getEdad());
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Empleado no existente según código\n\nPor favor, inténtelo de nuevo");
        }
        return false;
    }
    
    public boolean LastC (int cod){
        int c = cajeros.size() - 1;
        if (cajeros.get(cod).getCodigo() == cajeros.get(c).getCodigo())
            return true;
        return false;
    }
    
    public boolean LastV (int cod){
        int v = vendedores.size() - 1;
        if (vendedores.get(cod).getCodigo() == vendedores.get(v).getCodigo())
            return true;
        return false;
    }
    
     public boolean LastD (int cod){
        int d = despachadores.size() - 1;
        if (despachadores.get(cod).getCodigo() == despachadores.get(d).getCodigo())
            return true;
        return false;
    }
    
    public void A_EliminarEmpleado(Empleado empleado){
        if (VerificarEmpleado(empleado) != -1){
            int i = VerificarEmpleado(empleado);
            int cod;
            
            switch(CC(empleados.get(i).getPuesto())){
                case 1:
                    cod = V_Cajero(empleado);
                    if (LastC(cod))
                        cCajero--;
                    cajeros.remove(cod);
                    break;
                case 2:
                    cod = V_Vendedor(empleado);
                    if (LastV(cod))
                        cVendedor--;
                    vendedores.remove(cod);
                    break;
                case 3:
                    cod = V_Despachador(empleado);
                    if (LastD(cod))
                        cDespachador--;
                    despachadores.remove(cod);
                    break;
                default:
                    break;
            }
            empleados.remove(i);    
        }
        else{
            JOptionPane.showMessageDialog(null, "Empleado no existente según código\n\nPor favor, inténtelo de nuevo");
        }
    }
    
    public String A_leerArray(){
        String reporte="";
        for(int cont = 0; cont < empleados.size(); cont++)
        {
            reporte += empleados.get(cont).toString() + "\n";
        }
        return reporte;
    }
    
    public String readArray(){
        String r = "";
        for (int c = 0; c < cajeros.size(); c++) 
            r += cajeros.get(c).toString() + "\n";
        
        r+= "\n------------------------\n";
        
        for (int v = 0; v < vendedores.size(); v++)
            r += vendedores.get(v).toString() + "\n";
        
        r+= "\n------------------------\n";
        
        for (int d = 0; d < despachadores.size(); d++) 
            r += despachadores.get(d).toString() + "\n";
        
        return r;
    }
    
    public void GuardarA_Empleado() throws IOException{
        File file = new File(nombreFichero);
        File code = new File("Archivos\\CodigoE.dat");
        File c = new File("Archivos\\Cajero.dat");
        File v = new File("Archivos\\Vendedor.dat");
        File d = new File("Archivos\\Despachador.dat");
        
        if (!file.exists()) file.createNewFile();
        if (!code.exists()) code.createNewFile();
        if (!c.exists())    c.createNewFile();
        if (!v.exists())    v.createNewFile();
        if (!d.exists())    d.createNewFile();
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        ObjectOutputStream cos = new ObjectOutputStream(new FileOutputStream(code));
        ObjectOutputStream aos = new ObjectOutputStream(new FileOutputStream(c));
        ObjectOutputStream vos = new ObjectOutputStream(new FileOutputStream(v));
        ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(d));
        
        oos.writeObject(empleados);
        cos.writeObject(codigos);
        aos.writeObject(cajeros);
        vos.writeObject(vendedores);
        dos.writeObject(despachadores);
        
        oos.close();
        cos.close();
        aos.close();
        vos.close();
        dos.close();
    }
    
    public ArrayList SalvarA_Empleado() throws FileNotFoundException, IOException, ClassNotFoundException{
        File file = new File(nombreFichero);
        File code = new File("Archivos\\CodigoE.dat");
        File c = new File("Archivos\\Cajero.dat");
        File v = new File("Archivos\\Vendedor.dat");
        File d = new File("Archivos\\Despachador.dat");
        
        if (file.exists() && code.exists() && c.exists()
                && v.exists() && d.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ObjectInputStream cos = new ObjectInputStream(new FileInputStream(code));
            ObjectInputStream aos = new ObjectInputStream(new FileInputStream(c));
            ObjectInputStream vos = new ObjectInputStream(new FileInputStream(v));
            ObjectInputStream dos = new ObjectInputStream(new FileInputStream(d));
            
            empleados = (ArrayList<Empleado>) ois.readObject();
            codigos = (int[]) cos.readObject();
            cajeros = (ArrayList<Empleado>) aos.readObject();
            vendedores = (ArrayList<Empleado>) vos.readObject();
            despachadores = (ArrayList<Empleado>) dos.readObject();
        }
        
        if (!cajeros.isEmpty())
            cCajero = codigos[0];
        if (!vendedores.isEmpty())
            cVendedor = codigos[1];
        if (!despachadores.isEmpty())
            cDespachador = codigos[2];
        
        return empleados;
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
    
    public String Reporte(){
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("Archivos\\Empleado.html");
            myWriter.write( "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <title>Reporte Empleados</title>\n" +
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
                            "                <caption>EMPLEADOS</caption>\n" +
                            "                <tr>\n" +
                            "                   <th>CODIGO</th>\n" +
                            "                   <th>NOMBRE</th>\n" +
                            "                   <th>APELLIDO</th>\n" +
                            "                   <th>DPI</th>\n" +
                            "                   <th>DIRECCIÓN</th>\n" +
                            "                   <th>EDAD</th>\n" +
                            "                   <th>TELÉFONO</th>\n" +
                            "                   <th>SALARIO</th>\n" +
                            "                   <th>ID</th>\n" +
                            "                   <th>PUESTO</th>\n" +
                            "                </tr>\n" +
                            "            </thead>\n" + 
                            "            <tbody>\n");
            

            for (Iterator<Empleado> it = empleados.iterator(); it.hasNext();) {
                Empleado E = it.next();
                myWriter.write( "               <tr>\n" +
                                "                   <td>"+ E.getCodigo() + "</td>\n" +
                                "                   <td>"+ E.getNombre() + "</td>\n" +
                                "                   <td>"+ E.getApellido() + "</td>\n" +
                                "                   <td>"+ E.getDPI() + "</td>\n" +
                                "                   <td>"+ E.getDireccion() + "</td>\n" +
                                "                   <td>"+ E.getEdad() + "</td>\n" +
                                "                   <td>"+ E.getTelefono() + "</td>\n" +
                                "                   <td>"+ E.getSalario() + "</td>\n" +
                                "                   <td>"+ E.getID() + "</td>\n" +
                                "                   <td>"+ E.getPuesto() + "</td>\n" +
                                "               </tr>\n");
            }
    
            myWriter.write( "               </tbody>\n" +
                            "           </table>\n" +
                            "       </section>\n" +
                            "   <section class=\"struct\" id=\"Cajero\">\n" +
                            "        <table class=\"content\">\n" +
                            "            <thead>\n" +
                            "                <caption>CAJEROS</caption>\n" +
                            "                    <tr>\n" +
                            "                        <th>CODIGO</th>\n" +
                            "                        <th>NOMBRE</th>\n" +
                            "                        <th>APELLIDO</th>\n" +
                            "                        <th>DPI</th>\n" +
                            "                        <th>DIRECCIÓN</th>\n" +
                            "                        <th>EDAD</th>\n" +
                            "                        <th>TELÉFONO</th>\n" +
                            "                        <th>SALARIO</th>\n" +
                            "                        <th>ID</th>\n" +
                            "                        <th>PUESTO</th>\n" +
                            "                    </tr>\n" +
                            "            </thead>\n" + 
                            "       <tbody>\n");
            
            for (Iterator<Empleado> it = cajeros.iterator(); it.hasNext();) {
                Empleado E = it.next();
                myWriter.write( "               <tr>\n" +
                                "                   <td>"+ E.getCodigo() + "</td>\n" +
                                "                   <td>"+ E.getNombre() + "</td>\n" +
                                "                   <td>"+ E.getApellido() + "</td>\n" +
                                "                   <td>"+ E.getDPI() + "</td>\n" +
                                "                   <td>"+ E.getDireccion() + "</td>\n" +
                                "                   <td>"+ E.getEdad() + "</td>\n" +
                                "                   <td>"+ E.getTelefono() + "</td>\n" +
                                "                   <td>"+ E.getSalario() + "</td>\n" +
                                "                   <td>"+ E.getID() + "</td>\n" +
                                "                   <td>"+ E.getPuesto() + "</td>\n" +
                                "               </tr>\n");
            }
            myWriter.write( "               </tbody>\n" +
                            "           </table>\n" +
                            "       </section>\n" +
                            "   <section class=\"struct\" id=\"Vendedor\">\n" +
                            "        <table class=\"content\">\n" +
                            "            <thead>\n" +
                            "                <caption>VENDEDORES</caption>\n" +
                            "                    <tr>\n" +
                            "                       <th>CODIGO</th>\n" +
                            "                       <th>NOMBRE</th>\n" +
                            "                       <th>APELLIDO</th>\n" +
                            "                       <th>DPI</th>\n" +
                            "                       <th>DIRECCIÓN</th>\n" +
                            "                       <th>EDAD</th>\n" +
                            "                       <th>TELÉFONO</th>\n" +
                            "                       <th>SALARIO</th>\n" +
                            "                       <th>ID</th>\n" +
                            "                       <th>PUESTO</th>\n" +
                            "                    </tr>\n" +
                            "            </thead>\n" + 
                            "            <tbody>\n");
            
            for (Iterator<Empleado> it = vendedores.iterator(); it.hasNext();) {
                Empleado E = it.next();
                myWriter.write( "               <tr>\n" +
                                "                   <td>"+ E.getCodigo() + "</td>\n" +
                                "                   <td>"+ E.getNombre() + "</td>\n" +
                                "                   <td>"+ E.getApellido() + "</td>\n" +
                                "                   <td>"+ E.getDPI() + "</td>\n" +
                                "                   <td>"+ E.getDireccion() + "</td>\n" +
                                "                   <td>"+ E.getEdad() + "</td>\n" +
                                "                   <td>"+ E.getTelefono() + "</td>\n" +
                                "                   <td>"+ E.getSalario() + "</td>\n" +
                                "                   <td>"+ E.getID() + "</td>\n" +
                                "                   <td>"+ E.getPuesto() + "</td>\n" +
                                "               </tr>\n");
            }
            myWriter.write( "               </tbody>\n" +
                            "           </table>\n" +
                            "       </section>\n" +
                            "   <section class=\"struct\" id=\"Despachador\">\n" +
                            "        <table class=\"content\">\n" +
                            "            <thead>\n" +
                            "                <caption>DESPACHADORES</caption>\n" +
                            "                    <tr>\n" +
                            "                       <th>CODIGO</th>\n" +
                            "                       <th>NOMBRE</th>\n" +
                            "                       <th>APELLIDO</th>\n" +
                            "                       <th>DPI</th>\n" +
                            "                       <th>DIRECCIÓN</th>\n" +
                            "                       <th>EDAD</th>\n" +
                            "                       <th>TELÉFONO</th>\n" +
                            "                       <th>SALARIO</th>\n" +
                            "                       <th>ID</th>\n" +
                            "                       <th>PUESTO</th>\n" +
                            "                    </tr>\n" +
                            "            </thead>\n" + 
                            "            <tbody>\n");
            
                for (Iterator<Empleado> it = despachadores.iterator(); it.hasNext();) {
                    Empleado E = it.next();
                    myWriter.write( "               <tr>\n" +
                                    "                   <td>"+ E.getCodigo() + "</td>\n" +
                                    "                   <td>"+ E.getNombre() + "</td>\n" +
                                    "                   <td>"+ E.getApellido() + "</td>\n" +
                                    "                   <td>"+ E.getDPI() + "</td>\n" +
                                    "                   <td>"+ E.getDireccion() + "</td>\n" +
                                    "                   <td>"+ E.getEdad() + "</td>\n" +
                                    "                   <td>"+ E.getTelefono() + "</td>\n" +
                                    "                   <td>"+ E.getSalario() + "</td>\n" +
                                    "                   <td>"+ E.getID() + "</td>\n" +
                                    "                   <td>"+ E.getPuesto() + "</td>\n" +
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
        return ("Archivos\\Empleado.html");
    }
}
