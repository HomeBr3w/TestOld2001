/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test;

import com.sun.javafx.Utils;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Bounds;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import opencv2test.Core.Analyse;
import opencv2test.Support.PicturePanel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author jasper
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Load image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NoteFinder");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private PicturePanel pp;
    private Mat img;
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getPath());
        }
        
        getContentPane().removeAll();
        repaint();
        revalidate();
        setLayout(new BorderLayout());
        
        img = Highgui.imread(chooser.getSelectedFile().getPath());
 //        img = Analyse.convertToGrey(img);
        
        Imgproc.resize(img, img, img.size());
        MatOfByte matOfByte = new MatOfByte();
        Highgui.imencode(".jpg", img, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        pp = new PicturePanel(bufImage);
        
        setBounds(0,0,img.cols(), img.rows()+15);
        add(pp, BorderLayout.CENTER);
        
        JPanel buttonbar = new JPanel(new BorderLayout());
        JButton pc = new JButton("Process");
        pc.addActionListener(processor);
        buttonbar.add(pc, BorderLayout.WEST);

        JButton st = new JButton("Start");
        st.addActionListener(onStart);
        buttonbar.add(st, BorderLayout.EAST);

        add(buttonbar, BorderLayout.SOUTH);
        
        repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private ActionListener processor = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Mat img2 = new Mat(img.size(), img.type());
            img2.setTo(new Scalar(255.0, 255.0, 255.0));
            for (Rectangle2D r : pp.rectList)
            {
                for (int x = (int)Math.round(r.getX()); x < (int)Math.round(r.getX()+r.getWidth()); x++)
                {
                    for (int y = (int)Math.round(r.getY()); y < (int)Math.round(r.getY()+r.getHeight()); y++)
                    {
                        img2.put(y, x, img.get(y, x));
                    }
                }
            }
            
            getContentPane().removeAll();
            repaint();
            revalidate();
            setLayout(new BorderLayout());
            
            img = img2.clone();

            MatOfByte matOfByte = new MatOfByte();
            Highgui.imencode(".jpg", img, matOfByte);
            byte[] byteArray = matOfByte.toArray();
            BufferedImage bufImage = null;
            
            InputStream in = new ByteArrayInputStream(byteArray);
            try {
                bufImage = ImageIO.read(in);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            pp = new PicturePanel(bufImage);
            add(pp, BorderLayout.CENTER);

            JPanel buttonbar = new JPanel(new BorderLayout());
            JButton pc = new JButton("Process");
            pc.addActionListener(processor);
            buttonbar.add(pc, BorderLayout.WEST);
            
            JButton st = new JButton("Start");
            st.addActionListener(onStart);
            buttonbar.add(st, BorderLayout.EAST);
            
            add(buttonbar, BorderLayout.SOUTH);
            
            repaint();
            revalidate();
        }
    };
    
    private ActionListener onStart = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Opencv2Test.start(img);
        }
    };
    
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        if (Utils.isWindows()) {
            System.load(new File("C:\\opencv\\build\\java\\x64\\opencv_java2410.dll").getAbsolutePath());
        } else {
            System.load(new File("/opt/local/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
