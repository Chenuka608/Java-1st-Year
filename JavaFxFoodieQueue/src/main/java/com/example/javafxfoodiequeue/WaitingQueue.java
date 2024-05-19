package com.example.javafxfoodiequeue;

import java.util.*;
public class WaitingQueue {
    private int maxQueueSize;        //initialising the variables
    private Customer[] customers;
    private int front;  // Index of the front element
    private int rear;   // Index of the rear element
    private int size;   // Number of elements in the queue

    public static ArrayList<Customer> WaitingQCustomers = new ArrayList<>();

    public WaitingQueue(int maxQueueSize) { //@param maxQueueSize
        this.maxQueueSize = maxQueueSize;
        this.customers = new Customer[maxQueueSize];    //parameterised constructor method to set the MaxSize of the WaitingQueue and customers
                                                        //& customer array size
    }

    public void addToWaitingQ(Customer customer) { //Method adding customer to WaitingQ @param Customer customer
        if (!isFull()) {                          //checking if not full
            customers[rear] = customer;
            rear = (rear + 1) % maxQueueSize;        //customer added to the rear and rear is incremented rear will point back to original when reach MaxQueueSize
            size = size + 1;                                                             //thus implemented as a circular queue
        } else {
            System.out.println("Waiting Queue is FULL !");
        }

    }

    public Customer removeFromWaitingQ() {  //Method removing customer from WaitingQ
        Customer data = customers[front];     // customers[front] or first customer in WaitingQ is assinged to data
        if (!isEmpty()) {
            front = (front + 1) % maxQueueSize; // if not empty front pos will be incremented and when reach maxQueue size will point back to original pos
            size = size - 1;
        } else {
            System.out.println("Waiting Queue is Empty");
        }

        return data; //data is returned
    }

    public void viewWaitingQueue() { //Method to print customer in waiting Queue
        System.out.print("Customers in  Waiting Queue : ");
        for (int i = 0; i < size; i++) {        //iterates until the size of the customer array length and print the customers

            System.out.print(customers[(front + i) % maxQueueSize] + " ");
        }
        System.out.println();
        /*for (Customer customer : customers) {
            System.out.print(customer + " ");*/
    }

    public int getSize() {//returns size

        return size;
    }

    public boolean isEmpty() {//checks if empty

        return getSize() == 0;
    }

    public boolean isFull() {//checks array is full

        return getSize() == maxQueueSize;
    }

    public String customersInWQ() { //method to print customers in waiting queue
        String customersInWQ = "";

        for (int i = 0; i < size; i++) {
            Customer customer = customers[(front + i) % maxQueueSize];
             customersInWQ+= customer.getFirstname() + "  " + customer.getlastname() + " - " + customer.getRequiredBurgers() + "\n";
             WaitingQCustomers.add(customer);
        }
        return customersInWQ;
    }
}

