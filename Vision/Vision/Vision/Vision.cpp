// Vision.cpp : main project file.

#include "stdafx.h"
#include "opencv2\imgproc\imgproc.hpp"
#include "opencv2\highgui\highgui.hpp"
#include <iostream>

using namespace System;
using namespace std;

int main(array<System::String ^> ^args)
{
	// Importeer een afbeelding
	cv::Mat img = cv::imread("C:/img.jpg", CV_LOAD_IMAGE_UNCHANGED);
	cv::Mat grey;

	if (img.empty())
	{
		cout << "Error : Geen afbeelding??" << endl;
		return -1;
	}

	// Convert de afbeelding naar grijswaarden
	cv::cvtColor(img, grey, CV_RGB2GRAY);

	// Isoleer de zwarte stukken
	cv::threshold(grey, grey, 150, 255, CV_THRESH_BINARY);

	// Bereken voor alle Rows het gemiddelde aan pixels in de columns.
	cv::Mat rowCounted = cv::Mat(grey.rows, 1, IPL_DEPTH_16U);
	cv::cvtColor(rowCounted, rowCounted, CV_RGB2GRAY); // Hacky methode om een leeg zwartwit plaatje te krijgen.
	for (int r = 0; r < grey.rows; r++)
	{
		Int32 count = 0;
		for (int c = 0; c < grey.cols; c++)
		{
			count += grey.at<uchar>(r, c);
		}
		
		rowCounted.at<uchar>(r, 0) = cvRound(count / grey.cols); // Gemmiddelde uitrekenen
	}
	



	cv::namedWindow("Grijs", CV_WINDOW_AUTOSIZE);
	cv::imshow("Grijs", img);

	cv::namedWindow("Bron", CV_WINDOW_AUTOSIZE);
	cv::imshow("Bron", grey);

	cv::namedWindow("Lijnen", CV_WINDOW_AUTOSIZE);
	cv::imshow("Lijnen", rowCounted);

	cv::waitKey(0);
	cv::destroyWindow("Bron");
	cv::destroyWindow("Oplossing");

	/*vector<cv::Vec3f> circles;
	//vector<cv::Mat> images;

	cv::HoughCircles(grey, circles, CV_HOUGH_GRADIENT, 1, 30, 20, 10, 2, 6);

	for (size_t i = 0; i < circles.size(); i++)
	{
		cv::Point center = cv::Point(cvRound(circles[i][0]), cvRound(circles[i][1]));
		int radius = cvRound(circles[i][2]);

		//cv::circle(img, center, 3, cv::Scalar(0, 255, 0), -1, 8, 0);
		//cv::circle(img, center, radius, cv::Scalar(0, 0, 255), 3, 8, 0);

		cv::Mat tmpImg;
		int x = center.x;
		int y = center.y;
		tmpImg = img(cv::Rect(x - 15, y - 32.5, 30, 75));
		//cv::Mat dilation = getStructuringElement(cv::MORPH_RECT, cv::Size(3, 3), cv::Point(3, 3));
		//cv::Mat dest;
		//cv::dilate(tmpImg, dest, dilation);
		//tmpImg = dest.clone();
		//images.push_back(tmpImg); 
		cv::namedWindow("Random note", CV_WINDOW_AUTOSIZE);
		cv::imshow("Random note", tmpImg);
		cv::waitKey(0);
		cv::destroyWindow("Random note");
	}
	

	cv::namedWindow("Oplossing", CV_WINDOW_AUTOSIZE);
	cv::imshow("Oplossing", img);

	cv::namedWindow("Bron", CV_WINDOW_AUTOSIZE);
	cv::imshow("Bron", grey);

	cv::waitKey(0);
	cv::destroyWindow("Bron");
	cv::destroyWindow("Oplossing");*/
    return 0;
}