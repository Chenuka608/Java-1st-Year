package com.example.javafxfoodiequeue;
import javafx.application.Application;

import java.util.*;
public class Main {                                    //Student name : Chenuka Saratchandra  UoW:1998757W  IIT ID - 20221022

    public static void cashierPattern() {
        System.out.println("");
        System.out.println(" **********************");    //Printing the cashier and queue format
        System.out.println(" *      Cashiers      *");
        System.out.println(" **********************");

        System.out.println("QUEUE1\tQUEUE2\t  QUEUE3");
    }

    private static int queue1Income = 0;                    // Initialising the Variables
    private static int queue2Income = 0;                    //queueIncome variables for each queue
    private static int queue3Income = 0;
    private static boolean isTrue = true;                  // condition to run the while loop
    public static FoodQueue[] arrQueue = new FoodQueue[3]; //Making FoodQueue array of size 3 , arrQueue[0] = queue1, arrQueue[1]=queue2, arrQueue[2]=queue3

    public static WaitingQueue waitingListQueue;       // Intialising the WaitingQueue object
    private static int remainingStock = 50;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        arrQueue[0] = new FoodQueue(2);     //assigning each FoodQueue objects (arrQueue) to a new FoodQueue object of a Max Queue Size
        arrQueue[1] = new FoodQueue(3);     // ensuring all Queues can hold a certain amount of customers ( arrQueue[0] can hold 2 customers)
        arrQueue[2] = new FoodQueue(5);
        waitingListQueue = new WaitingQueue(5);




        System.out.println("\nFoodies Fave Food Center Main Menu\n------------------------------------------------------------------");
        System.out.println("100 or VFQ:  View all Queues.");
        System.out.println("101 or VEQ:  View all Empty Queues.");
        System.out.println("102 or ACQ:  Add customers to a Queue");
        System.out.println("103 or RCQ:  Remove a customer from a Queue.");
        System.out.println("104 or PCQ:  Remove a served customer");
        System.out.println("105 or VCS:  View customer Sorted in alphabetical");        //printing the Main Menu
        System.out.println("106 or SPD:  Store Program data into file.");
        System.out.println("107 or LPD:  Load Program Data from file.");
        System.out.println("108 or STK:  View Remaining burger Stock.");
        System.out.println("109 or AFS:  Add burgers to Stock");
        System.out.println("110 or IFQ:  View all Queue income ");
        System.out.println("112 or GUI   Launch the GUI ");
        System.out.println("999 or EXT:  Exit the Program.\n------------------------------------------------------------------");


        while (isTrue) {

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Customers in Queue :");
            arrQueue[0].printCustomers();
            arrQueue[1].printCustomers();
            arrQueue[2].printCustomers();                       // Method to print the customers in each Queue arraylist to easily see the names
            System.out.println();
            System.out.println();
            waitingListQueue.viewWaitingQueue();                // printing customers in the waiting queue
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println();
            System.out.println("Enter you option :");  // prompting user for option
            String option = input.next();



            switch (option) {        // switch case implemented for each option so depending on choice the relevant method is called
                case "100":
                case "VFQ":
                    viewAllQueues();
                    break;

                case "101":
                case "VEQ":
                    viewEmptyQueues();
                    break;

                case "102":
                case "ACQ":
                    AddCustomer(input);
                    break;

                case "103":
                case "RCQ":
                    removeCustomer(input);
                    break;

                case "104":
                case "PCQ":
                    removeServedCustomer(input);
                    break;

                case "105":
                case "VSC":
                    sortCustomerNames();
                    break;

                case "110":
                case "IFQ":
                    queueIncome();
                    break;

                case "106":
                case "SPD":
                    storeData();
                    break;

                case "107":
                case "LPD":
                    loadData();
                    break;

                case"108": case"STK":
                    System.out.println("Remaining stock is "+remainingStock);
                    break;

                case "109":
                case "AFS":
                    AddStocks(input);
                    break;
                case "999":
                case "EXT":
                    System.out.println("Thank You , Have a Great Day !");
                    isTrue = false;                                          // setting isTrue to false to break the while loop
                    break;

                case"112": case"GUI":
                    Application.launch(HelloApplication.class,args);     // launching the application from the Hello Application class
                    break;

                default:
                    System.out.println("Invalid Option !");  // handling in case invalid input
                    break;

            }
        }
    }

    private static void AddCustomer(Scanner input) {                    // Method to Add Customers to the Shortest Queue  ( @param Scanner input)
        try {
            System.out.println("Enter customer First name:");
            String name = input.next();
            System.out.println("Enter customer last name:");                // getting customer data ( firstname,lastname and required burgers)
            String lastname = input.next();
            System.out.println("Enter required burger amount:");
            int requiredBurgers = input.nextInt();
            if (requiredBurgers < 0 || requiredBurgers > 50) {                      // validating if required burgers entered is correct
                System.out.println(" Invalid burger amount has been requested  !");
            } else {
                FoodQueue minQueue = null;                                           // initialising variables to check which queue is the shortest
                int minCustomerSize = 100;
                int minQueueIndex = -1;
                boolean allQueuesFull = true;     //to check if all queues are full

                for (int i = 0; i < arrQueue.length; i++) {
                    int customerSize = arrQueue[i].getCustomerSize();               // for each arrQueue[index]it will get the customer list size and MaxQueueSize
                    if (customerSize < arrQueue[i].getMaxQueueSize()) {             // Finds the arrQueue index with the least number of customers
                        allQueuesFull = false;                                      // setting AllQueuesFull to false
                        if (customerSize < minCustomerSize) {
                            minCustomerSize = customerSize;            // replacing the minCustomerSize and updating the index position of the shortest Queue
                            minQueueIndex = i;
                        }
                    }
                }
                if (!allQueuesFull && minQueueIndex != -1) {                            // validating whether allQueues aren't full
                    minQueue = arrQueue[minQueueIndex];                                 //assigning the arrQueue[minQueueIndex] to minQueue
                    Customer customer = new Customer(name, lastname, requiredBurgers);     // creating the new customer object with the relevant entered data
                    minQueue.Addcustomers(customer);
                    System.out.println("Customer " + name + " added to Queue " + (minQueueIndex + 1));     //adding the Customer to the shortest Queue
                } else {
                    System.out.println("\n The Queue is Full ! - customers now will be added to the Waiting Queue");  //if AllQueuesFull add customer to Waiting Q
                    if (!waitingListQueue.isFull()) {
                        Customer customer = new Customer(name, lastname, requiredBurgers);          //validating if WaitingList is full
                        waitingListQueue.addToWaitingQ(customer);                                   // customer object created and added to WaitingQ


                    } else {
                        System.out.println("\nWaiting Queue is currently FULL !");                 //else if full it will print Waiting Queue is full
                    }
                }
            }
        }catch (InputMismatchException e){                                                      //Error Handling in case user enters String for Integer
            System.out.println("Pls Enter an Integer Value !");input.next();
        }
    }

    private static void queueIncome() {
        System.out.println("Queue 1 income is : "+queue1Income);     //printing the relevant queue income after it has been calculated
        System.out.println("Queue 2 income is : "+queue2Income);
        System.out.println("Queue 3 income is : "+queue3Income);
    }

    private static void removeCustomer(Scanner input) {      //method to remove customers from any Queue and position        @param - Scanner input
        try {
            System.out.println("Enter Queue Number");          // prompts user to enter Queue number and Position
            int queueNum = input.nextInt();
            System.out.println("Enter Customer Position");
            int customerPos = input.nextInt();
            if (queueNum < 1 || queueNum > 3) {
                System.out.println("Invalid Queue Entered");    //validates whether entered queueNum is in range 1 - 3

            } else {                               //arrQueue[index].getCustomer(pos) will assign the customer to be removed in the customer variable
                Customer customer = arrQueue[queueNum - 1].getCustomer(customerPos - 1);  // customer  to be removed is found
                arrQueue[queueNum - 1].removeCustomers(customer);                               //remove customer method in foodQueue class is called
                System.out.println("\n" + "Customer " + customer.getFirstname() + " has been removed from Queue "+queueNum);
                FoodQueue minQueue = null;
                int minCustomerSize = 100;                                                  //initialising variables to find shortest queue
                int minQueueIndex = -1;
                boolean allQueuesFull = true;

                if (waitingListQueue.getSize() > 0) {                                //checking if WaitingQueue is having customers
                    Customer nextCustomer = waitingListQueue.removeFromWaitingQ();   //removing the front end customer in waitingQ and assigning to nextCustomer

                    for (int i = 0; i < arrQueue.length; i++) {
                        int customerSize = arrQueue[i].getCustomerSize();
                        if (customerSize < arrQueue[i].getMaxQueueSize()) {             // Find the queue with the least number of customers
                            allQueuesFull = false;
                            if (customerSize < minCustomerSize) {
                                minCustomerSize = customerSize;
                                minQueueIndex = i;
                            }
                        }
                    }
                    if (!allQueuesFull && minQueueIndex != -1) {
                        minQueue = arrQueue[minQueueIndex];              //to the determined MinQueueIndex the nextCustomer object will be added to the Queue
                        minQueue.Addcustomers(nextCustomer);            //add customer method called to add the customer in the waiting Q
                        System.out.println("\n"+"Customer " + nextCustomer.getFirstname() + " has now been added from the waiting list to Queue " + (minQueueIndex + 1));

                    }
                }
            }

        } catch (IndexOutOfBoundsException e) {                                                 //Exceptions handling incase user enters invalid data
            System.out.println("Customer has not been found in the entered queue position");
        }catch(InputMismatchException e){
            System.out.println("Pls Enter an Integer value!");input.next();
        }
    }
    private static void removeServedCustomer(Scanner input) {  //Method to remove served Customers at index pos [0] of any queue , decrements the burger stock and
        try {                                                 //and calculates the income of each queue                  @param -  Scanner input
            if (remainingStock <=0) {
                System.out.println("--- Burger Stock is over !---- ");                 //checking if burger stock is <= 0
                remainingStock=0;
            } else {
                System.out.println("Enter Queue Number");                              //asking user to enter Queue num and validating
                int queueNum = input.nextInt();
                if (queueNum < 1 || queueNum > 3) {
                    System.out.println("Invalid Queue Entered");
                } else {
                    Customer customer = arrQueue[queueNum - 1].getCustomer(0); //getCustomer at entered Queue, index pos 0 and assign to the customer
                    int requiredBurgers = customer.getRequiredBurgers();               //getting the customers required burgers
                    if (requiredBurgers > remainingStock) {
                        System.out.println("Burger Stock is too Low to serve !");   //checking if they have enough stock to provide for the customer
                    } else {
                        arrQueue[queueNum - 1].removeCustomers(customer);   //customer in that arrQueue will be removed calling the remove method
                        remainingStock -= requiredBurgers;                  //Decrementing the remaining stock
                        System.out.println("\n" + "Customer " + customer.getFirstname() + " has been served" + " Remaining stock = " + remainingStock);
                        FoodQueue minQueue = null;
                        int minCustomerSize = 100;
                        int minQueueIndex = -1;
                        boolean allQueuesFull = true;
                        if (waitingListQueue.getSize() > 0) {
                            Customer nextCustomer = waitingListQueue.removeFromWaitingQ();    //customer in the front end of the Waiting Q is added to nextCustomer

                            for (int i = 0; i < arrQueue.length; i++) {
                                int customerSize = arrQueue[i].getCustomerSize();
                                if (customerSize < arrQueue[i].getMaxQueueSize()) {             // Find the queue with the least number of customers
                                    allQueuesFull = false;
                                    if (customerSize < minCustomerSize) {
                                        minCustomerSize = customerSize;
                                        minQueueIndex = i;
                                    }
                                }
                            }
                            if (!allQueuesFull && minQueueIndex != -1) {
                                minQueue = arrQueue[minQueueIndex];                 // adding nextCustomer to the arrQueue with least customers
                                minQueue.Addcustomers(nextCustomer);
                                System.out.println("\n" + "Customer " + nextCustomer.getFirstname() + " has now been added from the waiting list to Queue " + (minQueueIndex + 1));
                            }
                        }

                        if (queueNum == 1) {
                            queue1Income += requiredBurgers * 650;  //calculating the queue income for each queue
                        } else if (queueNum == 2) {
                            queue2Income += requiredBurgers * 650;
                        } else {
                            queue3Income += requiredBurgers * 650;
                        }
                        if (remainingStock < 10) {
                            System.out.println("-----Burger Stock is Less than 10!------");  //printing warning message once remaining stock < 10
                        }
                    }
                }
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Customer has not been found in the entered queue position");
        } catch (InputMismatchException e){
            System.out.println("Pls Enter an Integer value !");input.next();    //Handling exceptions in case user enters String instead of Integer
        }
    }
    private static void viewAllQueues() {       //Method to view all slots of the Queue to see which are Occupied "0" and Unoccupied "X"
        cashierPattern();
        int maxQSize = 0;
        for (int i = 0; i < arrQueue.length; i++) {
            if (arrQueue[i].getMaxQueueSize() > maxQSize) {     //for each arrayQueue determining its maximum FoodQueue object size (Queue size)
                maxQSize = arrQueue[i].getMaxQueueSize();
            }
        }
        for (int i = 0; i < maxQSize; i++) {                               //iterating until the MaxQsize
            for (int j = 0; j < arrQueue.length; j++) {
                int customersInQueue = arrQueue[j].getCustomerSize();     // getting the current arraylist size of arrQueue object determining the customers in queue
                int queueMax = arrQueue[j].getMaxQueueSize();             // getting the maximum queuesize of arrQueue object
                if (i < queueMax) {
                    if (i < customersInQueue) {
                        System.out.print(" 0        ");           // if i is less than arraylist size then a customer is present occupied is printed "0" else "X"
                    } else {
                        System.out.print(" X        ");
                    }
                } else {
                    System.out.print("          ");                                    // prints empty space
                }
            }
            System.out.println();                                                    // moves to the next line to print the next row of outputs
        }
    }
    private static void viewEmptyQueues() {     // Method to see all Unoccupied slots within the 3 arrQueues "X"
        cashierPattern();                                          //printing the cashier pattern
        int maxQSize = 0;
        for (int i = 0; i < arrQueue.length; i++) {
            if (arrQueue[i].getMaxQueueSize() > maxQSize) {     //for each arrayQueue determining its maximum FoodQueue object size (Queue size)
                maxQSize = arrQueue[i].getMaxQueueSize();
            }
        }
        for (int i = 0; i < maxQSize; i++) {                               //iterating until the MaxQsize
            for (int j = 0; j < arrQueue.length; j++) {
                int customersInQueue = arrQueue[j].getCustomerSize();     // getting the current arraylist size of arrQueue object determining the customers in queue
                int queueMax = arrQueue[j].getMaxQueueSize();             // getting the maximum queuesize of arrQueue object
                if (i < queueMax) {
                    if (i < customersInQueue) {
                        System.out.print("          ");           // if i is less than arraylist size then a customer is present occupied is black is printed
                    } else {
                        System.out.print(" X        ");                 // only shows the empty slots
                    }
                } else {
                    System.out.print("          ");                                    // prints empty space
                }
            }
            System.out.println();                                                    // moves to the next line to print the next row of outputs
        }
    }
    private static void sortCustomerNames(){                                        //calls the FoodQueue class sort method which prints all the sorted names
        System.out.println("Customer names Sorted in Alphabetical Order ");
        FoodQueue.sortnames();
    }
    private static void storeData(){    //Method to store all customer data to 3 textfiles
        arrQueue[0].storeQueueData("Queue1Data.txt");          //for each arrayQueue it will call the storeQueueData method in foodqueue to store the data
        arrQueue[1].storeQueueData("Queue2Data.txt");          // to the relevant txtFileName
        arrQueue[2].storeQueueData("Queue3Data.txt");

    }
    private static void loadData(){  //Method to load data method to load the customer data back into the original queue arraylists
        queue1Income=0;
        queue2Income=0;                //resetting the queueIncome and clearing the storedNames which were saved to sort names alphabetically in the FoodQueue class
        queue3Income=0;
        FoodQueue.clearStoredNames();
        arrQueue[0].loadQueueData("Queue1Data.txt");   //for each arrQueue it will call the loadQueueData method in foodQueue to load to data to their
        arrQueue[1].loadQueueData("Queue2Data.txt");   // arraylist (customers) of MaxQueueSize
        arrQueue[2].loadQueueData("Queue3Data.txt");
    }
    private static void AddStocks(Scanner input){ //* Method to addStocks to the remainingstock var   @param Scanner input
        try{System.out.println("Enter the amount of Stocks to be Added : ");  //asking user for the amount of stocks to be added
            int stocks = input.nextInt();
            if(stocks<0 || stocks+remainingStock>50){          //validates the required criterias for entering stock
                System.out.println("Given Stock cannot be added ");
            }else{
                System.out.println("Stock has been Added Successfully ");
                remainingStock+=stocks;      // remaining stocks is incremented by the given value
            }
        }catch(Exception e){
            System.out.println("Pls enter an Integer "); // exception incase user enters invalid data
            input.next();
        }
    }
}





























































