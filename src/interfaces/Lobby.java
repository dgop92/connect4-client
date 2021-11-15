package interfaces;

import core.C4Events;
import core.SocketManager;
import core.dataclasses.LobbyStatus;
import core.listeners.LobbyListener;
import game.GameSketch;
import java.awt.Color;

public class Lobby extends javax.swing.JFrame implements LobbyListener {

    SocketManager socketManager;
    String player1, player2;

    public Lobby() {
        initComponents();

        socketManager = SocketManager.getSocketManager();
        socketManager.setLobbyListener(this);

        player1_panel.setVisible(false);
        player2_panel.setVisible(false);

    }

    @Override
    public void onNewPlayer(LobbyStatus lobbyStatus) {
        if (lobbyStatus.getNumberOfClients() == 1) {

            player1_panel.setVisible(true);
            player1_name.setText(lobbyStatus.getPlayers().get(0).toString());
            
            
            if(lobbyStatus.getPlayers().get(0).getColorAsString().equalsIgnoreCase("Red"))  player1_panel.setBackground(Color.red.darker());
            if(lobbyStatus.getPlayers().get(0).getColorAsString().equalsIgnoreCase("Green"))  player1_panel.setBackground(Color.green.darker());
            if(lobbyStatus.getPlayers().get(0).getColorAsString().equalsIgnoreCase("BLue"))  player1_panel.setBackground(Color.blue.darker());
            if(lobbyStatus.getPlayers().get(0).getColorAsString().equalsIgnoreCase("Orange"))  player1_panel.setBackground(Color.orange.darker());
            
            System.out.println(lobbyStatus.getPlayers().get(0).toString());

        }

        if (lobbyStatus.getNumberOfClients() == 2) {
            
            player1_panel.setVisible(true);
            player1_name.setText(lobbyStatus.getPlayers().get(0).toString());
            
            if(lobbyStatus.getPlayers().get(0).getColorAsString().equalsIgnoreCase("Red"))  player1_panel.setBackground(Color.red.darker());
            if(lobbyStatus.getPlayers().get(0).getColorAsString().equalsIgnoreCase("Green"))  player1_panel.setBackground(Color.green.darker());
            if(lobbyStatus.getPlayers().get(0).getColorAsString().equalsIgnoreCase("BLue"))  player1_panel.setBackground(Color.blue.darker());
            if(lobbyStatus.getPlayers().get(0).getColorAsString().equalsIgnoreCase("Orange"))  player1_panel.setBackground(Color.orange.darker());
            
            if(lobbyStatus.getPlayers().get(1).getColorAsString().equalsIgnoreCase("Red"))  player2_panel.setBackground(Color.red.darker());
            if(lobbyStatus.getPlayers().get(1).getColorAsString().equalsIgnoreCase("Green"))  player2_panel.setBackground(Color.green.darker());
            if(lobbyStatus.getPlayers().get(1).getColorAsString().equalsIgnoreCase("BLue"))  player2_panel.setBackground(Color.blue.darker());
            if(lobbyStatus.getPlayers().get(1).getColorAsString().equalsIgnoreCase("Orange"))  player2_panel.setBackground(Color.orange.darker());

            player2_panel.setVisible(true);
            player2_name.setText(lobbyStatus.getPlayers().get(1).toString());

        }

    }
    
    
     

    @Override
    public void onPlayerLeave(LobbyStatus lobbyStatus) {
        System.out.println(lobbyStatus.getNumberOfClients());
        
        if(lobbyStatus.getPlayers().get(0) == null) player1_panel.setVisible(false); 
        if(lobbyStatus.getPlayers().get(1) == null) player2_panel.setVisible(false);
        
        
        
    }

    @Override
    public void onGameStarted() {

        socketManager.setLobbyListener(null);
        GameSketch gameSketch = new GameSketch();
        gameSketch.run();
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        username_label = new javax.swing.JLabel();
        player2_panel = new javax.swing.JPanel();
        player2_name = new javax.swing.JLabel();
        player1_panel = new javax.swing.JPanel();
        player1_name = new javax.swing.JLabel();
        start_button = new javax.swing.JButton();
        username_label3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Unite     win");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(320, 80, 152, 40);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(31, 137, 234));
        jLabel1.setText("4");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(400, 80, 17, 40);

        username_label.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        username_label.setForeground(new java.awt.Color(255, 255, 255));
        username_label.setText("Wellcome to the lobby");
        jPanel1.add(username_label);
        username_label.setBounds(310, 170, 190, 26);

        player2_name.setForeground(new java.awt.Color(255, 255, 255));
        player2_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout player2_panelLayout = new javax.swing.GroupLayout(player2_panel);
        player2_panel.setLayout(player2_panelLayout);
        player2_panelLayout.setHorizontalGroup(
            player2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player2_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        player2_panelLayout.setVerticalGroup(
            player2_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player2_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel1.add(player2_panel);
        player2_panel.setBounds(484, 238, 100, 26);

        player1_name.setBackground(new java.awt.Color(255, 255, 255));
        player1_name.setForeground(new java.awt.Color(255, 255, 255));
        player1_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout player1_panelLayout = new javax.swing.GroupLayout(player1_panel);
        player1_panel.setLayout(player1_panelLayout);
        player1_panelLayout.setHorizontalGroup(
            player1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player1_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        player1_panelLayout.setVerticalGroup(
            player1_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player1_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel1.add(player1_panel);
        player1_panel.setBounds(304, 238, 100, 26);

        start_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/images/start_buttom.png"))); // NOI18N
        start_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_buttonActionPerformed(evt);
            }
        });
        jPanel1.add(start_button);
        start_button.setBounds(350, 360, 100, 99);

        username_label3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        username_label3.setForeground(new java.awt.Color(255, 255, 255));
        username_label3.setText("Players:");
        jPanel1.add(username_label3);
        username_label3.setBounds(221, 238, 65, 26);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void start_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_buttonActionPerformed

        socketManager.getSocket().emit(C4Events.REQUEST_START_GAME);


    }//GEN-LAST:event_start_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lobby().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel player1_name;
    private javax.swing.JPanel player1_panel;
    private javax.swing.JLabel player2_name;
    private javax.swing.JPanel player2_panel;
    private javax.swing.JButton start_button;
    private javax.swing.JLabel username_label;
    private javax.swing.JLabel username_label3;
    // End of variables declaration//GEN-END:variables

}
