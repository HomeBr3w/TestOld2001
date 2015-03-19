package opencv2test;

import com.sun.javafx.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import opencv2test.Core.Analyse;
import opencv2test.Core.ClassifierImage;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class Imgconverthelper {

    public static void main(String[] args) {
        Mat img;
        if (Utils.isWindows()) {
            System.load(new File("C:\\opencv\\build\\java\\x64\\opencv_java2410.dll").getAbsolutePath());
            img = Highgui.imread("C:/opencv/img.jpg");
        } else {
            System.load(new File("/opt/local/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());
            img = Highgui.imread("/Volumes/Data/Dropbox/Minor/Kees/maatstreep.jpg");
        }
        
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

        Analyse.encryptImage(classifierImages.get(0).getImage(), true);
    }
}
