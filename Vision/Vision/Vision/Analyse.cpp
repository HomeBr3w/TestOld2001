#include "stdafx.h"
#include "Analyse.h"

cv::Mat Analyse::convertToGray(cv::Mat bron)
{
	cv::Mat temp;
	cv::cvtColor(bron, temp, CV_RGB2GRAY);
	return temp;
}

void Analyse::thresholdISOBlack(cv::Mat bron, cv::Mat doel)
{
	cv::threshold(bron, doel, 150, 255, CV_THRESH_BINARY);
}

cv::Mat Analyse::averageRows(cv::Mat bron)
{
	cv::Mat rowCounted = cv::Mat(bron.rows, 1, IPL_DEPTH_16U);
	cv::cvtColor(rowCounted, rowCounted, CV_RGB2GRAY); // Hacky methode om een leeg zwartwit plaatje te krijgen.
	for (int r = 0; r < bron.rows; r++)
	{
		System::Int32 count = 0;
		for (int c = 0; c < bron.cols; c++)
		{
			count += bron.at<uchar>(r, c);
		}

		rowCounted.at<uchar>(r, 0) = cvRound(count / bron.cols); // Gemmiddelde uitrekenen
	}

	return rowCounted;
}

std::vector<std::vector<int>> Analyse::oneDimensionalHorizontalBlobFinder(cv::Mat bron)
{
	std::vector<int> found;

	System::Int32 count = 0;
	System::Int32 mean = 0;
	System::Int32 thresh_blob;

	for (int r = 0; r < bron.rows; r++)
	{
		System::Int32  c = bron.at<uchar>(r, 0);
		if (c < 127) {
			if (found.size() > 1)
			{
				mean += (r - found.at(count));
				count++;
			}
			found.push_back(r);
		}
	}

	std::vector<std::vector<int>> blobList;

	thresh_blob = cvRound(mean / count);
	int minval = found.at(0);
	int maxval = 0;
	int i = 1;
	for (i; i < found.size() - 1; i++)
	{
		if (found.at(i) - found.at(i - 1) > thresh_blob)
		{
			std::vector<int> temp;

			maxval = found.at(i - 1);

			temp.push_back(minval);
			temp.push_back(maxval);

			blobList.push_back(temp);

			minval = found.at(i);
		}
	}
	std::vector<int> temp;
	temp.push_back(minval);
	maxval = found.at(i);
	temp.push_back(maxval);
	blobList.push_back(temp);

	return blobList;
}

void Analyse::drawOneDimensionalBlobsHorizontal(std::vector<std::vector<int>> blobList, cv::Mat img)
{
	for (int i = 0; i < blobList.size(); i++)
	{
		int margin = cvRound((blobList.at(i).at(1) - blobList.at(i).at(0)) / 4);
		cv::line(img, cv::Point(0, blobList.at(i).at(0) - margin * 3), cv::Point(img.cols, blobList.at(i).at(0) - margin * 3), cv::Scalar(255, 0, 0), 3, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1) + margin * 3), cv::Point(img.cols, blobList.at(i).at(1) + margin * 3), cv::Scalar(0, 255, 0), 3, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0) - margin * 2), cv::Point(img.cols, blobList.at(i).at(0) - margin * 2), cv::Scalar(0, 0, 255), 1, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1) + margin * 2), cv::Point(img.cols, blobList.at(i).at(1) + margin * 2), cv::Scalar(0, 0, 255), 1, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0) - margin), cv::Point(img.cols, blobList.at(i).at(0) - margin), cv::Scalar(0, 0, 255), 1, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1) + margin), cv::Point(img.cols, blobList.at(i).at(1) + margin), cv::Scalar(0, 0, 255), 1, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0)), cv::Point(img.cols, blobList.at(i).at(0)), cv::Scalar(0, 0, 255), 1, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1)), cv::Point(img.cols, blobList.at(i).at(1)), cv::Scalar(0, 0, 255), 1, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0) + margin), cv::Point(img.cols, blobList.at(i).at(0) + margin), cv::Scalar(0, 0, 255), 1, 8, 0);
		cv::line(img, cv::Point(0, blobList.at(i).at(1) - margin), cv::Point(img.cols, blobList.at(i).at(1) - margin), cv::Scalar(0, 0, 255), 1, 8, 0);

		cv::line(img, cv::Point(0, blobList.at(i).at(0) + margin * 2), cv::Point(img.cols, blobList.at(i).at(0) + margin * 2), cv::Scalar(0, 0, 255), 1, 8, 0);
	}
}

std::vector<cv::Mat> Analyse::getROIperBlob(std::vector<std::vector<int>> blobList, cv::Mat img)
{
	std::vector<cv::Mat> rois;

	for (int i = 0; i < blobList.size(); i++)
	{
		int margin = cvRound((blobList.at(i).at(1) - blobList.at(i).at(0)) / 4);
		
		int x = 0;
		int y = blobList.at(i).at(0) - margin * 3;
		int width = img.cols;
		int height = (blobList.at(i).at(1) + margin * 3) - (blobList.at(i).at(0) - margin * 3);
		cv::Rect roi(x, y, width, height);

		rois.push_back(img(roi));
	}

	return rois;
}