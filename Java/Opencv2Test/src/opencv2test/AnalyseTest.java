/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test;

import com.sun.javafx.Utils;
import java.io.File;
import opencv2test.Core.Analyse;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Siebren
 */
public class AnalyseTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Mat img;
        if (Utils.isWindows())
        {
            System.load(new File("C:\\opencv\\build\\java\\x64\\opencv_java2410.dll").getAbsolutePath());
            img = Highgui.imread("C:/Kees/kaartnoot.jpg");
        }
        else
        {
            System.load(new File("/opt/local/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());
            img = Highgui.imread("/Users/jasper/Desktop/img.jpg");
        }
        HeightDetectTest program = new HeightDetectTest();
        //Analyse.getNoteCenter(img);
        Opencv2Test.showResult(img);
    }

}
