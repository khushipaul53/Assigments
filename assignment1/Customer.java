/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignment1;
//importing all clase and interfaces

import java.util.*;

/**
 *
 * @author khushi
 */
//Customer class 
public class Customer {

    Scanner sc = new Scanner(System.in);

    private int Customer_Id;  //Customer id
    private String Customer_Name;  //Customer Name
    ArrayList<Car> Car = new ArrayList<>();    //adding customer car to arraylist car

    public Customer(int id, String name) {
        Customer_Id = id;
        Customer_Name = name;
    }

    public int Get_Customer_Id() {
        return Customer_Id;       //get customer id

    }

    public String Get_Customer_Name() {
        return Customer_Name;                  //get customer name
    }
    
    public int get_id()
{
    int n=0;
    try{
        String input=sc.next();
        n=Integer.parseInt(input);
    }
    catch(Exception e)
    {
        System.out.println("Please enter valid id.");
        n=get_id();
    }
    return n;
}

    public int get_price()
{
    int n=0;
    try{
        String input=sc.next();
        n=Integer.parseInt(input);
    }
    catch(Exception e)
    {
        System.out.println("Please enter valid id.");
        n=get_price();
    }
    return n;
}
    public void Add_Car() //Add car to customer
    {
         
        System.out.println("Types of cars:-\n");
        System.out.println("1.Hyundai(Enter 1)");
        System.out.println("2.Toyota(Enter 2)");
        System.out.println("3.Maruti(Enter 3)");               //Details of car
        int choice =get_id();

        if(choice==1||choice==2||choice==3)
        {
        System.out.println("Enter Car Id:-");
        int CarId =get_id();
        System.out.println("Enter Car Model:-");
        String Model = sc.next();
        System.out.println("Enter Car Price:-");
        double Price =get_price();
        switch (choice) {
            case 1:
                Car.add(new Hyundai(CarId, Model, Price));       //Adding Car Details to the individual car(Hundai)
                break;
            case 2:
                Car.add(new Toyota(CarId, Model, Price));        //Adding Car to the individual car(Toyoto)
                break;
            case 3:
                Car.add(new Maruti(CarId, Model, Price));      //Adding Car to the individual car(Maruti)     
                break;
                
           }
         
        System.out.println("Car Added.");    //car Added Successfully to Customer car Arraylist
    }
        else{
            System.out.println("Enter correct option");
        }
    }
    public void display() {

        for (Car carList : Car) {
            carList.display();
        }
    }
}
