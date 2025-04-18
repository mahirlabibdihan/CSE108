#include <iostream>
#include <cmath>
using namespace std;
class Point
{
// x and y coordinate
	int x, y;
public:
//Add necessary constructor(s) to initialize x and y
	Point()
	{
		this->x=0;
		this->y=0;
	}
	Point(int x,int y)
	{
		this->x=x;
		this->y=y;
	}
//Add your set and get functions for x and y
	void setX(int x)
	{
		this->x=x;
	}
	void setY(int y)
	{
		this->y=y;
	}
	int getX()
	{
		return x;
	}
	int getY()
	{
		return y;
	}
	void print()
	{
		cout << "Coordinate: " << x << ", " << y << endl;
	}
};

class Line{
	Point p1,p2;
public:
	Line(int a1, int b1, int a2, int b2)
	{
		p1.setX(a1);
		p1.setY(b1);
		p2.setX(a2);
		p2.setY(b2);
	}
	void print()
	{
		cout<<"Length: "<<sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())+(p1.getY()-p2.getY())*(p1.getY()-p2.getY()))<<endl;
	}
};
class Circle
{
	Point p;
	int radius;
public:
// Add necessary constructor(s) to initialize p and radius
	Circle(int x,int y,int radius)
	{
		p.setX(x);
		p.setY(y);
		this->radius=radius;
	}
// Overload “upload” functions
	void update(int dr)
	{
		this->radius+=dr;
	}
	void update(int dx,int dy)
	{
		p.setX(p.getX()+dx);
		p.setY(p.getY()+dy);
	}
	void update(int dx,int dy,int dr)
	{
		p.setX(p.getX()+dx);
		p.setY(p.getY()+dy);
		this->radius+=dr;
	}
	void print()
	{
		cout << "Center ";
		p.print();
		cout << "Radius: " << radius << endl;
	}
};
int main()
{
	Point p(5, 5);
	Circle c(2, 3, 5);
	cout << endl << "Point Display" << endl;
	p.print();
	cout << endl << "Circle Display" << endl;
	c.print();
	cout << endl;
//First update
	cout << "First Update" << endl;
// call “update” function of Circle class to increase the center’s x coordinate by 5 and y coordinate by 5;
	c.update(5,5);
	c.print();
	cout << endl;
//Second update
	cout << "Second Update" << endl;
// call “update” function of Circle class to increase the radius by 6;
	c.update(6);
	c.print();
	cout << endl;
//Third update
	cout << "Third Update" << endl;
// call “update” function of Circle class to increase the center’s x coordinate by 2 and y coordinate by 2 and the radius by 2;
	c.update(2,2,2);
	c.print();
	cout << endl;

	Line l(0,0,5,5);
	l.print();
	return 0;
}