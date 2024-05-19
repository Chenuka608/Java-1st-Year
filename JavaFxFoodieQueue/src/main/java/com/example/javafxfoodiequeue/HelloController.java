package com.example.javafxfoodiequeue;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.util.ArrayList;


public class HelloController {


    @FXML
    private Label Queue1label;    //setting labels to print in application
    @FXML
    private Label Queue2Label;
    @FXML
    private Label Queue3Label;

    @FXML
    private Label WaitingQLabel;

    public void viewCustomer() { //on the click of the view customers in queue button (OnAction viewCustomer)
        String Queue1Names = "";
        String Queue2Names = "";
        String Queue3Names = "";
        String WaitingQueue ="";
        for (int i = 0; i < Main.arrQueue[0].getCustomerSize(); i++) { //will iterate through each arrQueue[index] foodQueue array size until the size of the list
            Customer customer = Main.arrQueue[0].getCustomer(i);
            Queue1Names += customer.getFirstname() + "  " + customer.getlastname() + " - " + customer.getRequiredBurgers()+ "\n"; //concatenates the data to be printed

        }
        for (int i = 0; i < Main.arrQueue[1].getCustomerSize(); i++) {
            Customer customer = Main.arrQueue[1].getCustomer(i);
            Queue2Names += customer.getFirstname() + "  " + customer.getlastname() + " - " + customer.getRequiredBurgers()+ "\n";//concatenates the data to be printed
        }
        for (int i = 0; i < Main.arrQueue[2].getCustomerSize(); i++) {
            Customer customer = Main.arrQueue[2].getCustomer(i);
            Queue3Names += customer.getFirstname() + "  " + customer.getlastname() + " - " + customer.getRequiredBurgers()+ "\n";//concatenates the data to be printed
        }

        WaitingQLabel.setText(Main.waitingListQueue.customersInWQ());

        Queue1label.setText(Queue1Names);
        Queue2Label.setText(Queue2Names);
        Queue3Label.setText(Queue3Names);     //labels will now display the concatenated strings thus displaying all customer data in each queue.
    }

}


