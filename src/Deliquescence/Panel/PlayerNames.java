/*
 * Copyright (c) 2014, Deliquescence <Deliquescence1@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * - Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package Deliquescence.Panel;

import Deliquescence.Config;
import Deliquescence.Refreshable;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author Deliquescence <Deliquescence1@gmail.com>
 */
public class PlayerNames extends javax.swing.JPanel implements Refreshable {

    private JTextField[] playerTextFields;
    private int numPlayers;

    /**
     * Creates new form PlayerNames
     */
    public PlayerNames() {
        initComponents();

        Config.refreshables.add(this);
        refreshConfig();
    }

    /**
     * Gets the configured name of a player from its text field.
     *
     * @param ID The id of the players name to be found
     *
     * @return The name of the player
     */
    public String getPlayerName(int ID) {
        return playerTextFields[ID].getText();
    }

    private void makeTextFields() {
        fieldsPanel.removeAll();

        //Add player text boxes
        for (int i = 1; i <= numPlayers; i++) {
            final JTextField textField = new JTextField();
            playerTextFields[i] = textField;
            textField.setName(Integer.toString(i));
            textField.setText(Deliquescence.Config.getDefaultPlayerName(i));

            textField.setMaximumSize(new Dimension(400, 20));
            textField.setMinimumSize(new Dimension(400, 20));
            textField.setPreferredSize(new Dimension(400, 20));

            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (textField.getText().contains("Player ")) {
                        textField.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (textField.getText().equals("")) {
                        textField.setText("Player " + textField.getName());
                    }
                }
            });
            playerTextFields[i].setVisible(true);
            fieldsPanel.add(playerTextFields[i]);
        }
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

        fieldsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        fieldsPanel.setLayout(new javax.swing.BoxLayout(fieldsPanel, javax.swing.BoxLayout.Y_AXIS));
        add(fieldsPanel, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Player Names:");
        jLabel1.setToolTipText("");
        add(jLabel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fieldsPanel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refreshConfig() {
        try {
            numPlayers = Deliquescence.Config.getInt("MAX_PLAYERS");
        } catch (NullPointerException e) {//config not loaded
            numPlayers = 8;
        }
        playerTextFields = new JTextField[numPlayers + 1];
        makeTextFields();
    }
}