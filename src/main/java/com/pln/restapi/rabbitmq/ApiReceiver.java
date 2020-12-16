package com.pln.restapi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ApiReceiver {
    private String powerMessage;

    public String receiveFromDatabase() throws IOException, TimeoutException {
        powerMessage="";
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare("messageFromDatabase", false, false, false, null);
            System.out.println(" [*] Waiting for messages from database");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                powerMessage=message;

            };
            channel.basicConsume("messageFromDatabase", true, deliverCallback, consumerTag -> {
            });
//            System.out.println(" [x] IsiPowerMessagge '" + powerMessage + "'");
            while (powerMessage.equals("")){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return powerMessage;
        } catch (Exception e){
            System.out.println("Add Power Api Receiver Error: ");
            e.printStackTrace();
            return "0";
        }

    }
}
