package interfaces;

import core.SocketManager;
import core.listeners.ConnectionListener;
import core.listeners.MainMenuListener;
import inevaup.dialogs.InfoDialog;

public class MainMenu extends javax.swing.JFrame
        implements ConnectionListener, MainMenuListener {

    SocketManager socketManager;

    public MainMenu() {
        initComponents();

        socketManager = SocketManager.getSocketManager();
        socketManager.setConnectionListener(this);
        socketManager.setMainMenuListener(this);

        n.setValue(6);
        m.setValue(7);

    }

    @Override
    public void moveToLobby() {
        socketManager.setConnectionListener(null);
        Lobby lobby = new Lobby();
        lobby.setVisible(true);
        this.dispose();
    }

    @Override
    public void onConnection() {
        
    }

    @Override
    public void onDisconnection() {
        
    }
    
    boolean err = true;

    @Override
    public void onConnectionError() {
        if(err){
          InfoDialog infoDialog = new InfoDialog(
                this, 
                "Error", 
                "No se ha podido establecer conexión con el servidor",
                InfoDialog.TypeInfoDialog.ERROR_DIALOG
            );
            infoDialog.setVisible(true);
            err = false;
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        getIn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        username_label = new javax.swing.JLabel();
        username_texfield = new javax.swing.JTextField();
        roomname_textfield = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        username_label2 = new javax.swing.JLabel();
        n = new javax.swing.JSpinner();
        username_label1 = new javax.swing.JLabel();
        m = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 253, 255));
        jLabel1.setText("4");

        getIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/images/on.png"))); // NOI18N
        getIn.setBorderPainted(false);
        getIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getInMouseClicked(evt);
            }
        });
        getIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getInActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Unite     win");

        username_label.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        username_label.setForeground(new java.awt.Color(255, 255, 255));
        username_label.setText("Nickname:");

        username_texfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_texfieldActionPerformed(evt);
            }
        });

        roomname_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomname_textfieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sala:");

        username_label2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        username_label2.setForeground(new java.awt.Color(255, 255, 255));
        username_label2.setText("# filas");

        n.setFocusable(false);
        n.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nStateChanged(evt);
            }
        });
        n.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nMouseClicked(evt);
            }
        });
        n.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                nInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        username_label1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        username_label1.setForeground(new java.awt.Color(255, 255, 255));
        username_label1.setText("# columnas");

        m.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(username_label2)
                                .addGap(18, 18, 18)
                                .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(username_label1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(354, 354, 354)
                                .addComponent(getIn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roomname_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(username_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(username_texfield, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username_label)
                    .addComponent(username_texfield, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(roomname_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username_label2)
                    .addComponent(username_label1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(86, 86, 86)
                .addComponent(getIn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mStateChanged
        if (Integer.parseInt(m.getValue().toString()) < 0) {

            m.setValue(0);
        }
    }//GEN-LAST:event_mStateChanged

    private void nInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nInputMethodTextChanged

    }//GEN-LAST:event_nInputMethodTextChanged

    private void nMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nMouseClicked

    }//GEN-LAST:event_nMouseClicked

    private void nStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nStateChanged
        if (Integer.parseInt(n.getValue().toString()) < 0) {

            n.setValue(0);
        }
    }//GEN-LAST:event_nStateChanged

    private void roomname_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomname_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomname_textfieldActionPerformed

    private void username_texfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_texfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_texfieldActionPerformed

    private void getInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getInActionPerformed

    }//GEN-LAST:event_getInActionPerformed

    private void getInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getInMouseClicked

        boolean st1 = ((Integer.parseInt(n.getValue().toString()) >= 6
            && Integer.parseInt(n.getValue().toString()) <= 8)
        && (Integer.parseInt(m.getValue().toString()) >= 6
            && Integer.parseInt(m.getValue().toString()) <= 8)), st2 = (username_texfield.getText().length() >= 2 && username_texfield.getText().length() <= 8 );

    if (st1 &&  st2) {

        socketManager.initSocket(
            username_texfield.getText(),
            roomname_textfield.getText(),
            Integer.parseInt(n.getValue().toString()),
            Integer.parseInt(m.getValue().toString())
        );

        err = true;
        } else if (!st1){

            InfoDialog infoDialog = new InfoDialog(
                this,
                "Error",
                "La cantidad de filas y/o columnas es muy grande o muy pequeña, debes ingresar "
                + "un valor entre 6 y 8",
                InfoDialog.TypeInfoDialog.ERROR_DIALOG
            );
            infoDialog.setVisible(true);

        } else if(!st2){
            InfoDialog infoDialog2 = new InfoDialog(
                this,
                "Error",
                "El nombre de usuario debe estar entre 2 y 8 caracteres",
                InfoDialog.TypeInfoDialog.ERROR_DIALOG
            );
            infoDialog2.setVisible(true);

        }

    }//GEN-LAST:event_getInMouseClicked

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton getIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner m;
    private javax.swing.JSpinner n;
    private javax.swing.JTextField roomname_textfield;
    private javax.swing.JLabel username_label;
    private javax.swing.JLabel username_label1;
    private javax.swing.JLabel username_label2;
    private javax.swing.JTextField username_texfield;
    // End of variables declaration//GEN-END:variables
}
