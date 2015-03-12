package opencv2test.Core;

import java.util.ArrayList;
import opencv2test.Support.MatchResult;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Analyse {

    public static Mat convertToGrey(Mat img) {
        Imgproc.cvtColor(img, img, 7);
        return img;
    }

    public static void thresholdISOBlack(Mat src, Mat trg) {
        Imgproc.threshold(src, trg, 150, 255, 0);
    }

    public static void printImage(Mat bron) {
        for (int r = 0; r < bron.rows(); r++) {
            for (int c = 0; c < bron.cols(); c++) {
                System.out.print(bron.get(r, c)[0]);
            }
            System.out.print('\n');
        }
    }

    public static Mat averageRows(Mat bron) {
        Mat rowCounted = new Mat(bron.rows(), 1, 16);
        Imgproc.cvtColor(rowCounted, rowCounted, 7); // Hacky methode om een leeg zwartwit plaatje te krijgen.
        for (int r = 0; r < bron.rows(); r++) {
            int count = 0;
            for (int c = 0; c < bron.cols(); c++) {
                count += bron.get(r, c)[0];
            }

            int v = Math.round(count / bron.cols());
            rowCounted.put(r, 0, new double[]{v, v, v});
        }

        return rowCounted;
    }

    public static Mat averageCols(Mat bron) {
        Mat colCounted = new Mat(100, bron.cols(), 16);
        Imgproc.cvtColor(colCounted, colCounted, 7); // Hacky methode om een leeg zwartwit plaatje te krijgen.

        for (int c = 0; c < bron.cols(); c++) {
            int count = 0;
            for (int r = 0; r < bron.rows(); r++) {
                count += bron.get(r, c)[0];
            }
            colCounted.get(0, c)[0] = Math.round(count / bron.rows()); // Gemmiddelde uitrekenen

            int v = Math.round(count / bron.rows());
            for (int i = 0; i < 100; i++) {
                colCounted.put(i, c, new double[]{v, v, v});
            }
        }

        return colCounted;
    }

    public static ArrayList<ArrayList<Integer>> oneDimensionalHorizontalBlobFinder(Mat bron) {
        ArrayList<Integer> found = new ArrayList<>();

        int count = 0;
        int mean = 0;
        int thresh_blob;

        for (int r = 0; r < bron.rows(); r++) {
            int c = (int) bron.get(r, 0)[0];
            if (c < 127) {
                if (found.size() > 1) {
                    mean += (r - found.get(count));
                    count++;
                }
                found.add(r);
            }
        }

        ArrayList<ArrayList<Integer>> blobList = new ArrayList<>();

        thresh_blob = Math.round(mean / count);
        int minval = found.get(0);
        int maxval = 0;
        int i;

        for (i = 1; i < found.size() - 1; i++) {
            if (found.get(i) - found.get(i - 1) > thresh_blob) {
                ArrayList<Integer> temp = new ArrayList<>();

                maxval = found.get(i - 1);

                temp.add(minval);
                temp.add(maxval);

                blobList.add(temp);

                minval = found.get(i);
            }
        }
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(minval);
        maxval = found.get(i);
        temp.add(maxval);
        blobList.add(temp);

        return blobList;
    }

    public static ArrayList<ArrayList<Integer>> oneDimensionalVerticalBlobFinder(Mat bron) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> found;

        int pointer = 0;
        int min = -1;

        while (pointer < bron.cols() - 1) {
            if (min < 0) {
                min = pointer;
                double a = bron.get(0, min)[0];
                if (a > 253.0) {
                    min = -1;
                }
            } else {
                double a = bron.get(0, pointer)[0];
                if (a > 253.0) {
                    found = new ArrayList<>();
                    found.add(min);
                    found.add(pointer);
                    list.add(found);
                    min = -1;
                }
            }
            pointer++;
        }

        double a = bron.get(0, pointer)[0];
        if (a > 127.0) {
            found = new ArrayList<>();
            found.add(min);
            found.add(pointer);
            list.add(found);
        }

        return list;
    }

    public static void Erosion(Mat bron, int dilation_size, int dilation_type) {

        Mat element = Imgproc.getStructuringElement(dilation_type,
                new Size(2 * dilation_size + 1, 2 * dilation_size + 1),
                new Point(dilation_size, dilation_size));
        /// Apply the dilation operation
        Imgproc.erode(bron, bron, element);
    }

    public static Mat filterDifference(Mat src, Mat filter) {
        //thresholdISOBlack(filter, filter);
        Mat nw = new Mat(src.rows(), src.cols(), 16);
        convertToGrey(nw);
        convertToGrey(src);

        for (int c = 0; c < src.cols(); c++) {
            for (int r = 0; r < src.rows(); r++) {
                //nw.at<uchar>(r, c) = (src.at<uchar>(r, c) < filter.at<uchar>(r, 0)) ? 254 : 0;
                //nw.at<uchar>(r, c) = filter.at<uchar>(r, 0);

                boolean a = (filter.get(r, 0)[0] > 180) ? true : false;
                boolean b = (src.get(r, c)[0] < 127) ? true : false;

                if (a && b) {
                    nw.put(r, c, new double[]{0, 0, 0});
                } else {
                    nw.put(r, c, new double[]{254, 254, 254});
                }
            }
        }

        Erosion(nw, 1, 0);

        return nw;
    }

    public static void drawOneDimensionalBlobsHorizontal(ArrayList<ArrayList<Integer>> blobList, Mat img) {
        for (int i = 0; i < blobList.size(); i++) {
            int margin = Math.round((blobList.get(i).get(1) - blobList.get(i).get(0)) / 4);

            Core.line(img, new Point(0, blobList.get(i).get(0) - margin * 3), new Point(img.cols(), blobList.get(i).get(0) - margin * 3), new Scalar(255, 0, 0), 3, 8, 0);
            Core.line(img, new Point(0, blobList.get(i).get(1) + margin * 3), new Point(img.cols(), blobList.get(i).get(1) + margin * 3), new Scalar(0, 255, 0), 3, 8, 0);

            Core.line(img, new Point(0, blobList.get(i).get(0) - margin * 2), new Point(img.cols(), blobList.get(i).get(0) - margin * 2), new Scalar(0, 0, 255), 1, 8, 0);
            Core.line(img, new Point(0, blobList.get(i).get(1) + margin * 2), new Point(img.cols(), blobList.get(i).get(1) + margin * 2), new Scalar(0, 0, 255), 1, 8, 0);

            Core.line(img, new Point(0, blobList.get(i).get(0) - margin), new Point(img.cols(), blobList.get(i).get(0) - margin), new Scalar(0, 0, 255), 1, 8, 0);
            Core.line(img, new Point(0, blobList.get(i).get(1) + margin), new Point(img.cols(), blobList.get(i).get(1) + margin), new Scalar(0, 0, 255), 1, 8, 0);

            Core.line(img, new Point(0, blobList.get(i).get(0)), new Point(img.cols(), blobList.get(i).get(0)), new Scalar(0, 0, 255), 1, 8, 0);
            Core.line(img, new Point(0, blobList.get(i).get(1)), new Point(img.cols(), blobList.get(i).get(1)), new Scalar(0, 0, 255), 1, 8, 0);

            Core.line(img, new Point(0, blobList.get(i).get(0) + margin), new Point(img.cols(), blobList.get(i).get(0) + margin), new Scalar(0, 0, 255), 1, 8, 0);
            Core.line(img, new Point(0, blobList.get(i).get(1) - margin), new Point(img.cols(), blobList.get(i).get(1) - margin), new Scalar(0, 0, 255), 1, 8, 0);

            Core.line(img, new Point(0, blobList.get(i).get(0) + margin * 2), new Point(img.cols(), blobList.get(i).get(0) + margin * 2), new Scalar(0, 0, 255), 1, 8, 0);
        }
    }

    public static void drawOneDimensionalBlobsVertical(ArrayList<ArrayList<Integer>> blobList, Mat img) {
        Mat cropped = img.clone();
        for (int i = 0; i < blobList.size() - 1; i++) {
            Core.line(img, new Point(blobList.get(i).get(0), 5), new Point(blobList.get(i).get(0), img.rows() - 5), new Scalar(0, 255, 255));
            Core.line(img, new Point(blobList.get(i).get(1), 5), new Point(blobList.get(i).get(1), img.rows() - 5), new Scalar(0, 255, 255));

            Rect roi = new Rect(blobList.get(i).get(0), 5, blobList.get(i).get(1) - blobList.get(i).get(0), img.rows() - 5);
            Mat toWrite = cropped.submat(roi);

            //boolean result = Highgui.imwrite("C:/Kees/" + i + ".jpg", toWrite);
            //System.out.println("Saving result: " + result);
            //System.out.println(roi.toString());
        }
    }

    public static void matchBlobs(Mat noteBar, ArrayList<ArrayList<Integer>> noteList, Matcher blobMatcher) {
        for (int i = 0; i < noteList.size() - 1; i++) {
            Mat cropped = noteBar.clone();
            Rect roi = new Rect(noteList.get(i).get(0), 5, noteList.get(i).get(1) - noteList.get(i).get(0), noteBar.rows() - 5);
            cropped = cropped.submat(roi);
            MatchResult result = blobMatcher.matchImage(cropped);
            System.out.println("Result: " + result.getCompared().getName() + " Confidence: " + result.getConfidence());
        }
    }

    public static ArrayList<Mat> getROIperBlob(ArrayList<ArrayList<Integer>> blobList, Mat img) {
        ArrayList<Mat> rois = new ArrayList<>();

        for (int i = 0; i < blobList.size(); i++) {
            int margin = Math.round((blobList.get(i).get(1) - blobList.get(i).get(0)) / 4);

            int x = 0;
            int y = blobList.get(i).get(0) - margin * 3;
            int width = img.cols();
            int height = (blobList.get(i).get(1) + margin * 3) - (blobList.get(i).get(0) - margin * 3);

            Rect roi = new Rect(x, y, width, height);

            Mat cropped = img.clone();

            rois.add(cropped.submat(roi));
        }

        return rois;
    }

    
    /**
     * Removes whitespace from the image
     * @param source
     * @return 
     */
    public static Mat isolateImage(Mat source) {
        int leftmost = -1;
        int rightmost = -1;
        int topmost = -1;
        int botmost = -1;

        //System.out.println("ROWS: " + source.rows() + " Cols: " + source.cols());

        //source = Analyse.convertToGrey(source);
        for (int y = 0; y < source.rows(); y++) {
            for (int x = 0; x < source.cols(); x++) {
                if (!(source.get(y, x)[0] > 0)) {
                    if (topmost == -1 || topmost > y) {
                        topmost = y;
                    }
                    if (botmost < y) {
                        botmost = y;
                    }
                    if (leftmost == -1 || leftmost > x) {
                        leftmost = x;
                    }
                    if (x > rightmost) {
                        rightmost = x;
                    }
                }
            }
        }
        System.out.println("Bounds: { L:" + leftmost + ", T:" + topmost + ", R:" + rightmost + ", B:" + botmost + " } [0,1,2,3]");
        Rect roi = new Rect(leftmost, topmost, rightmost - leftmost, botmost - topmost);
        Mat result = source.submat(roi);
        return result;
    }
}
