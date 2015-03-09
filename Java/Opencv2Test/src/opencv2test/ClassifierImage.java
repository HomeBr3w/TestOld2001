/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test;

import org.opencv.core.Mat;

/**
 *
 * @author Siebren
 */
public class ClassifierImage {

    private final String imageName;
    private final Mat image;

    /**
     * Wrapper around the original Mat class.
     * So we can give the image a name!
     * @param name
     * @param image 
     */
    public ClassifierImage(String name, Mat image) {
        this.imageName = name;
        this.image = image;
    }

    /**
     * Gets the name of this classifier image
     * @return 
     */
    public String getName() {
        return imageName;
    }

    /**
     * Returns the Image which is being hold in this instance.
     * @return 
     */
    public Mat getImage() {
        return image;
    }

}
