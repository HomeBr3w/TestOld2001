/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test.Support;

import opencv2test.Core.ClassifierImage;
import org.opencv.core.Mat;

/**
 *
 * @author Siebren
 */
public class MatchResult {

    private final Mat image;
    private final ClassifierImage compare;
    private final float error;

    /**
     * Creates a new instance of MatchResult.
     * This is a data-holding class which is not doing any calculations.
     * @param error
     * @param compare
     * @param image 
     */
    public MatchResult(float error, ClassifierImage compare, Mat image) {
        this.error = error;
        this.compare = compare;
        this.image = image;
    }

    /**
     * Returns the error of this match.
     * @return 
     */
    public float getError() {
        return error;
    }

    /**
     * Returns the image which is compared to the classifierImage
     * @return 
     */
    public Mat getImage() {
        return image;
    }

    /**
     * Returns the image which is being compared with the original image
     * @return 
     */
    public ClassifierImage getCompared() {
        return compare;
    }

}
