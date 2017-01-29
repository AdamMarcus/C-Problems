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
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		Item nullItem = Item("NULL", -1);
		merchandise[i] = nullItem;
	}
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
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{	
		Item tempItem = merchandise[i];	
		if (tempItem.getProductName().compare("NULL") == 0)
		{
			cout << "Inside IF with product: " << i << "  " << tempItem.getProductName() << endl;
			merchandise[i] = _item;
			i = PRODUCT_COUNT;
			cout << i << endl;
		}
	}
}
