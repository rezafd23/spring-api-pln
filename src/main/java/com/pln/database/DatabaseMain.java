package com.pln.database;

import com.pln.database.rabbitmq.DatabaseReceiver;

public class DatabaseMain {
    public static DatabaseReceiver receiver = new DatabaseReceiver();
    public static void main(String[] args) {
        try{
            System.out.println(" [*] Waiting for messages..");
            receiver.addPower();
            receiver.editPower();
            receiver.deletePower();
            receiver.getVoucer();
            receiver.getCustomerById();
            receiver.buyToken();
        }catch (Exception e){
            System.out.println("Error DatabaseMain = " + e);
        }
    }
}
