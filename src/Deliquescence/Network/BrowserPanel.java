/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deliquescence.Network;

import Deliquescence.Config;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.DefaultListModel;
import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;

/**
 *
 * @author Josh
 */
public class BrowserPanel extends javax.swing.JPanel {

    DefaultListModel listModel;

    /**
     * Creates new form LANBrowserPanel
     */
    public BrowserPanel() {
        initComponents();

        //Socket testSocket = new Socket(addr, Config.getInt("LAN_PORT"));
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        ServerAddressFieldLabel = new javax.swing.JLabel();
        ServerAddressField = new javax.swing.JTextField();
        JoinServerButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        RefreshButton = new javax.swing.JButton();
        JoinLANButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listModel = new DefaultListModel();
        GameList = GameList = new javax.swing.JList(listModel);

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel2.setMinimumSize(new java.awt.Dimension(203, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 40));
        jPanel2.setLayout(new java.awt.BorderLayout());

        ServerAddressFieldLabel.setText("Server Address, with port (Default 22222)");
        ServerAddressFieldLabel.setMaximumSize(new java.awt.Dimension(203, 10));
        ServerAddressFieldLabel.setMinimumSize(new java.awt.Dimension(203, 10));
        ServerAddressFieldLabel.setPreferredSize(new java.awt.Dimension(203, 10));
        jPanel2.add(ServerAddressFieldLabel, java.awt.BorderLayout.PAGE_START);

        ServerAddressField.setText(":22222");
        ServerAddressField.setMaximumSize(new java.awt.Dimension(2147483647, 20));
        ServerAddressField.setPreferredSize(new java.awt.Dimension(40, 10));
        jPanel2.add(ServerAddressField, java.awt.BorderLayout.CENTER);

        JoinServerButton.setText("Join");
        jPanel2.add(JoinServerButton, java.awt.BorderLayout.LINE_END);

        add(jPanel2);

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 20));
        jPanel1.setLayout(new java.awt.GridLayout());

        RefreshButton.setText("Refresh");
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });
        jPanel1.add(RefreshButton);

        JoinLANButton.setText("Join Selected Game");
        jPanel1.add(JoinLANButton);

        add(jPanel1);

        jLabel1.setText("LAN Games");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(jLabel1);

        GameList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(GameList);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void joinGame() {

    }

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed

        GameList.removeAll();
        String local;
        Integer[] LocalIP = new Integer[4];

        try {
            local = InetAddress.getLocalHost().getHostAddress();
            String[] IPstr = local.split("\\.");
            for (int i = 0; i < 4; i++) {
                LocalIP[i] = Integer.valueOf(IPstr[i]);
            }
        } catch (UnknownHostException ex) {
            local = "Network Error";
            return;
        }
        SubnetUtils utils = new SubnetUtils(local, "255.255.255.0");
        SubnetInfo info = utils.getInfo();

        try {
            for (String ip : info.getAllAddresses()) {
                InetAddress addr = InetAddress.getByName(ip);
                if (addr.isReachable(10)) {
                    Socket socket = new Socket(addr, Config.getInt("LAN_PORT"));
                }

//                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//                oos.writeObject("ping");
//
//                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//                String response = (String) ois.readObject();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_RefreshButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList GameList;
    private javax.swing.JButton JoinLANButton;
    private javax.swing.JButton JoinServerButton;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JTextField ServerAddressField;
    private javax.swing.JLabel ServerAddressFieldLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}