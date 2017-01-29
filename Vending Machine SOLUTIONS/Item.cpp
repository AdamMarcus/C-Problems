/*
Author: Adam Austin
Date: 01/27/2017
*/

#include <iostream>
#include <array>
#include "Item.h"

using namespace std;

Item::Item()
{
	
}

Item::Item(string _productName, double _price)
{
	productName = _productName;
	price = _price;
}

double Item::getPrice()
{
	return price;
}

string Item::getProductName()
{
	return productName;
}

void Item::setPrice(double _price)
{
	price = _price;
}

void Item::setProductName(string _productName)
{
	productName = _productName;
}
