package opencv2test;

import java.rmi.Naming;
import opencv2test.Remote.RmiServer;
import static opencv2test.Remote.RmiServerIntf.RMI_LOCATION;

public class RmiServerStart {
    public static void main(String[] args) throws Exception {
        RmiServer rs = new RmiServer();
        
        Naming.rebind(RMI_LOCATION, rs);

    }
}
