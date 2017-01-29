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
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{
		Item nullItem = Item("NULL", -1);
		merchandise[i] = nullItem;
	}
}

void VendingMachine::centerText(string text, int lineWidth)
{
	int frontSpace;
	int afterSpace;
	int extraSpace = lineWidth - text.length();
	if (extraSpace % 2 == 0)
	{
		frontSpace = extraSpace/2;
		afterSpace = frontSpace;
	}
	else
	{
		frontSpace = extraSpace/2;
		afterSpace = frontSpace + 1;
	}
	
	for (int i = 0; i < frontSpace; i++)
	{
		cout << " ";
	}
	cout << text;
	for (int i = 0; i < afterSpace; i++)
	{
		cout << " ";
	}
}

void VendingMachine::centerDouble(double var, int lineWidth)
{
	int frontSpace;
	int afterSpace;
	int extraSpace = lineWidth - 3;
	if (extraSpace % 2 == 0)
	{
		frontSpace = extraSpace/2;
		afterSpace = frontSpace;
	}
	else
	{
		frontSpace = extraSpace/2;
		afterSpace = frontSpace + 1;
	}
	
	for (int i = 0; i < frontSpace; i++)
	{
		cout << " ";
	}
	cout << setprecision(3) << var;
	for (int i = 0; i < afterSpace; i++)
	{
		cout << " ";
	}
}



void VendingMachine::printContents()
{
	for (int j = 0; j < 4; j++)
	{
		cout << "---------------------------";
	}
	cout << endl;
	
	
	int index = 0;
	for (int i = 0; i < 5; i++)
	{
		int tempIndex = index;
		for (int j = 0; j < 4; j++)
		{
			string ind = to_string(tempIndex + 1);
			cout <<  "|";
			centerText( ind, 25);
			cout << "|";
			tempIndex++;
		}
		cout << endl;
		tempIndex = index;
		for (int j = 0; j < 4; j++)
		{
			cout <<  "|";
			centerText(merchandise[tempIndex].getProductName(), 25);
			cout << "|";
			tempIndex++;
		}
		cout << endl;
		for (int j = 0; j < 4; j++)
		{
			cout <<  "|";
			centerDouble(merchandise[index].getPrice(), 24);
			cout << "|";
			index++;
		}
		cout << endl;
		for (int j = 0; j < 4; j++)
		{
			cout << "---------------------------";
		}
		cout << endl;
	}
	
}

void VendingMachine::assignProductLocation(Item _item)
{
	for (int i = 0; i < PRODUCT_COUNT; i++)
	{	
		Item tempItem = merchandise[i];	
		if (tempItem.getProductName().compare("NULL") == 0)
		{
			merchandise[i] = _item;
			i = PRODUCT_COUNT;
		}
	}
}
