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
	"        XXXXX          ",
	"      XX     XX        ",
	"    XX         XX      ",
	"   X             X     ",
	"  X               X    ",
	"  X        X      X    ",
	"  X          X     X   ",
	"   X   X         X     ",
	"    XX         XX      ",
	"      XX     XX        ",
	"  X    XXXXXX          ",
	"X                      "
};
vector<string> image(image_args, image_args + sizeof(image_args)/sizeof(image_args[0]));

int main() {

	DTools dt;
	MTools mt;

	//vector<string> img = dt.createImage(20, 10);
	//dt.drawLine(dt.getLine(0, 0, 5, 9), img);

	vector<int> ana = mt.Analyse(image);

	dt.pr_img(ana[4], ana[5], image);

	return 0;
}
