/*
Author: Adam Austin
Date: 01/27/2017

This is the driver for the vending machine application.

*/

#include <iostream>

#include "Item.h"

using namespace std;

int main()
{
  bool done = false;
  while (!done)		//write menu
  {
    cout << "======Main Menu======" << endl;
    cout << "1. Find a movie" << endl;
    cout << "2. Rent a movie" << endl;
    cout << "3. Print the inventory" << endl;
    cout << "4. Delete a movie" << endl;
    cout << "5. Count the movies" << endl;
    cout << "6. Quit" << endl;
    string userChoiceString;
    cin >> userChoiceString;
  }



}
