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

int main()
{
  bool done = false;
  while (!done)		//write menu
  {
    VendingMachine myVendingMachine = stockVendingMachine();
    myVendingMachine.printContents();
   
    cout << "======Main Menu======" << endl;
    cout << "Enter any character to continue!" << endl;
    string userChoiceString;
    cin >> userChoiceString;
   
    if (userChoiceString.compare("OFF") != 0)
    {
		myVendingMachine.printContents();
		
	}
	else
	{
		done = true;
	}
  }
  return 0;
}

VendingMachine stockVendingMachine()
{
	string productNames[PRODUCT_COUNT] = { "Hot Chetos", "Chetos", "Smart Food" , " Doritos",
											"Ruffles", "Sun Chips", "Fritos" , "Cheez-It",
											"Sour Gummy Worms", "Skittles", "Snickers" , "M&Ms",
											"Twix", "Kit Kat", "Reese's" , "Almond Joy",
											"Butterfinger", "Mike & Ike's", "Nature Valey" , "Chunky Cookies" };
							
	double productPrices[PRODUCT_COUNT] = { 1.50, 1.50, 1.50 , 1.50,
											1.50, 1.50, 1.50 , 1,
											2.25, 1.50, 1.50 , 1.50,
											1.50, 1.50, 1.50 , 1.50,
											1.50, 1.50, 1.50 , 1.50 };
						
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
