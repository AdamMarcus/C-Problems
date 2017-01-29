/*
Author: Adam Austin
Date: 01/27/2017
*/

#include <iostream>
#include <array>
#include <iomanip>
#include "Item.h"
#include "VendingMachine.h"


using namespace std;

VendingMachine::VendingMachine()
{
	
}

void VendingMachine::printContents()
{
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		cout << setw(20) << "|" << /*merchandise[i].getProductName() <<*/ "|" << endl;
		//cout << setw(20) << "|" << i << " : " << merchandise[i].getPrice() << "|" << endl;
	}
}

void VendingMachine::assignProductLocation(Item _item)
{
	
}
