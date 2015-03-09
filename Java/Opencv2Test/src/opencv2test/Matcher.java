/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test;

import java.util.ArrayList;
import org.opencv.core.Mat;

/**
 *
 * @author Siebren
 */
public class Matcher {

    public static final int DEFAULT_ROTATIONS = 16;
    public static final boolean DEFAULT_MIRRORMODE = false;
    
    private final boolean mirrorImage;
    private int rotations;
    private ArrayList<ClassifierImage> images;

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
        
        return null;
    }

    public void addImage(String imageName, Mat image) {
        images.add(new ClassifierImage(imageName, image));
    }

    public boolean removeImage(Mat image) {
        return images.remove(image);
    }

}
