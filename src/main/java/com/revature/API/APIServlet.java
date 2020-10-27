package com.revature.API;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Database.DAOImpl;
import com.revature.Objects.Person;
import com.revature.Parsers.URIParser;

public class APIServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DAOImpl dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new DAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the id number based on the URL
        String lastSegment = URIParser.getLastSegment(req.getRequestURI());
        ObjectMapper objectMapper = new ObjectMapper();
        String JSON = null;
        int id = Integer.parseInt(lastSegment);
        Person result = dao.getPerson(id);
        //call the DAOImpl class, pass in the ID that the user has requested.  Check to ensure it returns something.
        if (result != null) {
            JSON = objectMapper.writeValueAsString(result);
            resp.setContentType("application/json");
            resp.getWriter().write(JSON);
        } else {
            resp.getWriter().write("Unsuccessful.  Likely no one with that ID # or there is a problem with the application.  Try again with different ID or debug.");
        }

    }    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
        Person newperson = objectMapper.readValue(req.getInputStream(), Person.class);

        if (dao.addPerson(newperson)) {
            resp.setStatus(200);
            resp.getWriter().write("Successfully added person to DB");
        } else {
            resp.setStatus(500);
            resp.getWriter().write("Unsuccessful.  Need to debug.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the id number based on the URL, for reference of who to update
        String lastSegment = URIParser.getLastSegment(req.getRequestURI());
        ObjectMapper objectMapper = new ObjectMapper();
        int id = Integer.parseInt(lastSegment);


        //get the new name out of the req body, just make a POJO for it
        Person newperson = objectMapper.readValue(req.getInputStream(), Person.class);    
        if (dao.updatePerson(newperson, id)) {
            resp.setStatus(200);
            resp.getWriter().write("Successfully updated person");
        } else {
            resp.setStatus(500);
            resp.getWriter().write("Unsuccessful.  Likely no one with that ID # or there is a problem with the application.  Try again with different ID or debug.");
        }
    }    


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the id number based on the URL, for reference of who to delete
        String lastSegment = URIParser.getLastSegment(req.getRequestURI());
        int id = Integer.parseInt(lastSegment);

        if (dao.deletePerson(id)) {
            resp.setStatus(200);
            resp.getWriter().write("Successfully deleted person");
        } else {
            resp.setStatus(500);
            resp.getWriter().write("Unsuccessful.  Likely no one with that ID # or there is a problem with the application.  Try again with different ID or debug.");
        }
    }    



}
