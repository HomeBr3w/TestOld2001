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

    private final ClassifierImage toBeChecked;
    private final ClassifierImage toCompareWith;
    private final float error;

    /**
     * Creates a new instance of MatchResult.
     * This is a data-holding class which is not doing any calculations.
     * @param error 
     * @param toCompareWith 
     * @param toBeChecked 
     */
    public MatchResult(float error, ClassifierImage toCompareWith, ClassifierImage toBeChecked) {
        this.error = error;
        this.toCompareWith = toCompareWith;
        this.toBeChecked = toBeChecked;
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
    public ClassifierImage getImage() {
        return toBeChecked;
    }

    /**
     * Returns the image which is being compared with the original image
     * @return 
     */
    public ClassifierImage getCompared() {
        return toCompareWith;
    }

}
