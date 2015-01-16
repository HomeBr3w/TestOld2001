// Vision.cpp : main project file.

#include "stdafx.h"
#include "opencv2\imgproc\imgproc.hpp"
#include "opencv2\highgui\highgui.hpp"
#include <iostream>

using namespace System;
using namespace std;

int main(int argc, char *argv[])
{
	// is er een arg>?
	string imageName = "C:/opencv/img.jpg";
	if (argc > 1) { imageName = argv[0]; }

	// Importeer een afbeelding
	cv::Mat img = cv::imread(imageName, CV_LOAD_IMAGE_UNCHANGED);
	cv::Mat grey;

	if (img.empty())
	{
		cout << "Error : Geen afbeelding??" << endl;
		cin.get();
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
	
	// Vind het aantal blobs en kijk of het 'systemen' zijn.
	vector<int> found;

	Int32 count = 0;
	Int32 mean = 0;
	Int32 thresh_blob;

	cout << "Gevonden waardes" << endl;
	for (int r = 0; r < rowCounted.rows; r++)
	{
		Int32  c = rowCounted.at<uchar>(r, 0);
		if (c < 127) {
			//cout << r << " -> " << c << endl;
			
			if (found.size() > 1)
			{
				mean += (r - found.at(count));
				count++;
			}
			
			found.push_back(r);

			cout << r << endl;
		}
	}

	vector<vector<int>> blobList;

	thresh_blob = cvRound(mean / count);
	int minval = found.at(0);
	int maxval = 0;
	int i = 1;
	for (i; i < found.size()-1; i++)
	{
		if (found.at(i) - found.at(i - 1) > thresh_blob)
		{
			vector<int> temp;

			maxval = found.at(i - 1);

			temp.push_back(minval);
			temp.push_back(maxval);

			blobList.push_back(temp);

			
			cout << "Blob gevonden van " << minval << " naar " << maxval << endl;
			minval = found.at(i);
		}
	}
	vector<int> temp;
	temp.push_back(minval);
	maxval = found.at(i);
	temp.push_back(maxval);
	blobList.push_back(temp);
	//cout << "Blob gevonden van " << minval << " naar " << maxval << endl;
	
	//Teken gevonden blobs
	cv::namedWindow("Notefind", CV_WINDOW_AUTOSIZE);
	cv::namedWindow("Tmp", CV_WINDOW_AUTOSIZE);

	for (int i = 0; i < blobList.size(); i++)
	{
		int margin = cvRound((blobList.at(i).at(1) - blobList.at(i).at(0)) / 4);
		cv::line(img, cv::Point(0, blobList.at(i).at(0) - margin * 3), cv::Point(img.cols, blobList.at(i).at(0) - margin * 3), cv::Scalar(255, 0, 0), 3, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1) + margin * 3), cv::Point(img.cols, blobList.at(i).at(1) + margin * 3), cv::Scalar(0, 255, 0), 3, 8, 0);

		int x = 0;
		int y = blobList.at(i).at(0) - margin * 3;
		int width = img.cols;
		int height = (blobList.at(i).at(1) + margin * 3) - (blobList.at(i).at(0) - margin * 3);
		cv::Rect roi(x, y, width, height);
		//rois.push_back(cv::Rect(x, y, width, height));





		
		int sumX = 0;
		int frameWidth = margin * 3;
		cv::Mat bar = cv::Mat(img, roi);
		cv::imshow("Notefind", bar);
		cv::waitKey(0);
		while (sumX < img.cols)
		{
			int frameX = sumX;
			int frameY = 0;
			cv::Rect partOfBar(frameX, frameY, frameWidth, bar.rows);
			cv::Mat frame(bar, partOfBar);

			sumX += frameWidth;
			cv::imshow("Tmp", frame);
			cv::waitKey(0);
		}
		cv::destroyWindow("Tmp");








		/*
		cv::line(img, cv::Point(0, blobList.at(i).at(0) - margin * 2), cv::Point(img.cols, blobList.at(i).at(0) - margin * 2), cv::Scalar(0, 0, 255), 1, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1) + margin * 2), cv::Point(img.cols, blobList.at(i).at(1) + margin * 2), cv::Scalar(0, 0, 255), 1, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0) - margin), cv::Point(img.cols, blobList.at(i).at(0) - margin), cv::Scalar(0, 0, 255), 1, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1) + margin), cv::Point(img.cols, blobList.at(i).at(1) + margin), cv::Scalar(0, 0, 255), 1, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0)), cv::Point(img.cols, blobList.at(i).at(0)), cv::Scalar(0, 0, 255), 1, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1)), cv::Point(img.cols, blobList.at(i).at(1)), cv::Scalar(0, 0, 255), 1, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0) + margin), cv::Point(img.cols, blobList.at(i).at(0) + margin), cv::Scalar(0, 0, 255), 1, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1) - margin), cv::Point(img.cols, blobList.at(i).at(1) - margin), cv::Scalar(0, 0, 255), 1, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0) + margin * 2), cv::Point(img.cols, blobList.at(i).at(0) + margin * 2), cv::Scalar(0, 0, 255), 1, 8, 0);*/

		/*
		for (int i = 1; i < img.cols-11; i++)
		{
			cv::Mat t = img(cv::Rect(i, blobList.at(i).at(0) - margin * 3, 10, (blobList.at(i).at(1) + margin * 3) - (blobList.at(i).at(0) - margin * 3)));
			cv::imshow("Notefind", t);
			cv::waitKey(0);
		}*/
	}

	
	cv::namedWindow("Bron", CV_WINDOW_AUTOSIZE);
	cv::imshow("Bron", img);

	cv::namedWindow("Oplossing", CV_WINDOW_AUTOSIZE);
	cv::imshow("Oplossing", grey);

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