package com.anushachandran1502.busticketbooking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Servlet implementation class SignIn
 */
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder requestBody = new StringBuilder();
        String line;
        
        try (BufferedReader br = request.getReader()) {
            while ((line = br.readLine()) != null) {
                requestBody.append(line);
            }
        }
        System.out.println(requestBody);

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = null;

        try {
            jsonObj = (JSONObject) parser.parse(requestBody.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String userName = (String) jsonObj.get("username");
        String password = (String) jsonObj.get("password");
        boolean result = Repository.getInstance().isValidUser(userName, password);

        JSONObject output = new JSONObject();
        response.setContentType("application/json");
        
        if (result) {
            output.put("status", "signin successfully");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            output.put("status", "Invalid Username/Password");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        response.getWriter().write(output.toJSONString());
	}

}
