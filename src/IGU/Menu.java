package IGU;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


public class Menu extends javax.swing.JFrame {
    private Clip audio;
    private JLabel imagen;
    
    public Menu() {
        initComponents();
        Dimension tamaño = Toolkit.getDefaultToolkit().getScreenSize();
        setTamañoBackground(tamaño, "/imagenes/menu.png");
        this.setExtendedState(MAXIMIZED_BOTH);
        reproducirAudio("src/audio/menu.wav");
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void reproducirAudio(String rutaAudio) {
        try {
            File archivo = new File(rutaAudio);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            audio = AudioSystem.getClip();
            audio.open(audioStream);
            audio.loop(Clip.LOOP_CONTINUOUSLY);
            audio.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
