/*
 * MTools.cpp
 *
 *  Created on: Feb 18, 2015
 *      Author: jasper
 */

#include "MTools.h"

using namespace std;

MTools::MTools() {}

vector<int> MTools::Analyse(vector<string> &image)
{
	int leftmost = -1;
	int rightmost = -1;
	int topmost = -1;
	int botmost = -1;

	for (int y = 0; y < image.size(); y++)
	{
		string line = image.at(y);

		for (int x = 0; x < line.length(); x++)
		{
			if (line[x] == 'X')
			{
				if (topmost == -1 || topmost > y)
				{
					topmost = y;
				}
				if (botmost < y)
				{
					botmost = y;
				}
				if (leftmost == -1 || leftmost > x)
				{
					leftmost = x;
				}
				if (x > rightmost)
				{
					rightmost = x;
				}
			}
		}
	}

	vector<int> list;
	list.push_back(leftmost);
	list.push_back(topmost);
	list.push_back(rightmost);
	list.push_back(botmost);
	list.push_back((leftmost + ((rightmost - leftmost) / 2)));
	list.push_back((topmost + ((botmost - topmost) / 2)));

	cout << "Bounds: { L:" << leftmost << ", T:" << topmost << ", R:" << rightmost << ", B:" << botmost << " } [0,1,2,3]" << endl;
	cout << "Center: { X:" << list[4] << ", Y:" << list[5] << " } [4,5]" << endl;

	return list;
}

int MTools::PathAnalyse(vector<Point2D> &coords, vector<string> &image)
{
	int found = -1;
	for(std::vector<Point2D>::size_type i = 0; i != coords.size(); i++)
		if (image[coords[i].getY()][coords[i].getX()] == 'X')
			found = i;

	return found;
}
