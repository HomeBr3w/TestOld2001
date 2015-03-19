/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test.Support;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import opencv2test.MainWindow;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

/**
 *
 * @author jasper
 */
public class DrawBoxState extends JFrame {

    public DrawBoxState(String title) throws HeadlessException {
        super(title);

        JButton pc = new JButton("Process");
        pc.addActionListener(processor);
        add(pc, BorderLayout.SOUTH);
    }
    
    public DrawBoxClass pp;
    public Mat img;
    
    public ActionListener processor = new ActionListener() {
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
            
            pp = new DrawBoxClass(bufImage);
            add(pp, BorderLayout.CENTER);

            JButton pc = new JButton("Process");
            pc.addActionListener(processor);
            add(pc, BorderLayout.SOUTH);
            
            repaint();
            revalidate();
        }
    };
}
