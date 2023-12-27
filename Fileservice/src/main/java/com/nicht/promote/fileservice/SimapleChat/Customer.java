package com.nicht.promote.fileservice.SimapleChat;

import java.io.*;
import java.util.*;

 public  class Customer {



    long  sitDownTime;
    long  endDiningTime;
    long startWaitTime;
    long endWaitTime;
    int ticketNumber;
    int tableNumber;
    int state ; //  0 waiting  1 dining

    double rating; // New feature: customer rating after dining

    public Customer(int ticketNumber, int tableNumber) {
        this.ticketNumber = ticketNumber;
        this.tableNumber = tableNumber;
        this.state = 1;
        this.rating = -1; // Initialized to -1 to denote no rating has been given yet
        this.sitDownTime = new Date().getTime();
    }


    public Customer(int ticketNumber){
        this.ticketNumber = ticketNumber;
        this.state = 0;
        this.startWaitTime = new Date().getTime();
    }

     public int getState() {
         return state;
     }

     public void setState(int state) {
         this.state = state;
     }

     public long getSitDownTime() {
         return sitDownTime;
     }

     public void setSitDownTime(long sitDownTime) {
         this.sitDownTime = sitDownTime;
     }


     public long getStartWaitTime() {
         return startWaitTime;
     }

     public void setStartWaitTime(long startWaitTime) {
         this.startWaitTime = startWaitTime;
     }

     public long getEndWaitTime() {
         return endWaitTime;
     }

     public void setEndWaitTime(long endWaitTime) {
         this.endWaitTime = endWaitTime;
     }

     public int getTicketNumber() {
         return ticketNumber;
     }

     public void setTicketNumber(int ticketNumber) {
         this.ticketNumber = ticketNumber;
     }

     public long getEndDiningTime() {
         return endDiningTime;
     }

     public void setEndDiningTime(long endDiningTime) {
         this.endDiningTime = endDiningTime;
     }

     public int getTableNumber() {
         return tableNumber;
     }

     public void setTableNumber(int tableNumber) {
         this.tableNumber = tableNumber;
     }

     public double getRating() {
         return rating;
     }

     public void setRating(double rating) {
        this.rating = rating;
    }

    // Method to format customer data for file saving
    public String toFileString() {
               return ticketNumber + "," + tableNumber + "," + rating;
    }
}
