package opencv2test.Remote;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JProgressBar;

public interface RmiServerIntf extends Remote
{
    public static final int RMI_REGISTER_ID = 1099;
    public static final String RMI_LOCATION = "//192.168.0.27/RmiServer";
    
    public String Rotate (String Source, double Degrees) throws RemoteException;
    public BufferedImage RemoveButSelected (BufferedImage Source, ArrayList<Rectangle2D> KeepList) throws RemoteException;
    public File Start (BufferedImage Source, JProgressBar ProgressBar) throws RemoteException;
    
    public String version() throws RemoteException;
}
