package opencv2test.Support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DrawLineState extends JPanel
{
    private BufferedImage img;
    public JSlider jslide;
    
    private int x1, x2, y1, y2;
    
    public float rotation = 0.0f;

    public DrawLineState(BufferedImage img) {
        this.img = img;
        this.setBounds(0, 0, img.getWidth(), img.getHeight());
        this.y1 = this.y2 = Math.round(img.getHeight() / 2);
        this.x1 = 0;
        this.x2 = img.getWidth();
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(img, null, 0, 0);
        
        g2d.setColor(Color.red);
        g2d.drawLine(x1, y1, x2, y2);
        int stepSize = 20;
        for(int i = 0; i < stepSize; i++)
        {
            int res = ((img.getHeight() / 2) / stepSize) * i;
            g2d.drawLine(x1, y1 + res, x2, y2 + res);
            g2d.drawLine(x1, y1 - res, x2, y2 - res);
        }
    }
    
    public ChangeListener slistner = (ChangeEvent e) -> {
        float slval = jslide.getValue() / 1000f;
        float centerX = Math.round(img.getWidth() / 2.0f);
        int y3 = Math.round(img.getHeight() / 2);
        int val = Math.round((float)Math.atan(slval) * centerX);
        y1 = y3 - val;
        y2 = y3 + val;
        repaint();
        rotation = (float)Math.toDegrees(Math.tan(val / centerX)); // x * 0.09 => x = -1000 > -90 gr.
        //System.out.println(rotation);
    };
}