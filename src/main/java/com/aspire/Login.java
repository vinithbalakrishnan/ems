package com.aspire;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		HttpSession httpsession=request.getSession();
		httpsession.setAttribute("User", name);
		RequestDispatcher dispatcher=null;
		Connection connection= null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/event?useSSL=false","root","1234");
			PreparedStatement preparedstatement= connection.prepareStatement("select * from register  where name = ? and password = ?");
			
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, password);
			
			
			ResultSet resultset= preparedstatement.executeQuery();
			
			
			if(resultset.next()) {
				httpsession.setAttribute("name",resultset.getString("name"));
				dispatcher= request.getRequestDispatcher("Eventbooking.jsp");
			}
			else {
				request.setAttribute("status", "failed");
				dispatcher= request.getRequestDispatcher("Login.html");
			}
			dispatcher.forward(request, response);
		}catch(Exception exception) {
			exception.printStackTrace();
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
