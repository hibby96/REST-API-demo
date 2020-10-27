package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.revature.API.APIServlet;
import com.revature.Database.ConnectionUtil;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {

    public static void main( String[] args ) {
        load();
        String contextPath = "/API";
        String appBase = new File("src/main/resources").getAbsolutePath();
        Tomcat server = new Tomcat();
        server.setBaseDir(new File("target/tomcat/").getAbsolutePath());
        server.setPort(8080);
        server.getConnector(); //connects tomcat to an HTTP service, runs tomcat on HTTP mode
        server.addWebapp(contextPath, appBase);
        server.addServlet(contextPath, "APIServlet", new APIServlet()).addMapping("/people/*");

        try {
            server.start();
            server.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
            System.out.println("server startup failed, program terminating");
        }      
    }

    private static void load(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("app.properties"));
            ConnectionUtil.setUsername(properties.getProperty("username", "Will"));
            ConnectionUtil.setPassword(properties.getProperty("password", "hi"));
            ConnectionUtil.setUrl(properties.getProperty("url", "jdbc:postgresql://localhost:5430/Will"));
            System.out.println("properties loaded");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
