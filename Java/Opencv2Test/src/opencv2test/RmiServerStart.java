package opencv2test;

import Client.Main;
import com.sun.javafx.Utils;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import opencv2test.Remote.RmiServer;
import static opencv2test.Remote.RmiServerIntf.RMI_LOCATION;

public class RmiServerStart {
    public static void main(String[] args) throws Exception {
        if (Utils.isWindows()) {
            System.load(new File("C:\\opencv\\build\\java\\x64\\opencv_java2410.dll").getAbsolutePath());
        } else {
            System.load(new File("/opt/local/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());
        }
        
        RmiServer rs = new RmiServer();
        
        Naming.rebind(RMI_LOCATION, rs);

    }
}
