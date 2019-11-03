/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panels;

import Clases.Punto;
import Clases.Ruta;
import DAO.DAOConveyance;
import DAO.DAOTravel;
import Model.Conveyance;
import Model.Travel;
import Model.User;
import com.mycompany.hacerdedo.punto0.SystemLogic;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sebaa
 */
public class DriverFrame extends javax.swing.JFrame {

    /**
     * Creates new form DriverFrame
     */
    private User user;
    private DAO.DAOTravel daoT = new DAOTravel();
    private DAOConveyance daoC = new DAOConveyance();
    
    private DefaultTableModel vehiculosModel;
    private DefaultTableModel viajesModel;
    public static DriverFrame df;
    
    public void setVehiculosModel(DefaultTableModel vehiculosModel) {
        this.vehiculosModel = vehiculosModel;
    }

    public void setViajesModel(DefaultTableModel viajesModel) {
        this.viajesModel = viajesModel;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public DriverFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //this.historyTable.removeAll();
        //this.vehiclesTable.removeAll();
    }
    
    public static DriverFrame initDriverFrame()
    {
        if (df == null) {
            df = new DriverFrame();
        }
        return null;
    }
    
    
    public void cargarTablaHistorial()
    {
        DefaultTableModel tm = (DefaultTableModel) this.historyTable.getModel();
        List<Travel> viajes = daoT.getAllFromUser(user.getCi());
        for (Travel viaje : viajes) {
            tm.addRow(new Object[]{viaje.getCi_driver(),viaje.getDate_ini(),
                                   viaje.getDate_end(),viaje.getProgress()});
        
        }
    }
    
    public void cargarTablaVehiculosChofer()
    {
        List<Conveyance> veh = daoC.getAllFromUser(user.getCi());
        if (!veh.isEmpty()) {
            DefaultTableModel tm = (DefaultTableModel) this.vehiclesTable.getModel();
            for (Conveyance vehiculo : veh) {
                tm.addRow(new Object[]{vehiculo.getMatricula(),vehiculo.getModel(),
                                            vehiculo.getColor(),vehiculo.getNumber_seats(),vehiculo.getType()});
            }
        }
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addVehicleButton = new javax.swing.JButton();
        initTravelButton = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vehiclesTable = new javax.swing.JTable();
        vehiculosLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        long_InicioTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lat_InicioTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        long_FinTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lat_FinTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        postearButton = new javax.swing.JButton();
        actualizarVehiculosButton = new javax.swing.JButton();
        vehiculosLabel1 = new javax.swing.JLabel();
        actualizarViajesButton = new javax.swing.JButton();

        addVehicleButton.setText("Añadir Vehiculo");
        addVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVehicleButtonActionPerformed(evt);
            }
        });

        initTravelButton.setText("Iniciar Viaje");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );

        vehiclesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Modelo", "Color", "Capacidad", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(vehiclesTable);

        vehiculosLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vehiculosLabel.setText("Vehiculos");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Posición Inicial");

        jLabel2.setText("Eje X:");

        jLabel3.setText("Eje Y:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Posición Final");

        jLabel5.setText("Eje X:");

        jLabel6.setText("Eje Y:");

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chofer", "Fecha Inicio", "Fecha Fin", "Progreso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(historyTable);

        postearButton.setText("Postear Viaje");
        postearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postearButtonActionPerformed(evt);
            }
        });

        actualizarVehiculosButton.setText("Actualizar");
        actualizarVehiculosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarVehiculosButtonActionPerformed(evt);
            }
        });

        vehiculosLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vehiculosLabel1.setText("Viajes");

        actualizarViajesButton.setText("Actualizar");
        actualizarViajesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarViajesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addVehicleButton)
                                    .addComponent(initTravelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(long_InicioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lat_InicioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(long_FinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lat_FinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(vehiculosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(actualizarVehiculosButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(vehiculosLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(actualizarViajesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(initTravelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(long_InicioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lat_InicioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(long_FinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lat_FinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(postearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vehiculosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actualizarVehiculosButton)
                    .addComponent(vehiculosLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actualizarViajesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVehicleButtonActionPerformed
        
        AddVehiclePanel.initAddVehiclePanel();
        AddVehiclePanel.initAddVehiclePanel().setUser(user);
        AddVehiclePanel.initAddVehiclePanel().cargaTablaVehiculos();
        AddVehiclePanel.initAddVehiclePanel().setVisible(true);
        
    }//GEN-LAST:event_addVehicleButtonActionPerformed

    private void postearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postearButtonActionPerformed
        
        if (this.vehiclesTable.getSelectedRow() != -1) {
            DefaultTableModel tm = (DefaultTableModel) this.vehiclesTable.getModel();
            Punto ini = new Punto(Double.parseDouble(this.long_InicioTextField.getText()),Double.parseDouble(this.lat_InicioTextField.getText()));
            Punto fin = new Punto(Double.parseDouble(this.long_FinTextField.getText()),Double.parseDouble(this.lat_FinTextField.getText()));
            SystemLogic.initSystemLogic().postDriverTravel((String)tm.getValueAt(this.vehiclesTable.getSelectedRow(), 1), new Ruta(ini,fin), user.getCi(),
                    Integer.parseInt((String)tm.getValueAt(this.vehiclesTable.getSelectedRow(), 4)));
            JOptionPane.showMessageDialog(this, "Posteado");
        } else {
            JOptionPane.showMessageDialog(this, "No ha seleccionado ningún vehículo");
        }

    }//GEN-LAST:event_postearButtonActionPerformed

    private void actualizarVehiculosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarVehiculosButtonActionPerformed
        DefaultTableModel tm = (DefaultTableModel) this.vehiclesTable.getModel();
        int numDatos = tm.getRowCount();
        for (int i = 0; i < numDatos; i++) {
            tm.removeRow(0);
        }
        this.cargarTablaVehiculosChofer();
    }//GEN-LAST:event_actualizarVehiculosButtonActionPerformed

    private void actualizarViajesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarViajesButtonActionPerformed
        //this.historyTable.removeAll();
        DefaultTableModel tm = (DefaultTableModel) this.historyTable.getModel();
        int numDatos = tm.getRowCount();
        for (int i = 0; i < numDatos; i++) {
            tm.removeRow(0);
        }
        //tm.setRowCount(0);
        this.cargarTablaHistorial();
    }//GEN-LAST:event_actualizarViajesButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DriverFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DriverFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DriverFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DriverFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DriverFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizarVehiculosButton;
    private javax.swing.JButton actualizarViajesButton;
    private javax.swing.JButton addVehicleButton;
    private javax.swing.JTable historyTable;
    private javax.swing.JButton initTravelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lat_FinTextField;
    private javax.swing.JTextField lat_InicioTextField;
    private javax.swing.JTextField long_FinTextField;
    private javax.swing.JTextField long_InicioTextField;
    private javax.swing.JButton postearButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTable vehiclesTable;
    private javax.swing.JLabel vehiculosLabel;
    private javax.swing.JLabel vehiculosLabel1;
    // End of variables declaration//GEN-END:variables
}
