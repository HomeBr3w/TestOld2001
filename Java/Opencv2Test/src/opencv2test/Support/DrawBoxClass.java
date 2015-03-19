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
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.opencv.core.Mat;

public class DrawBoxClass extends JPanel {

    private BufferedImage img;
    private boolean has2draw = false;
    private boolean candraw = true;
    private boolean isdrawing = false;
    
    private double currentX, currentY, startX, startY;
    public ArrayList<Rectangle2D> rectList = new ArrayList<>();

    public DrawBoxClass(BufferedImage img) {
        this.img = img;
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
                
                if (e.getButton() == 3)
                {
                    try
                    {
                        for (Rectangle2D r : rectList) {
                            if (r.contains(e.getPoint())) {
                                candraw = false;
                                new Runnable() {

                                    @Override
                                    public void run() {
                                        while (isdrawing) {
                                            try {

                                            Thread.sleep(1);
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(DrawBoxClass.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        rectList.remove(r);
                                        candraw = true;
                                        repaint();
                                    }

                                }.run();
                            }
                        }
                    }
                    catch (ConcurrentModificationException ex)
                    {

                    }
                }
                else
                {
                    has2draw = true;
                    startX = e.getX();
                    startY = e.getY();
                    currentX = e.getX()+100;
                    currentY = e.getY()+100;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                if (has2draw)
                {
                    has2draw = false;
                    double beginX, beginY, width, height;
                    beginX = Math.min(startX, currentX);
                    beginY = Math.min(startY, currentY);
                    width = Math.abs(currentX - startX);
                    height = Math.abs(currentY - startY);
                    rectList.add(new Rectangle2D.Double(beginX, beginY, width, height));
                    repaint();
                }
            }
            
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
                if (has2draw) {
                    currentX = e.getX();
                    currentY = e.getY();
                    repaint();
		}
            }
            
        });
        
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
                if (e.getKeyCode() == 00 && rectList.size() > 0)
                    rectList.remove(rectList.size()-1);
                
                System.out.println(e.getKeyCode());
            }
            
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    
        
        isdrawing = true;
        
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(img, null, 0, 0);

        if (candraw)
        {
            for (Rectangle2D r2d : rectList)
            {
                g2d.setColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
                g2d.fill(r2d);

                g2d.setColor(Color.black);
                g2d.draw(r2d);
            }
        }
        
        if (has2draw)
        {
            double beginX, beginY, width, height;
            beginX = Math.min(startX, currentX);
            beginY = Math.min(startY, currentY);
            width = Math.abs(currentX - startX);
            height = Math.abs(currentY - startY);

            Rectangle2D r = new Rectangle2D.Double(beginX, beginY, width, height);
            
            g2d.setColor(new Color(1.0f, 0.1f, 0.1f, 0.2f));
            g2d.fill(r);
            
            g2d.setColor(Color.black);
            g2d.draw(r);
        }
        
        isdrawing = false;
    }
}
