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
public class DebugClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (Utils.isWindows()) {
            System.load(new File("C:\\opencv\\build\\java\\x64\\opencv_java2410.dll").getAbsolutePath());
        } else {
            System.load(new File("/opt/local/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());
        }
        //Setup classifier & matcher
        ArrayList<ClassifierImage> classifierImages = new ArrayList<>();
        classifierImages.add(new ClassifierImage("sleutel", Highgui.imread("C:\\Kees\\sleutel.jpg")));
        classifierImages.add(new ClassifierImage("kwartnoot", Highgui.imread("C:\\Kees\\kwartnoot.jpg")));
        classifierImages.add(new ClassifierImage("halvenoot", Highgui.imread("C:\\Kees\\halve noot.jpg")));
        classifierImages.add(new ClassifierImage("hele noot", Highgui.imread("C:\\Kees\\hele noot.jpg")));
        classifierImages.add(new ClassifierImage("#", Highgui.imread("C:\\Kees\\#.jpg")));
        classifierImages.add(new ClassifierImage("achtste noot", Highgui.imread("C:\\Kees\\achtste noot.jpg")));
        classifierImages.add(new ClassifierImage("maatstreep", Highgui.imread("C:\\Kees\\maatstreep.jpg")));
        classifierImages.add(new ClassifierImage("tempoding", Highgui.imread("C:\\Kees\\tempoding.jpg")));
        classifierImages.add(new ClassifierImage("halfopdekop", Highgui.imread("C:\\Kees\\halfopdekop.jpg")));
        classifierImages.add(new ClassifierImage("hele met vermate", Highgui.imread("C:\\Kees\\vermate.jpg")));
        /*
         for (ClassifierImage i : classifierImages) {
         showResult(i.getImage());
         }*/

        String[] refList = new String[]{
            "sleutel",
            "#",
            "tempoding",
            "hele",
            "kwartnoot",
            "halve",
            "halve",
            "halve",
            "|",
            "halfopdekop",
            "halfopdekop",
            "hele met vermate",
            "halfopdekop",
            "halfopdekop",
            "halfopdekop",
            "halfopdekop",
            "|",
            "halfopdekop",
            "halve",
            "hele met vermate",
            "|"
        };
        
        for (int i = 0; i < 21; i++) {
            Mat compareWith = Highgui.imread("C:\\Kees\\" + i + ".jpg");
            Matcher matcher = new Matcher(classifierImages);
            MatchResult result = matcher.matchImage(compareWith);
            
            ArrayList<Float> res = Analyse.getBlackPercentage(result.getImage());
            ArrayList<Float> res1 = Analyse.getBlackPercentage(result.getCompared().getImage());
            System.out.println(i + ") Result: " + result.getCompared().getName() + " Confidence: " + result.getConfidence() + " <- " + refList[i]); 
            /*if (result.getConfidence() < 100) {
                showResult(result.getImage());
                showResult(result.getCompared().getImage());
                System.out.println(i + " Result: " + result.getCompared().getName() + " Confidence: " + result.getConfidence());
            }*/
            /*
             for(MatchResult m : matcher.getAllResults())
             {
             System.out.println("Image: " + i + " Compared with: " + m.getCompared().getName() + " Conf:" + m.getConfidence());
             }*/
            //showResult(compareWith);
        }
    }

}
