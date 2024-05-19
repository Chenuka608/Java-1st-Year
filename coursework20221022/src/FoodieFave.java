import java.io.*;
import java.io.IOException;
import java.util.*;
public class FoodieFave {
    //variable declaration                        Chenuka Sarathchandra ID-20221022      UoW ID- w19987574  SD2 CW
    private static boolean isTrue = true;
    private static boolean foundCustomer = false;
    private static String[] Queue1 = new String[2];   // Arrays for queues of [SIZE]
    private static String[] Queue2 = new String[3];
    private static String[] Queue3 = new String[5];
    private static String[] QueueData = new String[10];
    private static int remainingStock = 50;
    private static int i = 0;
    private static int j = 0;
    private static int k = 0;
    private static int original_i = 0;
    private static int original_j = 0;
    private static int original_k = 0;
    private static int queue1Size = 2;
    private static int queue2Size = 3;
    private static int queue3Size = 5;
    public static void cashierPattern() {
        System.out.println("");
        System.out.println(" **********************");    //Printing the cashier and queue format
        System.out.println(" *      Cashiers      *");
        System.out.println(" **********************");
        System.out.println("");
        System.out.println("QUEUE1\tQUEUE2\tQUEUE3");
    }
    private static int compareNames(String firstname, String secondname) {        //*method to compare the Strings to order
        int lenMinimum = Math.min(firstname.length(), secondname.length());      //them in alphabetical order
        for (int i = 0; i < lenMinimum; i++) {                                   //@param1 firstname  @param2 secondname
            char char1 = Character.toLowerCase(firstname.charAt(i));
            char char2 = Character.toLowerCase(secondname.charAt(i)); // for loop iterates from i=0 till the shortest len of the 2 names.
            if (char1 != char2) {
                return char1 - char2;           // returns the ascii difference between the characters at index i
            }
        }
        return firstname.length() - secondname.length(); // else if same characters it will return the length difference
    }
    public static String burgerStocks(boolean display) {  //method which decrements the remaining stock and prints it
        remainingStock -= 5;                              // @param boolean display
        if (display) {
            return "Remaining stock is " + remainingStock;
        }
        return "";
    }
    public static void main(String[] args) {     // Main method
        Scanner input = new Scanner(System.in);    // implementing scanner variable called input

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
        System.out.println("999 or EXT:  Exit the Program.\n------------------------------------------------------------------");

        while (isTrue) {      //starting the while loop

            /*System.out.println(Arrays.toString(Queue1));        // displaying the arrays
            System.out.println(Arrays.toString(Queue2));
            System.out.println(Arrays.toString(Queue3));*/
            System.out.println("");
            //asking the user option
            System.out.println("Enter Your Option :");  // asking user for option
            String option = input.next();
                                                // Utilising Switch case for all options
            switch (option) {                   // checks the option and does the particular assigned method after calling it
                case "100": case "VFQ":
                    viewAllQueues();
                    break;

                case "101": case "VEQ":
                    printEmptyQueues();
                    break;

                case "102": case "ACQ":
                    Addcustomer(input);
                    break;

                case "103": case "RCQ":
                    removeCustomer(input);
                    break;

                case "104": case "PCQ":
                    removeServedCustomers(input);
                    break;

                case "105": case "VCS":
                    SortCustomerNames();
                    break;
                case "106": case "SPD":
                    storedata();
                    break;

                case "107": case "LPD":
                    loadData();
                    break;

                case "108": case"STK":
                    System.out.println("Remaining stock is " + remainingStock);
                    break;

                case"109": case"AFS":
                    AddStocks(input);
                    break;

                case "999": case "EXT":
                    System.out.println("Thank You , Have a Great Day !");
                    isTrue = false;                                          // setting isTrue to false to break the while loop
                    break;
                default:
                    System.out.println("Invalid Option"); // default will be printed incase of an invalid input.
            }
        }
    }
    private static void Addcustomer(Scanner input) {            //* Adding customer to the Queues  @param Scannerinput
        try {System.out.println("Enter Queue (1, 2, or 3):");  //asks user to enter queue and customer name
            int queue = input.nextInt();
            System.out.println("Enter Customer name:");
            String customerName = input.next();

            if (queue < 1 || queue > 3) {         // validates whether queue is correct
                System.out.println("Invalid Queue Entered!");
            } else if (queue == 1 && i != queue1Size) {
                boolean Added = false;                  // boolean value to check if customer has been added
                for (int element = 0; element < 2; element++) {
                    if (Queue1[element] == null || Queue1[element].equals("null")) {
                        Queue1[element] = customerName;
                        System.out.println(customerName + " has been added to Queue 1 Successfully");
                        Added = true;      // customer name is added to the particular index
                        break;
                    }
                }
                if (Added) {   // if added is true,decrements the i count else it will print the relevant queue is full
                    i--;
                    original_i = i;
                } else {System.out.println("Queue 1 is FULL!");}

            } else if (queue == 2 && j != queue2Size) {
                boolean customerAdded = false;
                for (int element = 0; element < 3; element++) {
                    if (Queue2[element] == null || Queue2[element].equals("null")) {
                        Queue2[element] = customerName;
                        System.out.println(customerName + " has been added to Queue 2 Successfully");
                        customerAdded = true;
                        break;
                    }
                }
                if (customerAdded) {
                    j--;
                    original_j = j;
                } else {System.out.println("Queue 2 is FULL!");}

            } else if (queue == 3 && k != queue3Size) {
                boolean customerAdded = false;
                for (int element = 0; element < 5; element++) {
                    if (Queue3[element] == null || Queue3[element].equals("null")) {
                        Queue3[element] = customerName;
                        System.out.println(customerName + " has been added to Queue 3 Successfully");
                        customerAdded = true;
                        break;
                    }
                }
                if (customerAdded) {
                    k--;
                    original_k = k;
                } else {
                    System.out.println("Queue 3 is FULL!");
                }
            } else {
                System.out.println("Selected Queue is FULL!");
            }
        } catch (InputMismatchException e) {                // checks for any invalid inputs by user
            System.out.println("Please enter an integer");
            input.next();
        }
    }
    private static void viewAllQueues() {                  //*Method which prints all slots of the Queues to show which are occupied or not
        cashierPattern();
        if (Queue1[0] == null || Queue1[0].equals("null")) {  // checks whether the Queue[index] is null
            System.out.print(" X \t");                       // if so it will print X else 0
        } else {
            System.out.print(" 0 \t");
        }
        if (Queue2[0] == null || Queue2[0].equals("null")) {
            System.out.print("\t  X");
        } else {
            System.out.print("\t  0");
        }
        if (Queue3[0] == null|| Queue3[0].equals("null")) {
            System.out.print("\t       X ");
        } else {
            System.out.print("\t       0 ");
        }
        System.out.println("");
        if (Queue1[1] == null || Queue1[1].equals("null")) {
            System.out.print(" X \t");
        } else {
            System.out.print(" 0 \t");
        }
        if (Queue2[1] == null || Queue2[1].equals("null")) {
            System.out.print("\t  X");
        } else {
            System.out.print("\t  0");
        }
        if (Queue3[1] == null || Queue3[1].equals("null")) {
            System.out.print("\t       X ");
        } else {
            System.out.print("\t       0 ");
        }
        System.out.println("");

        if (Queue2[2] == null|| Queue2[2].equals("null")) {
            System.out.print("\t\t  X");
        } else {
            System.out.print("\t\t  O");
        }
        if (Queue3[2] == null|| Queue3[2].equals("null")) {
            System.out.print("\t\t   X");
        } else {
            System.out.print("\t\t   0");
        }
        System.out.println("");

        if (Queue3[3] == null|| Queue3[3].equals("null")) {
            System.out.print("\t\t\t\t   X");
        } else {
            System.out.print("\t\t\t\t   0");
        }
        System.out.println("");

        if (Queue3[4] == null|| Queue3[4].equals("null")) {
            System.out.print("\t\t\t\t   X");
        } else {
            System.out.print("\t\t\t\t   0");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("X - Not Occupied  0 - Occupied");
        i = original_i;
        j = original_j;
        k = original_k;        //resetting variables

    }
    private static void removeCustomer(Scanner input) {   //*removes customer from the array/Queue and moving the queue up  @param Scanner input
        try {
            System.out.println("Enter Customer Number"); //asks for customer number,name and queue
            int customerNum = input.nextInt();
            System.out.println("Enter Customer Name :");
            String name = input.next();
            System.out.println("Enter Which Queue :");
            int queueOption = input.nextInt();
            foundCustomer = false;
            if (queueOption < 1 || queueOption > 3) {  //validates whether the user entered the correct queue range
                System.out.println("Invalid Queue Entered!");
            } else if (queueOption == 1) {
                for (i = 0; i < 2; i++) {
                    if (Queue1[i] != null && Queue1[i].equals(name) && i == customerNum - 1 && !Queue1[i].equals("null")) {
                        Queue1[i] = null;
                        foundCustomer = true;
                        System.out.println("Customer " + name + " Has been Removed");
                        queue1Size += 1; //checks whether Queue[index] is not empty and the name and position are correct
                        break;
                    }
                }
            } else if (queueOption == 2) {
                for (j = 0; j < 3; j++) {
                    if (Queue2[j] != null && Queue2[j].equals(name) && j == customerNum - 1 && !Queue2[j].equals("null")) {
                        System.out.println("Customer " + name + " Has been Removed");
                        Queue2[j] = null;
                        foundCustomer = true;
                        queue2Size += 1;
                        break;
                    }
                }
            } else if (queueOption == 3) {
                for (k = 0; k < 5; k++) {
                    if (Queue3[k] != null && Queue3[k].equals(name) && k == customerNum - 1 && !Queue3[k].equals("null")) {
                        System.out.println("Customer " + name + " Has been Removed");
                        Queue3[k] = null;
                        queue3Size += 1;
                        foundCustomer = true;
                        break;
                    }
                }
            }
            if (foundCustomer) {
                // Shifting customers up the Queue in order to move the queue
                if (queueOption == 1) {
                    for (int element = i; element < 1; element++) {
                        Queue1[element] = Queue1[element + 1];
                    }
                    Queue1[1] = null; // Setting  the last element to null
                } else if (queueOption == 2) {
                    for (int element = j; element < 2; element++) {
                        Queue2[element] = Queue2[element + 1];
                    }
                    Queue2[2] = null; // Setting the last element to null
                } else if (queueOption == 3) {
                    for (int element = k; element < 4; element++) {
                        Queue3[element] = Queue3[element + 1];
                    }
                    Queue3[4] = null; // Setting the last element to null
                }
            } else {
                System.out.println("Customer not found in the queue."); //if customer isn't found
                i = original_i;
                j = original_j;
                k = original_k;     //resetting variables
            }
        }catch(InputMismatchException e){         // validates whether invalid data has been entered
            System.out.println("Pls Enter an Integer"); input.next();
        }
    }
    private static void printEmptyQueues() {                  //* prints all unoccupied slots Xs
        cashierPattern();
        if (Queue1[0] == null|| Queue1[0].equals("null")) {       // checks whether Queue[index] is null
            System.out.print(" X \t");                   // if so only X is printed else nothing is printed (blank)
        } else {
            System.out.print("   \t");
        }
        if (Queue2[0] == null|| Queue2[0].equals("null")) {
            System.out.print("\t  X");
        } else {
            System.out.print("\t   ");
        }
        if (Queue3[0] == null|| Queue3[0].equals("null")) {
            System.out.print("\t       X ");
        } else {
            System.out.print("\t         ");
        }
        System.out.println("");
        if (Queue1[1] == null|| Queue1[1].equals("null")) {
            System.out.print(" X \t");
        } else {
            System.out.print("   \t");
        }
        if (Queue2[1] == null|| Queue2[1].equals("null")) {
            System.out.print("\t  X");
        } else {
            System.out.print("\t   ");
        }
        if (Queue3[1] == null|| Queue3[1].equals("null")) {
            System.out.print("\t       X ");
        } else {
            System.out.print("\t         ");
        }
        System.out.println("");

        if (Queue2[2] == null|| Queue2[2].equals("null")) {
            System.out.print("\t\t  X");
        } else {
            System.out.print("\t\t   ");
        }
        if (Queue3[2] == null|| Queue3[2].equals("null")) {
            System.out.print("\t\t   X");
        } else {
            System.out.print("\t\t    ");
        }
        System.out.println("");

        if (Queue3[3] == null|| Queue3[3].equals("null")) {
            System.out.print("\t\t\t\t   X");
        } else {
            System.out.print("\t\t\t\t    ");
        }
        System.out.println("");

        if (Queue3[4] == null|| Queue3[4].equals("null")) {
            System.out.print("\t\t\t\t   X");
        } else {
            System.out.print("\t\t\t\t    ");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("X - Not Occupied  0 - Occupied");
        i = original_i;
        j = original_j;
        k = original_k;     //resetting the variables
    }
    private static void removeServedCustomers(Scanner input) {         //*Removes served customers, & decrements the remaining stock
        try {                                                            //@ param Scanner input
            if (remainingStock == 0) {                                  // checking if remaining stock is equal to 0
                System.out.println("--- Burger Stock is over !---- "); //if so it will say stock is over else it will proceed
            } else {
                System.out.println("Enter Queue Number");              //asks for queue number and validates whether it's correct
                foundCustomer = false;
                int queueNum = input.nextInt();
                if (queueNum < 1 || queueNum > 3) {
                    System.out.println("Invalid Queue Entered");

                } else if (queueNum == 1) {
                    if (Queue1[0] != null && !Queue1[0].equals("null")) {  //checks whether Queue[index=0] is NOT null or empty
                        System.out.println("Customer has been served");
                        Queue1[0] = "remove";
                        foundCustomer = true;                          // setting found customer to true so shifting can happen
                        queue1Size++;                                  // incrementing the relevant QueueSize
                        System.out.println(burgerStocks(true)); //calls burgerstocks method to decrement the remaining stocks
                    }
                } else if (queueNum == 2) {
                    if (Queue2[0] != null && !Queue2[0].equals("null")) {
                        System.out.println("Customer has been served");
                        Queue2[0] = "remove";
                        foundCustomer = true;
                        System.out.println(burgerStocks(true));
                        queue2Size++;
                    }
                } else if (queueNum == 3) {
                    if (Queue3[0] != null && !Queue3[0].equals("null")) {
                        System.out.println("Customer has been served");
                        Queue3[0] = "remove";
                        foundCustomer = true;
                        queue3Size++;
                        System.out.println(burgerStocks(true));
                    }
                }
                if (remainingStock < 10) {                     //prints warning message if stock is less than 10
                    System.out.println("\nStock is Lower than 10 !!!");
                }
                if (foundCustomer) {
                    // Shifting customers in order to move them up the queue
                    if (queueNum == 1) {
                        for (int element = 0; element < Queue1.length - 1; element++) {
                            Queue1[element] = Queue1[element + 1];
                        }
                        Queue1[Queue1.length - 1] = null;   // last index set to null
                    } else if (queueNum == 2) {
                        for (int element = 0; element < Queue2.length - 1; element++) {
                            Queue2[element] = Queue2[element + 1];
                        }
                        Queue2[Queue2.length - 1] = null;   // last index set to null
                    } else if (queueNum == 3) {
                        for (int element = 0; element < Queue3.length - 1; element++) {
                            Queue3[element] = Queue3[element + 1];
                        }
                        Queue3[Queue3.length - 1] = null;  // last index set to null
                    }
                } else {
                    System.out.println("Customer not found in the queue."); // else prints customer isn't found
                    i = original_i;
                    j = original_j;
                    k = original_k; //resetting variables
                }
            }
        }catch(InputMismatchException e){                 //Exception handling incase user enters invalid data while inputting
            System.out.println("Pls Enter an Integer"); input.next();
        }
    }
    private static void SortCustomerNames() { //* Method to View Customer names in Alphabetical order
        String[] CustomerNames = new String[10]; //array called CustomerNames created with size 10
        int element = 0;
        String tempVar;
        for (String Customer : Queue1) {                             //each Customer in the relevant Queues unless null
            if (Customer != null && !Customer.equals("null")) {      //is entered to the CustomerNames Array
                CustomerNames[element] = Customer;
                element++;                                          // incrementing the element count
            }
        }
        for (String Customer : Queue2) {
            if (Customer != null && !Customer.equals("null")) {
                CustomerNames[element] = Customer;
                element++;
            }
        }
        for (String Customer : Queue3) {
            if (Customer != null && !Customer.equals("null")) {
                CustomerNames[element] = Customer;
                element++;
            }
        }
        for (int i = 0; i < CustomerNames.length - 1; i++) {      //uses bubblesort to sort each element in CustomerNames
            for (int j = 0; j < CustomerNames.length - i - 1; j++) {
                if (CustomerNames[j] != null && CustomerNames[j + 1] != null &&
                        compareNames(CustomerNames[j], CustomerNames[j + 1]) > 0) { //calls compareNames method
                    tempVar = CustomerNames[j];
                    CustomerNames[j] = CustomerNames[j + 1];            //assigning to temp variable
                    CustomerNames[j + 1] = tempVar;                     //and swapping values between the indexes
                }
            }
        }
        System.out.println("Customers Sorted In Alphabetical Order: ");  //printing the sorted names without the null slots
        for (String name : CustomerNames) {
            if (name != null && !name.equals("null")) {
                System.out.println(name + "  ");
            }
        }
    }
    private static void storedata() {  //* Method to Store data within the textfile using FileWriter
        int element = 0;
        for (String Customer : Queue1) {            //All elements/values within the Queues are added to the QueueData array
            QueueData[element] = Customer;          // using enhances for loops
            element++;
        }
        for (String Customer : Queue2) {
            QueueData[element] = Customer;
            element++;
        }
        for (String Customer : Queue3) {
            QueueData[element] = Customer;
            element++;
        }
        try {
            FileWriter storeData = new FileWriter("storedata.txt");  // using FileWriter textfile name storedata.txt is created
            for (int index = 0; index < 10; index++) {
                storeData.write(QueueData[index] + "\n");     //for loop will run 10 times until all names are written in separate lines
            }
            storeData.close();                                 // closing the file
            System.out.println("File Saved Successfully");    //if file was successfully created it will print this else it will be caught by the catch.
        } catch (IOException e) {
            System.out.println("An Error Occurred when writing: " + e.getMessage());
        }
    }
    private static void loadData() { //*Method to Load Data back from the textfile to the original Queue1 Queue2 Queue3 Arrays
        try {
            int index=0;
            File rf = new File("storedata.txt");
            Scanner input = new Scanner(rf);
            while (input.hasNextLine()) {
                QueueData [index] = input.nextLine();     //each textfile line is read and is assigned to the QueueData[index] position
                index++;
            }
            input.close();
            Queue1[0]=QueueData[0];
            Queue1[1]=QueueData[1];
            Queue2[0]=QueueData[2];
            Queue2[1]=QueueData[3];   //the items within the QueueData are now assigned to the Original Queue in order to load the data
            Queue2[2]=QueueData[4];
            Queue3[0]=QueueData[5];
            Queue3[1]=QueueData[6];
            Queue3[2]=QueueData[7];
            Queue3[3]=QueueData[8];
            Queue3[4]=QueueData[9];
            System.out.println("Stored Queue Data Loaded : "+ Arrays.toString(QueueData));
            System.out.println("Data loaded successfully.");         // confirms data has been loaded
        } catch (IOException e) {
            System.out.println("Error occurred when reading the file: " + e.getMessage()); //else error will be handled
        }
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