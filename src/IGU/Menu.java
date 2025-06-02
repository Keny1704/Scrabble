package IGU;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JButton;
import scrabble.ReproductorAudio;


public class Menu extends javax.swing.JFrame {
    private JLabel imagen;
    private int volumenState = 0;
    private JButton btnVolumen;
    
    public Menu() {
        initComponents();
        Dimension tamaño = Toolkit.getDefaultToolkit().getScreenSize();
        setTamañoBackground(tamaño, "/imagenes/menu.png");
        ReproductorAudio.getInstancia("src/audio/menu.wav");
        initVolumeButton();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    private void initVolumeButton() {
        javax.swing.UIManager.put("Button.background", new Color(0, 0, 0, 0));

        ImageIcon iconMax = new ImageIcon(getClass().getResource("/imagenes/volMax.png"));
        ImageIcon iconMin = new ImageIcon(getClass().getResource("/imagenes/volMin.png"));
        ImageIcon iconMute = new ImageIcon(getClass().getResource("/imagenes/volMute.png"));
        
        
        btnVolumen = new JButton(iconMax);
        // Ubica el botón en la esquina superior izquierda; ajústalo según tu diseño
        btnVolumen.setBounds(20, 20, 50, 50);
        btnVolumen.setBorderPainted(false);
        btnVolumen.setFocusPainted(false);
        
        btnVolumen.addActionListener(e -> {
            // Incrementamos el estado cíclicamente (0 -> 1 -> 2 -> 0)
            volumenState = (volumenState + 1) % 3;
            switch (volumenState) {
                case 0:
                    btnVolumen.setIcon(iconMax);
                    ReproductorAudio.getInstancia("src/audio/menu.wav").ajustarVolumen(1.0);
                    break;
                case 1:
                    btnVolumen.setIcon(iconMin);
                    ReproductorAudio.getInstancia("src/audio/menu.wav").ajustarVolumen(0.5);
                    break;
                case 2:
                    btnVolumen.setIcon(iconMute);
                    ReproductorAudio.getInstancia("src/audio/menu.wav").ajustarVolumen(0.0);
                    break;
            }
        });
        
        // Agregamos el botón al panel. Si el fondo está en un JLabel, es importante agregar el botón después
        jPanel1.add(btnVolumen);
        // Opcional: si el JLabel del fondo cubre todo el panel, puedes agregar el botón al fondo o ajustar el Z-order.
        btnVolumen.setVisible(true);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    
    public void setTamañoBackground(Dimension tamaño, String rutaImagen) {
        int ancho = (int) tamaño.getWidth();
        int alto = (int) tamaño.getHeight();
        ImageIcon fondo = new ImageIcon(getClass().getResource(rutaImagen));
        Image imagenEscalada = fondo.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon fondoEscalado = new ImageIcon(imagenEscalada);
        imagen = new JLabel(fondoEscalado);
        imagen.setBounds(0, 0, ancho, alto);
        jPanel1.add(imagen);
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
