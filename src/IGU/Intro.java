package IGU;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Intro extends javax.swing.JFrame {

    public Intro() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jugarIntro = new javax.swing.JButton();
        salirIntro1 = new IGU.botones.salirIntro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scrabble");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(100, 100));
        setMaximumSize(new java.awt.Dimension(280, 420));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(280, 420));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jugarIntro.setText("JUGAR");
        jugarIntro.setMargin(new java.awt.Insets(12, 14, 12, 14));
        jugarIntro.setMaximumSize(new java.awt.Dimension(100, 43));
        jugarIntro.setMinimumSize(new java.awt.Dimension(100, 43));
        jugarIntro.setPreferredSize(new java.awt.Dimension(100, 43));
        jugarIntro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarIntroActionPerformed(evt);
            }
        });
        jPanel1.add(jugarIntro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, -1));

        salirIntro1.setText("SALIR");
        salirIntro1.setRadio(50);
        salirIntro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirIntro1ActionPerformed(evt);
            }
        });
        jPanel1.add(salirIntro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 120, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jugarIntroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarIntroActionPerformed
        Menu newFrame = new Menu();
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jugarIntroActionPerformed

    private void salirIntro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirIntro1ActionPerformed
        dispose();
    }//GEN-LAST:event_salirIntro1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Intro().setVisible(true);
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jugarIntro;
    private IGU.botones.salirIntro salirIntro1;
    // End of variables declaration//GEN-END:variables
}
