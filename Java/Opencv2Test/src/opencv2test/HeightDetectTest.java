/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test;

import com.sun.javafx.Utils;
import java.io.File;
import java.util.ArrayList;
import opencv2test.Core.Analyse;
import opencv2test.Core.ClassifierImage;
import opencv2test.Core.Matcher;
import static opencv2test.Opencv2Test.showResult;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Siebren
 */
public class HeightDetectTest {

    private ArrayList<ClassifierImage> classifierImages;
    private Matcher matcher;

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
        HeightDetectTest program = new HeightDetectTest();
        program.init();
        program.run(img);
    }

    public void init() {
        classifierImages = new ArrayList<>();/*
        classifierImages.add(new ClassifierImage("sleutel", Highgui.imread("C:\\Kees\\sleutel.jpg"), -1f));*/
        classifierImages.add(new ClassifierImage("kwartnoot", Highgui.imread("C:\\Kees\\kwartnoot.jpg"), 1f));
        classifierImages.add(new ClassifierImage("halvenoot", Highgui.imread("C:\\Kees\\halve noot.jpg"), 2f));/*
        classifierImages.add(new ClassifierImage("hele noot", Highgui.imread("C:\\Kees\\hele noot.jpg"), 4f));
        classifierImages.add(new ClassifierImage("#", Highgui.imread("C:\\Kees\\#.jpg"), -1f));
        classifierImages.add(new ClassifierImage("achtste noot", Highgui.imread("C:\\Kees\\achtste noot.jpg"), 0.5f));
        classifierImages.add(new ClassifierImage("maatstreep", Highgui.imread("C:\\Kees\\maatstreep.jpg"), -1f));
        classifierImages.add(new ClassifierImage("tempoding", Highgui.imread("C:\\Kees\\tempoding.jpg"), -1f));
        classifierImages.add(new ClassifierImage("halfopdekop", Highgui.imread("C:\\Kees\\halfopdekop.jpg"), 2f));
        classifierImages.add(new ClassifierImage("hele met vermate", Highgui.imread("C:\\Kees\\vermate.jpg"), 4f));*/

    }

    public void run(Mat img) {
        Mat grey = img.clone();
        Analyse.convertToGrey(grey);
        Analyse.thresholdISOBlack(grey, grey);
        Mat rowCounted = Analyse.averageRows(grey);
        Analyse.thresholdISOBlack(rowCounted, rowCounted);
        ArrayList<ArrayList<Integer>> blobList = Analyse.oneDimensionalHorizontalBlobFinder(rowCounted);
        ArrayList<Mat> bars = Analyse.getROIperBlob(blobList, img);
        matcher = new Matcher(classifierImages);

        for (Mat v : bars) {
            System.out.println("Bar: ");
            Mat filteredImage = Analyse.filterDifference(v, Analyse.averageRows(v));
            ArrayList<ArrayList<Integer>> noteList = Analyse.oneDimensionalVerticalBlobFinder(Analyse.averageCols(filteredImage));
            Analyse.matchBlobs(filteredImage, noteList, matcher);
            showResult(filteredImage);
            System.out.println("============================================================");
            break;
        }

        //Teken gevonden blobs
        Analyse.drawOneDimensionalBlobsHorizontal(blobList, img);

        showResult(img);

        //showResult(grey);
        //showResult(rowCounted);
        //Highgui.imwrite("/Users/jasper/Desktop/imgx.jpg", rowCounted);
    }

}
