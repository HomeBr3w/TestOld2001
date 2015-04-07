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
import static opencv2test.Opencv2Test.showResult;
import opencv2test.Support.MatchResult;
import opencv2test.Support.MidiTrack;
import opencv2test.Support.Note;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Siebren
 */
public class HeightDetectTest
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
        HeightDetectTest program = new HeightDetectTest();
        program.init();
        program.run(img);
    }

    private void init()
    {
        classifierImages = new ArrayList<>();
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
        classifierImages.add(new ClassifierImage("stip", Highgui.imread("C:\\Kees\\stip.jpg"), -1f));
    }

    public void run(Mat img)
    {
        MidiSequence sequence = new MidiSequence();
        MidiTrack tr = sequence.createTrack("generated track");
        int currentChannel = 0;
        int currentTick = 0;
        Mat grey = img.clone();
        Analyse.convertToGrey(grey);
        Analyse.thresholdISOBlack(grey, grey);
        Mat rowCounted = Analyse.averageRows(grey);
        Analyse.thresholdISOBlack(rowCounted, rowCounted);
        ArrayList<ArrayList<Integer>> blobList = Analyse.oneDimensionalHorizontalBlobFinder(rowCounted);
        ArrayList<Mat> bars = Analyse.getROIperBlob(blobList, img);
        matcher = new Matcher(classifierImages);

        /*
         Loop through the bars and identify the corresponding blobs.
         */
        for (Mat singleBar : bars)
        {
            //Mat v = bars.get(2);
            System.out.println("Bar: ");
            Mat filteredImage = Analyse.filterDifference(singleBar, Analyse.averageRows(singleBar));
            ArrayList<ArrayList<Integer>> noteList = Analyse.oneDimensionalVerticalBlobFinder(Analyse.averageCols(filteredImage));
            ArrayList<MatchResult> results = Analyse.matchBlobs(filteredImage, noteList, matcher);
            ArrayList<Note> notes = new ArrayList<>();

            /*
             Match all the images and create notes.
             */
            ArrayList<Integer> sharps = new ArrayList<>();
            ArrayList<Integer> flats = new ArrayList<>();
            int keyLocation = -1;
            for (MatchResult r : results)
            {
                float duration = r.getCompared().getDuration() * 100f;
                if (duration > 0)
                {
                    int noteHeight = Analyse.getNoteHeight(r.getSourceImage(), singleBar.rows(), keyLocation);
                    Note toAdd = new Note(noteHeight, 120, currentTick, (int) duration);
                    if (sharps.contains(toAdd.getNote()))
                    {
                        toAdd.applySharp();
                    }
                    if (flats.contains(toAdd.getNote()))
                    {
                        toAdd.applyFlat();
                    }
                    notes.add(toAdd);

                    System.out.println("Adding " + r.getCompared().getName() + "("+  toAdd.getNote() + ")"+  " at tick: " + currentTick + " with duration: " + duration);
                    currentTick += (int) duration;
                }
                //Apply a point to the last known note
                if ("stip".equals(r.getCompared().getName()) && notes.size() > 0)
                {
                    Note toPoint = notes.get(notes.size() - 1);
                    duration -= toPoint.getDuration();
                    toPoint.applyPoint();
                    duration += toPoint.getDuration();
                }
                //If there is a key-note, set the keyLocation to the specified key.
                else if ("sleutel".equals(r.getCompared().getName()))
                {
                    keyLocation = Analyse.getGLocation(r.getSourceImage(), singleBar.rows());
                }
                //If there is a sharp on this location, apply a sharp to this row
                else if ("#".equals(r.getCompared().getName()))
                {
                    sharps.add(Analyse.getNoteHeight(r.getSourceImage(), singleBar.rows(), keyLocation));
                }
                //Apply flats if neccessary
                else if ("mol".equals(r.getCompared().getName()))
                {
                    flats.add(Analyse.getNoteHeight(r.getSourceImage(), singleBar.rows(), keyLocation));
                }
            }
            //Add all notes to the track.
            for (Note n : notes)
            {
                tr.addNote(currentChannel, n);
            }
            showResult(filteredImage);
            System.out.println("============================================================");
            currentChannel++;
            //break;
        }

        sequence.writeToFile("generated midi");

        //Teken gevonden blobs
        Analyse.drawOneDimensionalBlobsHorizontal(blobList, img);

        showResult(img);

        //showResult(grey);
        //showResult(rowCounted);
        //Highgui.imwrite("/Users/jasper/Desktop/imgx.jpg", rowCounted);
    }

}
