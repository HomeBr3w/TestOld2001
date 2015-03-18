package opencv2test.Support;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class DrawLineClass extends JFrame {

    public DrawLineClass(String title) throws HeadlessException {
        super(title);
    }   
    
    public DrawLineState dlstate;
}
