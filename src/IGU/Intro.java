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
        salirIntro1 = new IGU.botones.salirIntro();
        jugarIntro1 = new IGU.botones.jugarIntro();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scrabble");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(100, 100));
        setMaximumSize(new java.awt.Dimension(280, 420));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(280, 420));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salirIntro1.setBorder(null);
        salirIntro1.setForeground(new java.awt.Color(164, 14, 14));
        salirIntro1.setText("SALIR");
        salirIntro1.setColor(new java.awt.Color(255, 151, 151));
        salirIntro1.setFont(new java.awt.Font("Jokerman", 1, 18)); // NOI18N
        salirIntro1.setRadio(50);
        salirIntro1.setRequestFocusEnabled(false);
        salirIntro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirIntro1ActionPerformed(evt);
            }
        });
        jPanel1.add(salirIntro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 330, 90, 50));

        jugarIntro1.setBorder(null);
        jugarIntro1.setForeground(new java.awt.Color(14, 84, 164));
        jugarIntro1.setText("JUGAR");
        jugarIntro1.setColor(new java.awt.Color(145, 198, 246));
        jugarIntro1.setFont(new java.awt.Font("Jokerman", 1, 18)); // NOI18N
        jugarIntro1.setRadio(50);
        jugarIntro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarIntro1ActionPerformed(evt);
            }
        });
        jPanel1.add(jugarIntro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 270, 90, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/intro.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirIntro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirIntro1ActionPerformed
        dispose();
    }//GEN-LAST:event_salirIntro1ActionPerformed

    private void jugarIntro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarIntro1ActionPerformed
        Menu newFrame = new Menu();
        newFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jugarIntro1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Intro().setVisible(true);
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private IGU.botones.jugarIntro jugarIntro1;
    private IGU.botones.salirIntro salirIntro1;
    // End of variables declaration//GEN-END:variables
}
