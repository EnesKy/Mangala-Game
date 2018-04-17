package Mangala;

import MangalaCl.Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class Entry extends javax.swing.JFrame {
    
    static Rules r = new Rules();
    
    public Entry() {
        initComponents();
        this.setResizable(false);
        this.setSize(337, 370);
        this.setLocation(250, 250);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        jLabel1.setFont(new Font("Courier New", Font.BOLD, 20));
        jLabel1.setForeground(Color.white);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
            int n = JOptionPane.showConfirmDialog(e.getWindow(),"Çıkış yapmakta ısrarcı mıyız?","Çıkış yapılıyor...",JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                e.getWindow().dispose();
                System.exit(1);
                }
                else if(n == 1) {
                System.out.println("Çıkış iptal edildi.");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        rules = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("MANGALA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        start.setText("Oyuna Başla");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        rules.setText("Kurallar");
        rules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesActionPerformed(evt);
            }
        });
        getContentPane().add(rules, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 101, -1));

        exit.setText("Çıkış");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 101, -1));
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 222, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 101, 10));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-14, -4, 360, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesActionPerformed
        // TODO add your handling code here:
        
        if (Rules.isOpen) {
            System.out.println(" Already Open");
            r.toFront();
        }
        else{
            Rules.isOpen = true;
            r.setVisible(true);
        }
        
    }//GEN-LAST:event_rulesActionPerformed

    /*
     If user enters a nick name and presses OK button then a Client object starts with ıp adress and port number.
    */
    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
        String s = (String)JOptionPane.showInputDialog( this,"","Takma adınızı giriniz.", JOptionPane.PLAIN_MESSAGE,null, null, "Oyuncu");
        //System.out.println(s);
        if (s == null) {
            System.out.println("Oyuna giriş yapılmadı.");
        }
        else {
            //Oyun sayfasını aç
            Mangala mangala = new Mangala();
            mangala.getnickL().setText(s); 
            //CONNECT
            Client.Start("127.0.0.1", 500);           
            
            mangala.setVisible(true);
            setVisible(false);
        }
        

    }//GEN-LAST:event_startActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here: 
        
        if (Rules.isOpen) {
            r.setVisible(false);
            r.dispose();
        }
        this.dispose();
        System.exit(1);
    }//GEN-LAST:event_exitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Entry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton rules;
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables
}
