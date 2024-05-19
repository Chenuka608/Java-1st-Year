package com.example.javafxfoodiequeue;
class Customer {
    private String customerFirstname;         //intialising the customer class variables
    private String customerLastname;
    private int requiredBurgers;

    @Override
    public String toString() {        //override to String method to be able to print the customerFirstname lastname and required burgers
        return "" +
                " " + customerFirstname + '\'' +
                " " + customerLastname + '\'' +
                " " + requiredBurgers +
                " ";
    }

    public  Customer(String firstname, String lastname, int requirednum) {     //parameterised method to set the firstname lastname and requiredburgers(variables)
        customerFirstname=firstname;
        customerLastname=lastname;
        requiredBurgers=requirednum;        //setting the variables

    }      //required getters and setters are made
    public String getFirstname(){

        return this.customerFirstname;
    }
    public  String getlastname(){

        return this.customerLastname;
    }
    public int getRequiredBurgers(){

        return this.requiredBurgers;
    }

    public void setCustomerFirstname(String customerFirstname) {

        this.customerFirstname = customerFirstname;
    }

    public void setCustomerLastname(String customerLastname) {

        this.customerLastname = customerLastname;
    }

    public void setRequiredBurgers(int requiredBurgers) {
        this.requiredBurgers = requiredBurgers;
    }
}






