package com.training.edureka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
////		response.getWriter().append("Served at: ").append(request.getContextPath());
//		PrintWriter out=response.getWriter();
//		for(int i=1;i<=5;i++)
//		{
//		
//			out.println("Hello from Welcome Servlet "+i);
//		}
//	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
    PrintWriter out=response.getWriter();	
    response.setContentType("text/html");
    String qs=request.getQueryString();
    out.println("Hello "+qs);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
    	   response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    
    
    String username=request.getParameter("username");
    String password=request.getParameter("password");
    String email=request.getParameter("email");
    String phone=request.getParameter("phone");
    
   ServletContext context=getServletContext(); 
    
    ServletConfig config=getServletConfig();
    String dbUrl=context.getInitParameter("url");
//    String dbUrl=config.getInitParameter("url");
    String dbUser=config.getInitParameter("dbuser");
    String dbPwd=config.getInitParameter("dbpwd");
    
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		PreparedStatement ps=con.prepareStatement("insert into appuser values(?,?,?,?)");
		ps.setString(1,  username);
		ps.setString(2,  password);
		ps.setString(3,  email);
		ps.setString(4,  phone);
		int count=ps.executeUpdate();
		if(count>=1)
	    out.println("<h1> Welcome to our World! Dear "+username+"<h1>");
		else
		{
			out.println("<h1> Something went wrong</h1>");
		}
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getLocalizedMessage());
		e.printStackTrace();
		System.out.println("aaya idhar");
	}
 

 
    }

}
