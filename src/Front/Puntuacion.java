package Front;

import Back.Jugador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Puntuacion extends javax.swing.JFrame {

    private JLabel jugador1;
    private JLabel jugador2;
    private JLabel fichasRestantes;
    private String nombre1;
    private String nombre2;
    private String a = "Jugador 1:  0 puntos", b = "Jugador 2:  0 puntos";

    public Puntuacion() {
        initBasicComponents();
        setUndecorated(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initBasicComponents() {
        setSize(200, 100);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));

        jugador1 = new JLabel("Jugador  1:  0 puntos");
        jugador2 = new JLabel("Jugador  2:  0 puntos");
        fichasRestantes = new JLabel("Fichas restantes:  100");

        jugador1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        jugador2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        fichasRestantes.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        panel.add(jugador1);
        panel.add(jugador2);
        panel.add(fichasRestantes);
        add(panel);

        JMenuBar menuBar = new JMenuBar();
        JMenuItem menuGuardar = new JMenuItem("Guardar");
        menuGuardar.setBackground(Color.LIGHT_GRAY);
        menuGuardar.addActionListener((ActionEvent e) -> {

            JOptionPane.showMessageDialog(Puntuacion.this,
                    "Función Guardar no desarrollada.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        });

        JMenuItem menuCargar = new JMenuItem("Cargar");
        menuCargar.setBackground(Color.LIGHT_GRAY);
        menuCargar.addActionListener((ActionEvent e) -> {

            JOptionPane.showMessageDialog(Puntuacion.this,
                    "Función Cargar no desarrollada.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        });

        menuBar.add(menuGuardar);
        menuBar.add(menuCargar);
        setJMenuBar(menuBar);
    }

    public void setPuntos1(int puntos) {
            jugador1.setText(nombre1 + ":  " + puntos + " puntos");
            a = "" + nombre1 + ":  " + puntos + " puntos";
            jugador2.setText("> " + b + "");
    }

    public void setPuntos2(int puntos) {
            jugador2.setText(nombre2 + ":  " + puntos + " puntos");
            b = "" + nombre2 + ":  " + puntos + " puntos";
            jugador1.setText("> " + a + "");
    }

    public void setNombre1(String texto) {
        nombre1 = texto;
        setPuntos1(0);
    }

    public void setNombre2(String texto) {
        nombre2 = texto;
        setPuntos2(0);
    }

    public void setfichasRestantes(int fichasR) {
        fichasRestantes.setText("Fichas restantes:  " + fichasR);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Puntuacion().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
