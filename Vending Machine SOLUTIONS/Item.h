/*
Author: Adam Austin
Date: 01/27/2017
*/

#ifndef ITEM_H
#define ITEM_H
#include <iostream>
#include <array>

class Item
{
	public:
	Item();
	Item(std::string, double);
	double getPrice();
    std::string getProductName();

    void setPrice(double);
    void setProductName(std::string);
	
	private:
    double price;
    std::string productName;
};

#endif // ITEM_H
