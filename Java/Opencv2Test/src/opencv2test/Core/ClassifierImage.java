/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test.Core;

import java.util.ArrayList;
import org.opencv.core.Mat;

/**
 *
 * @author Siebren
 */
public class ClassifierImage {

    private final String imageName;
    private final Mat image;
    private final float heightWidthRatio;
    private final float topDownRatio;
    //private final float leftRightRatio;
    private final ArrayList<Float> blackPercentage;
    private final float MARGIN = 10.0f;

    /**
     * Wrapper around the original Mat class. So we can give the image a name!
     *
     * @param name
     * @param image
     */
    public ClassifierImage(String name, Mat image) {
        this.imageName = name;
        this.image = Analyse.isolateImage(Analyse.deleteWhiteRows(image));
        this.heightWidthRatio = (float) image.cols() / (float) image.rows();
        this.blackPercentage = Analyse.getBlackPercentage(this.image);
        this.topDownRatio = blackPercentage.get(0) / blackPercentage.get(1);
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
    public ArrayList<Float> getBlackPercentage() {
        return blackPercentage;
    }
    
    public float getTopDownRatio()
    {
        return topDownRatio;
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
        float error = 0.0f;
        float featureCount = 5.0f;
        float incrementError = 100.0f / featureCount;
        float topPercentage = Math.abs(toCompare.getBlackPercentage().get(0) - blackPercentage.get(0));
        if (topPercentage > MARGIN) {
            error += incrementError;
        }
        float bottomPercentage = Math.abs(toCompare.getBlackPercentage().get(1) - blackPercentage.get(1));
        if (bottomPercentage > MARGIN) {
            error += incrementError;
        }
        float totalPercentage = Math.abs(toCompare.getBlackPercentage().get(2) - blackPercentage.get(2));
        if (totalPercentage > MARGIN) {
            error += incrementError;
        }
        float diffArea = Math.abs(toCompare.getHeightWidthRatio() - heightWidthRatio);
        if (diffArea > 0.005f) {
            error += incrementError;
        }
        
        float diffTopDownRatio = Math.abs(toCompare.getTopDownRatio() - topDownRatio);
        if(diffTopDownRatio > 0.01f)
        {
            error += incrementError;
        }
        //System.out.println("VVD DING: " + toCompare.getBlackPercentage().get(0) + " " + toCompare.getBlackPercentage().get(1));
        //System.out.println("ORIG: " + blackPercentage.get(0) + " " + blackPercentage.get(1));
        return error;
    }
}
