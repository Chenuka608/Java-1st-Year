
package com.example.javafxfoodiequeue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FoodQueue {
    private int maxQueueSize;  //each FoodQueue has a MaxQueueSize                                 //initialising the FoodQueue variables
    private static ArrayList<String> storedNames = new ArrayList<>();    //Arraylist to store the added first and lastNames for sorting

    private ArrayList<Customer> customers;           //3 seperate customers arraylist will be made for each FoodQueue

    public FoodQueue(int maxQueueSize) { //@param MaxQueueSize
        this.customers = new ArrayList<>();          //*parameterized constructor method to set the variables
        this.maxQueueSize = maxQueueSize;

    }

    public void Addcustomers(Customer customer) {       //*Method to add customers to the relevant customers arraylist (@param Customer customer)
        if (customers.size() != maxQueueSize) {         // checks if the arraylist size is not equal to maxQueueSize
            customers.add(customer);
            String firstname = customer.getFirstname();         //adds the customer to the arraylist and the first name and last name are added to the
            String lastname = customer.getlastname();           // storedNames arraylist for sorting
            storedNames.add(firstname + " " + lastname);
        } else {
            System.out.println("The Queue is Full !");

        }
    }

    public void removeCustomers(Customer customer) {  //*Method to remove customers from the customers arraylist of the particular arrQueue (@param customer)
        if (customers.contains(customer)) {           // checking if customer is there in the customers arraylist if so it will remove the customer
            customers.remove(customer);
            storedNames.remove(customer.getFirstname() + " " + customer.getlastname());  //removing the customer name from the storedNames used for sorting
        } else {
            System.out.println(customer.getFirstname() + " has not been found in the selected queue"); //else prints not found
        }

    }

    public void printCustomers() {
        //System.out.print(customers);
        for (Customer customer : customers) {       //*Method prints all customers in the customers array list
            System.out.print(customer);
            System.out.print(" ");
        }
    }
    public static void clearStoredNames(){    //*Method which when customers have been loaded it calls this method to clear the storedNames array
        storedNames.clear();
    }

    public int getMaxQueueSize() { // *Method to return the arrQueues MaxQueueSize

        return maxQueueSize;
    }

    public int getCustomerSize() { // Method which returns the arrQueue[indexes] current customers arraylist size

        return customers.size();
    }

    public Customer getCustomer(int index) { //Method to return the customer object at the entered index by converting the customers to array @param int index

        return customers.toArray(new Customer[0])[index];
    }

    public static void sortnames() { //Method to sortnames in alphabetical order using bubble sort
        int nameListSize= storedNames.size();
        String[] nameArr = storedNames.toArray(new String[0]); //converts the storedNames to an array

        for (int i = 0; i < nameListSize - 1; i++) {             //looping until the nameListSize
            for (int j = 0; j < nameListSize - i - 1; j++) {
                if (nameArr[j].compareToIgnoreCase(nameArr[j + 1]) > 0) { //comparing the names which are in [j] and [j+1] index

                    String temp = nameArr[j];       //swapping the indexes to sort the names
                    nameArr[j] = nameArr[j + 1];
                    nameArr[j + 1] = temp;
                }
            }
        }
        for(String sortedname : nameArr){
            System.out.println(sortedname);      //printing the sorted names
        }
    }
    public void storeQueueData (String txtFileName){   //*Method writes customer data in each arrQueue to the txtfile     @param String txtFileName
        try {
            FileWriter storeData = new FileWriter(txtFileName);      //FileWriter makes a new textfile taking the param txtFileName to write the data
            for (Customer person : customers) {
                String line = person.getFirstname() + " " + person.getlastname() + " " + person.getRequiredBurgers();
                storeData.write(line + "\n");              //writes each String line with .write and moves to the next line
            }
            storeData.close();
            System.out.println("File Saved Successfully");

        } catch (IOException e) {                                                   //exception incase writing fault occurs
            System.out.println("An Error Occurred when writing: " + e.getMessage());
        }
    }
    public void loadQueueData(String txtFileName) {  //*Method that loads the data from textfiles back to the customer arraylist @param String txtFileName
        customers.clear(); // clearing the customers arraylist to load the stored customers
        try {
            File rf = new File(txtFileName); //rf object created with txtFileName param
            Scanner read = new Scanner(rf);     //creating a read file object

            while(read.hasNextLine()){ //until the textfile has another line it will run
                String customerData= read.nextLine();
                String[] data = customerData.split(" "); //splitting each word by there space and storing each word index in an array called data
                String firstname = data[0];       // splitted word in data array [0] is assigned to first name
                String lastname = data[1];         // word in data array[1] in assigned to lastname
                int requiredBurgers = Integer.parseInt(data[2]); //data array[3] is the required Burgers so parsed as Int and stored in requiredBurgers

                Customer customer = new Customer(firstname,lastname,requiredBurgers);   // new customer object created
                customers.add(customer);                                                // customer added to the customers array
                storedNames.add(firstname + " " + lastname);
                System.out.println(data[0] + " has been loaded to back to the Queue "+"\n"); // data[0] first name printed to show he has been added to Queue
            }
            read.close();
            System.out.println("--------File Loaded Successfully--------"+"\n");

        } catch (IOException e) {
            System.out.println("An Error Occurred when reading: " + e.getMessage()); //error handling
        }
    }
}







