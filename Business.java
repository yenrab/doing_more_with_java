package com.doing.more.java.example;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Business {
    protected HashMap<String, EmployeeBean> employees;
    protected TreeMap<String, String> customerPhoneBook;

    public Business() {
        employees = new HashMap<String, EmployeeBean>();
        customerPhoneBook = new TreeMap<String, String>();
    }

    public void addEmployee(EmployeeBean anEmployee) {
        if (anEmployee != null && anEmployee.getLastName() != null &&
                anEmployee.getFirstName() != null) {
            String key = anEmployee.getLastName() +
                    ", " + anEmployee.getFirstName();
            employees.put(key, anEmployee);
        }
    }

    public EmployeeBean getEmployee(String lastAndFirstName) {
        return employees.get(lastAndFirstName);
    }

    public String getCustomerPhoneNumber(String lastAndFirstName) {
        if(lastAndFirstName == null){
            return null;
        }
        return customerPhoneBook.get(lastAndFirstName);
    }

    public String getAllCustomerPhoneNumbers() {
        StringBuffer phoneNumberBuffer = new StringBuffer();
        TreeSet<String> sortedKeys = new TreeSet<String>(customerPhoneBook.keySet());
        for(String aKey : sortedKeys){
            String phoneNumber = customerPhoneBook.get(aKey);
            phoneNumberBuffer.append(aKey);
            phoneNumberBuffer.append(" - ");
            phoneNumberBuffer.append(phoneNumber);
            phoneNumberBuffer.append("; ");
        }
        return phoneNumberBuffer.toString();
    }

    public void addCustomerToPhoneBook(CustomerBean aCustomer) {
        if (aCustomer != null && aCustomer.getLastName() != null &&
                aCustomer.getFirstName() != null) {
            String key = aCustomer.getLastName() +
                    ", " + aCustomer.getFirstName();
            customerPhoneBook.put(key, aCustomer.getPhoneNumber());
        }
    }
}
