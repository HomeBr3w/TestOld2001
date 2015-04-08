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
import opencv2test.Core.MidiSequence;
import opencv2test.Support.MatchResult;
import opencv2test.Support.MidiTrack;
import opencv2test.Support.Note;
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
        classifierImages.add(new ClassifierImage("sleutel", Highgui.imread("C:\\Kees\\sleutel.jpg"), -1f));
        classifierImages.add(new ClassifierImage("kwartnoot", Highgui.imread("C:\\Kees\\kwartnoot.jpg"), 1f));
        classifierImages.add(new ClassifierImage("halvenoot", Highgui.imread("C:\\Kees\\halve noot.jpg"), 2f));
        classifierImages.add(new ClassifierImage("hele noot", Highgui.imread("C:\\Kees\\hele noot.jpg"), 4f));
        classifierImages.add(new ClassifierImage("#", Highgui.imread("C:\\Kees\\#.jpg"), -1f));
        classifierImages.add(new ClassifierImage("achtste noot", Highgui.imread("C:\\Kees\\achtste noot.jpg"), 0.5f));
        classifierImages.add(new ClassifierImage("maatstreep", Highgui.imread("C:\\Kees\\maatstreep.jpg"), -1f));
        classifierImages.add(new ClassifierImage("tempoding", Highgui.imread("C:\\Kees\\tempoding.jpg"), -1f));
        classifierImages.add(new ClassifierImage("halfopdekop", Highgui.imread("C:\\Kees\\halfopdekop.jpg"), 2f));
        classifierImages.add(new ClassifierImage("hele met vermate", Highgui.imread("C:\\Kees\\vermate.jpg"), 4f));
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
        MidiSequence midiSeq = new MidiSequence(72);
        MidiTrack track = midiSeq.createTrack("track nummer 1");
        int channel = 0;
        int timer = 0;

        for (int i = 0; i < 21; i++) {
            Mat compareWith = Highgui.imread("C:\\Kees\\" + i + ".jpg");
            Matcher matcher = new Matcher(classifierImages);
            MatchResult result = matcher.matchImage(compareWith);

            //System.out.println(i + ") Result: " + result.getCompared().getName() + " Confidence: " + result.getConfidence() + " <- " + refList[i]);
            if (result.getCompared().getDuration() != -1) {
                float noteDuration = result.getCompared().getDuration();
                int duration = (int) (noteDuration * 100);
                track.addNote(channel, new Note(64, 100, timer, duration));
                System.out.println(i + ") Result: " + result.getCompared().getName() + " Error: " + result.getError() + " Adding note: Type: 64, Pitch 100, Duration: " + duration + " at tick nr. " + timer);
                timer += duration;
            }
            else
            {
                System.out.println(i + ") Result: " + result.getCompared().getName() + " Error: " + result.getError());
            }
        }
        midiSeq.writeToFile("testBV");
    }

}
