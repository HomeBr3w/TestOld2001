// Vision.cpp : main project file.

#include "stdafx.h"
#include "opencv2\highgui\highgui.hpp";
#include "opencv2\imgproc\imgproc.hpp"
#include <iostream>

using namespace System;
using namespace std;

int main(array<System::String ^> ^args)
{
	cv::Mat img = cv::imread("C:/Users/Siebren/Desktop/img.jpg", CV_LOAD_IMAGE_UNCHANGED);
	cv::Mat grey;

	if (img.empty())
	{
		cout << "Error : Geen afbeelding??" << endl;
		return -1;
	}

	cv::cvtColor(img, grey, CV_BGR2GRAY);
	//cv::GaussianBlur(grey, grey, cv::Size(9, 2), 2, 2);

	vector<cv::Vec3f> circles;
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
		tmpImg = img(cv::Rect(x - 15, y - 33.5, 30, 75));
		cv::Mat dilation = getStructuringElement(cv::MORPH_RECT, cv::Size(3, 3), cv::Point(3, 3));
		cv::Mat dest;
		cv::dilate(tmpImg, dest, dilation);
		tmpImg = dest.clone();
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
	cv::destroyWindow("Oplossing");
    return 0;
}