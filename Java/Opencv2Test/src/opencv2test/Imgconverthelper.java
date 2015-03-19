package opencv2test;

import com.sun.javafx.Utils;
import java.io.File;
import java.util.Arrays;
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
            img = Highgui.imread("/Volumes/Data/Dropbox/Minor/Kees/sleutel.jpg");
        }
        
        System.out.println(img.rows() + "," + img.cols());
        for (int r = 0; r < img.rows(); r++)
        {
            for (int c = 0; c < img.cols(); c++)
            {
                System.out.print((img.get(r, c)[0]<127)?"0":"1");
            }
            System.out.println();
        }
    }
}
