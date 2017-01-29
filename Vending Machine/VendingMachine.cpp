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
		Item emptyItem = Item("NULL" , -1);
		merchandise[i] = emptyItem;
	}	
}

void VendingMachine::printContents()
{
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		cout << "Product Name at location " << i + 1 << " :" << merchandise[i].getProductName() << endl;
	}
}

void VendingMachine::assignProductLocation(Item _item)
{
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		if (merchandise[i].getPrice() == -1)
		{
				merchandise[i] = _item;
				i = PRODUCT_COUNT;
		}	
	}	
}

Item VendingMachine::getItemAtIndex(int index)
{
	return merchandise[index];
}	

void VendingMachine::removeItemAtIndex(int index)
{
	Item emptyItem = Item("NULL" , -1);
	merchandise[index] = emptyItem;
}	
