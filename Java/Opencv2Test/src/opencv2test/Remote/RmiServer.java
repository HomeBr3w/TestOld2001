package opencv2test.Remote;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JProgressBar;

public class RmiServer extends UnicastRemoteObject implements RmiServerIntf
{

    public RmiServer() throws Exception
    {
        super(0);
        
        try {
            LocateRegistry.createRegistry(RMI_REGISTER_ID); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry already exists.");
        }
 
        System.out.println("PeerServer bound in registry");
    }
    
    @Override
    public BufferedImage Rotate(BufferedImage Source, int Degrees) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BufferedImage RemoveButSelected(BufferedImage Source, ArrayList<Rectangle2D> KeepList) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File Start(BufferedImage Source, JProgressBar ProgressBar) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String version() {
        return "Version 1";
    }
}
