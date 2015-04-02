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
public class ClassifierImage
{
    private static final boolean DEBUG_VAL = false;
    private final String imageName;
    private final Mat image;
    private final float heightWidthRatio;
    private final float topDownRatio;
    //private final float leftRightRatio;
    private final ArrayList<Float> blackPercentage;
    private final float noteDuration;

    /**
     * Wrapper around the original Mat class. So we can give the image a name!
     *
     * @param name
     * @param image
     * @param noteDuration
     */
    public ClassifierImage(String name, Mat image, float noteDuration)
    {
        this.imageName = name;
        this.image = Analyse.isolateImage(Analyse.deleteWhiteRows(image));
        this.heightWidthRatio = Analyse.getHeightWidthRatio(this.image);
        this.blackPercentage = Analyse.getBlackPercentage(this.image);
        this.topDownRatio = blackPercentage.get(0) / blackPercentage.get(1);
        this.noteDuration = noteDuration;
    }

    /**
     * Gets the name of this classifier image
     *
     * @return
     */
    public String getName()
    {
        return imageName;
    }

    /**
     * Returns the Image which is being hold in this instance.
     *
     * @return
     */
    public Mat getImage()
    {
        return image;
    }

    /**
     * Returns the percentage of black pixels in the image
     *
     * @return
     */
    public ArrayList<Float> getBlackPercentage()
    {
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
    public float getHeightWidthRatio()
    {
        return heightWidthRatio;
    }

    public float compare(ClassifierImage toCompare)
    {
        if (DEBUG_VAL)
        {
            System.out.println("===Comparing to " + imageName);
        }
        float error = 0.0f;
        float featureCount = 4.0f;
        float incrementError = 100.0f / featureCount;
        float topPercentage = Math.abs(toCompare.getBlackPercentage().get(0) - blackPercentage.get(0));
        if (DEBUG_VAL)
        {
            System.out.println("  TOP: ToCompareValue: " + toCompare.getBlackPercentage().get(0) + " ThisValue: " + blackPercentage.get(0));
        }
        if (topPercentage > 5.5f)
        {
            error += incrementError;
            if (DEBUG_VAL)
            {
                System.out.println("    ERROR++: Top Difference: " + topPercentage);
            }
        }
        float bottomPercentage = Math.abs(toCompare.getBlackPercentage().get(1) - blackPercentage.get(1));
        if (DEBUG_VAL)
        {
            System.out.println("  BOTTOM: ToCompareValue: " + toCompare.getBlackPercentage().get(1) + " ThisValue: " + blackPercentage.get(1));
        }
        if (bottomPercentage > 3f)
        {
            error += incrementError;
            if (DEBUG_VAL)
            {
                System.out.println("    ERROR++: Bottom Difference: " + bottomPercentage);
            }
        }
        float totalPercentage = Math.abs(toCompare.getBlackPercentage().get(2) - blackPercentage.get(2));
        if (DEBUG_VAL)
        {
            System.out.println("  TOTAL: ToCompareValue: " + toCompare.getBlackPercentage().get(2) + " ThisValue: " + blackPercentage.get(2));
        }
        if (totalPercentage > 7.5f)
        {
            error += incrementError;
            if (DEBUG_VAL)
            {
                System.out.println("    ERROR++: Total Difference: " + totalPercentage);
            }
        }

        float hwRatio = toCompare.getHeightWidthRatio();
        float diffArea = Math.abs(hwRatio - heightWidthRatio);
        if (DEBUG_VAL)
        {
            System.out.println("  HW: ToCompareValue: " + toCompare.getHeightWidthRatio() + " ThisValue: " + heightWidthRatio);
        }
        if (diffArea > 0.03f)
        {
            error += incrementError;
            if (DEBUG_VAL)
            {
                System.out.println("    ERROR++: HW-Difference: " + diffArea);
            }
        }
        if (DEBUG_VAL)
        {
            System.out.println("===Fin.");
        }

        //System.out.println("========================================== END");
        //System.out.println("VVD DING: " + toCompare.getBlackPercentage().get(0) + " " + toCompare.getBlackPercentage().get(1));
        //System.out.println("ORIG: " + blackPercentage.get(0) + " " + blackPercentage.get(1));
        return error;
    }

    public float getDuration()
    {
        return noteDuration;
    }
}
