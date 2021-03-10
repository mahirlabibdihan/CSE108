#include <iostream>
#include <cstdlib>
using namespace std;
class Matrix {
	int row, col;
	int **mat;	// 2d Pointer for matrix

public:
	Matrix(int row, int col)
	{
		/* Setting the row and col of class */
		this->row = row;
		this->col = col;

		/* Allocating Memory */
		int i;
		mat = (int**)malloc(sizeof(int*)*row);
		for (i = 0; i < row; i++)
		{
			*(mat + i) = (int*)malloc(sizeof(int) * col);
		}
	}
	~Matrix()
	{	
		/* Freeing Memory */
		int i;
		for (i = 0; i < row; i++)
		{
			free(*(mat + i));
		}
		free(mat);
	}
	void print()
	{
		/* Printing Matrix */
		int i, j;
		for (i = 0; i < row; i++)
		{
			for (j = 0; j < col; j++)
			{
				cout << mat[i][j] << " ";
			}
			cout << endl;
		}
	}
	void set(int row, int col, int value)
	{
		if (row < this->row && col < this->col)		// Checking if the row and col are valid .
		{
			mat[row][col] = value;
		}
	}
	int get(int row, int col)
	{
		if (row < this->row && col < this->col)		// Checking if the row and col are valid .
		{
			return mat[row][col];
		}
		return 0;
	}
	void add(int n)
	{
		int i, j;
		for (i = 0; i < row; i++)
		{
			for (j = 0; j < col; j++)
			{
				mat[i][j] += n;		// Adding n to all the elements of the matrix
			}
		}
	}
	int add()
	{
		int sum = 0, i, j;
		for (i = 0; i < row; i++)
		{
			for (j = 0; j < col; j++)
			{
				sum += mat[i][j];	   // Summation of all the elements of the matrix
			}
		}
		return sum;
	}
};

int main()
{
	cout << "Hello World" << '\n';
	Matrix m(3, 3);
	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			m.set(i, j, i + j);

	m.print();
	cout << m.get(0, 0) << '\n';
	m.set(0, 0, 100);
	cout << m.get(0, 0) << '\n';
	m.add(100);
	m.print();
	cout << m.add() << '\n';

	return 0;
}