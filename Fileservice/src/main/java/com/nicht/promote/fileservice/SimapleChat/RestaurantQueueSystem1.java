package com.nicht.promote.fileservice.SimapleChat;

import java.io.*;
import java.util.*;

public class RestaurantQueueSystem1 {

    static int[] tableTimers = new int[5];

    public static void main(String[] args) {




        Queue<Customer> queue = new LinkedList<>();
        // waiting Customer
        LinkedList<Customer>  waitingCustomerList = new LinkedList<Customer>();

        Queue<Integer>  availableTableNumber = new LinkedList<>();


        List<Long> waitingTimeList = new ArrayList<>();

        int availableSeats = 5;
        Map<Integer, Customer> diningCustomers = new HashMap<>();

        for (int i = 0; i < availableSeats; i++) {
            availableTableNumber.add(i);
        }

        int nextTicketNumber = 1;

        int defaultAverageWaitingTime = 10;

        Scanner scanner = new Scanner(System.in);

        System.out.println("*************************************************");
        System.out.println("Restaurant Queue System Menu");
        System.out.println("a. Get Ticket");
        System.out.println("b. Check Estimated Waiting Time");
        System.out.println("c. Finish Dining");
        System.out.println("d. Exit Program");
        System.out.println("e. Leave a Rating");
        System.out.println("Select an option (a/b/c/d/e): ");

        while (scanner.hasNextLine()) {
            String choice = scanner.nextLine().trim().toUpperCase();
            if(null == choice || choice.isEmpty()){ continue;}

            switch (choice){
                case "A":{
                    // can sit down
                    if(availableTableNumber.size() > 0){
                        System.out.println("You can sit down and have a meal now ");
                        //get unique ticketNumber  and       get a table number
                        Customer newCustomer = new Customer(nextTicketNumber++, availableTableNumber.poll());
                        diningCustomers.put(newCustomer.getTicketNumber(), newCustomer);
                    }else{

                        System.out.println("Please wait, the  current waiting  people  number ="+waitingCustomerList.size());
                        // get unique ticketNumber and  wait
                        Customer waitCustomer  = new Customer(nextTicketNumber++);
                        waitingCustomerList.add(waitCustomer);
                    }
                }
                     break;
                case "B": {
                    System.out.print("Enter your ticket number: ");
                    int ticketNumber = scanner.nextInt();
                    long waitingTime = calculateWaitingTime(waitingCustomerList, waitingTimeList, ticketNumber, defaultAverageWaitingTime);
                    if (waitingTime >= 0) {
                        System.out.println("Estimated waiting time: " + waitingTime + " minutes");
                    } else {
                        System.out.println("Invalid ticket number");
                    }
                }
                    break;

                case "C": {// 完成用餐
                    System.out.print("Enter your table number: ");
                    int tableNumber = scanner.nextInt();
                    if (diningCustomers.containsKey(tableNumber)) {
                        // return table number
                        availableTableNumber.add(diningCustomers.get(tableNumber).getTableNumber());
                        diningCustomers.remove(tableNumber);
                        System.out.println("Thank you for dining with us!");
                        // 如果队列中有等待的顾客，给下一个顾客分配餐桌
                        if (!waitingCustomerList.isEmpty() && !availableTableNumber.isEmpty()) {
                            System.out.println("Assign a dining table to the next customer");
                            Customer newCustomer = waitingCustomerList.poll();
                            //   get a table number
                            newCustomer.setTableNumber(availableTableNumber.poll());
                            // change state
                            newCustomer.setState(1);
                            // add customer wait cost  time
                            waitingTimeList.add(new Date().getTime() - newCustomer.getStartWaitTime());
                            diningCustomers.put(newCustomer.getTicketNumber(), newCustomer);
                        }
                    } else {
                        System.out.println("Invalid table number.");
                    }
                }
                    break;

                case "D": {
                    System.out.println("Program has exited.");
                    // save dining and wait customer

                    LinkedList<Customer> allCustomer = new LinkedList<>();
                    allCustomer.addAll(diningCustomers.values());
                    allCustomer.addAll(waitingCustomerList);
                    saveQueueState(allCustomer); // Save the state before exiting
                    scanner.close();

                    System.exit(0);
                }
                    break;
                case "E": {
                    System.out.print("Enter your ticket number: ");
                    int enterTicketNumber = scanner.nextInt();
                    System.out.print("Enter your rating (1-5): ");
                    double rating = scanner.nextDouble();
                    if (diningCustomers.containsKey(enterTicketNumber)) {
                        diningCustomers.get(enterTicketNumber).setRating(rating);
                        System.out.println("Thank you for your feedback!");
                    } else {
                        System.out.println("Invalid ticket number.");
                    }
                }
                    break;
                default:
                    System.out.println("Invalid option, please select again.");

            }

            System.out.println("*************************************************");
            System.out.println("Restaurant Queue System Menu");
            System.out.println("a. Get Ticket");
            System.out.println("b. Check Estimated Waiting Time");
            System.out.println("c. Finish Dining");
            System.out.println("d. Exit Program");
            System.out.println("e. Leave a Rating");
            System.out.println("Select an option (a/b/c/d/e): ");


        }
    }


    private  static  long calculateWaitingTime(List<Customer> waitQueue,List<Long> allCustomerWaitCostTimeList,int ticketNumber, int defaultAverageWaitingTime){

        if( null == waitQueue || waitQueue.size()==0 || null ==  allCustomerWaitCostTimeList || allCustomerWaitCostTimeList.size()==0 ){
            return  defaultAverageWaitingTime;
        }

        long totalWaitingTime = 0L;

        for (Long time :allCustomerWaitCostTimeList) {
            totalWaitingTime += time;
        }

        long averageWaitingTime = (totalWaitingTime/allCustomerWaitCostTimeList.size()) / 1000 / 60; // milliseconds/1000 / 60 seconds

        int count = 0;

        for (Customer customer : waitQueue) {
            if (customer.ticketNumber == ticketNumber) {
                break;
            }
            count++;
        }

        return  count>0?defaultAverageWaitingTime: (averageWaitingTime*count) ;

    }


    // Method to load the queue state from a file
    private static void loadQueueState(Queue<Customer> queue) {
        try {
            File file = new File("queueData.txt");
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] details = line.split(",");
                    int ticketNumber = Integer.parseInt(details[0]);
                    int tableNumber = Integer.parseInt(details[1]);
                    Customer customer = new Customer(ticketNumber, tableNumber);
                    if (details.length > 2) {
                        double rating = Double.parseDouble(details[2]);
                        customer.setRating(rating);
                    }
                    queue.add(customer);
                }
                fileScanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Queue data file not found, starting a new queue.");
        } catch (NumberFormatException e) {
            System.out.println("Queue data file is corrupted.");
        }
    }

    // Method to save the queue state to a file
    private static void saveQueueState(Queue<Customer> queue) {
        try {

            String currentWorkingDirectory = System.getProperty("user.dir");
            File file = new File(currentWorkingDirectory+"/queueData.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            for (Customer customer : queue) {
                writer.write(customer.toFileString());
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving queue data.");
        }
    }

}
