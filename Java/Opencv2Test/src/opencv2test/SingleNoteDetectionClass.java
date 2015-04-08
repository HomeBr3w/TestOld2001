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
import opencv2test.Support.MatchResult;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Siebren
 */
public class SingleNoteDetectionClass
{
    private ArrayList<ClassifierImage> classifierImages;
    private Matcher matcher;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Mat img;

        if (Utils.isWindows())
        {
            System.load(new File("C:\\opencv\\build\\java\\x64\\opencv_java2410.dll").getAbsolutePath());
            img = Highgui.imread("C:/opencv/img.jpg");
        }
        else
        {
            System.load(new File("/opt/local/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());
            img = Highgui.imread("/Users/jasper/Desktop/img.jpg");
        }
        SingleNoteDetectionClass program = new SingleNoteDetectionClass();
        program.init();
        program.run(img);
    }

    public void run(Mat img)
    {
        Mat grey = img.clone();
        Analyse.convertToGrey(grey);
        Analyse.thresholdISOBlack(grey, grey);
        Mat rowCounted = Analyse.averageRows(grey);
        Analyse.thresholdISOBlack(rowCounted, rowCounted);
        ArrayList<ArrayList<Integer>> blobList = Analyse.oneDimensionalHorizontalBlobFinder(rowCounted);
        ArrayList<Mat> bars = Analyse.getROIperBlob(blobList, img);
        matcher = new Matcher(classifierImages);

        //6
        Mat b = bars.get(0); //1de balk
        Mat filteredImage = Analyse.filterDifference(b, Analyse.averageRows(b));
        ArrayList<ArrayList<Integer>> noteList = Analyse.oneDimensionalVerticalBlobFinder(Analyse.averageCols(filteredImage));
        ArrayList<Integer> noteToFind = noteList.get(3); // 4e plaatje
        MatchResult r = Analyse.matchBlob(filteredImage, noteToFind, matcher);

        int result = Analyse.getNoteHeight(r.getSourceImage(), b.rows(), 12);
        System.out.println("Note height: " + result);

        showResult(filteredImage);
        showResult(r.getSourceImage().getImage());
        
        //Teken gevonden blobs
        Analyse.drawOneDimensionalBlobsHorizontal(blobList, img);

        showResult(img);
    }

    private void init()
    {
        classifierImages = new ArrayList<>();
        classifierImages.add(new ClassifierImage("kwartnoot", Highgui.imread("C:\\Kees\\kwartnoot.jpg"), 1f));
        classifierImages.add(new ClassifierImage("halvenoot", Highgui.imread("C:\\Kees\\halve noot.jpg"), 2f));
        classifierImages.add(new ClassifierImage("halfopdekop", Highgui.imread("C:\\Kees\\halfopdekop.jpg"), 2f));
    }

}
