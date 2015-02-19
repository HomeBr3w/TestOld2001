/*
 * Dtools.h
 *
 *  Created on: Feb 18, 2015
 *      Author: jasper
 */

#ifndef DTOOLS_H_
#define DTOOLS_H_

#include <iostream>
#include <vector>
#include "../StTools/Point2D.h"


class DTools
{
public:
	void setPixel (int, int, char, std::vector<std::string>&);
	void drawLine(std::vector<Point2D>, std::vector<std::string>&);
	std::vector<Point2D> getLine(int, int, int, int);
	void pr_img(std::vector<std::string>&);      					// Print image
	void pr_img(int, int, std::vector<std::string>&); 				// Print image with mythical center displayed

	std::vector<std::string> createImage(int, int);
};

#endif /* DTOOLS_H_ */
