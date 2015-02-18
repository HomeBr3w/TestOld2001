/*
 * Point2D.h
 *
 *  Created on: Feb 18, 2015
 *      Author: jasper
 */

#ifndef POINT2D_H_
#define POINT2D_H_

class Point2D {
private:
	int ix, iy;
public:
	Point2D(int x, int y);
	int getX(void);
	int getY(void);
	void set(int, int);
};

#endif /* POINT2D_H_ */
