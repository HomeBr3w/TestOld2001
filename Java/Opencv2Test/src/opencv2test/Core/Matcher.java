/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test.Core;

import java.util.ArrayList;
import opencv2test.Support.MatchResult;
import opencv2test.Support.MatchResults;
import org.opencv.core.Mat;

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
        ClassifierImage source = new ClassifierImage("sourceimg", image, -1);
        float conf = 100.0f - ci.compare(source);
        return new MatchResult(conf, ci, source.getImage());
    }

    public void addImage(String imageName, Mat image, int noteDuration) {
        images.add(new ClassifierImage(imageName, image, noteDuration));
    }

    public boolean removeImage(ClassifierImage image) {
        return images.remove(image);
    }
    
    public ArrayList<MatchResult> getAllResults()
    {
        return matchResults.getResults();
    }
}
