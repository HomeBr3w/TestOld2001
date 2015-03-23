package opencv2test;

import java.rmi.Naming;
import opencv2test.Remote.RmiServerIntf;

public class TestRMICLIENT {
    public static void main(String[] args) throws Exception {
        RmiServerIntf obj = (RmiServerIntf)Naming.lookup(RmiServerIntf.RMI_LOCATION);
        
        System.out.println(obj.version());
    }
}
