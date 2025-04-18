#include <iostream>
#include <cstring>
using namespace std;
namespace infrastructure
{
class pool {
private:
	int height, width, depth;
	char painted_color[10];
public:
	void set_properties(int h, int w, int d, char *pc)
	{
		height = h;
		width = w;
		depth = d;
		strcpy(painted_color,pc);
	}
	void show() // This function will show the dimention (height x width x depth), and the underlying painted_color information
	{
		cout<<"Dimension: "<<height<<"x"<<width<<"x"<<depth<<endl;
		cout<<"Color: "<<painted_color<<endl;
	}
};
}


namespace sports
{
class pool {
private:
	char table_ingredient[20];
	char table_color[10];
public:
	void set_properties(char *ti, char* tc)
	{
		strcpy(table_ingredient,ti);
		strcpy(table_color,tc);
	}
	void show() // This function will show the pool table ingredient and table color
	{
		cout<<"Ingredient: "<<table_ingredient<<endl;
		cout<<"Color: "<<table_color<<endl;
	}
};
}

int main()
{
	infrastructure::pool a;
	a.set_properties(1,2,3,"Blue");
	a.show();
	sports::pool b;
	b.set_properties("Wood","Green");
	b.show();
}