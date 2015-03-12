/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test.Core;

import static opencv2test.Core.Analyse.isolateImage;
import org.opencv.core.Mat;

/**
 *
 * @author Siebren
 */
public class ClassifierImage {

    private final String imageName;
    private final Mat image;
    private final float heightWidthRatio;
    private final float blackPercentage;
    private final float MARGIN = 0.1f;

    /**
     * Wrapper around the original Mat class. So we can give the image a name!
     *
     * @param name
     * @param image
     */
    public ClassifierImage(String name, Mat image) {
        this.imageName = name;
        this.image = isolateImage(image);
        this.heightWidthRatio = (float)image.cols() / (float)image.rows();
        float countBlack = 0;
        float totalPixels = image.cols() * image.rows();
        for (int r = 0; r < image.rows(); r++) {
            for (int c = 0; c < image.cols(); c++) {
                if (image.get(r, c)[0] < 127.0) {
                    countBlack++;
                }
            }
        }
        this.blackPercentage = (countBlack / totalPixels) * 100.0f;
    }

    /**
     * Gets the name of this classifier image
     *
     * @return
     */
    public String getName() {
        return imageName;
    }

    /**
     * Returns the Image which is being hold in this instance.
     *
     * @return
     */
    public Mat getImage() {
        return image;
    }

    /**
     * Returns the percentage of black pixels in the image
     *
     * @return
     */
    public float getBlackPercentage() {
        return blackPercentage;
    }

    /**
     * Returns the height/width ratio of the defined image
     *
     * @return
     */
    public float getHeightWidthRatio() {
        return heightWidthRatio;
    }

    public float compare(ClassifierImage toCompare) {
        float error = 100.0f;
        float diffPercentage = Math.abs(toCompare.getBlackPercentage() - blackPercentage);
        if(diffPercentage <= MARGIN)
        {
            error -= 50.0f;
        }
        float diffArea = Math.abs(toCompare.getHeightWidthRatio() - heightWidthRatio);
        if(diffArea <= MARGIN)
        {
            error -= 50.0f;
        }
        
        return error;
    }
}
