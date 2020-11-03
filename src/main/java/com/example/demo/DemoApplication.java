package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws JSONException {
        SpringApplication.run(DemoApplication.class, args);


    final RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://91.241.64.178:7081/api/users", String.class);
        String coocies = forEntity.getHeaders().get("Set-Cookie").stream().collect(Collectors.joining(";"));

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", coocies);

        JSONObject user = new JSONObject();
        user.put("id" , 3);
        user.put("name", "James");
        user.put("lastName", "Brown");
        user.put("age", 27);

        HttpEntity entity = new HttpEntity<String>(user.toString(), headers);

        ResponseEntity<String> users = restTemplate.exchange("http://91.241.64.178:7081/api/users", HttpMethod.POST, entity, String.class);

        user.put("name", "Thomas");
        user.put("lastName", "Shelby");

        HttpEntity entityU = new HttpEntity(user.toString(), headers);

        ResponseEntity<String> userUodate = restTemplate.exchange("http://91.241.64.178:7081/api/users", HttpMethod.PUT, entityU, String.class);

        HttpHeaders headerD = new HttpHeaders();
        headerD.set("Cookie", coocies);
        HttpEntity entityD = new HttpEntity<String>(headerD);

        ResponseEntity<String> userDelete = restTemplate.exchange("http://91.241.64.178:7081/api/users/3", HttpMethod.DELETE, entityD, String.class);

        System.out.println(users.toString());
        System.out.println(userUodate.toString());
        System.out.println(userDelete.toString());
    }
}
