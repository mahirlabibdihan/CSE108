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
	char title[100];
	int price;
	public:
		void setISBN(int isbn){this->isbn=isbn;}
		void setTitle(char* title){strcpy(this->title,title);}
		void setPrice(int price){this->price=price;}

		int getISBN(){return isbn;}
		char* getTitle(){return title;}
		int getPrice(){return price;}
		
		Book(){
			isbn=0;
			strcpy(title,"");
			price=0;
			cout<< "In the default constructor of the book: "<<isbn<<endl;
		}
		
		Book(int isbn, char* title, int price){
			this->isbn=isbn;
			strcpy(this->title,title);
			this->price=price;			
			cout<< "In the parameterized constructor of the book: "<<isbn<<endl;
		}
		
		Book(const Book& pr){
			this->isbn=pr.isbn;
			strcpy(this->title,pr.title);
			this->price=pr.price;
			cout<< "In the copy constructor of Book class with ISBN: "<<isbn<<endl;
		}
		
		~Book(){
			cout<< "In the destructor of the book: "<<isbn<<endl;
		}
			
		int isSameBook(Book pr){
			if (this->isbn==pr.isbn) return 1;
			else return 0;
		}
		
		Book getNewEdition(int isbn, int price){
			return Book(isbn,this->title,price);			
		}
		
		void print(){
			if (isbn>0) cout << "ISBN: "<< isbn <<", Title: " << title <<", Edition: "<<price<<endl;
			else cout<<"Values not yet set\n";
		}
};

int main(){
	cout<<green<<"\n+++++++++++Mark 1++++++++\n"<<def;
	Book b1;
	b1.print();	
	char title[]="Teach yourelf C++";
	int isbn=101;
	cout<<green<<"\n+++++++++++Mark 2++++++++\n"<<def;
	cout<<title<<endl;
	Book b2(isbn, title,3);
	b2.print();
	cout<<green<<"\n+++++++++++Mark 3++++++++\n"<<def;
	cout<<b2.isSameBook(b1)<<endl;
	cout<<green<<"\n+++++++++++Mark 4++++++++\n"<<def;
	Book b4=b2.getNewEdition(102,300);
	b4.print();
	cout<<green<<"\n+++++++++++Mark End++++++++\n"<<def;
}