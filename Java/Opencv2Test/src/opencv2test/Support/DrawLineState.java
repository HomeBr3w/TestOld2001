package opencv2test.Support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawLineState extends JPanel
{
    private BufferedImage img;
    
    private int startx, starty, currentx, currenty;
    private boolean isDrawing = false;

    public DrawLineState(BufferedImage img) {
        this.img = img;
        this.setBounds(0, 0, img.getWidth(), img.getHeight());
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
                
                if (e.getButton() == 3)
                {
                    
                }
                else
                {
                    if (!isDrawing)
                    {
                        startx = currentx = e.getX();
                        starty = currenty = e.getY();
                        isDrawing = true;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                isDrawing = false;
                currentx = e.getX();
                currenty = e.getY();
            }
            
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
                
                currentx = e.getX();
                currenty = e.getY();
                
                repaint();
            }
            
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(img, null, 0, 0);
        
        if (isDrawing)
        {
            g2d.setColor(Color.red);
            g2d.drawLine(startx, starty, currentx, currenty);
        }
    }
    
    
}
