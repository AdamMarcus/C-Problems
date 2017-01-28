/*
Author: Adam Austin
Date: 01/27/2017
*/

#ifndef ITEM_H
#define ITEM_H
#include <iostream>

class Item
{
  private:
    double price;
    std::string productName;

    double getPrice();
    std::string getProductName();

    void setPrice(double);
    void setProductName(std::string);

};

#endif // ITEM_H
