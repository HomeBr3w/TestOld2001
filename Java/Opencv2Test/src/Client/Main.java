/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import com.sun.javafx.Utils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;
import opencv2test.Core.Analyse;
import opencv2test.Remote.RmiServerIntf;
import opencv2test.Support.DrawBoxClass;
import opencv2test.Support.DrawBoxState;
import opencv2test.Support.DrawLineClass;
import opencv2test.Support.DrawLineState;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;

/**
 *
 * @author jasper
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() throws RemoteException {
        initComponents();
        
        try {
            RMISERV = (RmiServerIntf)Naming.lookup(RmiServerIntf.RMI_LOCATION);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(RMISERV.version());
    }
    
    RmiServerIntf RMISERV;
    
    private Color active = new Color(219,242,249);
    private Color nonactive = new Color(250, 250, 250);
    private Color error = new Color(255,204,204);
    
    private void disableAll()
    {
        openffbtn.setEnabled(false);
        openfsbtn.setEnabled(false);
        bg1.setBackground(nonactive);
        
        back21.setEnabled(false);
        go23.setEnabled(false);
        bg2.setBackground(nonactive);
        
        back22.setEnabled(false);
        go24.setEnabled(false);
        bg3.setBackground(nonactive);
        
        saveimg.setEnabled(false);
        savemidi.setEnabled(false);
        savemp3.setEnabled(false);
        bg4.setBackground(nonactive);
        
        if (window != null && window.isVisible())
        {
            window.setVisible(false);
        }
        
        if (window2 != null && window2.isVisible())
        {
            img = window2.img;
            window2.setVisible(false);
        }
    }
    
    private void goto1()
    {
        disableAll();
        openffbtn.setEnabled(true);
        //openfsbtn.setEnabled(true);
        bg1.setBackground(active);
        repaint();
        revalidate();
        pbar.setValue(00);
    }
    
    private DrawLineClass window;
    private void goto2()
    {
        disableAll();
        back21.setEnabled(true);
        go23.setEnabled(true);
        bg2.setBackground(active);
        repaint();
        revalidate();
        
        window = new DrawLineClass("Draw a line on the first track. Try to match the line perfectly!");
        window.setLayout(new BorderLayout());
        window.setBounds(500, 0, img.getWidth(), img.getHeight());
        window.dlstate = new DrawLineState(img);
        window.add(window.dlstate, BorderLayout.CENTER);
        
        window.dlstate.jslide = new JSlider(-1000, 1000, 0);
        window.dlstate.jslide.addChangeListener(window.dlstate.slistner);
        window.add(window.dlstate.jslide, BorderLayout.SOUTH);
        
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
        
        pbar.setValue(15);
    }
    
    private DrawBoxState window2;
    private void goto3() throws RemoteException
    {
        disableAll();
        
        int w = img.getWidth();
        int h = img.getHeight();
        
        String result = RMISERV.Rotate(encryptBufferedImage(img, false), (double)window.dlstate.rotation);
        img = decryptBufferedImage(result);
                
        window2 = new DrawBoxState("Mark the area's to keep. Left click-drag to mark. Right click to remove.");
        window2.img = img;
        window2.setBounds(500,0,img.getWidth(), img.getHeight()+15);
        window2.setVisible(true);
        
        window2.pp = new DrawBoxClass(img);
        window2.add(window2.pp, BorderLayout.CENTER);
        
        back22.setEnabled(true);
        go24.setEnabled(true);
        bg3.setBackground(active);
        repaint();
        revalidate();
        pbar.setValue(30);
    }
    
    private void goto4()
    {
        disableAll();
        saveimg.setEnabled(true);
        savemidi.setEnabled(true);
        savemp3.setEnabled(true);
        bg4.setBackground(active);
        repaint();
        revalidate();
        pbar.setValue(45);
    }

    private BufferedImage img;
    private ArrayList<Integer> t;
    private File imgf;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pathfound = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        openffbtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        go23 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        openfsbtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        back21 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        back22 = new javax.swing.JButton();
        go24 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        saveimg = new javax.swing.JButton();
        savemp3 = new javax.swing.JButton();
        savemidi = new javax.swing.JButton();
        pbar = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        bg4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bg3 = new javax.swing.JPanel();
        bg2 = new javax.swing.JPanel();
        bg1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pathfound.setFont(new java.awt.Font("Lucida Grande", 2, 10)); // NOI18N
        pathfound.setForeground(new java.awt.Color(102, 102, 102));
        pathfound.setText(" ");
        getContentPane().add(pathfound, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 140, 180, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Step 1:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, -1));

        openffbtn.setText("Open image from file");
        openffbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openffbtnActionPerformed(evt);
            }
        });
        getContentPane().add(openffbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 94, 180, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Step 2:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 176, 60, -1));

        go23.setText("Next step");
        go23.setEnabled(false);
        go23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                go23ActionPerformed(evt);
            }
        });
        getContentPane().add(go23, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 239, 180, -1));

        jLabel4.setText("Open an image");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 99, 160, -1));

        openfsbtn.setText("Open image from scanner");
        openfsbtn.setEnabled(false);
        openfsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openfsbtnActionPerformed(evt);
            }
        });
        getContentPane().add(openfsbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 129, 180, -1));

        jLabel5.setText("Draw a line across the first bar");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 209, 210, -1));

        back21.setText("Back");
        back21.setEnabled(false);
        back21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back21ActionPerformed(evt);
            }
        });
        getContentPane().add(back21, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 204, 180, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel8.setText("Step 3:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 286, 120, -1));

        jLabel9.setText("Select area's to keep");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 319, 170, -1));

        back22.setText("Back");
        back22.setEnabled(false);
        back22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back22ActionPerformed(evt);
            }
        });
        getContentPane().add(back22, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 314, 180, -1));

        go24.setText("Next step");
        go24.setEnabled(false);
        go24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                go24ActionPerformed(evt);
            }
        });
        getContentPane().add(go24, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 349, 180, -1));

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel10.setText("Step 4:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 396, 100, -1));

        jLabel11.setText("Processing done");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 429, 140, -1));

        saveimg.setText("Save image");
        saveimg.setEnabled(false);
        saveimg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveimgActionPerformed(evt);
            }
        });
        getContentPane().add(saveimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 424, 180, -1));

        savemp3.setText("Save MP3");
        savemp3.setEnabled(false);
        savemp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savemp3ActionPerformed(evt);
            }
        });
        getContentPane().add(savemp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 459, 180, -1));

        savemidi.setText("Save MIDI");
        savemidi.setEnabled(false);
        savemidi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savemidiActionPerformed(evt);
            }
        });
        getContentPane().add(savemidi, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 494, 180, -1));
        getContentPane().add(pbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 543, 412, -1));

        jLabel6.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("lw");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 32)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Note`Finder");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 190, 30));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 430, -1));

        bg4.setBackground(new java.awt.Color(250, 250, 250));

        javax.swing.GroupLayout bg4Layout = new javax.swing.GroupLayout(bg4);
        bg4.setLayout(bg4Layout);
        bg4Layout.setHorizontalGroup(
            bg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bg4Layout.setVerticalGroup(
            bg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(bg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 430, 110));

        jLabel1.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("solutions");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 170, 20));

        bg3.setBackground(new java.awt.Color(250, 250, 250));

        javax.swing.GroupLayout bg3Layout = new javax.swing.GroupLayout(bg3);
        bg3.setLayout(bg3Layout);
        bg3Layout.setHorizontalGroup(
            bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bg3Layout.setVerticalGroup(
            bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(bg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 430, 70));

        bg2.setBackground(new java.awt.Color(250, 250, 250));

        javax.swing.GroupLayout bg2Layout = new javax.swing.GroupLayout(bg2);
        bg2.setLayout(bg2Layout);
        bg2Layout.setHorizontalGroup(
            bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bg2Layout.setVerticalGroup(
            bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(bg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 430, 70));

        bg1.setBackground(new java.awt.Color(219, 242, 249));

        javax.swing.GroupLayout bg1Layout = new javax.swing.GroupLayout(bg1);
        bg1.setLayout(bg1Layout);
        bg1Layout.setHorizontalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bg1Layout.setVerticalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(bg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 430, 70));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openffbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openffbtnActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getPath());
        }
        pathfound.setText(chooser.getSelectedFile().getName());
        imgf = chooser.getSelectedFile();
        
        try {
            img = ImageIO.read(imgf);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (img.getHeight()* img.getWidth()< 480000)
        {
            pathfound.setText("Image is too small!");
            bg1.setBackground(error);
            
        }
        else
        {
            goto2();
        }
        
    }//GEN-LAST:event_openffbtnActionPerformed

    private void openfsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openfsbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openfsbtnActionPerformed

    private void back21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back21ActionPerformed
        goto1();
    }//GEN-LAST:event_back21ActionPerformed

    private void go23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_go23ActionPerformed
        try {
            goto3();
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_go23ActionPerformed

    private void back22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back22ActionPerformed
        goto2();
    }//GEN-LAST:event_back22ActionPerformed

    private void go24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_go24ActionPerformed
        goto4();
    }//GEN-LAST:event_go24ActionPerformed

    private void saveimgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveimgActionPerformed
        /*JFileChooser fc = new JFileChooser();
        if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File f = fc.getSelectedFile();
            Highgui.imwrite(f.getAbsolutePath(), img);
        } */       
    }//GEN-LAST:event_saveimgActionPerformed

    private void savemp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savemp3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_savemp3ActionPerformed

    private void savemidiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savemidiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_savemidiActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        if (Utils.isWindows()) {
            System.load(new File("C:\\opencv\\build\\java\\x64\\opencv_java2410.dll").getAbsolutePath());
        } else {
            System.load(new File("/opt/local/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());
        }
               
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static String encryptBufferedImage(BufferedImage img, boolean print) {
        int icount = 0;
        int ocount = 0;
        String buffer = "";
        buffer += img.getType() + "#";
        for (int r = 0; r < img.getHeight(); r++) {
            for (int c = 0; c < img.getWidth(); c++) {
                if (img.getRGB(c, r) < 127) {
                    if (icount > 0) {
                        if (icount == 1) {
                            buffer += "|";
                        } else {
                            buffer += icount + "|";
                        }
                        icount = 0;
                    }
                    ocount++;
                } else {
                    if (ocount > 0) {
                        if (ocount == 1) {
                            buffer += ".";
                        } else {
                            buffer += ocount + ".";
                        }
                        ocount = 0;
                    }
                    icount++;
                }
            }
            if (icount > 0) {
                if (icount == 1) {
                    buffer += "|";
                } else {
                    buffer += icount + "|";
                }
            }
            if (ocount > 0) {
                if (ocount == 1) {
                    buffer += ".";
                } else {
                    buffer += ocount + ".";
                }
            }
            ocount = 0;
            icount = 0;
            buffer += "-";
        }
        if (print) {
            for (int i = 1; i < buffer.length() + 1; i++) {
                System.out.print(buffer.charAt(i - 1));
                if (i % 60 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
        return buffer;
    }

    public static BufferedImage decryptBufferedImage(String buffer) {
        String[] imgSplit = buffer.split("#");
        int type = Integer.parseInt(imgSplit[0]);
        String[] lines = imgSplit[1].split("-");
        ArrayList<ArrayList<Integer>> totalRows = new ArrayList<>();
        for (String s : lines) {
            String cached = "";
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '.') {
                    int count = 0;
                    try {
                        count = Integer.parseInt(cached);
                    } catch (Exception ex) {
                        count = 1;
                    }

                    for (int c = 0; c < count; c++) {
                        row.add(0);
                    }
                    cached = "";
                } else if (s.charAt(i) == '|') {
                    int count = 0;
                    try {
                        count = Integer.parseInt(cached);
                    } catch (Exception ex) {
                        count = 1;
                    }
                    for (int c = 0; c < count; c++) {
                        row.add(1);
                    }
                    cached = "";
                } else {
                    cached += s.charAt(i);
                }

            }
            totalRows.add(row);
        }
        int height = totalRows.size();
        int width = totalRows.get(0).size();
        BufferedImage result = new BufferedImage(width, height, type);
        for (int y = 0; y < totalRows.size(); y++) {
            ArrayList<Integer> row = totalRows.get(y);
            for (int x = 0; x < row.size(); x++) {
                int resX = row.get(x) * 254;
                result.setRGB(x, y, resX);
            }
        }
        return result;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back21;
    private javax.swing.JButton back22;
    private javax.swing.JPanel bg1;
    private javax.swing.JPanel bg2;
    private javax.swing.JPanel bg3;
    private javax.swing.JPanel bg4;
    private javax.swing.JButton go23;
    private javax.swing.JButton go24;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton openffbtn;
    private javax.swing.JButton openfsbtn;
    private javax.swing.JLabel pathfound;
    private javax.swing.JProgressBar pbar;
    private javax.swing.JButton saveimg;
    private javax.swing.JButton savemidi;
    private javax.swing.JButton savemp3;
    // End of variables declaration//GEN-END:variables
}
