/*
 * Point2D.cpp
 *
 *  Created on: Feb 18, 2015
 *      Author: jasper
 */

#include "Point2D.h"

Point2D::Point2D(int x, int y) {
	this->ix = x;
	this->iy = y;
}

int Point2D::getX()
{
	return ix;
}

int Point2D::getY()
{
	return iy;
}

void Point2D::set(int x, int y)
{
	this->ix = x;
	this->iy = y;
}
