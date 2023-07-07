package com.aspire;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
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
		String date=request.getParameter("Date");
		String name=request.getParameter("Card Holder Name");
		String cardnumber=request.getParameter("Card Number");
		String monthyear=request.getParameter("MM/YY");
	    String cvvnumber=request.getParameter("CVV");
	    if((date.equals(""))||(name.equals(""))||(cardnumber.equals(""))||(monthyear.equals(""))||(cvvnumber.equals("")))
        {
			out.println("<h1>Invalid Login</h1>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Payment.html");
			requestDispatcher.forward(request,response);
		}
		else {
			out.println("<h1>Valid Login</h1>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Paymentsuccess.html");
			//response.addCookie(cookie);
			requestDispatcher.forward(request,response);
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
