package com.pln.restapi.controller;

import com.google.gson.Gson;
import com.pln.database.model.Power;
import com.pln.restapi.rabbitmq.ApiReceiver;
import com.pln.restapi.rabbitmq.ApiSender;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Properties;


@RestController
@RequestMapping("/api/power")
public class PowerController {
    public final ApiReceiver receiver = new ApiReceiver();
//    private Properties properties = new Properties();
//    private String propName = "config.properties";

    // -------------------Add Power-------------------------------------------
    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public ResponseEntity<?> addPower(@RequestBody Power power, @RequestHeader String apikey) {
        JSONObject object = new JSONObject();
        if (apikey.equals("1001")) {
            try {
                ApiSender.sendToDb(new Gson().toJson(power),"addPower");
                String res = receiver.receiveFromDatabase();
                if (res.contains("1")) {
                    object.put("response", 200);
                    object.put("status", "Success");
                    object.put("message", "Success Add Data Power");
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
                object.put("message", "Error Add Power");
                return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
            }
        } else {
            object.put("response", 401);
            object.put("status", "Unauthorized");
            object.put("message", "Invalid Apikey Access");
            return new ResponseEntity<>(object, HttpStatus.UNAUTHORIZED);
        }

    }

    // -------------------Edit Power-------------------------------------------
    @RequestMapping(value = "/edit/{id_power}", method = RequestMethod.PUT)
    public ResponseEntity<?> editPower(@PathVariable("id_power") int id_power,@RequestBody Power power,@RequestHeader String apikey) {
        JSONObject object = new JSONObject();
        if (apikey.equals("1001")){
            try {
                power.setId_daya(id_power);
                ApiSender.sendToDb(new Gson().toJson(power),"editPower");
                String res = receiver.receiveFromDatabase();
                if (res.contains("1")) {
                    object.put("response", 200);
                    object.put("status", "Success");
                    object.put("message", "Success Edit Data Power");
                    return new ResponseEntity<>(object, HttpStatus.OK);
                } else {
                    object.put("response", 400);
                    object.put("status", "Error");
                    object.put("message", "Internal Server Error");
                    return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e)
            {
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

    // -------------------Delete Power-------------------------------------------
    @RequestMapping(value = "/delete/{id_power}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePower(@PathVariable("id_power") int id_power,@RequestHeader String apikey) {
        JSONObject object = new JSONObject();
        if (apikey.equals("1001")){
            try {
                ApiSender.sendToDb(new Gson().toJson(id_power),"deletePower");
                String res = receiver.receiveFromDatabase();
                if (res.contains("1")) {
                    object.put("response", 200);
                    object.put("status", "Success");
                    object.put("message", "Success Delete Data Power");
                    return new ResponseEntity<>(object, HttpStatus.OK);
                } else {
                    object.put("response", 400);
                    object.put("status", "Error");
                    object.put("message", "Internal Server Error");
                    return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e)
            {
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

    // -------------------Retrieve Power-------------------------------------------
//    @RequestMapping(value = "/", method = RequestMethod.DELETE)
//    public ResponseEntity<?> getPower(@RequestHeader String apikey) {
//        JSONObject object = new JSONObject();
//        if (apikey.equals("1001")){
//            try {
//                ApiSender.sendToDb("getPower");
//                String res = receiver.receiveFromDatabase();
//                if (res.contains("1")) {
//                    object.put("response", 200);
//                    object.put("status", "Success");
//                    object.put("message", "Success Delete Data Power");
//                    return new ResponseEntity<>(object, HttpStatus.OK);
//                } else {
//                    object.put("response", 400);
//                    object.put("status", "Error");
//                    object.put("message", "Internal Server Error");
//                    return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
//                }
//            } catch (Exception e)
//            {
//                System.out.println("error = " + e);
//                object.put("response", 400);
//                object.put("status", "Error");
//                object.put("message", "Internal Server Error");
//                return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            object.put("response", 401);
//            object.put("status", "Unauthorized");
//            object.put("message", "Invalid Apikey Access");
//            return new ResponseEntity<>(object, HttpStatus.UNAUTHORIZED);
//        }
//    }



//    backup with properties
//    // -------------------Add Power-------------------------------------------
//    @RequestMapping(value = "/add/", method = RequestMethod.POST)
//    public ResponseEntity<?> addPower(@RequestBody Power power, @RequestHeader String apikey) {
////        InputStream stream = PowerController.class.getResourceAsStream(propName);
//        JSONObject object = new JSONObject();
////        try {
////        properties.load(stream);
////            System.out.println("isiprop: "+properties.getProperty("apikey"));
//        if (apikey.equals("1001")){
//            try {
//                ApiSender.addPower(new Gson().toJson(power));
//                String res = receiver.receiveFromDatabase();
//                if (res.contains("1")){
////                        JSONObject object = new JSONObject();
//                    object.put("response",200);
//                    object.put("status","Success");
//                    object.put("message","Success Add Data Power");
//                    return new ResponseEntity<>(object, HttpStatus.OK);
//                } else {
////                        JSONObject object = new JSONObject();
//                    object.put("response",400);
//                    object.put("status","Error");
//                    object.put("message","Internal Server Error");
//                    return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
//                }
//            }catch (Exception e){
//                System.out.println("error = " + e);
////                    JSONObject object = new JSONObject();
//                object.put("response",400);
//                object.put("status","Error");
//                object.put("message","Error Add Power");
//                return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
//            }
//        }
//        else {
////                JSONObject object = new JSONObject();
//            object.put("response",401);
//            object.put("status","Unauthorized");
//            object.put("message","Invalid Apikey Access");
//            return new ResponseEntity<>(object, HttpStatus.UNAUTHORIZED);
//        }
////        } catch (Exception e){
////            object.put("response",400);
////            object.put("status","Error");
////            object.put("message","Error Api Key");
////            return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
////        }
//
//    }
}
