package Front;

import Back.Ficha;
import Back.Tablero;
import Back.Jugador;
import Back.CasillaBoton;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class VentanaTablero extends javax.swing.JFrame {
    
    public VentanaTablero(){
        setTitle("Scrabble (Keny & Thiago)");
    }
    
    public static java.awt.Color BOTON_COLOR_ORIGINAL;
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        botonNuevoJuego = new javax.swing.JButton();
        botonRack1 = new javax.swing.JButton();
        botonRack2 = new javax.swing.JButton();
        botonRack3 = new javax.swing.JButton();
        botonRack4 = new javax.swing.JButton();
        botonRack5 = new javax.swing.JButton();
        botonRack6 = new javax.swing.JButton();
        jugador1Label = new javax.swing.JLabel();
        puntosJugador1Label = new javax.swing.JLabel();
        terminarTurnoBoton = new javax.swing.JButton();
        jugador2Label = new javax.swing.JLabel();
        botonRack7 = new javax.swing.JButton();
        verificarBoton = new javax.swing.JButton();
        puntosJugador2Label = new javax.swing.JLabel();
        tableroBasePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(606, 800));
        setResizable(false);

        controlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        controlPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controlPanel.setPreferredSize(new java.awt.Dimension(616, 151));

        botonNuevoJuego.setText("Nuevo Juego");
        botonNuevoJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoJuegoActionPerformed(evt);
            }
        });

        botonRack1.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRack1ActionPerformed(evt);
            }
        });

        botonRack2.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRack2ActionPerformed(evt);
            }
        });

        botonRack3.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRack3ActionPerformed(evt);
            }
        });

        botonRack4.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRack4ActionPerformed(evt);
            }
        });

        botonRack5.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRack5ActionPerformed(evt);
            }
        });

        botonRack6.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRack6ActionPerformed(evt);
            }
        });

        jugador1Label.setText("Jugador 1");

        puntosJugador1Label.setText("0 puntos");

        terminarTurnoBoton.setText("Terminar Turno");
        terminarTurnoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarTurnoBotonActionPerformed(evt);
            }
        });

        jugador2Label.setText("Jugador 2");

        botonRack7.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRack7ActionPerformed(evt);
            }
        });

        verificarBoton.setText("Verificar");
        verificarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verificarBotonActionPerformed(evt);
            }
        });

        puntosJugador2Label.setText("0 puntos");
        puntosJugador2Label.setMaximumSize(new java.awt.Dimension(150, 20));
        puntosJugador2Label.setMinimumSize(new java.awt.Dimension(150, 20));
        puntosJugador2Label.setPreferredSize(new java.awt.Dimension(150, 20));

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonNuevoJuego)
                            .addComponent(terminarTurnoBoton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jugador1Label)
                            .addComponent(jugador2Label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlPanelLayout.createSequentialGroup()
                                .addComponent(puntosJugador1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(345, 345, 345)
                                .addComponent(verificarBoton))
                            .addGroup(controlPanelLayout.createSequentialGroup()
                                .addComponent(puntosJugador2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addGap(399, 399, 399)))
                        .addGap(10, 10, 10))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(botonRack1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonRack2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonRack3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonRack4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonRack5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonRack6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonRack7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonNuevoJuego)
                            .addComponent(jugador1Label)
                            .addComponent(puntosJugador1Label)))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(verificarBoton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jugador2Label)
                    .addComponent(terminarTurnoBoton)
                    .addComponent(puntosJugador2Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(botonRack1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonRack2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonRack3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonRack5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonRack4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botonRack7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRack6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        tableroBasePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableroBasePanel.setPreferredSize(new java.awt.Dimension(616, 600));
        tableroBasePanel.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableroBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tableroBasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, 153, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void botonRack7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRack7ActionPerformed
    }//GEN-LAST:event_botonRack7ActionPerformed

    private void botonRack6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRack6ActionPerformed
    }//GEN-LAST:event_botonRack6ActionPerformed

    private void botonRack5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRack5ActionPerformed
    }//GEN-LAST:event_botonRack5ActionPerformed

    private void botonRack4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRack4ActionPerformed
    }//GEN-LAST:event_botonRack4ActionPerformed

    private void botonRack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRack3ActionPerformed
    }//GEN-LAST:event_botonRack3ActionPerformed

    private void botonRack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRack2ActionPerformed
    }//GEN-LAST:event_botonRack2ActionPerformed

    private void botonRack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRack1ActionPerformed
    }//GEN-LAST:event_botonRack1ActionPerformed
         
    private void botonNuevoJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoJuegoActionPerformed
            
    }//GEN-LAST:event_botonNuevoJuegoActionPerformed
   
    private void verificarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verificarBotonActionPerformed
        
    }//GEN-LAST:event_verificarBotonActionPerformed

    private void terminarTurnoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarTurnoBotonActionPerformed
        
    }//GEN-LAST:event_terminarTurnoBotonActionPerformed

    public void initBasicComponents(Tablero tablero) {
        panelTablero = new JPanel();
        controlPanel = new javax.swing.JPanel();
        botonNuevoJuego = new javax.swing.JButton();
        BOTON_COLOR_ORIGINAL = botonNuevoJuego.getBackground();
        botonRack1 = new CasillaBoton();
        botonRack2 = new CasillaBoton();
        botonRack3 = new CasillaBoton();
        botonRack4 = new CasillaBoton();
        botonRack5 = new CasillaBoton();
        botonRack6 = new CasillaBoton();
        botonRack7 = new CasillaBoton();
        botonRack = new ArrayList<>();
        listaCasillaBoton = new ArrayList<>();
        jugador1Label = new javax.swing.JLabel();
        puntosJugador1Label = new javax.swing.JLabel();
        jugador2Label = new javax.swing.JLabel();
        puntosJugador2Label = new javax.swing.JLabel();
        terminarTurnoBoton = new javax.swing.JButton();
        verificarBoton = new javax.swing.JButton();
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(628, 780));
        setResizable(true);
        setLocationRelativeTo(null);
        setResizable(false);
        
        
        jugador1Label.setText("Jugador 1:");
        puntosJugador1Label.setText("0 puntos");

        jugador2Label.setText("Jugador 2:");
        puntosJugador2Label.setText("0 puntos");

        terminarTurnoBoton.setText("Terminar turno");

        botonRack1.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack1.setEnabled(false);
        botonRack2.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack2.setEnabled(false);
        botonRack3.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack3.setEnabled(false);
        botonRack4.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack4.setEnabled(false);
        botonRack5.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack5.setEnabled(false);
        botonRack6.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack6.setEnabled(false);
        botonRack7.setPreferredSize(new java.awt.Dimension(80, 62));
        botonRack7.setEnabled(false);

        botonRack.add((CasillaBoton) botonRack1);
        botonRack.add((CasillaBoton) botonRack2);
        botonRack.add((CasillaBoton) botonRack3);
        botonRack.add((CasillaBoton) botonRack4);
        botonRack.add((CasillaBoton) botonRack5);
        botonRack.add((CasillaBoton) botonRack6);
        botonRack.add((CasillaBoton) botonRack7);

        panelTablero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelTablero.setPreferredSize(new java.awt.Dimension(616, 600));
        panelTablero.setLayout(new java.awt.GridLayout(0, 15));

        controlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        controlPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        controlPanel.setPreferredSize(new java.awt.Dimension(616, 151));

        botonNuevoJuego.setText("Nuevo Juego");
        verificarBoton.setText("Verificar");

        for (int i = 0; i < tablero.casillas.size(); i++) {

            listaCasillaBoton.add(new CasillaBoton());

            listaCasillaBoton.get(i).setText(tablero.casillas.get(i).getLabel());
            if (tablero.casillas.get(i).getLabel().equalsIgnoreCase("3xP")) {
                listaCasillaBoton.get(i).setBackground(new Color(241, 141, 64));
            } else if (tablero.casillas.get(i).getLabel().equalsIgnoreCase("3xL")) {
                listaCasillaBoton.get(i).setBackground(new Color(69, 81, 232));
            } else if (tablero.casillas.get(i).getLabel().equalsIgnoreCase("2xP")) {
                listaCasillaBoton.get(i).setBackground(new Color(255, 143, 226));
            } else if (tablero.casillas.get(i).getLabel().equalsIgnoreCase("2xL")) {
                listaCasillaBoton.get(i).setBackground(new Color(134, 220, 255));
            }
            

            listaCasillaBoton.get(i).setMargin(new Insets(0, 0, 0, 0));
            listaCasillaBoton.get(i).setCasillaAsignada(tablero.casillas.get(i));

            if (i == 112) {
                listaCasillaBoton.get(i).setBackground(new Color(229, 65, 176));
                listaCasillaBoton.get(i).getCasillaAsignada().setValidacion(true);
            }

            if (listaCasillaBoton.get(i).getCasillaAsignada().getValido()) {
                listaCasillaBoton.get(i).setEnabled(true);
            } else {
                listaCasillaBoton.get(i).setEnabled(false);
            }
            panelTablero.add(listaCasillaBoton.get(i));

        }
        
        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
                controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(controlPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(botonNuevoJuego)
                                                .addComponent(terminarTurnoBoton))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jugador1Label)
                                                .addComponent(jugador2Label))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(puntosJugador2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(puntosJugador1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(verificarBoton))
                                .addGroup(controlPanelLayout.createSequentialGroup()
                                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(controlPanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(botonRack1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(botonRack2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(botonRack3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(botonRack4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(botonRack5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(botonRack6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(botonRack7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(controlPanelLayout.createSequentialGroup()
                                                        .addGap(12, 12, 12)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlPanelLayout.setVerticalGroup(
                controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(controlPanelLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(botonNuevoJuego)
                                                .addComponent(jugador1Label)
                                                .addComponent(puntosJugador1Label)))
                                .addGroup(controlPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(verificarBoton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jugador2Label)
                                .addComponent(puntosJugador2Label)
                                .addComponent(terminarTurnoBoton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(botonRack4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(botonRack1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botonRack2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botonRack3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botonRack5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botonRack6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(botonRack7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(panelTablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }

    public void añadirBotonJuegoNuevoActionListener(ActionListener listener) {
        botonNuevoJuego.addActionListener(listener);
    }

    public void añadirBotonVerificarActionListener(ActionListener listener) {
        verificarBoton.addActionListener(listener);
    }

    public void añadirBotonTerminarTurnoActionListener(ActionListener listener) {
        terminarTurnoBoton.addActionListener(listener);
    }

    public void añadirBotonRack1ActionListener(ActionListener listener) {
        botonRack1.addActionListener(listener);
    }

    public void añadirBotonRack2ActionListener(ActionListener listener) {
        botonRack2.addActionListener(listener);
    }

    public void añadirBotonRack3ActionListener(ActionListener listener) {
        botonRack3.addActionListener(listener);
    }

    public void añadirBotonRack4ActionListener(ActionListener listener) {
        botonRack4.addActionListener(listener);
    }

    public void añadirBotonRack5ActionListener(ActionListener listener) {
        botonRack5.addActionListener(listener);
    }

    public void añadirBotonRack6ActionListener(ActionListener listener) {
        botonRack6.addActionListener(listener);
    }

    public void añadirBotonRack7ActionListener(ActionListener listener) {
        botonRack7.addActionListener(listener);
    }

    public void añadirListaCasillaBotonActionListener(ActionListener listener) {
        for (int i = 0; i < 225; i++) {
            listaCasillaBoton.get(i).addActionListener(listener);
        }
    }

    public void actualizarRack(Jugador jugador) throws IndexOutOfBoundsException {
        for (CasillaBoton botonActual : botonRack) {
            botonActual.setVisible(false);
        }

        for (int i = 0; i < jugador.getRack().size(); i++) {
            Ficha fichaAsignada = jugador.getRack().get(i);

            if (fichaAsignada.getPuntos() == 0) {
                fichaAsignada.setLetra(" ");
            }

            botonRack.get(i).setFichaAsignada(fichaAsignada);
            botonRack.get(i).setVisible(true);
            botonRack.get(i).setEnabled(true);

        }
    }

    public void actualizarTablero(Tablero tablero) {
        for (int i = 0; i < tablero.casillas.size(); i++) {

            if (tablero.casillas.get(i).getFichaAsignada() == null) {

                listaCasillaBoton.get(i).setText(tablero.casillas.get(i).getLabel());
            if (tablero.casillas.get(i).getLabel().equalsIgnoreCase("3xP")) {
                listaCasillaBoton.get(i).setBackground(new Color(241, 141, 64));
            } else if (tablero.casillas.get(i).getLabel().equalsIgnoreCase("3xL")) {
                listaCasillaBoton.get(i).setBackground(new Color(69, 81, 232));
            } else if (tablero.casillas.get(i).getLabel().equalsIgnoreCase("2xP")) {
                listaCasillaBoton.get(i).setBackground(new Color(255, 143, 226));
            } else if (tablero.casillas.get(i).getLabel().equalsIgnoreCase("2xL")) {
                listaCasillaBoton.get(i).setBackground(new Color(134, 220, 255));
            }
            
                if (i == 112) {
                    listaCasillaBoton.get(i).setBackground(new Color(229, 65, 176));
                }
            }

            if (tablero.casillas.get(i).getValido()) {
                listaCasillaBoton.get(i).setEnabled(true);
            } else {
                listaCasillaBoton.get(i).setEnabled(false);
            }

        }
    }

    public void setBotonRack(ArrayList<CasillaBoton> newButtonRack) {
        botonRack = newButtonRack;
    }

    public ArrayList<CasillaBoton> getBotonRack() {
        return botonRack;
    }

    public ArrayList<CasillaBoton> getCasillaBotonList() {
        return listaCasillaBoton;
    }

    public void setBotonRack1Enabled(boolean enabled) {
        botonRack1.setEnabled(enabled);
    }

    public void setBotonRack2Enabled(boolean enabled) {
        botonRack2.setEnabled(enabled);
    }

    public void setBotonRack3Enabled(boolean enabled) {
        botonRack3.setEnabled(enabled);
    }

    public void setBotonRack4Enabled(boolean enabled) {
        botonRack4.setEnabled(enabled);
    }

    public void setBotonRack5Enabled(boolean enabled) {
        botonRack5.setEnabled(enabled);
    }

    public void setBotonRack6Enabled(boolean enabled) {
        botonRack6.setEnabled(enabled);
    }

    public void setBotonRack7Enabled(boolean enabled) {
        botonRack7.setEnabled(enabled);
    }

    public void setBotonRack1bg(java.awt.Color color) {
        botonRack1.setBackground(color);
    }

    public void setBotonRack2bg(java.awt.Color color) {
        botonRack2.setBackground(color);
    }

    public void setBotonRack3bg(java.awt.Color color) {
        botonRack3.setBackground(color);
    }

    public void setBotonRack4bg(java.awt.Color color) {
        botonRack4.setBackground(color);
    }

    public void setBotonRack5bg(java.awt.Color color) {
        botonRack5.setBackground(color);
    }

    public void setBotonRack6bg(java.awt.Color color) {
        botonRack6.setBackground(color);
    }

    public void setBotonRack7bg(java.awt.Color color) {
        botonRack7.setBackground(color);
    }

    public void setBotonJuegoNuevo1Establecido(boolean establecido) {
        botonNuevoJuego.setEnabled(establecido);
    }

    public void setVerificarBotonEstablecido(boolean establecido) {
        verificarBoton.setEnabled(establecido);
    }

    public void setPuntosJugador1(int nuevaPuntuacion) {
        puntosJugador1Label.setText(String.valueOf(nuevaPuntuacion) + " puntos");
    }

    public void setPuntosJugador2(int nuevaPuntuacion) {
        puntosJugador2Label.setText(String.valueOf(nuevaPuntuacion) + " puntos");
    }
    
    public void setNombreJugador1(String nombre) {
        jugador1Label.setText(nombre + ":");
    }
    
    public void setNombreJugador2(String nombre) {
        jugador2Label.setText(nombre + ":");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonNuevoJuego;
    private javax.swing.JButton botonRack1;
    private javax.swing.JButton botonRack2;
    private javax.swing.JButton botonRack3;
    private javax.swing.JButton botonRack4;
    private javax.swing.JButton botonRack5;
    private javax.swing.JButton botonRack6;
    private javax.swing.JButton botonRack7;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel jugador1Label;
    private javax.swing.JLabel jugador2Label;
    private javax.swing.JLabel puntosJugador1Label;
    private javax.swing.JLabel puntosJugador2Label;
    private javax.swing.JPanel tableroBasePanel;
    private javax.swing.JButton terminarTurnoBoton;
    private javax.swing.JButton verificarBoton;
    // End of variables declaration//GEN-END:variables

    //custom component variables
    private javax.swing.JPanel panelTablero;
    private ArrayList<CasillaBoton> botonRack;
    private ArrayList<CasillaBoton> listaCasillaBoton;

}