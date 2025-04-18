/************Topics of Demonstration********************/
/* => Dynamic Allocation of objects         		   */
/* => Constructor overloading		        		   */
/* => Copy constructor				        		   */
/* => Passing and returning objects from functions     */
/*******************************************************/

#include<iostream>
#include<cstring>
#include<cmath>
#include"colormod.h"

using namespace std;

class Book{
	int isbn;
	//char title[100];
	char* title=new char[100];
	int price;
	public:
		//Setter functions
		void setISBN(int isbn){this->isbn=isbn;}
		void setTitle(char* title){strcpy(this->title,title);}
		void setPrice(int price){this->price=price;}

		//Getter functions
		int getISBN(){return isbn;}
		char* getTitle(){return title;}
		int getPrice(){return price;}
		
		//Constructor functions
		Book(){ //default constructor
			isbn=0;
			strcpy(title,"");
			price=0;
			cout<< green <<"In the default constructor of the book: "<<isbn<<endl<<def;
		}
		Book(int isbn, char* title, int price){//constructor with parameters
			this->isbn=isbn;
			strcpy(this->title,title);
			this->price=price;			
			cout<< green<<"In the parameterized constructor of the book: "<<isbn<<endl<<def;
		}
		Book(const Book& book){//Copy constructor
			// *this=book;
			this->isbn=book.isbn;
			this->price=book.price;
			strcpy(this->title,book.title);			
			
			cout<< green<<"In the copy constructor of the book: "<<isbn<<endl<<def;
		}
		
		//destructor function
		~Book(){ 
			cout<< red <<"In the destructor of the book: "<<isbn<<endl <<def;
			delete[] title;

			//cout<<yellow<<title<<def<<endl;
		}
			
		//Other functions
		void print(){
			if (isbn>0) cout << yellow << "ISBN: "<< isbn <<", Title: " << title <<", Edition: "<<price<<endl<<def;
			else cout<<cyan << "Values not yet set\n"<<def;
		}

		int isTheSameBook(Book b){
			if (this->isbn==b.isbn) return 1;
			else return 0;
		}

		Book generateNewEdition(int isbn, int price){
			Book temp(isbn,this->title,price);
			return temp;			
		}
};

int main(){
	cout<<magenta<<"\n+++++++++++Start++++++++\n"<<def;
	Book b1;
	b1.print();
	Book b2(101,"Teach yourself C++",230);
	b2.print();	
	cout<<magenta<<"\n+++++++++++Mark 1++++++++\n"<<def;
	Book* bp1=new Book(102,"C++ for dummies",250);
	bp1->print();
	delete bp1;
	
	Book* ba=new Book[3];	
	delete [] ba;	

	cout<<magenta<<"\n+++++++++++Mark 2++++++++\n"<<def;
	Book b3(b2); //initialization
	b3.print();

	Book b4=b2; //initialization
	b4.print();
	b4.setTitle("Java for dummies");
	b4.print(); //output: java for dummies??
	b3.print(); //output: java for dummies??
	b2.print(); //output: java for dummies??*/
	cout<<magenta<<"\n+++++++++++Mark 3++++++++\n"<<def;
	int result=b2.isTheSameBook(b3);
	if (result==1) cout<<green<<"The two books are same"<<def<<endl;
	else cout<<red<<"The books are not the same"<<def<<endl;

	Book b5=b2.generateNewEdition(201,330);
	b5.print();

	cout<<magenta<<"\n+++++++++++End++++++++\n"<<def;	
}