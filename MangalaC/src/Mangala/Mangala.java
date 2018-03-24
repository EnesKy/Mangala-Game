package Mangala;

import MangalaCl.Client;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Mangala.Entry;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Mangala extends javax.swing.JFrame {

    public static Mangala mangala;
    public Thread th;
    //karşı tarafın seçimi seçim -1 deyse seçilmemiş
    public int RivalSelection = -1;
    //benim seçimim seçim -1 deyse seçilmemiş
    public int MySelection = -1;

    int[][] kuyular = new int[2][7];
    int[][] temp = new int[2][7];
    //Bu değerleri 2.clienta verirken ters yüz et.
    // 6,5,4,3,2,1,0
    // 0,1,2,3,4,5,6

    public void diziDoldur() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 7; j++) {
                kuyular[i][j] = 4;
            }
        }
        kuyular[1][6] = 0; kuyular[0][0]=0;
    }

    public void diziCevir() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 7; j++) {
               temp[i][j] = kuyular[(i+1)%2][6-j];
            }
        }
    }
    
    public void diziAktar(){
        a1.setText(kuyular[1][0]+"");a2.setText(kuyular[1][1]+"");a3.setText(kuyular[1][2]+"");
        a4.setText(kuyular[1][3]+"");a5.setText(kuyular[1][4]+"");a6.setText(kuyular[1][5]+"");
        aH.setText(kuyular[1][6]+"");
        b1.setText(kuyular[0][6]+"");b2.setText(kuyular[0][1]+"");b3.setText(kuyular[0][2]+"");
        b4.setText(kuyular[0][3]+"");b5.setText(kuyular[0][4]+"");b6.setText(kuyular[0][5]+"");
        bH.setText(kuyular[0][0]+"");
    }
    
    public Mangala() {
        initComponents();
        mangala = this;
        this.setBackground(Color.darkGray);
        this.setLocation(250, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        jLabel1.setFont(new Font("Courier New", Font.BOLD, 20));
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
            int n = JOptionPane.showConfirmDialog(mangala,"Çıkış yapmakta ısrarcı mıyız?","Çıkış yapılıyor...",JOptionPane.YES_NO_OPTION);
                if (Rules.isOpen) {
                    Entry.r.setVisible(false);
                    Entry.r.dispose();
                }
                if (n == 0) {
                Client.Stop();//DISCONNECT
                System.exit(1);
                mangala.dispose();
                }
                else if(n == 1) {
                System.out.println("Çıkış iptal edildi.");
                }
            }
        });
        diziDoldur(); diziCevir(); diziAktar();
        /*b1.setEnabled(false); b2.setEnabled(false); b3.setEnabled(false); b4.setEnabled(false); b5.setEnabled(false); b6.setEnabled(false);
         a1.setEnabled(false); a2.setEnabled(false); a3.setEnabled(false); a4.setEnabled(false); a5.setEnabled(false); a6.setEnabled(false);
         aH.setEnabled(false); bH.setEnabled(false);*/
        b1.setFocusable(false);b2.setFocusable(false);b3.setFocusable(false);
        b4.setFocusable(false);b5.setFocusable(false);b6.setFocusable(false);
        a1.setFocusable(false);a2.setFocusable(false);a3.setFocusable(false);
        a4.setFocusable(false);a5.setFocusable(false);a6.setFocusable(false);
        aH.setFocusable(false);bH.setFocusable(false);
        b1.setOpaque(true);b2.setOpaque(true);b3.setOpaque(true);
        b4.setOpaque(true);b5.setOpaque(true);b6.setOpaque(true);
        a1.setOpaque(true);a2.setOpaque(true);a3.setOpaque(true);
        a4.setOpaque(true);a5.setOpaque(true);a6.setOpaque(true);
        aH.setOpaque(true);bH.setOpaque(true);
        b1.setHorizontalAlignment(SwingConstants.CENTER);b2.setHorizontalAlignment(SwingConstants.CENTER);
        b3.setHorizontalAlignment(SwingConstants.CENTER);b4.setHorizontalAlignment(SwingConstants.CENTER);
        b5.setHorizontalAlignment(SwingConstants.CENTER);b6.setHorizontalAlignment(SwingConstants.CENTER);
        a1.setHorizontalAlignment(SwingConstants.CENTER);a2.setHorizontalAlignment(SwingConstants.CENTER);
        a3.setHorizontalAlignment(SwingConstants.CENTER);a4.setHorizontalAlignment(SwingConstants.CENTER);
        a5.setHorizontalAlignment(SwingConstants.CENTER);a6.setHorizontalAlignment(SwingConstants.CENTER);
        aH.setHorizontalAlignment(SwingConstants.CENTER);bH.setHorizontalAlignment(SwingConstants.CENTER);
        nickL.setHorizontalTextPosition(SwingConstants.CENTER);
        
        /*JLabel label = new JLabel( new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/kuyu.jpg"))).getImage()) );
        label.setLayout( new BorderLayout() );
        label.add( a1 );*/
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b6 = new javax.swing.JTextField();
        b5 = new javax.swing.JTextField();
        b4 = new javax.swing.JTextField();
        b3 = new javax.swing.JTextField();
        b1 = new javax.swing.JTextField();
        b2 = new javax.swing.JTextField();
        a1 = new javax.swing.JTextField();
        a2 = new javax.swing.JTextField();
        a3 = new javax.swing.JTextField();
        a4 = new javax.swing.JTextField();
        a5 = new javax.swing.JTextField();
        a6 = new javax.swing.JTextField();
        aH = new javax.swing.JTextField();
        bH = new javax.swing.JTextField();
        rulesB = new javax.swing.JButton();
        getBackB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nickL = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        newGameB = new javax.swing.JButton();
        rivalNick = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        b6.setText("6");

        b5.setText("5");

        b4.setText("4");

        b3.setText("3");

        b1.setText("1");

        b2.setText("2");

        a1.setText("1");
        a1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                a1MouseReleased(evt);
            }
        });

        a2.setText("2");

        a3.setText("3");

        a4.setText("4");

        a5.setText("5");

        a6.setText("6");

        aH.setText("Hazine1");
        aH.setPreferredSize(new java.awt.Dimension(60, 24));
        aH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aHActionPerformed(evt);
            }
        });

        bH.setText("Hazine2");
        bH.setPreferredSize(new java.awt.Dimension(60, 24));

        rulesB.setText("Kurallar");
        rulesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesBActionPerformed(evt);
            }
        });

        getBackB.setText("Geri Al");

        jLabel1.setText("Mangala");

        jLabel2.setText("<==");

        jLabel3.setText("==>");

        nickL.setText("Nick Name");

        newGameB.setText("Yeni Oyun");
        newGameB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameBActionPerformed(evt);
            }
        });

        rivalNick.setText("Rakip Bekleniyor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(bH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(rivalNick)
                        .addGap(192, 192, 192))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(rulesB, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addComponent(getBackB, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(nickL)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(251, 251, 251)
                .addComponent(newGameB)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(newGameB)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rivalNick)
                        .addGap(45, 45, 45)
                        .addComponent(nickL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(aH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getBackB, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rulesB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        aH.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void aHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aHActionPerformed

    private void rulesBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesBActionPerformed
        // TODO add your handling code here:
        if (Rules.isOpen) {
            System.out.println(" Already Open");
        } else {
            Entry.r.setVisible(true);
        }
    }//GEN-LAST:event_rulesBActionPerformed

    private void newGameBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameBActionPerformed
        // TODO add your handling code here:
        System.out.println("DISCONNECT AND GO TO ENTRY FRAME");
        //DISCONNECT
        this.dispose();
        Entry giris = new Entry();
        giris.setVisible(true);
    }//GEN-LAST:event_newGameBActionPerformed

    private void a1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_a1MouseReleased
        // TODO add your handling code here:
        Message msg = new Message(Message.Message_Type.Selected);
        msg.content = kuyular;
        Client.Send(msg);
    }//GEN-LAST:event_a1MouseReleased
    //Cant access to variables so i made these methods
    //With this methods i can access these variables

    public JLabel getnickL() {
        return nickL;
    }

    public JLabel getrivalNick() {
        return rivalNick;
    }

    public JTextField geta1() {
        return a1;
    }

    public JTextField geta2() {
        return a2;
    }

    public JTextField geta3() {
        return a3;
    }

    public JTextField geta4() {
        return a4;
    }

    public JTextField geta5() {
        return a5;
    }

    public JTextField geta6() {
        return a6;
    }

    public JTextField getaH() {
        return aH;
    }

    public JTextField getb1() {
        return b1;
    }

    public JTextField getb2() {
        return b2;
    }

    public JTextField getb3() {
        return b3;
    }

    public JTextField getb4() {
        return b4;
    }

    public JTextField getb5() {
        return b5;
    }

    public JTextField getb6() {
        return b6;
    }

    public JTextField getbH() {
        return bH;
    }

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
            java.util.logging.Logger.getLogger(Mangala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mangala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mangala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mangala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new Mangala().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a1;
    private javax.swing.JTextField a2;
    private javax.swing.JTextField a3;
    private javax.swing.JTextField a4;
    private javax.swing.JTextField a5;
    private javax.swing.JTextField a6;
    private javax.swing.JTextField aH;
    private javax.swing.JTextField b1;
    private javax.swing.JTextField b2;
    private javax.swing.JTextField b3;
    private javax.swing.JTextField b4;
    private javax.swing.JTextField b5;
    private javax.swing.JTextField b6;
    private javax.swing.JTextField bH;
    private javax.swing.JButton getBackB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton newGameB;
    private javax.swing.JLabel nickL;
    private javax.swing.JLabel rivalNick;
    private javax.swing.JButton rulesB;
    // End of variables declaration//GEN-END:variables
}
