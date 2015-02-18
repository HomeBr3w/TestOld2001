/*
 * MTools.h
 *
 *  Created on: Feb 18, 2015
 *      Author: jasper
 */

#ifndef TOOLS_MTOOLS_H_
#define TOOLS_MTOOLS_H_

#include <iostream>
#include <vector>
#include "../StTools/Point2D.h"

class MTools {
public:
	MTools();
	std::vector<int> Analyse(std::vector<std::string>&);
	int PathAnalyse (std::vector<Point2D>&, std::vector<std::string>&);
};

#endif /* TOOLS_MTOOLS_H_ */
