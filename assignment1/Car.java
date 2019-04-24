/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignment1;

/**
 *
 * @author khushi
 */
//importing all the class and interfaces
// An abstract method resaleValue() which will be override by every Car
import java.util.*;
//Car class
//hundai maruti toyoto inheriting Car class.

abstract public class Car {   // abstract class

    int carId;
    String carModel;
    double carPrice;

    int getId() {
        return carId;     //This method used to get id of Car and return Id of current object of int type  
    }

    String getModel() {
        return carModel;       //This method used to get id of Car and return Model of current object of String type  
    }

    double getPrice() {
        return carPrice;    //get car prize
    }

    void display() {
        System.out.println("Enter Car ID :-" + getId());                         //display Car details.
        System.out.println("Enter Car Model :-" + getModel());
        System.out.println("Enter Car Price :-" + getPrice());

        //System.out.println("Enter Car Resale Value :" + ResaleValue());
    }

    abstract double Resale_Value();  //abstract resale_value function defined in extended classses. and overrided by every car.

}

class Hyundai extends Car {

    /*This is Constructor of Hyundai class
	 *  id of int type 
         *  model of String Type
	    price of double type */
    Hyundai(int id, String model, double price) {
        carId = id;                                   //hundai car constructor geting car id,car model, car price
        carModel = model;
        carPrice = price;
    }

    double Resale_Value() {
        return carPrice * 0.40;                // definaton of abstract method resale_value. 
    }

}

class Toyota extends Car {

    /* This is Constructor of Toyoto class
	 *  id of int type 
         *  model of String Type
	 * price of double type*/
    Toyota(int id, String model, double price) {
        carId = id;
        carModel = model;                             //Toyoto car constructor for getting car id,car model,car price
        carPrice = price;
    }

    double Resale_Value() {
        return carPrice * 0.80;                   //defination of abstract method resale_value;
    }

}

class Maruti extends Car {

    /*This is Constructor of Hyundai class
	 * id of int type 
         *  model of String Type
	 *  price of double type */
    Maruti(int id, String model, double price) {
        carId = id;
        carModel = model;                                         //Maruti car constructor for getting car id,car model,car price
        carPrice = price;
    }

    double Resale_Value() {
        return carPrice * 0.60;                                    //defination of abstract method resale_value;
    }

}
