package opencv2test.Remote;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JProgressBar;
import opencv2test.Core.Analyse;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

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
    public BufferedImage Rotate(BufferedImage Source, double Degrees) throws RemoteException {
        Mat img = Analyse.getMatFromBufferedImage(Source);
        Analyse.rotate(img, Degrees, img);
        return Analyse.getBufferedImgFromMat(img);
    }

    @Override
    public BufferedImage RemoveButSelected(BufferedImage Source, ArrayList<Rectangle2D> KeepList) throws RemoteException {
        Mat img = Analyse.getMatFromBufferedImage(Source);
        
        Mat img2 = new Mat(img.size(), img.type());
        
        img2.setTo(new Scalar(255.0, 255.0, 255.0));
        for (Rectangle2D r : KeepList)
        {
            for (int x = (int)Math.round(r.getX()); x < (int)Math.round(r.getX()+r.getWidth()); x++)
            {
                for (int y = (int)Math.round(r.getY()); y < (int)Math.round(r.getY()+r.getHeight()); y++)
                {
                    img2.put(y, x, img.get(y, x));
                }
            }
        }
        
        return Analyse.getBufferedImgFromMat(img2.submat(new Rect(0,0, Source.getWidth(), Source.getHeight())));
    }

    @Override
    public File Start(BufferedImage Source, JProgressBar ProgressBar) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String version() {
        return "Version 1 " + System.currentTimeMillis();
    }
}
