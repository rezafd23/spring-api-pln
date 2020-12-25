package com.pln.database.rabbitmq;

import com.google.gson.Gson;
import com.pln.database.model.ViewPelanggan;
import com.pln.database.model.Voucer;
import com.pln.database.service.CustomerService;
import com.pln.database.service.PowerService;
import com.pln.database.service.TokenService;
import com.pln.database.service.VoucerService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class DatabaseReceiver {

    DatabaseSender sender = new DatabaseSender();
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private PowerService powerService = new PowerService();
    private VoucerService voucerService = new VoucerService();
    private CustomerService customerService = new CustomerService();
    private TokenService tokenService = new TokenService();

    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void addPower() {
        String queueNameReceive="addPowerMessage";
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("addPower", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String powerData = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + powerData + "'");
                int res = powerService.addPower(powerData);
//                System.out.println("Hasil RES POWER SERVICE: "+res);
                if (res == 1) {
                    try {
                        sender.sendToRestApi(String.valueOf(res),queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Add Power: ");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sender.sendToRestApi("0",queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Add Power: ");
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("addPower", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Add Power = " + e);
        }
    }

    public void editPower() {
        String queueNameReceive="editPowerMessage";
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("editPower", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String powerData = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + powerData + "'");
                int res = powerService.editPower(powerData);
//                System.out.println("Hasil RES POWER SERVICE: "+res);
                if (res == 1) {
                    try {
                        sender.sendToRestApi(String.valueOf(res),queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Edit Power: ");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sender.sendToRestApi("0",queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Edit Power: ");
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("editPower", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Edit Power = " + e);
        }
    }

    public void deletePower() {
        String queueNameReceive="deletePowerMessage";
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("deletePower", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String powerData = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + powerData + "'");
                int res = powerService.deletePower(powerData);
//                System.out.println("Hasil RES POWER SERVICE: "+res);
                if (res == 1) {
                    try {
                        sender.sendToRestApi(String.valueOf(res),queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Delete Power: ");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sender.sendToRestApi("0",queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Delete Power: ");
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("deletePower", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Delete Power = " + e);
        }
    }

    public void getVoucer() {
        String queueNameReceive="getPowerMessage";
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("getVoucer", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String powerData = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + powerData + "'");
//                int res = powerService.deletePower(powerData);
                List<Voucer> voucerList = voucerService.getVoucer();
//                System.out.println("Hasil RES get Voucer: "+voucerList.get(0).getId_voucer());
//                System.out.println("Hasil RES get Voucer: "+voucerList.get(0).getVoucer());
                if (voucerList.size()>0) {
                    try {
                        String res = new Gson().toJson(voucerList);
                        sender.sendToRestApi(res,queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Get Voucer: ");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sender.sendToRestApi("0",queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Get Voucer: ");
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("getVoucer", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Get Voucer = " + e);
        }
    }

    public void getCustomerById() {
        String queueNameReceive="getCustomerByIdMessage";
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("getCustomerById", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String no_pelanggan = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + no_pelanggan + "'");
//                int res = powerService.deletePower(powerData);
                Object pelanggan = customerService.getCustomerById(no_pelanggan);
//                System.out.println("Hasil RES get Voucer: "+voucerList.get(0).getId_voucer());
//                System.out.println("Hasil RES get Voucer: "+voucerList.get(0).getVoucer());
                if (pelanggan!=null) {
                    try {
                        String res = new Gson().toJson(pelanggan);
                        sender.sendToRestApi(res,queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Get CustomerById: ");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sender.sendToRestApi("0",queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Get Voucer: ");
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("getCustomerById", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Get CustomerById = " + e);
        }
    }

    public void buyToken() {
        String queueNameReceive="buyTokenMessage";
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("buyToken", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String buyToken = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + buyToken + "'");
                String res="0";
                try {
                    JSONParser parser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) parser.parse(buyToken);
                    Object id_pelanggan =customerService.getCustomerId(jsonObject.get("no_pelanggan").toString());
                    System.out.println("id_pelanggan: "+id_pelanggan);
                    jsonObject.remove("no_pelanggan");
                    jsonObject.put("id_pelanggan",id_pelanggan);
                    res = tokenService.buyToken(jsonObject.toJSONString());
                } catch (Exception e){
                    e.printStackTrace();
                }
//                String res = tokenService.buyToken(buyToken);
//
//                System.out.println("Hasil RES POWER SERVICE: "+res);
                if (!res.equals("0")) {
                    try {
                        sender.sendToRestApi(res,queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Add Power: ");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sender.sendToRestApi("0",queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Add Power: ");
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("buyToken", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Add Power = " + e);
        }
    }

    public void redeemToken() {
        String queueNameReceive="redeemTokenMessage";
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("redeemToken", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String buyToken = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + buyToken + "'");
                int res = tokenService.redeemToken(buyToken);
//                System.out.println("Hasil RES POWER SERVICE: "+res);
                if (res == 1) {
                    try {
                        sender.sendToRestApi(String.valueOf(res),queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Redeem Token: ");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sender.sendToRestApi("0",queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Redeem Token: ");
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("redeemToken", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Add Power = " + e);
        }
    }

    public void getActiveToken() {
        String queueNameReceive="getTokenActiveMessage";
        try {
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("getActiveToken", false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String no_pelanggan = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + no_pelanggan + "'");
                String res = tokenService.getToken(no_pelanggan);
//                System.out.println("Hasil RES POWER SERVICE: "+res);
                if (!res.equals("null")) {
                    try {
                        sender.sendToRestApi(res,queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Get Active Token: ");
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sender.sendToRestApi("0",queueNameReceive);
                    } catch (Exception e) {
                        System.out.println("Error Get Active Token: ");
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("getActiveToken", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error Get Active Token = " + e);
        }
    }
}
