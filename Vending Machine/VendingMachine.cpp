/*
Author: Adam Austin
Date: 01/27/2017
*/

#include <iostream>
#include <array>
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
		cout << "Product Name at location " << i << " :" << merchandise[i].getProductName() << endl;
	}
}

void VendingMachine::assignProductLocation(Item _item)
{
	
}
