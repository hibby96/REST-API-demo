package com.revature;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.revature.Database.ConnectionUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
   
    public static void main( String[] args ) {
        load();
        SpringApplication.run(App.class, args);

    }

    private static void load(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("db.credentials"));
            ConnectionUtil.setUsername(properties.getProperty("username", "Will"));
            ConnectionUtil.setPassword(properties.getProperty("password", "hi"));
            ConnectionUtil.setUrl(properties.getProperty("url", "jdbc:postgresql://localhost:5430/Will"));
            System.out.println("properties loaded");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
