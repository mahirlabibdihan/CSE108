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
#include "book.cpp"

using namespace std;

class BookShop{
	char* name;
	Book* books[];
	int size;
	int count;
	public:
		BookShop(){
		}
		
		BookShop(int size){
		}
		
		BookShop(const BookShop& pr){
		}
		
		~BookShop(){		
		}
			
		int addBook(Book b){
		}
		
		int addBook(Book[] ba, int count){			
		}

		Book getBookInfo(char* title){			
		}
		
		Book updateBookPrice(int isbn, int price){			
		}
		
		Book removeBook(int isbn){			
		}
				
		int totalPrice(){
		}		
		
		void print(){
		}

		BookShop mergeShop(BookShop b){
			
		}		
};

int main(){
	
}