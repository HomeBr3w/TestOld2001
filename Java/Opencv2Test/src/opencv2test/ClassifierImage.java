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
public class ClassifierImage extends Mat {

    private final String imageName;
    private final Mat image;

    public ClassifierImage(String name, Mat image) {
        this.imageName = name;
        this.image = image;
    }

    public String getName() {
        return imageName;
    }

    public Mat getImage() {
        return image;
    }

}
