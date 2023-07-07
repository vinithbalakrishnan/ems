package com.aspire;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EventRegistration
 */
@WebServlet("/EventRegistration")
public class EventRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
	    String number=request.getParameter("number");
		String gender=request.getParameter("gender");
		
		RequestDispatcher dispatcher = null;
		Connection connection =null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/event","root","1234");
			PreparedStatement preparedStatement = connection.prepareStatement("insert into register(name,password,email,number,gender) values(?,?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, number);
			preparedStatement.setString(5, gender);
			
			
			int rowCount = preparedStatement.executeUpdate();
			dispatcher = request.getRequestDispatcher("Login.html");
			if(rowCount>0) {
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("status","failed");
			}
			dispatcher.forward(request, response);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
		



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
