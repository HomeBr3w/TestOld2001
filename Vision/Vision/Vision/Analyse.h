
#ifndef _ANALYSE_HEADER_
#define _ANALYSE_HEADER_

#include "opencv2\highgui\highgui.hpp"
#include "opencv2\imgproc\imgproc.hpp"
#include <iostream>

class Analyse
{
public :
	static cv::Mat convertToGray(cv::Mat);
	static void thresholdISOBlack(cv::Mat, cv::Mat);
	static cv::Mat averageRows(cv::Mat);
	static cv::Mat averageCols(cv::Mat);
	static cv::Mat filterDifference(cv::Mat, cv::Mat);
	static std::vector<std::vector<int>> oneDimensionalHorizontalBlobFinder(cv::Mat);
	static std::vector<std::vector<int>> oneDimensionalVerticalBlobFinder(cv::Mat);
	static void drawOneDimensionalBlobsHorizontal(std::vector<std::vector<int>>, cv::Mat);
	static void drawOneDimensionalBlobsVertical(std::vector<std::vector<int>>, cv::Mat);
	static std::vector<cv::Mat> getROIperBlob(std::vector<std::vector<int>>, cv::Mat);
};

#endif