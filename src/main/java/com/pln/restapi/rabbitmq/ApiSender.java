package com.pln.restapi.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ApiSender {
//    public static void addPower(String powerData) throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.queueDeclare("addPower", false, false, false, null);
//            channel.basicPublish("", "addPower", null, powerData.getBytes(StandardCharsets.UTF_8));
//            System.out.println(" [x] Sent '" + powerData + "'");
//        }
//    }
//    public static void editPower(String powerData) throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.queueDeclare("editPower", false, false, false, null);
//            channel.basicPublish("", "editPower", null, powerData.getBytes(StandardCharsets.UTF_8));
//            System.out.println(" [x] Sent '" + powerData + "'");
//        }
//    }

    public static void sendToDb(String powerData,String queueNamae) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueNamae, false, false, false, null);
            channel.basicPublish("", queueNamae, null, powerData.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + powerData + "'");
        }
    }
    public static void sendToDb(String queueNamae) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueNamae, false, false, false, null);
            channel.basicPublish("", queueNamae, null, null);
            System.out.println(" [x] Sent '" + "get Power" + "'");
        }
    }
}
