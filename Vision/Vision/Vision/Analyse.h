
#include "stdafx.h"
#include "opencv2\imgproc\imgproc.hpp"
#include "opencv2\highgui\highgui.hpp"
#include <vector>
#include <string>

class Analyse
{
private:
	cv::Mat sheetMusic;
	int margin = 1;
	
	cv::Mat getLines();
	std::vector<int> getBlobs();
public:
	Analyse();
	~Analyse();
	bool loadImage(std::string imagePath);
	void analyseSheet();
};