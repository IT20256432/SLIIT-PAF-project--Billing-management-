package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/billsAPI")
public class billsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
		 String[] p = param.split("="); 
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}

	
    public billsAPI() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		bill itemObj = new bill(); 
		String output = itemObj.insertItem(request.getParameter("cusName"), 
				request.getParameter("cusEmail"), 
				request.getParameter("accNo"), 
				request.getParameter("cusCNo"),
				request.getParameter("billval")); 
				response.getWriter().write(output);
		
		//doGet(request, response);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bill itemObj = new bill(); 
		Map paras = getParasMap(request); 
		 String output = itemObj.updateItem(paras.get("hidItemIDSave").toString(), 
		paras.get("cusName").toString(), 
		paras.get("cusEmail").toString(), 
		paras.get("accNo").toString(), 
		paras.get("cusCNo").toString(),
		paras.get("billval").toString()); 
		 
		response.getWriter().write(output); 
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		bill itemObj = new bill();
		Map paras = getParasMap(request); 
		 String output = itemObj.deleteItem(paras.get("billID").toString()); 
		response.getWriter().write(output);
		
	}

}
