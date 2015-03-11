/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test;

import opencv2test.Core.Analyse;
import com.sun.javafx.Utils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import opencv2test.Core.ClassifierImage;
import opencv2test.Core.Matcher;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author jasper
 */
public class Opencv2Test {

    public static final int IMAGE_WIDTH = 30;
    public static final int IMAGE_HEIGHT = 100;

    /**
     * @param args the command line arguments
     */   
    public static void main(String[] args) {
        Mat img;

        if (Utils.isWindows()) {
            System.load(new File("C:\\opencv\\build\\java\\x64\\opencv_java2410.dll").getAbsolutePath());
            img = Highgui.imread("C:/opencv/img.jpg");
        } else {
            System.load(new File("/opt/local/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());
            img = Highgui.imread("/Users/jasper/Desktop/img.jpg");
        }
        start(img);
    }
    
    public static void start(Mat img)
    {
        Mat grey = img.clone();

        // Convert de afbeelding naar grijswaarden
        Analyse.convertToGrey(grey);

        // Isoleer de zwarte stukken
        Analyse.thresholdISOBlack(grey, grey);

        // Bereken voor alle Rows het gemiddelde aan pixels in de columns.
        Mat rowCounted = Analyse.averageRows(grey);
        Analyse.thresholdISOBlack(rowCounted, rowCounted);

        // Vind het aantal blobs en kijk of het 'systemen' zijn.
        ArrayList<ArrayList<Integer>> blobList = Analyse.oneDimensionalHorizontalBlobFinder(rowCounted);

        //Uitsnedes maken van notenbalken en het kwardratische verticalegemiddelde berekenen per balk
        ArrayList<Mat> rois = Analyse.getROIperBlob(blobList, img);

        //Setup classifier & matcher
        ArrayList<ClassifierImage> classifierImages = new ArrayList<>();
        classifierImages.add(new ClassifierImage("sleutel", Highgui.imread("C:\\Kees\\sleutel.jpg")));
        classifierImages.add(new ClassifierImage("kwartnoot", Highgui.imread("C:\\Kees\\kwartnoot.jpg")));
        classifierImages.add(new ClassifierImage("halvenoot", Highgui.imread("C:\\Kees\\halve noot.jpg")));
        classifierImages.add(new ClassifierImage("hele noot", Highgui.imread("C:\\Kees\\hele noot.jpg")));
        classifierImages.add(new ClassifierImage("#", Highgui.imread("C:\\Kees\\hashtag.jpg")));
        classifierImages.add(new ClassifierImage("achtste noot", Highgui.imread("C:\\Kees\\achtste noot.jpg")));

        Matcher blobMatcher = null;
        blobMatcher = new Matcher(classifierImages);

        for (Mat v : rois) {

            Mat filteredImage = Analyse.filterDifference(v, Analyse.averageRows(v));

            // Vind het aantal blobs en kijk of het 'noten' zijn.
            ArrayList<ArrayList<Integer>> noteList = Analyse.oneDimensionalVerticalBlobFinder(Analyse.averageCols(filteredImage));

            /*
            Imgproc.cvtColor(filteredImage, filteredImage, Imgproc.);
            Mat circles = new Mat();
            int minRadius = 10;
            int maxRadius = 18;
            Imgproc.HoughCircles(img, circles, Imgproc.CV_HOUGH_GRADIENT, 1, minRadius, 120, 10, minRadius, maxRadius);
            
            for(int i = 0; i < circles.cols(); i++) {
                double[] circle = circles.get(0, i);
                Point center = new Point((int)Math.round(circle[0]), (int)Math.round(circle[1]));
                int radius = (int)Math.round(circle[2]);
                Core.circle( img, center, 3, new Scalar(0,255,0), -1, 8, 0 );
                Core.circle(img, center, radius, new Scalar(0,0,255), 3, 8, 0);
            }
            */
            
            Analyse.drawOneDimensionalBlobsVertical(noteList, filteredImage);

            Analyse.matchBlobs(v, noteList, blobMatcher);

            showResult(filteredImage);
        }

        //Teken gevonden blobs
        Analyse.drawOneDimensionalBlobsHorizontal(blobList, img);

        showResult(img);

        //showResult(grey);
        //showResult(rowCounted);
        //Highgui.imwrite("/Users/jasper/Desktop/imgx.jpg", rowCounted);
    }

    public static void showResult(Mat img) {
        Imgproc.resize(img, img, img.size());
        MatOfByte matOfByte = new MatOfByte();
        Highgui.imencode(".jpg", img, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
