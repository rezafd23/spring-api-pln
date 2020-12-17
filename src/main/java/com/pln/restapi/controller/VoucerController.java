package com.pln.restapi.controller;

import com.google.gson.Gson;
import com.oracle.javafx.jmx.json.JSONException;
import com.pln.database.model.Power;
import com.pln.database.model.Voucer;
import com.pln.restapi.rabbitmq.ApiReceiver;
import com.pln.restapi.rabbitmq.ApiSender;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voucer")
public class VoucerController {
    public final ApiReceiver receiver = new ApiReceiver();

    // -------------------Retrieve Power-------------------------------------------
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getPower(@RequestHeader String apikey) {
        JSONObject object = new JSONObject();
        if (apikey.equals("1001")){
            try {
                String queueNameReceive="getPowerMessage";
                ApiSender.sendToDb("getVoucer","getVoucer");
                String res = receiver.receiveFromDatabase(queueNameReceive);
                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(res);
//                JSONArray voucer = (JSONArray) jsonArray.get(0);
//                System.out.println("isiRes: "+voucer.get(1));
                JSONArray listVoucer = new JSONArray();
                for (int i=0;i<jsonArray.size();i++){
                    JSONArray voucer = (JSONArray) jsonArray.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id_voucer",voucer.get(0));
                    jsonObject.put("voucer","Rp."+voucer.get(1));
                    jsonObject.put("price",voucer.get(2));
                    listVoucer.add(jsonObject);
                }
                if (res.contains("1")) {
                    object.put("response", 200);
                    object.put("status", "Success");
                    object.put("payload", listVoucer);
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


}
