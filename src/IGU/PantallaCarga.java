package IGU;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class PantallaCarga extends javax.swing.JFrame {
    private float progreso = 0.0f;
    private BufferedImage imagen;
    private final JLabel labelImagen;
    
    public PantallaCarga() {
        initComponents();
        setTitle("Transición de Imagen");
        setSize(500, 71);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY);
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30)); // Bordes redondeados
        
        try {
            imagen = ImageIO.read(new File("src/imagenes/carga.png"));
            if (imagen == null) throw new IOException("Imagen no encontrada.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: No se pudo cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        labelImagen = new JLabel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int widthVisible = (int) (500 * progreso); // Revelando progresivamente de izquierda a derecha
                g2.drawImage(imagen, 0, 0, widthVisible, 71, 0, 0, widthVisible, 71, null);

            }
        };
        labelImagen.setPreferredSize(new Dimension(500, 71));

        add(labelImagen, BorderLayout.CENTER);
        setVisible(true);
        iniciarTransicion();
    }
    
        private void iniciarTransicion() {
            Timer timer = new Timer(50, e -> {
                if (progreso < 1.0f) { 
                    progreso += 0.53f;
                    labelImagen.repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                    Menu newFrame = new Menu();
                    newFrame.setVisible(true);
                    this.dispose();
                }
            });
        timer.start();
        }
        
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PantallaCarga::new);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
