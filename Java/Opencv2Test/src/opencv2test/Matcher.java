/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test;

import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Siebren
 */
public class Matcher {

    public static final int DEFAULT_ROTATIONS = 16;
    public static final boolean DEFAULT_MIRRORMODE = false;

    private final MatchResults matchResults = new MatchResults();
    private final boolean mirrorImage;
    private final int rotations;
    private final ArrayList<ClassifierImage> images;

    public Matcher(ArrayList<ClassifierImage> images) {
        this.images = images;
        this.rotations = DEFAULT_ROTATIONS;
        this.mirrorImage = DEFAULT_MIRRORMODE;
    }

    public Matcher(ArrayList<ClassifierImage> images, int rotations, boolean mirrorImage) {
        this.images = images;
        this.rotations = rotations;
        this.mirrorImage = mirrorImage;
    }

    public MatchResult matchImage(Mat image) {
        matchResults.clear();
        for (ClassifierImage ci : images) {
            matchResults.addResult(compareImage(ci, image));
        }

        return matchResults.getBestMatch();
    }

    private MatchResult compareImage(ClassifierImage ci, Mat image) {
        float difference = 0;
        Imgproc.resize(image, image, new Size(Opencv2Test.IMAGE_WIDTH, Opencv2Test.IMAGE_HEIGHT));

        for (int x = 1; x < image.cols() - 1; x++) {
            for (int y = 1; y < image.rows() - 1; y++) {
                if (ci.getImage().get(y, x)[0] != image.get(y, x)[0]) {
                    difference++;
                }
            }
        }
        float result = 1.0f - (difference / (image.rows() * image.cols()));
        result *= 100.0f;
        //System.out.println("Diff: " + difference + " Pixels: " + totalPixels + " Result: " + result);
        return new MatchResult(result, ci, image);
    }

    public void addImage(String imageName, Mat image) {
        images.add(new ClassifierImage(imageName, image));
    }

    public boolean removeImage(Mat image) {
        return images.remove(image);
    }
}
