
#include "Analyse.h"
#include <iostream>

using namespace std;

bool Analyse::loadImage(string imagePath)
{
	cv::Mat image = cv::imread(imagePath, CV_LOAD_IMAGE_UNCHANGED);
	cv::Mat grey;

	if (!image.empty())
	{
		return true;
	}
	return false;
}

void Analyse::analyseSheet()
{

}

vector<int> getBlobs()
{

}

