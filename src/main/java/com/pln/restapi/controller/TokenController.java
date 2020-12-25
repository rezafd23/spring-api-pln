package com.pln.restapi.controller;

import com.google.gson.Gson;
import com.pln.database.model.BuyTokenParam;
import com.pln.database.model.Power;
import com.pln.database.model.Token;
import com.pln.restapi.rabbitmq.ApiReceiver;
import com.pln.restapi.rabbitmq.ApiSender;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    public final ApiReceiver receiver = new ApiReceiver();
//    private Properties properties = new Properties();
//    private String propName = "application.properties";

    // -------------------Buy Token-------------------------------------------
    @RequestMapping(value = "/buy/", method = RequestMethod.POST)
//    public ResponseEntity<?> addPower(@RequestBody Token token, @RequestHeader String apikey) {
    public ResponseEntity<?> addPower(@RequestBody BuyTokenParam token, @RequestHeader String apikey) {
        JSONObject object = new JSONObject();
        JSONObject object2 = new JSONObject();
        if (apikey.equals("1001")) {
            try {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR_OF_DAY, 1);
//                calendar1.set(Calendar.HOUR, 01);
                calendar1.set(Calendar.MINUTE, 0);
                calendar1.set(Calendar.SECOND, 0);
//                Calendar1.get(Calendar.HOUR_OF_DAY);

                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(Calendar.HOUR_OF_DAY, 2);
                calendar2.set(Calendar.MINUTE, 0);
                calendar2.set(Calendar.SECOND, 0);


                Calendar calendar3 = Calendar.getInstance();
                Date x = calendar3.getTime();

                if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                    object.put("response", 400);
                    object.put("status", "Error");
                    object.put("message", "Internal Server Error, Server is Maintanance");
                    return new ResponseEntity<>(object, HttpStatus.OK);
//                        System.out.println("CEKHASIL: BENAR");
                } else {
                    String queueNameReceive = "buyTokenMessage";
                    ApiSender.sendToDb(new Gson().toJson(token), "buyToken");
                    String res = receiver.receiveFromDatabase(queueNameReceive);
                    if (!res.equals("0")) {
                        object.put("response", 200);
                        object.put("status", "Success");
                        object2.put("token", res);
                        object.put("payload", object2);
                        return new ResponseEntity<>(object, HttpStatus.OK);
                    } else {
                        object.put("response", 400);
                        object.put("status", "Error");
                        object.put("message", "Internal Server Error");
                        return new ResponseEntity<>(object, HttpStatus.OK);
                    }
//                        System.out.println("CEKHASIL: SALAH");
                }


            } catch (Exception e) {
                System.out.println("error = " + e);
                object.put("response", 400);
                object.put("status", "Error");
                object.put("message", "Error Buy Token");
                return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
            }
        } else {
            object.put("response", 401);
            object.put("status", "Unauthorized");
            object.put("message", "Invalid Apikey Access");
            return new ResponseEntity<>(object, HttpStatus.UNAUTHORIZED);
        }
    }

    // -------------------Redeem Token-------------------------------------------
    @RequestMapping(value = "/redeem/{no_token}", method = RequestMethod.PUT)
    public ResponseEntity<?> redeemToken(@PathVariable("no_token") String no_token, @RequestHeader String apikey) {
        JSONObject object = new JSONObject();
        if (apikey.equals("1001")) {
            try {
                String queueNameReceive = "redeemTokenMessage";
                ApiSender.sendToDb(new Gson().toJson(no_token), "redeemToken");
                String res = receiver.receiveFromDatabase(queueNameReceive);
                if (res.contains("1")) {
                    object.put("response", 200);
                    object.put("status", "Success");
                    object.put("message", "Success Redeem Token");
                    return new ResponseEntity<>(object, HttpStatus.OK);
                } else {
                    object.put("response", 400);
                    object.put("status", "Error");
                    object.put("message", "Internal Server Error");
                    return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                System.out.println("error = " + e);
                object.put("response", 400);
                object.put("status", "Error");
                object.put("message", "Internal Server Error");
                return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
            }
        } else {
            object.put("response", 401);
            object.put("status", "Unauthorized");
            object.put("message", "Invalid Apikey Access");
            return new ResponseEntity<>(object, HttpStatus.UNAUTHORIZED);
        }
    }

    // -------------------Retrieve Active Token-------------------------------------------
    @RequestMapping(value = "/active/{no_pelanggan}", method = RequestMethod.GET)
    public ResponseEntity<?> getActiveToken(@PathVariable("no_pelanggan") String no_pelanggan, @RequestHeader String apikey) {
        JSONObject object = new JSONObject();
        if (apikey.equals("1001")) {
            try {
                String queueNameReceive = "getTokenActiveMessage";
                ApiSender.sendToDb(no_pelanggan, "getActiveToken");
                String res = receiver.receiveFromDatabase(queueNameReceive);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", res + " kwh");
                if (!res.contains("null")) {
                    object.put("response", 200);
                    object.put("status", "Success");
                    object.put("payload", jsonObject);
                    return new ResponseEntity<>(object, HttpStatus.OK);
                } else {
                    object.put("response", 400);
                    object.put("status", "Error");
                    object.put("message", "Internal Server Error");
                    return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                System.out.println("error = " + e);
                object.put("response", 400);
                object.put("status", "Error");
                object.put("message", "Internal Server Error");
                return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
            }
        } else {
            object.put("response", 401);
            object.put("status", "Unauthorized");
            object.put("message", "Invalid Apikey Access");
            return new ResponseEntity<>(object, HttpStatus.UNAUTHORIZED);
        }
    }
}
