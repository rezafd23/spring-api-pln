package com.pln.restapi.controller;

import com.pln.restapi.rabbitmq.ApiReceiver;
import com.pln.restapi.rabbitmq.ApiSender;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    public final ApiReceiver receiver = new ApiReceiver();

    // -------------------Retrieve Customer By Id-------------------------------------------
    @RequestMapping(value = "/{no_pelanggan}", method = RequestMethod.GET)
    public ResponseEntity<?> getPower(@PathVariable("no_pelanggan") String no_pelanggan,@RequestHeader String apikey) {
        JSONObject object = new JSONObject();
        if (apikey.equals("1001")){
            try {
                ApiSender.sendToDb(no_pelanggan,"getCustomerById");
                String res = receiver.receiveFromDatabase();
                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(res);

//                JSONArray pelanggan = (JSONArray) jsonArray.get(0);
                System.out.println("isiRes: "+res);
                System.out.println("isiResJSON: "+jsonArray);
//                    JSONArray pelanggan = (JSONArray) jsonArray.get(0);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("nama",jsonArray.get(0));
                    jsonObject.put("no_pelanggan",jsonArray.get(1));
                    jsonObject.put("daya",jsonArray.get(2));
                    jsonObject.put("tarif",jsonArray.get(3));

                if (res.contains("1")) {
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