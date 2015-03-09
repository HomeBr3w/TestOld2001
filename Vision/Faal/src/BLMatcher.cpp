//============================================================================
// Name        : BLMatcher.cpp
// Author      : jjj
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <vector>

#include "Tools/Dtools.h"
#include "Tools/MTools.h"

using namespace std;

const char* image_args[] = {
	"                       ",
	"                       ",
	"                       ",
	"                       ",
	"                       ",
	"                       ",
	"                       ",
	"                       ",
	"                       ",
	"                       ",
	"                       ",
	"                       "
};
vector<string> image(image_args, image_args + sizeof(image_args)/sizeof(image_args[0]));

int main() {

	DTools dt;
	MTools mt;

	//vector<string> img = dt.createImage(20, 10);
	dt.drawLine(dt.getLine(0, 0, 13, 9), image);

	//vector<int> ana = mt.Analyse(image);

	dt.pr_img(image);

	return 0;
}
