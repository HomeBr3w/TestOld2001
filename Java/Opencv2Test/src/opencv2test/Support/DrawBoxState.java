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
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import opencv2test.Remote.RmiServerIntf;

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
    public BufferedImage img;
    public RmiServerIntf RMISERV;
    
    public ActionListener processor = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                img = RMISERV.RemoveButSelected(img, pp.rectList);
            } catch (RemoteException ex) {
                Logger.getLogger(DrawBoxState.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            getContentPane().removeAll();
            repaint();
            revalidate();
            setLayout(new BorderLayout());

            pp = new DrawBoxClass(img);
            add(pp, BorderLayout.CENTER);

            JButton pc = new JButton("Process");
            pc.addActionListener(processor);
            add(pc, BorderLayout.SOUTH);
            
            repaint();
            revalidate();
        }
    };
}
