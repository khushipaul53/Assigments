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
//
public class Car_Purchase {

    public static void main(String[] args) {

        String Choice;

        String Menu;
        Admin Admin = new Admin();

        Scanner sc = new Scanner(System.in);

        //Enter your choices.
        try {
            do {
                System.out.println("\n");
                System.out.println("Enter your Choice\n");
                System.out.println("1.Add new Customer");
                System.out.println("2.Add Car to an existing Customer");
                System.out.println("3.List of Customers with their cars sorted by name");
                System.out.println("4.List individual Customer based on ID");
                System.out.println("5.Generate prizes for the customer");

                Choice = sc.next();

                switch (Choice) {
                    case "1":
                        Admin.Add_New_Customer(); //Add new Customer 
                        break;
                    case "2":
                        Admin.Add_Car_To_Customer(); //Add Car to Costumer
                        break;
                    case "3":
                        Admin.View_Customers(); //To View Customer according sorted name
                        break;
                    case "4":
                        Admin.List_By_Id();// To view Customer according to Ids
                        break;
                    case "5":
                        Admin.Prize(); //To show the winner.
                        break;

                    default:
                        System.out.println("Admin please enter Correct choice");

                }
                System.out.println("If you want to use menu again press:- Y for Yes and N for NO");

                Menu = sc.next();

            } while (Menu.equals("Y") || Menu.equals("y"));
        } 
        catch (InputMismatchException e) {
            System.out.println("Input incorrect.. Please enter correct input");
        }
    }
}
