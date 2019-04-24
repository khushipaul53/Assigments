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
import java.util.*;
//prize class to display the winner

public class Prize {

    public static void LuckyDraw(ArrayList<Customer> customerList) {
        ArrayList<Integer> admin = new ArrayList<>();  //arraylist for admin to enter 3 id's
        ArrayList<Integer> Random = new ArrayList<>();  //arraylist for ramdom 6 values(id's) to choosen by system itself.
        int a;
        Scanner sc = new Scanner(System.in);
        if (customerList.size() == 0) {
            System.out.println("No costumer in list\n");
        } else if (customerList.size() > 6) {
            for (int i = 0; i < 6; i++) {
                a = (int) (Math.random() * 10 + 1);   //Random values
                Random.add(a);
            }
            System.out.println("Enter 3 Costumer id's of your choice\n");

            for (int i = 0; i < 3; i++) {
                int s = sc.nextInt();
                if (Random.contains(s)) {          //Admin entering 3 values.
                    admin.add(s);
                }
            }
            System.out.println(admin);
        } else {
            System.out.println("Add more Costumer(Atleast 6)");
        }

    }

}
