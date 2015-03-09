// Vision.cpp : main project file.

#include "stdafx.h"
#include <iostream>
#include "opencv2/core/core.hpp"
#include "opencv2/features2d/features2d.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/calib3d/calib3d.hpp"
#include "opencv2/nonfree/nonfree.hpp"

#include "Analyse.h"

using namespace System;
using namespace std;

int main(int argc, char *argv[])
{
	cv::Mat scene = cv::imread("C:/opencv/imgx.jpg", CV_LOAD_IMAGE_GRAYSCALE);
	cv::Mat object = cv::imread("C:/opencv/kwart noot.jpg", CV_LOAD_IMAGE_GRAYSCALE);//image(cv::Range(240,320), cv::Range(100,150));
	if (!scene.data || !object.data)
		return 0;

	cv::namedWindow("Original");
	imshow("Original", scene);

	cv::namedWindow("Template");
	imshow("Template", object);

	cv::waitKey();

	cv::FastFeatureDetector detector(15);
	vector<cv::KeyPoint> sceneKP, objectKP;
	detector.detect(scene, sceneKP);
	detector.detect(object, objectKP);

	cv::SurfDescriptorExtractor extractor;
	cv::Mat descriptorScene, descriptorObject;
	extractor.compute(scene, sceneKP, descriptorScene);
	extractor.compute(object, objectKP, descriptorObject);
	
	cv::BFMatcher matcher;
	vector<cv::DMatch> matches;
	matcher.match(descriptorScene, descriptorObject, matches);

	cv::namedWindow("matches", 1);
	cv::Mat img_matches;
	cv::drawMatches(scene, sceneKP, object, objectKP, matches, img_matches);
	imshow("matches", img_matches);

	vector<cv::Point2f> objectPoints, scenePoints;
	
	for (int i = 0; i < matches.size(); i++)
	{
		//-- Get the keypoints from the good matches
		objectPoints.push_back(objectKP[matches[i].queryIdx].pt);
		scenePoints.push_back(sceneKP[matches[i].trainIdx].pt);
	}
	
	cv::Mat H = cv::findHomography(cv::Mat(objectPoints), cv::Mat(scenePoints), CV_RANSAC);

	cv::Mat points1Projected;
	perspectiveTransform(cv::Mat(scenePoints), points1Projected, H);

	imshow("Good Matches & Object detection", points1Projected);
	cv::waitKey(0);
	return 0;


	/*
	// is er een arg>?
	string imageName = "";
	imageName.append(std::getenv("OPENCV"));
	imageName.append("\\imgw.jpg");

	if (argc > 1) { imageName = argv[0]; }

	// Importeer een afbeelding
	cv::Mat img = cv::imread(imageName, CV_LOAD_IMAGE_UNCHANGED);
	cv::Mat grey;


	if (img.empty())
	{
		cout << "Error : Geen afbeelding?? - " << imageName << endl;
		cin.get();
		return -1;
	}

	// Convert de afbeelding naar grijswaarden
	grey = Analyse::convertToGray(img);

	// Isoleer de zwarte stukken
	Analyse::thresholdISOBlack(grey, grey);

	// Bereken voor alle Rows het gemiddelde aan pixels in de columns.
	cv::Mat rowCounted = Analyse::averageRows(grey);
	
	// Vind het aantal blobs en kijk of het 'systemen' zijn.
	vector<vector<int>> blobList = Analyse::oneDimensionalHorizontalBlobFinder(rowCounted);
	
	//Uitsnedes maken van notenbalken en het kwardratische verticalegemiddelde berekenen per balk
	std::vector<cv::Mat> rois = Analyse::getROIperBlob(blobList, img);

	cv::namedWindow("Uitsnede", CV_WINDOW_AUTOSIZE);
	for each (cv::Mat var in rois)
	{
		cv::Mat filteredImage = Analyse::filterDifference(var, Analyse::averageRows(var));
		
		cv::imshow("Uitsnede", filteredImage);
		cv::waitKey(0);
	}
	cv::destroyWindow("Uitsnede");

	//Teken gevonden blobs
	Analyse::drawOneDimensionalBlobsHorizontal(blobList, img);

	cv::namedWindow("Bron", CV_WINDOW_AUTOSIZE);
	cv::imshow("Bron", img);

	cv::namedWindow("Grijs", CV_WINDOW_AUTOSIZE);
	cv::imshow("Grijs", grey);

	cv::waitKey(0);
	cv::destroyWindow("Bron");
	cv::destroyWindow("Grijs");

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
	}*/
    return 0;
}