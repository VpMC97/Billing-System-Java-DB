
package Vista;


import Controlador.AB_Factura3597;
import Modelo.DetalleFactura_3597;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class F_Reporte3597 extends javax.swing.JFrame {

    ArrayList <DetalleFactura_3597> A_Factura = new ArrayList();
    AB_Factura3597 ab_Factura3597 = new AB_Factura3597();
    
    public F_Reporte3597(ArrayList <DetalleFactura_3597> facturas) {
         
        initComponents();
        A_Factura = facturas;
        
        try {
            ab_Factura3597.SalvarA_Factura();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(F_Reporte3597.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        for (Iterator <DetalleFactura_3597> it = A_Factura.iterator(); it.hasNext();){
            DetalleFactura_3597 f = it.next();
            
            DefaultTableModel modelo = (DefaultTableModel) TableFactura.getModel();
            Object[] fila = new Object[5];
            fila[0] = f.getCodigo();
            fila[1] = f.getFecha();
            fila[2] = f.getIDempleado();
            fila[3] = f.getNITcliente();
            fila[4] = String.format("%.2f", f.getTotal());
            modelo.addRow(fila);
        }
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableFactura = new javax.swing.JTable();
        bttBuscar = new javax.swing.JButton();
        bttHTML = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lbTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(204, 204, 204));
        lbTitulo.setText("REPORTE DE FACTURAS");
        jPanel1.add(lbTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        TableFactura.setBackground(new java.awt.Color(255, 255, 255));
        TableFactura.setForeground(new java.awt.Color(51, 0, 51));
        TableFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Correlativo", "Fecha", "ID Cajero", "NIT Cliente", "Total"
            }
        ));
        TableFactura.setSelectionBackground(new java.awt.Color(102, 0, 102));
        TableFactura.setSelectionForeground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(TableFactura);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 650, 270));

        bttBuscar.setBackground(new java.awt.Color(204, 204, 204));
        bttBuscar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        bttBuscar.setForeground(new java.awt.Color(51, 0, 51));
        bttBuscar.setText("Visualizar");
        bttBuscar.setBorder(null);
        bttBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(bttBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 140, 40));

        bttHTML.setBackground(new java.awt.Color(204, 204, 204));
        bttHTML.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        bttHTML.setForeground(new java.awt.Color(51, 0, 51));
        bttHTML.setText("Reporte HTML");
        bttHTML.setBorder(null);
        bttHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttHTMLActionPerformed(evt);
            }
        });
        jPanel1.add(bttHTML, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 230, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttBuscarActionPerformed
        String op = JOptionPane.showInputDialog("Ingrese correlativo de factura a buscar");
        
        DetalleFactura_3597 F = new DetalleFactura_3597(); 
        F.setCodigo(parseInt(op));
        
        ab_Factura3597.VisualizarFactura(F);
    }//GEN-LAST:event_bttBuscarActionPerformed

    private void bttHTMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttHTMLActionPerformed
        ab_Factura3597.AbrirURL(ab_Factura3597.Reporte());
    }//GEN-LAST:event_bttHTMLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableFactura;
    private javax.swing.JButton bttBuscar;
    private javax.swing.JButton bttHTML;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTitulo;
    // End of variables declaration//GEN-END:variables
}
