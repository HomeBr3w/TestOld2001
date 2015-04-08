package opencv2test.Core;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import opencv2test.MainWindow;
import opencv2test.Opencv2Test;
import opencv2test.Support.MatchResult;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class Analyse
{
    /**
     * Convert an image to a "greyscale"-image.
     *
     * @param img
     * @return
     */
    public static Mat convertToGrey(Mat img)
    {
        Imgproc.cvtColor(img, img, 7);
        return img;
    }

    /**
     * Function which determines the MIDI-note-height
     *
     * @param sourceImage
     * @param barHeight
     * @param gLocation
     * @return
     */
    public static int getNoteHeight(ClassifierImage sourceImage, float barHeight, int gLocation)
    {/*
         int rows = 10;
         int rowLocation = -1;
         int heightBar = singleBar.rows();
         int[] cutOffValues = sourceImage.getCutOffValues();
         rowLocation = cutOffValues[0] + getCenterOfNote();
         rowLocation = (rowLocation / heightBar) * rows;
         */
        float rows = 20;
        int gValue = 68; //MIDI-declaration
        int[] cutOffValues = sourceImage.getCutOffValues();
        int noteCenter = getThickestRowIndex(sourceImage.getImage());
        float noteLocation = cutOffValues[0] + noteCenter;
        noteLocation = ((noteLocation / barHeight) * rows);
        noteLocation = Math.round(noteLocation);
        
        int noteHeight = gValue - (((int)noteLocation - gLocation) * 2); //Formula to get the right midi-note
        return noteHeight;
    }

    /**
     * 
     * @param sourceImage
     * @param barHeight
     * @return 
     */
    public static int getGLocation(ClassifierImage sourceImage, int barHeight)
    {
        return 12;
    }

    /**
     * Gets the row (index) where the image has the most black parts.
     * This is equal to the center of the note.
     * @param image
     * @return 
     */
    public static int getThickestRowIndex(Mat image)
    {
        int thickestRowIndex = -1;
        int maxRowThickness = 0;
        int currentRowThickness = 0;
        for (int r = 0; r < image.rows(); r++)
        {
            for (int c = 0; c < image.cols(); c++)
            {
                if (image.get(r, c)[0] < 127)
                {
                    currentRowThickness++;
                }
            }
            if (currentRowThickness > maxRowThickness)
            {
                thickestRowIndex = r;
                maxRowThickness = currentRowThickness;
            }
            currentRowThickness = 0;
        }
        return thickestRowIndex;
    }

    /**
     * Threshold on darkpixels.
     * Dark will become black and light pixels will be white.
     *
     * @param src
     * @param trg
     */
    public static void thresholdISOBlack(Mat src, Mat trg)
    {
        Imgproc.threshold(src, trg, 150, 255, 0);
    }

    /**
     * Function to print an image in the console.
     * ASCII-purists have fun!
     *
     * @param bron
     */
    public static void printImage(Mat bron)
    {
        for (int r = 0; r < bron.rows(); r++)
        {
            for (int c = 0; c < bron.cols(); c++)
            {
                System.out.print(bron.get(r, c)[0]);
            }
            System.out.print('\n');
        }
    }

    /**
     *
     * @param bron
     * @return
     */
    public static Mat averageRows(Mat bron)
    {
        Mat rowCounted = new Mat(bron.rows(), 1, 16);
        Imgproc.cvtColor(rowCounted, rowCounted, 7); // Hacky methode om een leeg zwartwit plaatje te krijgen.
        for (int r = 0; r < bron.rows(); r++)
        {
            int count = 0;
            for (int c = 0; c < bron.cols(); c++)
            {
                count += bron.get(r, c)[0];
            }

            int v = Math.round(count / bron.cols());
            rowCounted.put(r, 0, new double[]
            {
                v, v, v
            });
        }

        return rowCounted;
    }

    /**
     *
     * @param bron
     * @return
     */
    public static Mat averageCols(Mat bron)
    {
        Mat colCounted = new Mat(100, bron.cols(), 16);
        Imgproc.cvtColor(colCounted, colCounted, 7); // Hacky methode om een leeg zwartwit plaatje te krijgen.

        for (int c = 0; c < bron.cols(); c++)
        {
            int count = 0;
            for (int r = 0; r < bron.rows(); r++)
            {
                count += bron.get(r, c)[0];
            }
            colCounted.get(0, c)[0] = Math.round(count / bron.rows()); // Gemmiddelde uitrekenen

            int v = Math.round(count / bron.rows());
            for (int i = 0; i < 100; i++)
            {
                colCounted.put(i, c, new double[]
                {
                    v, v, v
                });
            }
        }

        return colCounted;
    }

    /**
     * Finds blobs in the horizontal direction.
     * In our case, this is the noteBar.
     *
     * @param bron
     * @return
     */
    public static ArrayList<ArrayList<Integer>> oneDimensionalHorizontalBlobFinder(Mat bron)
    {
        ArrayList<Integer> found = new ArrayList<>();

        int count = 0;
        int mean = 0;
        int thresh_blob;

        for (int r = 0; r < bron.rows(); r++)
        {
            int c = (int) bron.get(r, 0)[0];
            if (c < 127)
            {
                if (found.size() > 1)
                {
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

        for (i = 1; i < found.size() - 1; i++)
        {
            if (found.get(i) - found.get(i - 1) > thresh_blob)
            {
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

    /**
     * Finds blobs in the vertical direction.
     * In this case these are the notes and stuff.
     *
     * @param bron
     * @return
     */
    public static ArrayList<ArrayList<Integer>> oneDimensionalVerticalBlobFinder(Mat bron)
    {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> found;

        int pointer = 0;
        int min = -1;

        while (pointer < bron.cols() - 1)
        {
            if (min < 0)
            {
                min = pointer;
                double a = bron.get(0, min)[0];
                if (a > 253.0)
                {
                    min = -1;
                }
            }
            else
            {
                double a = bron.get(0, pointer)[0];
                if (a > 253.0)
                {
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
        if (a > 127.0)
        {
            found = new ArrayList<>();
            found.add(min);
            found.add(pointer);
            list.add(found);
        }

        return list;
    }

    /**
     * Erosion done on the WHITE-pixels of an image.
     *
     * @param bron
     * @param erosionSize
     * @param erosionType
     */
    public static void Erosion(Mat bron, int erosionSize, int erosionType)
    {
        Mat element = Imgproc.getStructuringElement(erosionType,
                new Size(2 * erosionSize + 1, 2 * erosionSize + 1),
                new Point(erosionSize, erosionSize));
        /// Apply the erosion operation
        Imgproc.erode(bron, bron, element);
    }

    /**
     * Dilation done on the WHITE-pixels of an image.
     *
     * @param bron
     * @param dilation_size
     * @param dilation_type
     */
    public static void Dilate(Mat bron, int dilation_size, int dilation_type)
    {
        Mat element = Imgproc.getStructuringElement(dilation_type,
                new Size(2 * dilation_size + 1, 2 * dilation_size + 1),
                new Point(dilation_size, dilation_size));
        /// Apply the dilation operation
        Imgproc.dilate(bron, bron, element);
    }

    public static Mat filterDifference(Mat src, Mat filter)
    {
        //thresholdISOBlack(filter, filter);
        Mat nw = new Mat(src.rows(), src.cols(), 16);
        convertToGrey(nw);
        convertToGrey(src);

        for (int c = 0; c < src.cols(); c++)
        {
            for (int r = 0; r < src.rows(); r++)
            {
                //nw.at<uchar>(r, c) = (src.at<uchar>(r, c) < filter.at<uchar>(r, 0)) ? 254 : 0;
                //nw.at<uchar>(r, c) = filter.at<uchar>(r, 0);

                boolean a = (filter.get(r, 0)[0] > 180) ? true : false;
                boolean b = (src.get(r, c)[0] < 127) ? true : false;

                if (a && b)
                {
                    nw.put(r, c, new double[]
                    {
                        0, 0, 0
                    });
                }
                else
                {
                    nw.put(r, c, new double[]
                    {
                        254, 254, 254
                    });
                }
            }
        }

        Erosion(nw, 1, 0);

        return nw;
    }

    /**
     * Draw lines for each found noteBar.
     *
     * @param blobList
     * @param img
     */
    public static void drawOneDimensionalBlobsHorizontal(ArrayList<ArrayList<Integer>> blobList, Mat img)
    {
        for (int i = 0; i < blobList.size(); i++)
        {
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

    /**
     * Draw a line around each found blob, in this case the notes / stuff found on the bars.
     *
     * @param blobList
     * @param img
     */
    public static void drawOneDimensionalBlobsVertical(ArrayList<ArrayList<Integer>> blobList, Mat img)
    {
        Mat cropped = img.clone();
        for (int i = 0; i < blobList.size() - 1; i++)
        {
            Core.line(img, new Point(blobList.get(i).get(0), 5), new Point(blobList.get(i).get(0), img.rows() - 5), new Scalar(0, 255, 255));
            Core.line(img, new Point(blobList.get(i).get(1), 5), new Point(blobList.get(i).get(1), img.rows() - 5), new Scalar(0, 255, 255));

            Rect roi = new Rect(blobList.get(i).get(0), 5, blobList.get(i).get(1) - blobList.get(i).get(0), img.rows() - 5);
            Mat toWrite = cropped.submat(roi);

            //boolean result = Highgui.imwrite("C:/Kees/" + i + ".jpg", toWrite);
            //System.out.println("Saving result: " + result);
            //System.out.println(roi.toString());
        }
    }

    /**
     * Function which loops through all given blobcoordinates given in the
     * parameter noteList and determines which blob matches a blob in the matcher.
     * Results are returned in an Arraylist with MatchResult(s)
     *
     * @param noteBar
     * @param noteList
     * @param blobMatcher
     * @return
     */
    public static ArrayList<MatchResult> matchBlobs(Mat noteBar, ArrayList<ArrayList<Integer>> noteList, Matcher blobMatcher)
    {
        ArrayList<MatchResult> notes = new ArrayList<>();
        for (int i = 0; i < noteList.size() - 1; i++)
        {
            Mat cropped = noteBar.clone();
            Rect roi = new Rect(noteList.get(i).get(0), 5, noteList.get(i).get(1) - noteList.get(i).get(0), noteBar.rows() - 5);
            cropped = cropped.submat(roi);
            MatchResult result = blobMatcher.matchImage(cropped);
            notes.add(result);
            System.out.println(i + ") Result: " + result.getCompared().getName() + " Error: " + result.getError());
        }
        return notes;
    }

    /**
     * Checks in the classifier which blob is the perfect match for the notes.
     * Requires a Notebar and the coordinates of the to-match blob.
     * Also requires a blobMatcher to match this image with.
     * Shows best match.
     *
     * @param noteBar
     * @param noteToFind
     * @param blobMatcher
     * @return 
     */
    public static MatchResult matchBlob(Mat noteBar, ArrayList<Integer> noteToFind, Matcher blobMatcher)
    {
        Mat cropped = noteBar.clone();
        Rect roi = new Rect(noteToFind.get(0), 5, noteToFind.get(1) - noteToFind.get(0), noteBar.rows() - 5);
        cropped = cropped.submat(roi);
        MatchResult result = blobMatcher.matchImage(cropped);
        System.out.println("NOTE) Result: " + result.getCompared().getName() + " Error: " + result.getError());
        return result;
    }

    /**
     * Function to get all "Notes" from the specified list of blobLocations given by the argument
     * blobList. Also requires the 'bar' to be read from.
     *
     * @param blobList
     * @param img
     * @return
     */
    public static ArrayList<Mat> getROIperBlob(ArrayList<ArrayList<Integer>> blobList, Mat img)
    {
        ArrayList<Mat> rois = new ArrayList<>();

        for (int i = 0; i < blobList.size(); i++)
        {
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
     * Removes whitespace from the image and therefore isolates the image
     *
     * @param source
     * @return
     */
    public static IsolateResult isolateImage(Mat source)
    {
        int leftmost = -1;
        int rightmost = -1;
        int topmost = -1;
        int botmost = -1;

        //System.out.println("Width BEFORE: " + source.cols() + " Height BEFORE: " + source.rows());
        //source = Analyse.convertToGrey(source);
        for (int y = 0; y < source.rows(); y++)
        {
            for (int x = 0; x < source.cols(); x++)
            {
                if (!(source.get(y, x)[0] > 0))
                {
                    if (topmost == -1 || topmost > y)
                    {
                        topmost = y;
                    }
                    if (botmost < y)
                    {
                        botmost = y;
                    }
                    if (leftmost == -1 || leftmost > x)
                    {
                        leftmost = x;
                    }
                    if (x > rightmost)
                    {
                        rightmost = x;
                    }
                }
            }
        }
        //System.out.println("Bounds: { L:" + leftmost + ", T:" + topmost + ", R:" + rightmost + ", B:" + botmost + " }");
        Rect roi = new Rect(leftmost, topmost, rightmost - leftmost + 1, botmost - topmost + 1);
        int[] results = new int[4];
        results[0] = topmost;
        results[1] = source.rows() - 1 - botmost;
        results[2] = leftmost;
        results[3] = source.cols() - 1 - rightmost;
        //System.out.println("TopCut: " + results[0] + " BottomCut: " + results[1] + " LeftCut: " + results[2] + " RightCut: " + results[3]);

        Mat result = source.submat(roi);

        //System.out.println("SanityCheck: " + (source.cols() - results[2] - results[3] == result.cols()) + " " + (source.rows() - results[0] - results[1] == result.rows()));
        //Highgui.imwrite("appel.jpg", result);
        //System.out.println("Width AFTER: " + result.cols() + " Height AFTER: " + result.rows());
        return new IsolateResult(result, results);
    }

    /**
     * Gets the amount of blackpixels (in percentage) of the specified image
     * Returns a list with the following results:
     * at index 0 -> topBlackPixel-percentage (percentage black pixels of the
     * until the height / 2 -th row.
     * at index 1 -> bottomBlackPixel-percentage (percentage black pixels of the bottom rows, height / 2 until height.
     * at index 2 -> total amount of blackpixels (percentage)
     *
     * @param image
     * @return
     */
    public static ArrayList<Float> getBlackPercentage(Mat image)
    {

        //top pixels
        ArrayList<Float> blackPercentage = new ArrayList<>();
        float topBlackPixels = 0;
        float topPixels = image.cols() * (image.rows() / 2);
        int r = 0;
        for (r = r; r < image.rows() / 2; r++)
        {
            for (int c = 0; c < image.cols(); c++)
            {
                if (image.get(r, c)[0] < 127.0)
                {
                    topBlackPixels++;
                }
            }
        }
        blackPercentage.add((topBlackPixels / topPixels) * 100.0f);
        //bottom pixels

        float bottomBlackPixels = 0;
        float bottomPixels = image.cols() * (image.rows() - (image.rows() / 2));
        for (r = r; r < image.rows(); r++)
        {
            for (int c = 0; c < image.cols(); c++)
            {
                if (image.get(r, c)[0] < 127.0)
                {
                    bottomBlackPixels++;
                }
            }
        }
        blackPercentage.add((bottomBlackPixels / bottomPixels) * 100.0f);
        //all pixels
        float totalPixels = bottomPixels + topPixels;
        float totalBlackPixels = bottomBlackPixels + topBlackPixels;

        blackPercentage.add((totalBlackPixels / totalPixels) * 100.0f);
        return blackPercentage;
    }

    /**
     * Removes whitespaces from the image.
     * IsolateImage does the same, with a few more additions.
     * Therefore deprecated.
     *
     * @param src
     * @return
     */
    @Deprecated
    public static Mat deleteWhiteRows(Mat src)
    {
        Mat dst = src.clone();
        int rindex = 0;
        for (int r = 0; r < src.rows(); r++)
        {
            boolean count = false;
            for (int c = 0; c < src.cols(); c++)
            {
                if (src.get(r, c)[0] < 127)
                {
                    count = true;
                    break;
                }
            }
            if (count)
            {
                for (int c = 0; c < src.cols(); c++)
                {
                    dst.put(rindex, c, src.get(r, c));
                }
                rindex++;
            }
        }
        return dst.submat(new Rect(0, 0, dst.cols(), rindex));
    }

    /**
     * Rotates an image with an amount of degrees.
     * Result is stored in dst.
     *
     * @param src
     * @param angle
     * @param dst
     */
    public static void rotate(Mat src, double angle, Mat dst)
    {
        int len = Math.max(src.cols(), src.rows());
        Point pt = new Point(len / 2.0, len / 2.0);
        Mat r = Imgproc.getRotationMatrix2D(pt, angle, 1.0);

        Imgproc.warpAffine(src, dst, r, new Size(len, len));
    }

    /**
     * Gets the HeightWidthRatio of an image.
     * Done by dividing the cols by the rows.
     *
     * @param image
     * @return
     */
    public static float getHeightWidthRatio(Mat image)
    {
        return (float) image.cols() / (float) image.rows();
    }

    /**
     * Fancy algorithm to determine the rotation on a line.
     * Angle is returned in a double
     *
     * @param centerPt
     * @param targetPt
     * @return
     */
    public static double calcRotationAngleInDegrees(Point centerPt, Point targetPt)
    {
        double theta = Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x);

        theta += Math.PI / 2.0;

        double angle = Math.toDegrees(theta);

        if (angle < 0)
        {
            angle += 360;
        }

        return angle;
    }

    /**
     * Create a bufferedImage from a Mat-image
     * To send by network (RMI)
     *
     * @param img
     * @return
     */
    public static BufferedImage getBufferedImgFromMat(Mat img)
    {
        MatOfByte matOfByte = new MatOfByte();
        Highgui.imencode(".jpg", img, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;

        InputStream in = new ByteArrayInputStream(byteArray);
        try
        {
            bufImage = ImageIO.read(in);
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bufImage;
    }

    /**
     * Conversion of a BufferedImage to Mat
     *
     * @param bi
     * @return
     */
    public static Mat getMatFromBufferedImage(BufferedImage bi)
    {
        Mat im = new Mat(bi.getHeight(), bi.getWidth(), 7);
        byte[] pixels = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        im.put(0, 0, pixels);

        return im;
    }

    /**
     * Encryption method to "encrypt" an image to random data.
     *
     * @param img
     * @param print
     * @return
     */
    public static String encryptImage(Mat img, boolean print)
    {
        int icount = 0;
        int ocount = 0;
        String buffer = "";
        buffer += img.type() + "#";
        for (int r = 0; r < img.rows(); r++)
        {
            for (int c = 0; c < img.cols(); c++)
            {
                if (img.get(r, c)[0] < 127)
                {
                    if (icount > 0)
                    {
                        if (icount == 1)
                        {
                            buffer += "|";
                        }
                        else
                        {
                            buffer += icount + "|";
                        }
                        icount = 0;
                    }
                    ocount++;
                }
                else
                {
                    if (ocount > 0)
                    {
                        if (ocount == 1)
                        {
                            buffer += ".";
                        }
                        else
                        {
                            buffer += ocount + ".";
                        }
                        ocount = 0;
                    }
                    icount++;
                }
            }
            if (icount > 0)
            {
                if (icount == 1)
                {
                    buffer += "|";
                }
                else
                {
                    buffer += icount + "|";
                }
            }
            if (ocount > 0)
            {
                if (ocount == 1)
                {
                    buffer += ".";
                }
                else
                {
                    buffer += ocount + ".";
                }
            }
            ocount = 0;
            icount = 0;
            buffer += "-";
        }
        if (print)
        {
            for (int i = 1; i < buffer.length() + 1; i++)
            {
                System.out.print(buffer.charAt(i - 1));
                if (i % 60 == 0)
                {
                    System.out.println();
                }
            }
            System.out.println();
        }
        return buffer;
    }

    /**
     * Decrypt function to convert random data to an image.
     *
     * @param buffer
     * @return
     */
    public static Mat decryptImage(String buffer)
    {
        String[] imgSplit = buffer.split("#");
        int type = Integer.parseInt(imgSplit[0]);
        String[] lines = imgSplit[1].split("-");
        ArrayList<ArrayList<Integer>> totalRows = new ArrayList<>();
        for (String s : lines)
        {
            String cached = "";
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < s.length(); i++)
            {
                if (s.charAt(i) == '.')
                {
                    int count = 0;
                    try
                    {
                        count = Integer.parseInt(cached);
                    }
                    catch (Exception ex)
                    {
                        count = 1;
                    }

                    for (int c = 0; c < count; c++)
                    {
                        row.add(0);
                    }
                    cached = "";
                }
                else if (s.charAt(i) == '|')
                {
                    int count = 0;
                    try
                    {
                        count = Integer.parseInt(cached);
                    }
                    catch (Exception ex)
                    {
                        count = 1;
                    }
                    for (int c = 0; c < count; c++)
                    {
                        row.add(1);
                    }
                    cached = "";
                }
                else
                {
                    cached += s.charAt(i);
                }

            }
            totalRows.add(row);
        }
        int height = totalRows.size();
        int width = totalRows.get(0).size();
        Mat result = new Mat(height, width, type);
        for (int y = 0; y < totalRows.size(); y++)
        {
            ArrayList<Integer> row = totalRows.get(y);
            for (int x = 0; x < row.size(); x++)
            {
                int resX = row.get(x) * 254;
                result.put(y, x, new double[]
                {
                    resX, resX, resX
                });
            }
        }
        return result;
    }

    /**
     *
     * Class to wrap the results of isolate image.
     * Since java is unable to pass values by reference.
     */
    public static class IsolateResult
    {
        public final Mat result;
        public final int[] results;

        public IsolateResult(Mat result, int[] results)
        {
            this.result = result;
            this.results = results;
        }
    }
}
