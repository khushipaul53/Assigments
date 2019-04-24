/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignment1;

import java.util.*;

/**
 *
 * @author khushi
 */
/*
  Class Admin which consists of all the methods used to performs tasks like:
  Add new Customer, 
  Add new Car to an existing Customer, 
  List all Customers with their cars sorted by name, 
  List individual Customer based on ID, 
  Generate prizes for the customer.
 
  
 */
public class Admin {

    Scanner sc = new Scanner(System.in);

    ArrayList<Customer> customer_List = new ArrayList<>();    //Array list to contain the information.

//defination of Add_new_Costumer method.
    public void Add_New_Customer() {
        int count = 0;
        int id;
        System.out.println("Enter Customer ID No.");
        id = sc.nextInt();
        for (Customer cust : customer_List) {
            if (cust.Get_Customer_Id() == id) {   //checking entered id and customer id to proceed next to add car in 
                //particular id to customer
                count++;
                break;
            }
        }
        if (count > 0) {
            System.out.println("Customer with same ID already Exists.");
        } else {
            System.out.println("Enter Customer Name");
            String name = sc.next();
            Customer customer = new Customer(id, name);
            if (customer_List.add(customer)) {
                System.out.println("Customer Added");
            }
        }
    }

    //Defination of Add_car_To_customer;
    public void Add_Car_To_Customer() {

        int id;
        if(customer_List.size()==0)
        {
            System.out.println("No Data Found");
        }
        else{
        System.out.println("Enter Customer ID");
        id = sc.nextInt();

        for (Customer cust : customer_List) {
            if (id == cust.Get_Customer_Id()) {
                cust.Add_Car();
            } else {
                System.out.println("Id doesnt exist");
            }

        }
        }

    }

    //Defination of view_Customers in sorted order by name   
    public void View_Customers() {
        if (customer_List.size() == 0) {
            System.out.println("No Customer Found");
        }

        //Compairing name of all names to sort them using collections
        Comparator<Customer> comparator = (Customer c1, Customer c2) -> c1.Get_Customer_Name().compareTo(c2.Get_Customer_Name());

        //USing collections.sort to sort the list 
        Collections.sort(customer_List, comparator);

        //printing the sortes list 
        for (Customer cust : customer_List) {
            System.out.println("Customer ID: " + cust.Get_Customer_Id() + "\n" + "Customer Name:" + cust.Get_Customer_Name());

            cust.display();  //display function of Customer class is called.

        }
    }

    //  Defination of method List by id
    public void List_By_Id() {
        int id;
      if(customer_List.size()==0)
                {
                    System.out.println("No Data found");
                }
      else{
        System.out.println("Enter Customer id");
        id = sc.nextInt();

        for (Customer cust : customer_List) {
            if (id == cust.Get_Customer_Id()) {
                System.out.println("Customer id: " + cust.Get_Customer_Id() + "\n" + "Customer Name:" + cust.Get_Customer_Name());
                cust.display(); //display function of Customer class is called.
                break;
            } else {
                System.out.println("Id Does'nt exist");
            }

        }
    }
    }

    //   Prize function to display the winner
    public void Prize() {
        Prize.LuckyDraw(customer_List);
    }

}
