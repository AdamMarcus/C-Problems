/*
Author: Adam Austin
Date: 01/27/2017

This is the driver for the vending machine application.

*/

#include <iostream>
#include <array>

#include "Item.h"
#include "VendingMachine.h"

using namespace std;

VendingMachine stockVendingMachine();
void takePayment(double);


int main()
{
	VendingMachine myVendingMachine = stockVendingMachine();
	bool done = false;
	while (!done)		//write menu
	{
		cout << "======Main Menu======" << endl;
		cout << "Enter any character to continue!" << endl;
		string userChoiceString;
		cin >> userChoiceString;

		if (userChoiceString.compare("OFF") != 0)
		{
			myVendingMachine.printContents();
			cout << "Enter product number!" << endl;
			int userProductChoice;
			cin >> userProductChoice;
			Item userSelection = myVendingMachine.getItemAtIndex(userProductChoice - 1);
			if (userSelection.getPrice() == -1)
			{
					cout << "Out of order." << endl;
			}		
			else 
			{
				cout << "Your selection is: " << userSelection.getProductName() << endl;
				cout << "You owe: " << userSelection.getPrice() << endl;
				takePayment(userSelection.getPrice());
				myVendingMachine.removeItemAtIndex(userProductChoice - 1);
			}
		}
		else
		{
			done = true;
		}
	}
	return 0;
}

void takePayment(double moneyOwed)
{
	 
	double userPayment;
	cin >> userPayment;
	if (userPayment == moneyOwed)
	{
		cout << "Enjoy your snack" << endl;
	}	
	else if (userPayment > moneyOwed)
	{
		double change = userPayment - moneyOwed;
		cout << "Your change is " << change << ", enjoy your snack!" << endl;
	}	
	else
	{
		cout << "You still owe " << moneyOwed - userPayment << "." << endl;	
		takePayment(moneyOwed - userPayment);
	}	
}

VendingMachine stockVendingMachine()
{
	string productNames[PRODUCT_COUNT] = { "Hot Chetos", "Chetos", "Smart Food" , " Doritos",
											"Ruffles", "Sun Chips", "Fritos" , "Cheez-It",
											"Sour Gummy Worms", "Skittles", "Snickers" , "M&Ms",
											"Twix", "Kit Kat", "Reese's" , "Almond Joy",
											"Butterfinger", "Mike & Ike's", "Nature Valey" , "Chunky Cookies" };
							
	double productPrices[PRODUCT_COUNT] = { 1.55, 1.55, 1.55 , 1.55,
											1.55, 1.55, 1.55 , 1.25,
											2.25, 1.55, 1.55 , 1.55,
											1.55, 1.55, 1.55 , 1.55,
											1.55, 1.55, 1.55 , 1.55 };
						
	VendingMachine newVendingMachine = VendingMachine();
	
	Item itemArray[PRODUCT_COUNT] = { };
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		Item tempItem = Item(productNames[i], productPrices[i]);
		itemArray[i] = tempItem;
	}
	
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		newVendingMachine.assignProductLocation(itemArray[i]);
	}
	return newVendingMachine;
}
