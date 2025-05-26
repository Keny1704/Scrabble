package IGU;

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
        salirIntro = new javax.swing.JButton();
        jugarIntro = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scrabble");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(100, 100));
        setMaximumSize(new java.awt.Dimension(280, 420));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(280, 420));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salirIntro.setText("SALIR");
        salirIntro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 51), new java.awt.Color(255, 102, 102), java.awt.Color.magenta, java.awt.Color.orange));
        salirIntro.setBorderPainted(false);
        salirIntro.setMargin(new java.awt.Insets(12, 14, 12, 14));
        salirIntro.setMaximumSize(new java.awt.Dimension(100, 43));
        salirIntro.setMinimumSize(new java.awt.Dimension(100, 43));
        salirIntro.setPreferredSize(new java.awt.Dimension(100, 43));
        salirIntro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirIntroActionPerformed(evt);
            }
        });
        jPanel1.add(salirIntro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, -1, -1));

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/intro.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirIntroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirIntroActionPerformed
        dispose();
    }//GEN-LAST:event_salirIntroActionPerformed

    private void jugarIntroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarIntroActionPerformed
        Menu newFrame = new Menu();
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jugarIntroActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Intro().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jugarIntro;
    private javax.swing.JButton salirIntro;
    // End of variables declaration//GEN-END:variables
}
