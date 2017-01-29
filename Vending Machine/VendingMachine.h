/*
Author: Adam Austin
Date: 01/27/2017
*/

#ifndef VENDINGMACHINE_H
#define VENDINGMACHINE_H

#include <iostream>
#include <array>
#include "Item.h"

using namespace std;

const int PRODUCT_COUNT = 20;

class VendingMachine
{
	public:
	VendingMachine();
	void assignProductLocation(Item);
	void printContents();
	void centerText(string, int);
	
	private:
	Item merchandise[PRODUCT_COUNT];
};

#endif // VENDINGMACHINE_H
