/*
 * Dtools.cpp
 *
 *  Created on: Feb 18, 2015
 *      Author: jasper
 */

#include "Dtools.h"

using namespace std;

vector<string> DTools::createImage(int width, int height)
{
	vector<string> image;

	string line = "";
	for (int i = 0; i < width; i++)
		line += ' ';

	for (int i = 0; i < height; i++)
		image.push_back(line);

	return image;
}

void DTools::setPixel(int x, int y, char c, vector<string> &image)
{
	image[y][x] = c;
}

void DTools::drawLine(vector<Point2D> v, vector<string> &image)
{
	for(std::vector<Point2D>::size_type i = 0; i != v.size(); i++) {
	    setPixel(v[i].getX(), v[i].getY(), '.', image);
	}
}

vector<Point2D> DTools::getLine(int x0, int y0, int x1, int y1)
{
	vector<Point2D> list;
	int dx = abs(x1 - x0);
	int dy = abs(y1 - y0);
	int sgnX = x0 < x1 ? 1 : -1;
	int sgnY = y0 < y1 ? 1 : -1;
	int e = 0;
	for (int i=0; i < dx+dy; i++) {
		list.push_back(Point2D(x0, y0));
		int e1 = e + dy;
		int e2 = e - dx;
		if (abs(e1) < abs(e2)) {
			x0 += sgnX;
			e = e1;
		} else {
			y0 += sgnY;
			e = e2;
		}
	}
	return list;
}

void DTools::pr_img(vector<string> &image)
{
	string firstline = image.at(0);
	int hsize = firstline.length() + 2;
	string hrule = "";
	for (int n = 0; n < hsize; n++)
	{
		hrule.append("-");
	}

	cout << hrule << endl;

	for (int i = 0; i < image.size(); i++)
	{
		cout << "|" << image.at(i) << "|" << endl;
	}

	cout << hrule << endl;
}

void DTools::pr_img (int x, int y, vector<string> &image)
{
	string firstline = image.at(0);
	int hsize = firstline.length() + 2;
	string hrule = "";
	for (int n = 0; n < hsize; n++)
	{
		if (n != x+1)
		{
			hrule.append("-");
		}
		else
		{
			hrule.append("+");
		}
	}

	cout << hrule << endl;

	for (int i = 0; i < image.size(); i++)
	{
		if (i != y)
		{
			cout << "|" << image.at(i) << "|" << endl;
		}
		else
		{
			cout << "+" << image.at(i) << "+" << endl;
		}
	}

	cout << hrule << endl;
}
