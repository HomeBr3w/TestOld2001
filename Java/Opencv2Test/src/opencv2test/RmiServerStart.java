package opencv2test;

import com.sun.javafx.Utils;
import java.io.File;
import java.rmi.Naming;
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
