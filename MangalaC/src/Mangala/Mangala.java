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
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Mangala extends javax.swing.JFrame implements ActionListener {

    public static Mangala mangala;
    public Thread th;
    public int turn = 0;
    boolean sent = false;
    public String sended="";
    public int[][] pit = new int[2][7];
    public int[][] RivalsPit = new int[2][7];
    public int[][] temp = new int[2][7];
    //Bu değerleri 2.clienta verirken ters yüz et.
    // 6,5,4,3,2,1,0
    // 0,1,2,3,4,5,6

    public Mangala() {
        initComponents();
        mangala = this;
        this.setBackground(Color.darkGray);
        this.setLocation(250, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        jLabel1.setFont(new Font("Courier New", Font.BOLD, 20));
        
        try {
            File f = new File("C:\\Users\\nskml\\Desktop\\Dosyalar\\3.Sınıf\\Bahar Dönemi\\Bilgisayar Ağları\\Lab\\Proje1\\Mangala-Game\\MangalaC\\src\\images\\redArrow.png");
            BufferedImage bi = ImageIO.read(f);
            Image scaled = bi.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaled);
            rakipHak.setIcon(icon);
            yourHak.setIcon(icon);
        } catch (IOException ex) {
            Logger.getLogger(Mangala.class.getName()).log(Level.SEVERE, null, ex);
        }
        rakipHak.setVisible(false);
        yourHak.setVisible(false);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(mangala, "Çıkış yapmakta ısrarcı mıyız?", "Çıkış yapılıyor...", JOptionPane.YES_NO_OPTION);
                if (Rules.isOpen) {
                    Entry.r.setVisible(false);
                    Entry.r.dispose();
                }
                if (n == 0) {
                    Client.Stop();//DISCONNECT
                    System.exit(1);
                    mangala.dispose();
                } else if (n == 1) {
                    System.out.println("Çıkış iptal edildi.");
                }
            }
        });
        
        diziDoldur();
        diziAktar(pit);
        
        th = new Thread(() -> {
            
             b1.setEnabled(true);b2.setEnabled(true);b3.setEnabled(true);
        b4.setEnabled(true);b5.setEnabled(true);b6.setEnabled(true);
        a1.setEnabled(true);a2.setEnabled(true);a3.setEnabled(true);
        a4.setEnabled(true);a5.setEnabled(true);a6.setEnabled(true);
        aH.setEnabled(true);bH.setEnabled(true);
            
            //soket bağlıysa dönsün
            while (Client.socket.isConnected()) {
                
                 if (turn == 1) {
                    yourHak.setVisible(true);
                    rakipHak.setVisible(false);
                 }
                 else {
                     rakipHak.setVisible(true);
                     yourHak.setVisible(false);
                 }
                
                if (sended.equals("ok")) {
                    diziAktar(RivalsPit);
                    sended = "";
                }
                
                try {
                    th.sleep(100);
                    if (sent) {
                        th.sleep(2000); //az bi aktarma süresi ekleyelim.
                        //diziAktar(RivalsPit);
                        sent = false;
                        pit = RivalsPit;
                        //diziAktar(pit);//////????
                    }                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Mangala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
        });
        
        b1.setEnabled(false);b2.setEnabled(false);b3.setEnabled(false);
        b4.setEnabled(false);b5.setEnabled(false);b6.setEnabled(false);
        a1.setEnabled(false);a2.setEnabled(false);a3.setEnabled(false);
        a4.setEnabled(false);a5.setEnabled(false);a6.setEnabled(false);
        aH.setEnabled(false);bH.setEnabled(false);
        
        b1.setFocusable(false);b2.setFocusable(false);b3.setFocusable(false);
        b4.setFocusable(false);b5.setFocusable(false);b6.setFocusable(false);
        a1.setFocusable(false);a2.setFocusable(false);a3.setFocusable(false);
        a4.setFocusable(false);a5.setFocusable(false);a6.setFocusable(false);
        aH.setFocusable(false);bH.setFocusable(false);
        aH.setOpaque(true);
        bH.setOpaque(true);
        b1.setHorizontalAlignment(SwingConstants.CENTER);b2.setHorizontalAlignment(SwingConstants.CENTER);
        b3.setHorizontalAlignment(SwingConstants.CENTER);b4.setHorizontalAlignment(SwingConstants.CENTER);
        b5.setHorizontalAlignment(SwingConstants.CENTER);b6.setHorizontalAlignment(SwingConstants.CENTER);
        a1.setHorizontalAlignment(SwingConstants.CENTER);a2.setHorizontalAlignment(SwingConstants.CENTER);
        a3.setHorizontalAlignment(SwingConstants.CENTER);a4.setHorizontalAlignment(SwingConstants.CENTER);
        a5.setHorizontalAlignment(SwingConstants.CENTER);a6.setHorizontalAlignment(SwingConstants.CENTER);
        aH.setHorizontalAlignment(SwingConstants.CENTER);bH.setHorizontalAlignment(SwingConstants.CENTER);
        nickL.setHorizontalTextPosition(SwingConstants.CENTER);
        rivalNick.setHorizontalTextPosition(SwingConstants.CENTER);

        /*JLabel label = new JLabel( new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/kuyu.jpg"))).getImage()) );
         label.setLayout( new BorderLayout() );
         label.add( a1 );*/
        
        a1.setActionCommand("a1"); a1.addActionListener(this);
        a2.setActionCommand("a2"); a2.addActionListener(this);
        a3.setActionCommand("a3"); a3.addActionListener(this);
        a4.setActionCommand("a4"); a4.addActionListener(this);
        a5.setActionCommand("a5"); a5.addActionListener(this);
        a6.setActionCommand("a6"); a6.addActionListener(this);
        b1.setActionCommand("b1"); b1.addActionListener(this);
        b2.setActionCommand("b2"); b2.addActionListener(this);
        b3.setActionCommand("b3"); b3.addActionListener(this);
        b4.setActionCommand("b4"); b4.addActionListener(this);
        b5.setActionCommand("b5"); b5.addActionListener(this);
        b6.setActionCommand("b6"); b6.addActionListener(this);
        rulesB.setActionCommand("rulesB"); rulesB.addActionListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aH = new javax.swing.JTextField();
        bH = new javax.swing.JTextField();
        rulesB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nickL = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        newGameB = new javax.swing.JButton();
        rivalNick = new javax.swing.JLabel();
        b6 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        a3 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        a1 = new javax.swing.JButton();
        a2 = new javax.swing.JButton();
        a4 = new javax.swing.JButton();
        a5 = new javax.swing.JButton();
        a6 = new javax.swing.JButton();
        rakipHak = new javax.swing.JLabel();
        yourHak = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        aH.setText("Hazine1");
        aH.setPreferredSize(new java.awt.Dimension(60, 24));

        bH.setText("Hazine2");
        bH.setPreferredSize(new java.awt.Dimension(60, 24));

        rulesB.setText("Kurallar");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mangala");

        jLabel2.setText("<==");

        jLabel3.setText("==>");

        nickL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nickL.setText("Nick Name");
        nickL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nickL.setMaximumSize(new java.awt.Dimension(100, 16));
        nickL.setMinimumSize(new java.awt.Dimension(100, 16));
        nickL.setPreferredSize(new java.awt.Dimension(100, 16));

        newGameB.setText("Yeni Oyun");
        newGameB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameBActionPerformed(evt);
            }
        });

        rivalNick.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rivalNick.setText("Rakip Bekleniyor");
        rivalNick.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rivalNick.setMaximumSize(new java.awt.Dimension(100, 16));
        rivalNick.setMinimumSize(new java.awt.Dimension(100, 16));
        rivalNick.setPreferredSize(new java.awt.Dimension(100, 16));

        b6.setText("1");

        b3.setText("1");

        b4.setText("1");

        b2.setText("1");

        a3.setText("1");

        b1.setText("1");

        b5.setText("1");

        a1.setText("1");

        a2.setText("1");

        a4.setText("1");

        a5.setText("1");

        a6.setText("1");

        rakipHak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rakipHak.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bH, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(yourHak, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(9, 9, 9)
                                                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13)
                                                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13)
                                                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13)
                                                .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(nickL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(rivalNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(rakipHak, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(93, 93, 93)
                                .addComponent(rulesB, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aH, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(newGameB))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rulesB)
                            .addComponent(newGameB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bH, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(aH, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rakipHak, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rivalNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(yourHak, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nickL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        aH.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        switch (action) {
            case "a1":
                System.out.println("a1 pressed!");
                gamePlay("a1");
                break;
            case "a2":
                System.out.println("a2 pressed!");
                gamePlay("a2");
                break;
            case "a3":
                System.out.println("a3 pressed!");
                gamePlay("a3");
                break;
            case "a4":
                System.out.println("a4 pressed!");
                gamePlay("a4");
                break;
            case "a5":
                System.out.println("a5 pressed!");
                gamePlay("a5");
                break;
            case "a6":
                System.out.println("a6 pressed!");
                gamePlay("a6");
                break;
            case "b1":
                System.out.println("b1 pressed!");
                JOptionPane.showMessageDialog(this, "Rakibin kuyusuna dokunmamalısın.", "Yanlış Kuyu", JOptionPane.ERROR_MESSAGE);
                break;
            case "b2":
                System.out.println("b2 pressed!");
                JOptionPane.showMessageDialog(this, "Rakibin kuyusuna dokunmamalısın.", "Yanlış Kuyu", JOptionPane.ERROR_MESSAGE);
                break;
            case "b3":
                System.out.println("b3 pressed!");
                JOptionPane.showMessageDialog(this, "Rakibin kuyusuna dokunmamalısın.", "Yanlış Kuyu", JOptionPane.ERROR_MESSAGE);
                break;
            case "b4":
                System.out.println("b4 pressed!");
                JOptionPane.showMessageDialog(this, "Rakibin kuyusuna dokunmamalısın.", "Yanlış Kuyu", JOptionPane.ERROR_MESSAGE);
                break;
            case "b5":
                System.out.println("b5 pressed!");
                JOptionPane.showMessageDialog(this, "Rakibin kuyusuna dokunmamalısın.", "Yanlış Kuyu", JOptionPane.ERROR_MESSAGE);
                break;
            case "b6":
                System.out.println("b6 pressed!");
                JOptionPane.showMessageDialog(this, "Rakibin kuyusuna dokunmamalısın.", "Yanlış Kuyu", JOptionPane.ERROR_MESSAGE);
                break;
            case "rulesB":
                if (Rules.isOpen) {
                    System.out.println(" Already Open");
                } else {
                    Entry.r.setVisible(true);
                }
                break;
        }
    }
    
    
    public void diziDoldur() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 7; j++) {
                pit[i][j] = 4;
            }
        }
        pit[1][6] = 0; // aH set to 0
        pit[0][0] = 0; // bH set to 0
    }

    public void diziCevir() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 7; j++) {
                temp[i][j] = pit[(i + 1) % 2][6 - j];
            }
        }
    }

    public void diziAktar(int [][] a) {
        a1.setText(a[1][0] + "");a2.setText(a[1][1] + "");a3.setText(a[1][2] + "");
        a4.setText(a[1][3] + "");a5.setText(a[1][4] + "");a6.setText(a[1][5] + "");
        aH.setText(a[1][6] + "");
        b1.setText(a[0][6] + "");b2.setText(a[0][5] + "");b3.setText(a[0][4] + "");
        b4.setText(a[0][3] + "");b5.setText(a[0][2] + "");b6.setText(a[0][1] + "");
        bH.setText(a[0][0] + "");
    }

    public int movePit(int pitValue, int pitNo) {
        int temp = 0;
        int lastIndex = 0;
        boolean passed = false;
        if (pitValue > 0) {
            if (pitValue == 1) { 
                return 0;
            } else if (pitValue > 1 && pitValue <= 7) {
                for (int i = 0; i < pitValue - 1; i++) {
                    if (i + pitNo < 7) //Alt sıra için
                        pit[1][i + pitNo]++;
                    else { //üst sıraya geçince...
                        passed = true;
                        pit[0][6 - temp]++;
                        lastIndex = 6-temp;
                        temp++;
                    }
                }
                if (passed && pit[0][lastIndex] % 2 == 0) { 
                    //Son taş karşı kuyulardan birini çift sayı yaparsa. O kuyudaki taşların hepsini hazinesine katar.
                            pit[1][6] += pit[0][lastIndex];
                            pit[0][lastIndex] = 0;
                        }
                return 1;
            } else if (pitValue > 7) { //TODO burada sıkıntı var hallet bakam
                pitValue--;//İlk kuyuya 1 tane bıraktık.
                while (pitNo<7) {//Alt bölgedeki işlemler...                    
                    pit[1][pitNo]++;
                    pitValue--;
                    pitNo++;
                }
                pitNo--; //üst tarafa geçince 7 olan pitNo değerini 6 yaparız.
                while (pitValue>0) {//Üst bölgedeki işlemler        
                    pit[0][pitNo]++;
                    pitNo--;
                    pitValue--;
                }
                pitNo++; //son index için son kuyu değerini çift sayın kontrolü için alırız.
                if (pit[0][pitNo] % 2 == 0) {
                    pit[1][6] += pit[0][pitNo];
                    pit[0][pitNo] = 0;
                }
                return 1;
            }
        } else {
            System.out.println("nothing to do");
            return 0;
        }
        return -999;
    }

    public void gamePlay(String s) {
        switch (s) {
            case "a1":
                if (pit[1][0] == 0) 
                    break; //kS += 1;
                pit[1][0] = movePit(pit[1][0], 1);
                if (pit[1][0] == 0) 
                    pit[1][1] += 1;
                break;
            case "a2":
                if (pit[1][1] == 0)
                    break;
                pit[1][1] = movePit(pit[1][1], 2);
                if (pit[1][1] == 0)
                    pit[1][2] += 1;
                break;
            case "a3":
                if (pit[1][2] == 0) 
                    break;
                pit[1][2] = movePit(pit[1][2], 3);
                if (pit[1][2] == 0) 
                    pit[1][3] += 1;
                break;
            case "a4":
                if (pit[1][3] == 0) 
                    break;
                pit[1][3] = movePit(pit[1][3], 4);
                if (pit[1][3] == 0) 
                    pit[1][4] += 1;
                break;
            case "a5":
                if (pit[1][4] == 0) 
                    break;
                pit[1][4] = movePit(pit[1][4], 5);
                if (pit[1][4] == 0) 
                    pit[1][5] += 1;
                break;
            case "a6":
                if (pit[1][5] == 0) 
                    break;
                pit[1][5] = movePit(pit[1][5], 6);
                if (pit[1][5] == 0) 
                    pit[1][6] += 1;
                break;
            default:
                System.out.println("Select a valid kuyu");
        }
        
        diziAktar(pit);
        
        if (Client.socket.isConnected()) {//TODO bağlantı yoksa , rakip gelmediyse oyuna başlatma izin verme
            Message msg = new Message(Message.Message_Type.Pits);
            diziCevir();
            RivalsPit = temp;
            msg.content = RivalsPit;
            Client.Send(msg);
            
            Message msg2 = new Message(Message.Message_Type.Text);
            msg2.content = "ok";
            Client.Send(msg2);
            sent = true;
            
            Message msg3 = new Message(Message.Message_Type.WhosTurn);
            msg3.content = 1;
            Client.Send(msg3);
            turn = 0;
             
            rakipHak.setVisible(true);
            yourHak.setVisible(false);
            System.out.println("gameplayden mesaj yollandı");
        }
        else
            System.out.println("Client bağlantısı yok.");
        
    }

    private void newGameBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameBActionPerformed
        // TODO add your handling code here:
        System.out.println("DISCONNECT AND GO TO ENTRY FRAME");
        //DISCONNECT
        this.dispose();
        Entry giris = new Entry();
        giris.setVisible(true);
    }//GEN-LAST:event_newGameBActionPerformed

    //Cant access to variables so i made these methods
    //With this methods i can access these variables
    public JLabel getnickL() {
        return nickL;
    }
    public JLabel getrivalNick() {
        return rivalNick;
    }
    public JButton geta1() {
        return a1;
    }
    public JButton geta2() {
        return a2;
    }
    public JButton geta3() {
        return a3;
    }
    public JButton geta4() {
        return a4;
    }
    public JButton geta5() {
        return a5;
    }
    public JButton geta6() {
        return a6;
    }
    public JTextField getaH() {
        return aH;
    }
    public JButton getb1() {
        return b1;
    }
    public JButton getb2() {
        return b2;
    }
    public JButton getb3() {
        return b3;
    }
    public JButton getb4() {
        return b4;
    }
    public JButton getb5() {
        return b5;
    }
    public JButton getb6() {
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
    private javax.swing.JButton a1;
    private javax.swing.JButton a2;
    private javax.swing.JButton a3;
    private javax.swing.JButton a4;
    private javax.swing.JButton a5;
    private javax.swing.JButton a6;
    private javax.swing.JTextField aH;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JTextField bH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton newGameB;
    private javax.swing.JLabel nickL;
    private javax.swing.JLabel rakipHak;
    private javax.swing.JLabel rivalNick;
    private javax.swing.JButton rulesB;
    private javax.swing.JLabel yourHak;
    // End of variables declaration//GEN-END:variables

}
